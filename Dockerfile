FROM openjdk:17
COPY build/libs/course-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java"]
CMD ["-jar", "/app/course-0.0.1-SNAPSHOT.jar"]
