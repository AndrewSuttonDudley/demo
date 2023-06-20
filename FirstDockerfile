FROM eclipse-temurin:17-jdk-jammy
MAINTAINER crscreditapi.com
COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ENTRYPOINT ["./wait-for-it.sh","mysql:3306","--","java","-jar","/app.jar"]
