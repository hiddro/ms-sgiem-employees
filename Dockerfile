FROM openjdk:17
EXPOSE 8080
ADD target/ms-sgiem-employees.jar ms-sgiem-employees.jar
ENTRYPOINT ["java", "-jar", "/ms-sgiem-employees.jar"]