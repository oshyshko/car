#!/bin/sh

set -xue

source setvars.sh

# make
lein uberjar

# copy
ssh $CAR_USER@$CAR_HOST "mkdir -p $PI_JAR_DIR"
scp -q $JAR $CAR_USER@$CAR_HOST:$PI_JAR

# kill previous Java, if there is any
set +e
ssh $CAR_USER@$CAR_HOST "sudo killall java"
set -e

# run
ssh $CAR_USER@$CAR_HOST "sudo java -jar $PI_JAR"
