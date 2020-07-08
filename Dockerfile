FROM openjdk:14-alpine
COPY target/covidit-*.jar covidit.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "covidit.jar"]