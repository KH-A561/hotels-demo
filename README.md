REST-api, созданный на основе Quarkus и Jakarta EE.

Запуск:
* gradle build
* docker build -f src/main/docker/Dockerfile.jvm -t hotels-demo-quarkus-app .
* docker-compose up -d

Открывать в браузере http://localhost:8080/q/swagger-ui

Авторизация: 
openIdConnect (OAuth2, password)
username: admin
password: admin
Authorization header
client_id: hotels-app
client_secret: secret