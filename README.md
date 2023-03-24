# IBM-MQ-BASICS
## _Implementing producers, consumers, publishers and subscribers_

This repository shows examples of how to configure multiple listeners, multiple senders and how to convert the message from the broker. The examples mostly uses default configuration. I did not configured things such as QOS, message lifetime, persistence, auto confirms and etc.

## What is IBM MQ?
IBM MQ supports the exchange of information between applications, systems, services and files by sending and receiving message data via messaging queues. This simplifies the creation and maintenance of business applications. IBM MQ works with a broad range of computing platforms, and can be deployed across a range of different environments including on-premise, in cloud, and hybrid cloud deployments. IBM MQ supports a number of different APIs including Message Queue Interface (MQI), Java Message Service (JMS), REST, .NET, IBM MQ Light and MQTT.

## Features
This repository uses abstract messages to exemplify the functionality. So I made a drawing of what it does and I hope it makes sense.
![Architecture model ](https://github.com/hi-im-yan/ibm-mq-basics/blob/main/MQ-Arquitecture-Model.png)

## Tech

This repository uses the following techs:
- [IBM MQ] - For message queue
- [Spring Boot] - For consuming and sending messages
- [Docker] - For running the IBM MQ container
- [JAVA 8] - For programming with Spring Boot

## Installation

### First: the docker

```sh
docker volume create qm1data
docker run --env LICENSE=accept --env MQ_QMGR_NAME=QM1 --volume qm1data:/mnt/mqm --publish 1414:1414 --publish 9443:9443 --detach --env MQ_APP_PASSWORD=passw0rd --name QM1 icr.io/ibm-messaging/mq:9.2.0.0-r1
```
I did'nt use the latest versions because of some bugs that happens when you restart the container.

### Second: the spring boot
Go to the root of each project and do this:
```sh
mvn clean install
mvn spring-boot:run
```

## How to use

* We need to create the Queue and the Topic in the IBM MQ Console. If your container is running you should be able to access the console https://localhost:9443/. The credentials is by default: _admin_ and _passw0rd_. 
* After that go to _manage_ -> _queue_ -> _create_ (https://localhost:9443/ibmmq/console/#/manage/qmgr/QM1/queues). Select _local_ and create a queue named _CUSTOM_QUEUE_ and _ERROR_QUEUE_ this will be mapped in the sender and the listeners.
* Now go to _manage_ -> _topic_ -> _create_ (https://localhost:9443/ibmmq/console/#/manage/qmgr/QM1/topics). Create a topic named _CUSTOM_TOPIC_ with the topic chain of _topic/message_. The topic chain will be mapped in the sender and the listeners.

Now it's all setup to use, you should be able to send a message making a POST request to _http://localhost:8080/queue_ with body: 
```
{
    "title":"This message is beign sent to IBM MQ",
    "queueMessage":"This message is supposed to be processed by only one listener."
}
```
You should be able to send a topic making a POST request to _http://localhost:8080/topic_ with body:

```
{
    "title":"This message is beign sent to IBM MQ",
    "topicMessage":"This message is supposed to be processed by all subscribers."
}
```

Swagger documentation is also available at http://localhost:8080/swagger-ui/index.html

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [IBM MQ]: <https://www.ibm.com/products/mq>
   [Spring Boot]: <https://spring.io/>
   [Docker]: <https://www.docker.com/>
   [JAVA 8]: <https://www.oracle.com/br/java/technologies/javase/javase8-archive-downloads.html>

