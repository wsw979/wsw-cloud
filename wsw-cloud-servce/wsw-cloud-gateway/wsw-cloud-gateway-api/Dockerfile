FROM openjdk:8-jre-alpine
 
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JHIPSTER_SLEEP=0 \
    JAVA_OPTS=""
 
CMD echo "The application will start in ${JHIPSTER_SLEEP}s..." && \
    sleep ${JHIPSTER_SLEEP} && \
    java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /wsw-cloud-gateway-api.jar
 
EXPOSE 9998
 
ADD *.jar /wsw-cloud-gateway-api.jar