package com.minshenglife.zktest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;

public class TokenTestServer {

    public static void main(String[] args) throws Exception {


        
        Server server = new Server(8000);
        
        ContextHandler ch = new ContextHandler("/");
        ch.addEventListener(new Init());
        
        server.setHandler(ch);
        
        server.start();
        server.join();
    }
}
