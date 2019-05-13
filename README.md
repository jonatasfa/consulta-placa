# consulta-placa
Serviço de Mensageria para CRUD de profissional no ESB

## Pré requisitos
- Cluster ECS criado na amazon

## Código do projeto
- Crie um Dockerfile que faça a build e execução do processo/listener
- Adicione um arquivo buildspec.yml	com o seguinte formato (atenção para os parâmetros entre <>):

```
version: 0.2

phases:
  pre_build:
    commands:
      - echo Logging in to Amazon ECR...
      - aws --version
      - $(aws ecr get-login --region $AWS_DEFAULT_REGION --no-include-email)
      - REPOSITORY_URI=$(aws ecr get-authorization-token --region $AWS_DEFAULT_REGION --query authorizationData[].proxyEndpoint --output text)/NOME_DA_IMAGEM_CONFIGURADA_NA_TASK
      - REPOSITORY_URI=$(echo $REPOSITORY_URI | cut -c 9-)
      - IMAGE_TAG=$(echo $CODEBUILD_RESOLVED_SOURCE_VERSION | cut -c 1-7)
  build:
    commands:
      - echo Build started on `date`
      - echo Building the Docker image...
      - docker build -t $REPOSITORY_URI:latest .
      - docker tag $REPOSITORY_URI:latest $REPOSITORY_URI:$IMAGE_TAG
  post_build:
    commands:
      - echo Build completed on `date`
      - echo Pushing the Docker images...
      - docker push $REPOSITORY_URI:latest
      - docker push $REPOSITORY_URI:$IMAGE_TAG
      - echo Writing image definitions file...
      - printf '[{"name":"NOME-DA-TASK","imageUri":"%s"}]' $REPOSITORY_URI:$IMAGE_TAG > imagedefinitions.json
artifacts:
  files: imagedefinitions.json
```

## CloudCommit
- Crie um repositório no CloudCommit da AWS e adicione ele como remoto do processor/listener. Faça o push do código.

## ECR
- Acesse o Amazon ECS -> Amazon ECR e crie um repositório com o mesmo nome informado no buildspec.yaml
  - *PASSOS APENAS PARA DEV*
  - Acesso o repositório criado -> Lifecycle policy -> Add
  - Preencha o campo Rule description (e.g. `remover imagens docker antigas`). 
  - Em `Images status` selecione `Any`.  
  - Em `Match criteria` selecione `Image count more than`.
  - Preencha `Count number` com `1` e clique em  `Dry run`
  - Esses passos vizam minimizar o custo que é manter as imagens docker na AWS de dev.

## ECS - Task Definition
- Acesse o Amazon ECS -> Task definitions -> Create new Task Definition
  - Selecione FARGATE
  - Preencha os campos obrigatório com dados que desejar
  - Adicione um novo container
  - Em `Container name` utilize o mesmo nome informado em `<NOME_DA_IMAGEM_DOCKER>` no buildspec.yaml
  - Em `Environment` adicione todas as variavéis de ambiente necessárias para rodar a aplicação no container.
  - Finalize a criação da task

```
    Task Definition Name: esb-profissional-processor
    Task Role: ecsTaskExecutionRole
    Network Mode: awsvpc
    Compatibilities: EC2, FARGATE
    Requires compatibilities: FARGATE

    Task execution IAM role
        Task execution role: ecsTaskExecutionRole
        Task size
            Task memory (MiB): 1024
            Task CPU (unit): 0.5

    Container
        Container name: esb-profissional-processor
        Image: ECR_HOST/esb-profissional-processor

        Environment variables
            KAFKA_HOSTS         IP-KAFKA:9092
            DB_URL              jdbc:postgresql://DB_HOST:DB_PORT/BANCO-DO-RST
            DB_USER
            DB_USER_PASSW
            CONSUMER_TOPIC      esb-profissionais
            PRODUCER_ERROR      esb-profissionais-falha
            PRODUCER_LOG        sesi-bus-import
            PRODUCER_SUCCESS    esb-profissionais-sucesso

        Log configuration: Auto-configure CloudWatch Logs
```

## ECS - Service
- Acesse o Amazon ECS -> Cluster do barramento -> Services -> Create
  - Launch type `FARGATE`
  - Em `Task Definition family` <NOME_DA_TASK_DEFINITION>
  - Em `Cluster` selecione o cluster do barramento
  - Em `Service name` defina o nome do serviço
  - Em `Number of tasks` informe o número de instâncias da aplicação que você quer subir
  - Na tela seguinte, informe o VPC, subnet e security groups de acordo com o ambiente
  - *Para dev solutis*
  - Cluster VPC = vpc-0c2f2899c.....
  - Subnets do cluster
  - Edit security groups -> Select existing security group -> EC2ContainerService-esb-cluster-EcsSecurityGroup-12DYGQKC0IOOQ -> Save
  - Desmarque a opção `Enable service discovery` no fim da paǵina
  - Finalize a criação do serviço sem alterar qualquer valor


```
    Launch type: FARGATE
    Task Definition: esb-profissional-processor
    Revision: latest
    Platform version: LATEST
    Cluster: CLUSTER_ESB
    Service name: profissional-processor
    Number of tasks: 1
    Minimum healthy percent: 100
    Maximum percent: 200

    Configure network
        Cluster VPC: SELECIONAR A VPC DO CLUSTER
        Subnets: ESCOLHER AS VPCS DO CLUSTER
        Security groups: SELECIONAR O SECURITY GROUP DO CLUSTER
        Auto-assign public IP: ENABLED

    Enable service discovery integration: DESMARCAR
```

## CodeBuild
- Acesse o CodeBuild da AWS e crie um novo projeto
  - *Os passos a seguir foram realizados an região de Ohio da AWS. Alguns deles podem não estar disponíveis em outras regiões sendo necessário adaptar* 
  - Em `Source provider` selecione `AWS CodeCommit`
  - Selecione o repositorio CloudCommit do projeto
  - Em `operating system` selecione `Ubuntu`
  - Em `Runtime` selecione `Docker`
  - Em `Runtime version` selecione a ultima disponível
  - Em `Service role` use um serviço já existe ou crie um novo.
  - *Em dev selecione `codebuild-sesi-mule-service-role`*
  - *Caso precise criar uma nova conta de serviço, ela precisa ter permissão de fazer pull/push no repositório docker criado*
  - Deixe o resto das opções no default e finalize a criação do CodeBuild

## CodePipeline
- Acesse o AWS CodePipeline e começe a criação de um novo pipeline
- Informe o nome
- Escolha um *service role* já existente ou crie um novo
 - *Para AWS SOLUTIS*
 - Selecine o *service role* AWS-CodePipeline-Service
 - Next -> Selecione AWS CodeCommit / Github -> Informe os dados do repositório no CloudCommit / Github
 - Next -> Selecione AWS CodeBuild -> Selecione o CodeBuild criado anteriormente
 - Next -> Selecione Amazon ECS -> Selecione o cluster do barramento -> Selecione o `service` criado anteriomente
 - Next -> Create Pipeline
- Acessar a pipeline criada -> Edit -> Edit stage -> Add action
- Em `Action name` informar `Aprovar`
- Em `Àction provider` selecionar `Manual approval`
- Clicar em `Save`
- Clicar em `Save`

## Erros comuns
- Contas de serviço (`service role`) sem as permissões necessárias (e.g. realizar push no repositório ECR, executar o build do projeto)
- Falta de parâmetros no container causando o restart contínuo das tasks
- Divergência de nomeclatura (e.g. nome no buildspec diferente do nome do container na task definition)

## Finalizando
Aguarde a execução do pipeline. Caso hajam erros, proceder de acordo com as indicações apontadas pelos logs da amazon

