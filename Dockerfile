FROM adoptopenjdk:17-jre-hotspot
WORKDIR /app
COPY build.gradle .
COPY settings.gradle .
RUN ./gradlew clean build --no-daemon
COPY build/libs/click.jar click.jar
CMD ["java", "-jar", "click.jar"]
