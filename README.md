# springbootproducerconsumer
springbootproducerconsumer


## Setting up you mysql.
docker run --name=mysql1 -d mysql/mysql-server:tag

docker run --restart always --name mysqldatabase -v /usr/local/opt/mysql/8.0:/var/lib/mysql -p 3306:3306 -d -e MYSQL_ROOT_PASSWORD=root mysql
 mysql -u root -p root
 GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' IDENTIFIED BY 'root';
 create database producerConsumer;
 USE producerConsumer;
 
create table request_details
(
    id                      int  not null
    primary                 key,
    create_time             date null,
    increase_consumer_count int  null,
    increase_producer_count int  null
);
 
## Setting up your docker file.
Refer to docer file.
gradle clean build.
go to folder for dockerfile at root
##Run below command to start pipeline from local. 
docker build --tag=spring-boot-producerconsumer --rm=true .
docker volume create --name=spring-boot-producerconsumer-repo
docker run -d --name=spring-boot-producerconsumer --publish=8080:8080 --volume=spring-boot-producerconsumer-repo:/var/lib/spring-boot-producerconsumer-repo spring-boot-producerconsumer:latest

#As there are multiple services you need to run through, docker compose.
##for running docker compose
docker pull spring-boot-producerconsumer

docker-compose up --build --remove-orphans

##to shut down
docker-compose down

