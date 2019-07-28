# adjose bank
Sample app let user sign up, create bank account, save money, withdraw money and transfer money to another bank account.


### tools
- spring security, data-jpa, data-redis, web
- hibernate
- lombok
- flyway db migration
- mysql
- redis


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
mysql> insert into users (username, password, enabled, created_at) values ('admin', '$2a$10$RmCOh37Cuzrs6eHM.YtDGeYwyZbi0eFVC2yuKwE6mRq2tGOWaojHy', 1, current_timestamp());

# admin user authorities
mysql> insert into authorities (username, authority, created_at) values ('admin', 'ADMINISTRATOR', current_timestamp());
mysql> insert into authorities (username, authority, created_at) values ('admin', 'CUSTOMER', current_timestamp());

# admin user profile
mysql> insert into user_profiles (username, email, phone_number, created_at) values ('admin', 'admin@adjose.com', '0987654321', current_timestamp());
```


### run redis docker container
```bash
docker pull redis
docker run --network my-network --name adjose-bank-redis -d -p 6379:6379 redis

# connecting via redis-cli
docker run -it --network my-network --rm redis redis-cli -h adjose-bank-redis
# list keys in redis
adjose-bank-redis:6379> keys *
```


### endpoints
see ENDPOINTS.md


### todo
- redis cache transaction records (let user query transaction records of an account)
- handle currency transfer
- todo try store java.util.Date data type in redis
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
- multiple data modules in spring boot
https://stackoverflow.com/questions/39432764/info-warnings-about-multiple-modules-in-spring-boot-what-do-they-mean
- Redis
https://juejin.im/post/5ad6acb4f265da239e4e9906
https://www.baeldung.com/spring-data-redis-tutorial
