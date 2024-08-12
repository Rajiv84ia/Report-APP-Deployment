FROM openjdk:17
COPY /target/01_ReportApp-2-0.0.1-RELEASE /usr/app/01_ReportApp-2-0.0.1-RELEASE
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["catalina.sh", "run"]
