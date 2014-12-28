#!/bin/sh

set -xue

# IMPORTANT! Don't edit this file!
# Instead, create ".setvars.sh" and override vars there.

PI_USER=pi
PI_HOST=

JAR_NAME=car-0.1.0-SNAPSHOT-standalone.jar
JAR=./target/$JAR_NAME
PI_JAR_DIR=/home/pi/.car
PI_JAR=$PI_JAR_DIR/$JAR_NAME

LOCAL_VARS=.setvars.sh

if [ -f $LOCAL_VARS ]; then
  source $LOCAL_VARS
fi
