FROM maven:3.9.9-eclipse-temurin-17

USER root

RUN apt-get update && \
    apt-get install -y wget gnupg && \
    wget -q -O - https://dl.google.com/linux/linux_signing_key.pub \
    | gpg --dearmor -o /usr/share/keyrings/google-linux.gpg && \
    echo "deb [arch=amd64 signed-by=/usr/share/keyrings/google-linux.gpg] http://dl.google.com/linux/chrome/deb/ stable main" \
    > /etc/apt/sources.list.d/google-chrome.list && \
    apt-get update && \
    apt-get install -y google-chrome-stable && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

ENV HOME=/tmp
ENV MAVEN_CONFIG=/tmp/.m2

COPY pom.xml .

RUN mvn -B -P Smoke dependency:go-offline && \
    mvn -B -P Smoke -DskipTests test-compile

COPY . .

RUN mkdir -p /app/reports /app/target /app/src/main/resources/screenShots && \
    chgrp -R 0 /app && \
    chmod -R g=u /app

CMD ["mvn", "clean", "test", "-P", "Smoke", "-Dbrowser=chrome", "-Dheadless=true"]