#!/bin/bash
cd /home/ubuntu/Apps/tgc-server

pid="/home/ubuntu/Apps/tgc-server/run.pid"

if [  -f "$pid" ]
then
    echo "=========Old running instance=========="
    echo `cat run.pid`
    sudo kill -9  `cat run.pid`
fi
echo "=========starting in dev mode========="
sudo nohup java -jar -Dspring.profiles.active=dev /home/ubuntu/Apps/tgc-server/target/gotoclasses-0.0.1-SNAPSHOT.jar & echo $! > run.pid
