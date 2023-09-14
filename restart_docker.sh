#!/bin/bash
path=/home/clkj
container_name=clkj
image_name=clkj/clkj
image_version=1.0

image=$image_name:$image_version

#删除运行的容器
container_id=`docker ps -a -q -f name=$container_name | awk '{print $1}'`
if [ "$container_id" = "" ]
then
	echo "no docker container alive!"
else
    docker rm -f $container_id
    echo "docker container remove list :$container_id"
    sleep 2
fi

#删除镜像
image_id=`docker images -q $image_name | awk '{print $1}'`
if [ "$image_id" = "" ]
then
    echo "no docker image alive!"
else
    docker rmi $image_id
    echo "docker image remove list :$image_id"
    sleep 2
fi

docker build -t $image $path
sleep 5
docker run -d -e "SPRING_PROFILES_ACTIVE=test" --name $container_name -p 8088:8080 -p 8089:9099 -v /home/yunweitong/upload:/home/yunweitong/upload $image
