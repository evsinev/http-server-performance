package com.payneteasy.jetty;

import com.payneteasy.Config;
import com.payneteasy.servlet.TestServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class MainJetty {

    public static void main(String[] args) throws Exception {

        Server server = new MainJetty().start(Config.JETTY);
        server.join();

    }

    public Server start(int aPort) throws Exception {
        Server server = new Server(aPort);

        ServletContextHandler context  = new ServletContextHandler(server, "/", ServletContextHandler.NO_SESSIONS);
        context.addServlet(TestServlet.class,  "/test/*").setAsyncSupported(true);

        server.setHandler(context);

        server.start();
        return server;
    }

}
