FROM openjdk:17
VOLUME /tmp
ENV LANG C.UTF-8
COPY *.jar app.jar
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" > /etc/timezone
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/urandom", "-Djava.util.Arrays.useLegacyMergeSort=true", "-jar","app.jar"]
