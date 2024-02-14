FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY *.jar ForwardingAgentApp.jar
ENTRYPOINT ["java","-jar","/ForwardingAgentApp.jar"]
EXPOSE 8080