FROM alpine:edge
RUN apk add --no-cache openjdk8
VOLUME /tmp
COPY springbootproducerconsumer-latest.jar spring-boot-producerconsumer.jar
ENTRYPOINT ["java","-D<java.security.egd=file:/dev/./urandom","-jar","/spring-boot-producerconsumer.jar"]
