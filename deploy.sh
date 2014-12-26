#!/bin/sh

set -xue

source setvars.sh

# make
lein uberjar

# copy
scp $FILE_LOCAL $USER@$HOST:$FILE_REMOTE

# run
ssh $USER@$HOST "java -jar $FILE_REMOTE"
