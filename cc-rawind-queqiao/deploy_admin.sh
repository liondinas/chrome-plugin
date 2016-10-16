#!/bin/sh

PROJECT_HOME=/home/rd/chewen/git/obd
#DEST_IP=205.209.185.57
DEST_IP=205.209.187.142

if [ $# -le 1 ]
then
  echo "Please input the server ip and restartResin need, e.g.=$DEST_IP 0 no resin restart,  e.g.=$DEST_IP 1 resin restar"
  exit 1
fi 

DEST_IP=$1
echo "\033[33m deploy to ip= $DEST_IP, project=obd-admin  \033[0m"


#cd $PROJECT_HOME
#git pull --rebase

#cd $PROJECT_HOME/obd-admin
#if [ $? -ne 0 ]
#then
#    echo "Error"
#    exit 1
#fi

mvn prepare-package war:exploded -Dmaven.test.skip
if [ $? -ne 0 ]
then
    echo "\033[31m error in mvn admin \033[0m"
    exit 1
fi

#ssh $DEST_IP "cp -r /data/www/admin.obd.chewen.com /data/www/admin.obd.chewen.com.`date +%Y%m%d%H%M%S`"
#if [ $? -ne 0 ]
#then
#    echo "\033[31m error in ssh $DEST_IP to cp file \033[0m"
#    exit 1
#fi


rsync -rvctz --progress --delete ./target/cc-rawind-queqiao-1.0-SNAPSHOT/ root@$DEST_IP:/data/www/proxy.xiaochengzi.vip/
if [ $? -ne 0 ]
then
    echo "\033[31m error in rsync $DEST_IP to cp file \033[0m"
    exit 1
fi


echo "\033[33m rsync finish to $DEST_IP \033[0m"


if [ $# -ne 0 ]
then
    if [ $2 -eq 1 ]
    then
        ssh root@$DEST_IP "/data/server/resin/bin/httpd.sh restart"
        echo "\033[33m restart resin on $DEST_IP \033[0m"
    fi
fi

echo "\033[33m deploy end to $DEST_IP \033[0m"

