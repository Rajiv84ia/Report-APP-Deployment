FROM openjdk:17
COPY /target/01_ReportApp-2-0.0.1-RELEASE.war /usr/app/01_ReportApp-2-0.0.1-RELEASE.war
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "01_ReportApp-2-0.0.1-RELEASE.war"]
