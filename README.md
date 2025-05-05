Rodar primeiro 
mvn clean package -DskipTests
para gerer o .jar 
depois subir com 
docker compose up --build

porque se não o docker compose dá erro pois não tem o .jar e se rodar o mvn clean para rodar o .jar dará erro pois o container no banco não subiu com o docker-compose
