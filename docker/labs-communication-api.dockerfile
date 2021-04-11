FROM openjdk:13
RUN cd /opt/
RUN rm -rf application
RUN mkdir application
RUN cd /application/
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]