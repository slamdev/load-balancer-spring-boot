# Load Balancer Spring Boot Starter [![Build Status](https://travis-ci.org/slamdev/load-balancer-spring-boot.svg?branch=master)](https://travis-ci.org/slamdev/load-balancer-spring-boot) [![Download](https://api.bintray.com/packages/slamdev/maven/load-balancer-spring-boot-starter/images/download.svg)](https://bintray.com/slamdev/maven/load-balancer-spring-boot-starter/_latestVersion)
Spring Boot Starter for [Load Balancer](https://github.com/slamdev/load-balancer) library. Provides auto configuration for ```LoadBalancer``` bean, sets ```RestClient``` to use the load balancer and implements ```HostAvailabilityChecker``` with requests to the actuator's  ```/health``` endpoint.
## Library usage
Add it to the project dependencies. Latest version can be found at Bintray (https://bintray.com/slamdev/maven/load-balancer-spring-boot-starter) and added via Maven or Gradle
## Existing integrations
There is a `load-balancer-spring-boot-sample` project with this starter located in this repository.
Also you can find example of the starter used at: https://github.com/slamdev/catalog/tree/master/client
