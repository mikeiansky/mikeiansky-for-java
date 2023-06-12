FROM openjdk:11

MAINTAINER **** #维护者信息

# 设置时区，构建镜像时执行的命令
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" > /etc/timezone

WORKDIR /home/ciwei

# 复制jar包
COPY target/*.jar /home/ciwei/app.jar

EXPOSE 20001

ENTRYPOINT ["/bin/sh","-c","java -Dfile.encoding=utf8 -Djava.security.egd=file:/dev/./urandom -jar app.jar ${PARAMS}"]
