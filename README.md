# consulta-placa

## Pre-requisito
- Ter um banco de dados criado e rodando
    - para o desenvolvimento criei uma base no elephantsql. Acessando com as credenciais abaixo vc pode pegar os dados de conexao
    ```
    https://www.elephantsql.com/
    loguin: jonatasfa@gmail
    senha: tanner123
    ```
    - caso seja criado outro banco, existe um de criacao das tabelas na pasta doc na raiz do projeto
    - as configuracoes de bancos sao feitas no arquivo src/main/resources/application.properties

## Build
- O build da aplicação é feito através do gradle
    - Instrunções para instalação do gradle
    ```
        https://www.tutorialspoint.com/gradle/gradle_installation.htm
    ```

## Executar
- Apos o build, o arquivo jar é gerado dentro da pasta build/libs
- Para executar execute o camando abaixo
```
java -jar consulta-placa-0.0.1-SNAPSHOT.jar