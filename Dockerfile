FROM openjdk:17-jdk-slim
WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY .gradle .gradle
COPY src ./src
RUN chmod +x ./gradlew
RUN ./gradlew clean build
COPY build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]