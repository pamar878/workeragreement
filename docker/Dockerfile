FROM ${DOCKER_IMAGE}

COPY target/worker-agreement-renewal-1.0.0.jar /usr/src/app/worker-agreement-renewal-1.0.0.jar

RUN echo "America/Guatemala" > /etc/timezone

# JVM_ARGS default value
ENV JVM_ARGS="${JVM_ARGS_DEFAULT}"
ENV SPRING_PROFILES_ACTIVE="${SPRING_PROFILES_ACTIVE_DEFAULT}"

ENTRYPOINT java $JVM_ARGS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -jar /usr/src/app/worker-agreement-renewal-1.0.0.jar