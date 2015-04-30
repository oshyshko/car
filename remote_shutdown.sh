#!/bin/sh

set -xue

source setvars.sh

# run
ssh $CAR_USER@$CAR_HOST "sudo shutdown now"
