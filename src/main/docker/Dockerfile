FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD srcs-0.1.0.jar srcs.jar
RUN sh -c 'touch /srcs.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.config.location=/var/app/srcs/conf/", "-jar","/srcs.jar"]