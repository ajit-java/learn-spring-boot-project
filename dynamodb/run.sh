#!/usr/bin/env bash
echo 'Starting local dynamo db on port 9000'
java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb -port 9000

