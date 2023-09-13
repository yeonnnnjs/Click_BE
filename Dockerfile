FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build.gradle .
COPY settings.gradle .
RUN ./gradlew clean build --no-daemon
COPY build/libs/*.jar click.jar
CMD ["java", "-jar", "click.jar"]