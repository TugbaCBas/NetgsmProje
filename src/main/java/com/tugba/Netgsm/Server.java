/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tugba.Netgsm;

import java.io.IOException;
import java.nio.channels.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tugbacanoglu
 */
public class Server {

    AsynchronousServerSocketChannel serverChannel;
    AsynchronousSocketChannel clientChannel;
int number = 0;
    AsynchronousServerSocketChannel createSocket() throws IOException, InterruptedException, ExecutionException {

        serverChannel = AsynchronousServerSocketChannel.open();
        InetSocketAddress hostAddress = new InetSocketAddress("127.0.0.1", 8088);
        serverChannel.bind(hostAddress);
        
        while (true) {
            System.out.println("while loop");
            serverChannel.accept(
                    null, new CompletionHandler<AsynchronousSocketChannel, Object>() {

                @Override
                public void completed(
                        AsynchronousSocketChannel result, Object attachment) {
                    if (serverChannel.isOpen()) {
                        serverChannel.accept(null, this);
                        System.out.println("it's open");
                        clientChannel = result;
                        if ((clientChannel != null) && (clientChannel.isOpen())) {
                            number++;
                            ReadWriteHandler handler = new ReadWriteHandler();
                            ByteBuffer buffer = ByteBuffer.allocate(32);

                            Map<String, Object> readInfo = new HashMap<>();
                            readInfo.put("action", "read");
                            readInfo.put("buffer", buffer);
                            clientChannel.read(buffer, readInfo, handler);
                            System.out.println(buffer.toString());
                            buffer.flip();
                            String str = "temp";
                            buffer = ByteBuffer.wrap(str.getBytes());
                            clientChannel.write(buffer);
                            System.out.println(number+" is the number of connections");
                        }
                    }

                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("failed");
                    String str = "failed";
                    ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
                    clientChannel.write(buffer);
                }
            });
            System.in.read();
        }

    }

    public void runServer(Future acceptResult) throws IOException, InterruptedException, ExecutionException {
        System.out.println("runserver");
//        Future acceptResult = serverChannel.accept();
        clientChannel = (AsynchronousSocketChannel) acceptResult.get();
        if ((clientChannel != null) && (clientChannel.isOpen())) {
            while (true) {
                ByteBuffer buffer = ByteBuffer.allocate(32);
                Future<Integer> readResult = clientChannel.read(buffer);

                // perform other computations
                readResult.get();

                buffer.flip();
                Future<Integer> writeResult = clientChannel.write(buffer);
                System.out.println(writeResult.toString());
                // perform other computations
                writeResult.get();
                clientChannel.close();

                buffer.clear();
            }

        }
    }

    class ReadWriteHandler implements
            CompletionHandler<Integer, Map<String, Object>> {

        @Override
        public void completed(
                Integer result, Map<String, Object> attachment) {
            Map<String, Object> actionInfo = attachment;
            String action = (String) actionInfo.get("action");

            if ("write".equals(action)) {
                ByteBuffer buffer = (ByteBuffer) actionInfo.get("buffer");
                buffer.flip();
                actionInfo.put("action", "read");

                clientChannel.write(buffer, actionInfo, this);
                buffer.clear();

            } else if ("read".equals(action)) {
                ByteBuffer buffer = ByteBuffer.allocate(32);

                actionInfo.put("action", "write");
                actionInfo.put("buffer", buffer);

                clientChannel.read(buffer, actionInfo, this);
            }
        }

        @Override
        public void failed(Throwable exc, Map<String, Object> attachment) {
            // 
        }
    }

}
