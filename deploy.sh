#!/usr/bin/bash

mvn spring-boot:build-image
docker tag datagrid:latest localhost:5000/datagrid:latest
docker push localhost:5000/datagrid:latest
kubectl delete deployments datagrid-deployment
kubectl apply -f kube/deployment.yaml
