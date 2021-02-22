# Microservices with Spring boot

The microservice project is build using spring cloud with Eureka, Zuul, Cloud Config server, boot admin, zipkin and ELK 
starters.
Since microservices has a distributed architecture, there can be many microservices, and each microservice can have many 
instances, each microservice can communicate with multiple microservices. Since, there are a lot of components involved, 
things could go wrong unless it is monitored properly. With the help of the tools that spring cloud provides we can
maintain the project much cleaner and much reliable way.

## Eureka 
Eureka is used for registering microservices. Since a typical microservice project contains 20-30 microservices and each
service can have many instances. Unless they are registered somewhere. it is hard to track which service is available
and which one is down.

- Eureka maintains a registry for microservices which registers themselves as eureka clients. It provides a dashboard 
with the list of services that are registered to it.
- Whenever an instance goes down, Eureka does not route the requests to that instance.
- It can be used by other spring cloud components for the discovery of the service.

## Cloud Config server 
 - It is a configuration server which points to central repository where the application configurations of microservices are maintained. 
   The central repository can be for example github. 
 - Each microservice configuration is maintained in a <service name-profile>.yml in the repository. By default, application.yml
   is used if the specific configuration filed does not exist, and the entries in the default configuration file  can be overridden 
   by its specific configuration file.
 - Microservices can register themselves as config clients to access their application configurations.
 - Application configurations can be changed without needing a restart




