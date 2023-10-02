FROM maven:3.6-jdk-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Estágio de implantação
FROM tomcat:9-jre11

COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/api.war
CMD ["catalina.sh", "run"]
