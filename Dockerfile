FROM java:8
EXPOSE 8098

VOLUME /tmp
ADD timerzone-api.jar  /app.jar

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
