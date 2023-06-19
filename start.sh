#!/bin/bash

docker compose down
./gradlew clean
./gradlew build
docker image build -t sleepyheadlabs-demo:latest .
docker compose build --pull --no-cache
docker compose up
