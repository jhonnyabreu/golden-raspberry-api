# golden-raspberry-api Project

## Executando a aplicação

Para executar a aplicação, pode ser utilziado o comando:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Ao executar a aplicação as URL podem ser invocadas pela URL http://localhost:8080.

## Compilação da Aplicação

Para compilar a aplicação utilize o camando:

```shell script
./mvnw clean package
```

Ao executar, será executado os testes existentes e será gerado os arquivos de execução da aplicação.

É criado o executável `quarkus-run.jar` no diretório `target/quarkus-app/`.
É necessário manter o executavel no diretório atual, pois possui dependencias no diretório `target/quarkus-app/lib/`.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

As APIs disponiveis podem ser acessáradas no swagger da aplicação:

http://localhost:8080/q/swagger-ui/
