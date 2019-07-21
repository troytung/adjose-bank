# adjose bank
Sample app let user sign up, create bank account, save money, withdraw money and transfer money to another bank account.


### tools
- spring security, jpa, web
- hibernate
- lombok
- flyway db migration
- mysql


### run mysql docker container
```bash
docker network create my-network
docker pull mysql  
docker run --network my-network --name adjose-bank -e MYSQL_ROOT_PASSWORD=root -d -p 3306:3306 -p 33060:33060 mysql  
```


### create database and add admin user
```bash
docker run -it --network my-network --rm mysql mysql -hadjose-bank -uroot -p
mysql> create database adjose_bank;
mysql> create user 'adjose' identified by 'adjose';
mysql> grant all on adjose_bank.* to 'adjose';

# admin user (password: admin)
mysql> insert into users (username, password, enabled) values ('admin', '$2a$10$RmCOh37Cuzrs6eHM.YtDGeYwyZbi0eFVC2yuKwE6mRq2tGOWaojHy', 1);

# admin user authorities
mysql> insert into authorities (username, authority) values ('admin', 'ADMINISTRATOR');
mysql> insert into authorities (username, authority) values ('admin', 'CUSTOMER');

# admin user profile
mysql> insert into user_profiles (username, email, phone_number, created_at, updated_at) values ('admin', 'admin@adjose.com', '0987654321', current_timestamp(), current_timestamp());
```


### endpoints
see ENDPOINTS.md


### todo
- add Transaction entity and use cases
- add Swagger
- add https


### reference
- JPA / Hibernate One to One Mapping Example with Spring Boot  
https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-one-mapping-example/
- JPA / Hibernate One to Many Mapping Example with Spring Boot  
https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/
- Spring Security  
https://segmentfault.com/a/1190000015191298
- Hibernate Inheritance
https://www.baeldung.com/hibernate-inheritance
