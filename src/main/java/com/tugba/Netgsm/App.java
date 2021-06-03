package com.tugba.Netgsm;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        Server server = new Server();
        server.createSocket();
        System.out.println( "Hello World!" );
    }
}
