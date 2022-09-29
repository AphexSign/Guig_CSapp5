package com.example.guig_csapp5.Protocol;

import com.example.guig_csapp5.Core.Connectable;

import java.io.*;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;



public class GuigProtocolClient implements Connectable {

    public static final int DEFAULT_PORT = 8000, TIMEOUT = 3000 /* in milliseconds */;

    private Socket socket;
    private String server;
    private int port;
    private InputStream inStream;
    private OutputStream outStream;
    Scanner in;
    PrintWriter out;
    private Logger log;

    public GuigProtocolClient(String server) {
        this(server, DEFAULT_PORT);
    }

    public GuigProtocolClient(String server, int port) {
        this.log = Logger.getLogger("global");
        this.server = server;
        this.port = port;
    }

    @Override
    public void connect() throws IOException {
        this.socket = new Socket();
        this.socket.connect(new InetSocketAddress(InetAddress.getByName(this.server), this.port), TIMEOUT);
        log.info(String.format("Connection to server %s established at port %d.\n", server, port));
        this.inStream =  this.socket.getInputStream();
        this.outStream = this.socket.getOutputStream();
        this.in = new Scanner(this.inStream);
        this.out = new PrintWriter(new OutputStreamWriter(this.outStream, StandardCharsets.UTF_8), true /*autoFlush */);
    }

    @Override
    public void send(String message) {
        this.out.println(message);
        log.info(String.format("Message %s sent.\n", message));

    }

    @Override
    public void sendObj(Object object) throws IOException {
        ObjectOutputStream outputStream=new ObjectOutputStream(this.socket.getOutputStream());
        outputStream.writeObject(object);
    }

    @Override
    public Object getObj() throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream=new ObjectInputStream(this.socket.getInputStream());
        return inputStream.readObject();
    }


    @Override
    public String receive() {
        String message = this.in.nextLine();
        log.info(String.format("Message %s received.\n", message));
        return message;
    }

    @Override
    public int getPort() {
        return this.port;
    }

    public String getServer() {
        return this.server;
    }

    public boolean isConnectionClosed() {
        return this.socket.isClosed();
    }






}
