#!/bin/sh

set -xue

source setvars.sh

# run
ssh $PI_USER@$PI_HOST "sudo shutdown now"
