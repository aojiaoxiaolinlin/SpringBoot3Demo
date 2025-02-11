FROM bellsoft/liberica-openjdk-debian:21.0.6-cds

LABEL maintainer="linlin"

ENV SERVERT_PORT=8080

RUN mkdir -p /home/java

WORKDIR /home/java

EXPOSE ${SERVERT_PORT}

COPY ./target/app-vben-0.0.1-SNAPSHOT.jar ./app-vben.jar

ENTRYPOINT ["java", "-jar", "app-vben.jar"]