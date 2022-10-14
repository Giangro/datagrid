#!/usr/bin/bash

./mvnw spring-boot:build-image -DskipTests
docker tag datagrid:latest localhost:5000/datagrid:latest
docker push localhost:5000/datagrid:latest
kubectl delete deployments datagrid-deployment -n develop
kubectl apply -f kube/deployment.yaml -n develop
