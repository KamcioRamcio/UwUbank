# UwUbank

## Project Overview
UwUbank is a banking application built using Java, Spring Boot, and Gradle. It includes functionalities for managing customers, accounts, transfers, incomes, and outcomes.

## Prerequisites
- Java 11 or higher
- Gradle
- Docker

## Getting Started

### Clone the Repository
```sh
git clone https://github.com/yourusername/uwubank.git
cd uwubank
```

### Create a Docker container for PostgreSQL: 
```sh
docker run --name uwubank-db -e POSTGRES_DB=uwubank -e POSTGRES_USER=uwubank -e POSTGRES_PASSWORD=uwubank -p 5432:5432 -d postgres
```

### If you want you can add basic data to the database:
```sh
INSERT INTO branches (branch_id, branch_name, branch_address, branch_phone_number) VALUES
(1, 'Straw Hat Pirates', 'Thousand Sunny, Grand Line', '111-222-3333'),
(2, 'Akatsuki', 'Hidden Rain Village, Amegakure', '444-555-6666'),
(3, 'Soul Society', 'Seireitei, Soul Society', '777-888-9999'),
(4, 'Mugiwara Store', 'Sabaody Archipelago, Grand Line', '123-456-7890'),
(5, 'Konoha Bank', 'Hidden Leaf Village, Konoha', '234-567-8901'),
(6, 'Gotei 13', 'Seireitei, Soul Society', '345-678-9012'),
(7, 'Marine Headquarters', 'Marineford, Grand Line', '456-789-0123');
```

### Apply the database schema:
```sh
docker exec -i uwubank-db psql -U uwubank -d uwubank < src/main/resources/schema.sql
```
### Configure Application Properties
```sh
Edit the src/main/resources/application.properties file to configure the database connection:

for example:

spring.datasource.url=jdbc:postgresql://localhost:5432/uwubank
spring.datasource.username=uwubank
spring.datasource.password=uwubank
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

```
### Configure CORS for the Frontend
```
In file WebConfig.java set the allowed origins for the frontend application.
```



### Build and Run the Application
```sh
./gradlew build
```
```sh
./gradlew bootRun
```
### Usage
Once the application is running, you can access it at http://localhost:8080.