http-server-performance
=======================

Build 
-----

```
mvn clean install assembly:single
```

Run server
----------
````
java -cp target/netty-http-server-1.0-SNAPSHOT-jar-with-dependencies.jar [main class]
````

Where main class can be:
* com.payneteasy.jetty.MainJetty
* com.payneteasy.netty.MainNetty 
* com.payneteasy.jetty.HelloWebServer
* com.payneteasy.one_nio.MainOneNio
* com.payneteasy.resin.MainResin

Execute performance test
------------------------

```
ab -c 10 -k -r -t 10 http://localhost:8080/test
```
