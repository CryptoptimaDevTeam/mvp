#!/bin/bash
cd /home/ubuntu/server/scripts

echo "> docker-compose up"    >> /home/ubuntu/server/deploy.log

sudo docker-compose up
