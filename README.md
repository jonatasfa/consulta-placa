# consulta-placa

## Pre-requisito
- Ter um banco de dados criado e rodando
    - Para o desenvolvimento criei uma base no elephantsql. O projeto ja esta configurado para acessar essa base.
    - Acessando com as credenciais abaixo vc pode pegar os dados de conexao (host, usuario, senha)
    ```
    https://www.elephantsql.com/
    loguin: jonatasfa@gmail
    senha: tanner123
    ```
    - caso seja criado outro banco, existe um script de criacao das tabelas na pasta doc na, raiz do projeto
    - as configuracoes de bancos sao feitas no arquivo src/main/resources/application.properties

## Build
- O build da aplicação é feito através do gradle
    - Instrunções para instalação do gradle
        - Faz o donwload do zip no endereço
        ```
        https://gradle.org/releases/
        ```
        - Descompacta em algum lugar e seta uma variavel de ambiente no windows
        ```
        GRADLE_HOME = o caminho da pasta do gradle ate o \bin. e: C:\gradle\gradle-5.4.1\bin
        ```
    - Faz o build do projeto
        - Baixa o projeto aqui do git.
        - Entra na pasta do projeto pelo prompt de comando e digita. 
        ```
        gradle build
        ```

## Executar
- Apos o build, o arquivo jar é gerado dentro da pasta build/libs
- Para executar, va ate a pasta onde o jar foi gerado e execute o camando abaixo no prompt de comando
```
java -jar consulta-placa-0.0.1-SNAPSHOT.jar
```
## Endpoints
- Feito o login, todas as outras requisicoes precisaram do header de Authorization como ilustrado abaixo

![alt text](https://github.com/jonatasfa/consulta-placa/blob/master/images/Captura%20de%20tela%20de%202019-05-14%2011-45-54.png)

- Adicionar novo usuario.
    - Metodo POST
    - Header Authorization preenchido
    - Passar um body do typo raw json
![alt text](https://github.com/jonatasfa/consulta-placa/blob/master/images/Captura%20de%20tela%20de%202019-05-14%2011-46-19.png)

- Update xlsx
    - Metodo POST
    - Header Authorization preenchido
    - Passa um body do tipo form-data, key=file, value=caminho do arquivo
![alt text](https://github.com/jonatasfa/consulta-placa/blob/master/images/Captura%20de%20tela%20de%202019-05-14%2011-46-48.png)

- Reiniciar senha
    - Metodo GET
    - Header Authorization preenchido
    - Passa o ID do usuario como parametro na url
![alt text](https://github.com/jonatasfa/consulta-placa/blob/master/images/Captura%20de%20tela%20de%202019-05-14%2011-47-15.png)

- Consultar placa
    - Metodo GET
    - Header Authorization preenchido
    - Passa a placa como parametro na url
![alt text](https://github.com/jonatasfa/consulta-placa/blob/master/images/Captura%20de%20tela%20de%202019-05-14%2011-47-40.png)
