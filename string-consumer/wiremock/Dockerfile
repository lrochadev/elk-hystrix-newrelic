FROM java:8

ENV VERSION 1.58

RUN mkdir /opt/wiremock
WORKDIR /opt/wiremock
#RUN curl -sSL -o wiremock.jar https://repo1.maven.org/maven2/com/github/tomakehurst/wiremock/$VERSION/wiremock-$VERSION-standalone.jar
ADD wiremock-standalone-2.6.0.jar /opt/wiremock

EXPOSE 8765

ENTRYPOINT ["java","-jar","wiremock-standalone-2.6.0.jar","--verbose", "--port", "8765"]