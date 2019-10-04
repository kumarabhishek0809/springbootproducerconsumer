# springbootproducerconsumer
springbootproducerconsumer

docker run --name=mysql1 -d mysql/mysql-server:tag

docker run --restart always --name mysql -v /usr/local/opt/mysql/8.0:/var/lib/mysql -p 3306:3306 -d -e MYSQL_ROOT_PASSWORD=root mysql
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
 
