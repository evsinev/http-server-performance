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

Ports
-----
```
    public static final int JETTY   = 9001;
    public static final int NETTY_1 = 9002;
    public static final int NETTY_2 = 9003;
    public static final int ONE_NIO = 9004;
    public static final int RESIN   = 9005;
```
