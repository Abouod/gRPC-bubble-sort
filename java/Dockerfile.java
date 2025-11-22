FROM eclipse-temurin:17-jre-focal AS runtime

# app will be copied from host build/install/examples
ARG INSTALL_DIR=/opt/examples
WORKDIR /opt

# Copy the Gradle installDist output (build/install/examples) into the image
# Make sure you run ./gradlew :examples:installDist on host before building image.
COPY examples/build/install/examples ${INSTALL_DIR}

ENV EXAMPLES_HOME=${INSTALL_DIR}
ENV CLASSPATH=${EXAMPLES_HOME}/lib/*

# By default run nothing; docker-compose will set SERVER_CLASS env var per service.
ENTRYPOINT ["sh", "-c", "exec java -cp \"$CLASSPATH\" ${SERVER_CLASS}"]
