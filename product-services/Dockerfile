FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/product-services-0.0.1-SNAPSHOT.jar product-services.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "product-services.jar"]
