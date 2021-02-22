# Microservices with Spring boot

The microservice project is build using spring cloud with Eureka, Zuul, Cloud Config server, boot admin, zipkin and ELK 
starters.
Since microservices has a distributed architecture, there can be many microservices, and each microservice can have many 
instances, each microservice can communicate with multiple microservices. Since, there are a lot of components involved, 
things could go wrong unless it is monitored properly. With the help of the tools that spring cloud provides we can
maintain the project much cleaner and much reliable way.

## Eureka (Service discovery)
Eureka is used for registering microservices. Since a typical microservice project contains 20-30 microservices and each
service can have many instances. Unless they are registered somewhere. it is hard to track which service is available
and which one is down.

- Eureka maintains a registry for microservices which registers themselves as eureka clients. It provides a dashboard 
with the list of services that are registered to it.
- Whenever an instance goes down, Eureka does not route the requests to that instance.
- It can be used by other spring cloud components for the discovery of the service.

## Cloud Config server (Centralized configuration server)
 - It is a configuration server which points to central repository where the application configurations of microservices are maintained. 
   The central repository can be for example github. 
 - Each microservice configuration is maintained in a <service name-profile>.yml in the repository. By default, application.yml
   is used if the specific configuration filed does not exist, and the entries in the default configuration file  can be overridden 
   by its specific configuration file.
 - Microservices can register themselves as config clients to access their application configurations.
 - Application configurations can be changed without needing a restart

## Hystrix (Resiliency) 
- It gives resiliency to your microservices. 
- If you annotate a service call with @HystrixCommand annotation, hystrix will monitor the service call. Whenever it sees
  multiple failures while executing the service. It is going to isolate the service from the rest of your application which in the hystrix
  world called as opening the circuit. During this phase, it does not allow any calls to be made to this service, and it is going to honour the
  service with a fallback implementation if provided. Once the service comes back to the fully working state, hystrix closes the circuit and
  allows the traffic to the service.
- Hystrix also provides a dashboard which shows the state of services with Hystrix command annotation. It also shows whether the circuit is open/
close. 
_ For hystrix dashboard to work, you also need to enable the actuator for sending Hystix streams to the dashboard.   


## Zuul (API gateway and load balancing)
- Acts as an API gateway for microservices.
- Front door to the application, takes care of routing & filtering, common features like authentication,
authorization & auditing which is required by most of the microservices can be placed here.
- It also acts as load balancer. You can choose the load balancer implementation which you want to integrate 
with zuul. By default, it uses ribbon load balancer implementation

## Feign Client (communication)
- It simplifies the communication  with web microservices with a simple interface.
- You just need an interface annotated with @FeignClient with the web service name that you want to communicate.
Feign looks up into Eureka to identify the web service and communicates with it. It eliminates the boiler plate code
  used for making those request calls.

## Spring boot admin (collects the metrics)
- Spring boot admin consolidates all the metrics collected from each microservice.
- To make it work, every microservice has to stream all its metrics to spring boot admin by enabling all the 
  actuator endpoints.
- It gives us a unified view of all the metrics of all microservices in an application.