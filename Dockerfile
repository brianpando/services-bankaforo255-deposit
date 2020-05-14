FROM openjdk:13
VOLUME /tmp
EXPOSE 8888
ADD ./target/services-bankaforo255-deposit-0.0.1-SNAPSHOT.jar service-deposit.jar
ENTRYPOINT ["java","-jar","/service-deposit.jar"]