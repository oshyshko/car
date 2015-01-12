#!/bin/sh

set -xue

source setvars.sh

# make
lein uberjar

# copy
ssh $PI_USER@$PI_HOST "mkdir -p $PI_JAR_DIR"
scp -q $JAR $PI_USER@$PI_HOST:$PI_JAR

# kill previous Java, if there is any
set +e
ssh $PI_USER@$PI_HOST "sudo killall java"
set -e

# run
ssh $PI_USER@$PI_HOST "sudo java -jar $PI_JAR"
