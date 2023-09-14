#!/bin/bash
path=/server/clkj/java
packName=clkj-api.jar
activeEnv=prod

#删除运行的进程
process_id=`ps -ef|grep ${packName}|grep -v "grep" |awk '{print $2}'`

if [ "$process_id" = "" ]
then
	echo "no process alive!"
else
  kill -9 $process_id
  echo "process remove list :$process_id"
  sleep 10
fi

nohup java -jar ${path}/${packName} --spring.profiles.active=${activeEnv} >/dev/null 2>&1 &
sleep 2

netstat -nptl
ps -ef | grep java | grep -v grep
