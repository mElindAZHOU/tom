#!/bin/sh

if [ ! -d  gen/sequents ]; then mkdir gen/sequents; fi
if [ ! -d  gen/urban ]; then mkdir gen/urban; fi
if [ ! -d  gen/lambda ]; then mkdir gen/lambda; fi

touch gen/sequents/sequents.tom
touch gen/urban/urban.tom
touch gen/lambda/lambda.tom

