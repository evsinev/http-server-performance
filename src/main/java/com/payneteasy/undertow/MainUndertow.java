package com.payneteasy.undertow;

import com.payneteasy.Config;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

/**
 *
 */
public class MainUndertow {

    public static void main(String[] args) {
        Undertow server = Undertow.builder()
                .addHttpListener(Config.UNDERTOW, "localhost")
                .setHandler(new HttpHandler() {
                    @Override
                    public void handleRequest(final HttpServerExchange exchange) throws Exception {
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                        exchange.getResponseSender().send("Hello\n");
                    }
                }).build();
        server.start();

    }
}
