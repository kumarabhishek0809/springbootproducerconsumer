FROM alpine:edge
RUN apk add --no-cache openjdk8
VOLUME /tmp
COPY springbootproducerconsumer-0.0.1-SNAPSHOT.jar spring-boot-producerconsumer.jar
ENTRYPOINT ["java","-D<java.security.egd=file:/dev/./urandom","-jar","/spring-boot-producerconsumer.jar"]
