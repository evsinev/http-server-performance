package com.payneteasy.jetty;

import com.payneteasy.Config;
import com.payneteasy.servlet.TestServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainJetty {

    public static final byte[] HELLO = "hello\n".getBytes();

    public static void main(String[] args) throws Exception {

        Server server = new MainJetty().start(Config.JETTY);
        server.join();

    }

    public Server start(int aPort) throws Exception {
        Server server = new Server(aPort);

        ServletContextHandler context  = new ServletContextHandler(server, "/", ServletContextHandler.NO_SESSIONS);
        context.addServlet(TestServlet.class,  "/test/*").setAsyncSupported(true);

        server.setHandler(context);
//        server.setHandler(new AbstractHandler() {
//            @Override
//            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//                response.setStatus(HttpServletResponse.SC_OK);
//                baseRequest.setHandled(true);
//                response.getOutputStream().write(HELLO);
//            }
//        });

        server.start();
        return server;
    }

}
