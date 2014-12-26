#!/bin/sh

set -xue

source setvars.sh

# run
ssh $USER@$HOST "sudo shutdown -r now"
