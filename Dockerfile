FROM alpine:3.11.2
RUN apk add --no-cache openjdk11
COPY build/libs/card-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app/
ENTRYPOINT ["java"]
CMD ["-jar", "/app/card-0.0.1-SNAPSHOT.jar"]
