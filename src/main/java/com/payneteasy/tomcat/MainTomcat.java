package com.payneteasy.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardServer;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.http11.Http11NioProtocol;

import javax.servlet.ServletException;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainTomcat {

    public static void main(String[] args) throws LifecycleException, ServletException, UnknownHostException {
        Tomcat tomcat = new Tomcat();
        // will be apr
        tomcat.setPort(9008);

        tomcat.addWebapp("/", new File("src/main/webapp").getAbsolutePath());

        Connector nioConnector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        nioConnector.setPort(9006);
        nioConnector.setProperty("address", InetAddress.getByName("127.0.0.1").getHostAddress());
        tomcat.getService().addConnector(nioConnector);

        // Add AprLifecycleListener
        StandardServer server = (StandardServer)tomcat.getServer();
        AprLifecycleListener listener = new AprLifecycleListener();
        server.addLifecycleListener(listener);

        Connector aprConnector = new Connector("org.apache.coyote.http11.Http11Protocol");
        aprConnector.setProperty("SSLEnabled", "false");
        aprConnector.setProperty("secure", "false");
        aprConnector.setProperty("scheme", "http");
        aprConnector.setPort(9007);

        tomcat.getService().addConnector(aprConnector);

        tomcat.start();
        tomcat.getServer().await();
    }
}
