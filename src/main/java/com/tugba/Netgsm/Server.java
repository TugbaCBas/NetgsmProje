/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tugba.Netgsm;

import java.io.IOException;
import java.nio.channels.*;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
/**
 *
 * @author tugbacanoglu
 */
public class Server {
    
    AsynchronousServerSocketChannel createSocket() throws IOException {
   
     AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
     server.bind(new InetSocketAddress ("127.0.0.1", 8000));
     
    return server;
    }
    
    void accept() throws Exception{
        AsynchronousServerSocketChannel server = createSocket();
    
    
    
    }
   
    
}
