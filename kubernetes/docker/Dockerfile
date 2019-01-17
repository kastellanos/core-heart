FROM openjdk:8-jdk-alpine



COPY ./run_jar.sh ./app.jar ./

RUN chmod +x /run_jar.sh

ENTRYPOINT ["./run_jar.sh"]
