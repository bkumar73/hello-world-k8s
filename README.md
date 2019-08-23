# k8s-example

## Assuption:-

    1. A running kubernetes cluster accessible via kubectl

## Create namespace demo

    kubectl create namespace demo

## Build Hello Service and upload docker image

    ./gradlew hello-service:dockerPush -Pdockerusername=yourdockerusername

## Deploy Hello-service on kubernetes

    Edit image address of hello-service in hello-service/src.main/resource/kubernetes/deployment.yaml
    Deploy using kubectl:-
        kubectl create -f hello-service/src/main/resources/kubernetes/

## Build Wrold Service and upload docker image

    ./gradlew world-service:dockerPush -Pdockerusername=yourdockerusername

## Deploy world-service on kubernetes

    Edit image address of world-service in world-service/src.main/resource/kubernetes/deployment.yaml
    Deploy using kubectl:-
        kubectl create -f world-service/src/main/resources/kubernetes/

## Access services using NodePort

        kubectl get svc -n demo -o wide

    Note down the NodePort of both services and Hit yourclusteip:Nodeport on browser.


## Clean up

        kubectl delete ns demo

## Deployment using helm

    helm upgrade --install  hello-service -f hello-service/src/main/resources/helm/hello-service/values.yaml  hello-service/src/main/resources/helm/hello-service --namespace=demo
    helm upgrade --install  world-service -f world-service/src/main/resources/helm/world-service/values.yaml  world-service/src/main/resources/helm/world-service --namespace=demo

## Clean up

    helm del --purge hello-service
    helm del --purge world-service

## Deploy istio using helm

    helm upgrade  istio-init --install Infra/istio-1.2.4/helm/istio-init/  --namespace istio-system
    helm upgrade  istio --install Infra/istio-1.2.4/helm/istio/  --namespace istio-system

## Deploy hello-service & world-service with istio enabled

    Enable Auth-Injection in namespace Demo:-
        kubectl apply -f Infra/namespace/demo.yaml
    Change istio.enabled to true in values.yaml file in both service's helm charts.

    helm upgrade --install  hello-service -f hello-service/src/main/resources/helm/hello-service/values.yaml  hello-service/src/main/resources/helm/hello-service --namespace=demo
    helm upgrade --install  world-service -f world-service/src/main/resources/helm/world-service/values.yaml  world-service/src/main/resources/helm/world-service --namespace=demo

## Accessing Hello and World Service

    Create istio-ingressgateway for k8s cluster
        kubectl create -f Infra/istio-components/istio-gateway.yaml -n demo

    Hit http://yourclusterip:31380/ in browser


## Canary deployment of world-service

    helm upgrade --install  world-service-canary -f world-service/src/main/resources/helm/world-service/values-canary.yaml  world-service/src/main/resources/helm/world-service --namespace=demo --force
