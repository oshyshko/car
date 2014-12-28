#!/bin/sh

set -xue

source setvars.sh

# make
lein uberjar

# copy
ssh $PI_USER@$PI_HOST "mkdir -p $PI_JAR_DIR"
scp -q $JAR $PI_USER@$PI_HOST:$PI_JAR

# kill previous Java, if there is any
ssh $PI_USER@$PI_HOST "killall java"

# run
ssh $PI_USER@$PI_HOST "java -jar $PI_JAR"
