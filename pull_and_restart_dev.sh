#!/bin/bash
cd /home/ubuntu/Apps/tgc-server
echo "==========pulling from git========"
sudo git pull
echo "==========running maven==========="
sudo mvn install

pid="/home/ubuntu/Apps/tgc-server/run.pid"
echo `sudo lsof -ti tcp:8080`
sudo kill -9 `sudo lsof -ti tcp:8080`
if [  -f "$pid" ]
then
    echo "=========Old running instance=========="
    echo `cat run.pid`
    sudo kill -9  `cat run.pid`
fi
echo "=========starting in dev mode========="
sudo nohup java -jar -Dspring.profiles.active=dev /home/ubuntu/Apps/tgc-server/target/gotoclasses-0.0.1-SNAPSHOT.jar & echo $! > run.pid
