# readme

### mysql

docker network create my-network
docker pull mysql  
docker run --network my-network --name adjose-bank -e MYSQL_ROOT_PASSWORD=root -d -p 3306:3306 -p 33060:33060 mysql  
docker run -it --network my-network --rm mysql mysql -hadjose-bank -uroot -p  


### create database
mysql> create database adjose_bank;
mysql> create user 'adjose' identified by 'adjose';
mysql> grant all on adjose_bank.* to 'adjose';


### admin user (password: admin)
insert into users (username, password, enabled) values ('admin', '$2a$10$RmCOh37Cuzrs6eHM.YtDGeYwyZbi0eFVC2yuKwE6mRq2tGOWaojHy', 1);

### admin user authorities
insert into authorities (username, authority) value ('admin', 'ADMIN');
insert into authorities (username, authority) value ('admin', 'USER');


### reference
* JPA / Hibernate One to Many Mapping Example with Spring Boot  
https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/
* Spring Security  
https://segmentfault.com/a/1190000015191298
