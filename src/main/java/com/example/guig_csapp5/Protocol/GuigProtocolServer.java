package com.example.guig_csapp5.Protocol;

import com.example.guig_csapp5.Core.Connectable;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;




public class GuigProtocolServer implements Connectable {

    public static final int DEFAULT_PORT = 8000;

    private int port;
    private Socket socket;
    private ServerSocket serverSocket;
    private InputStream inStream;
    private OutputStream outStream;
    Scanner in;
    PrintWriter out;
    private Logger log;

    public GuigProtocolServer() throws IOException {
        this(DEFAULT_PORT);
    }

    public GuigProtocolServer(int port) throws IOException {
        this.log = Logger.getLogger("global");
        this.port = port;
        this.serverSocket = new ServerSocket(this.port);
        log.info(String.format("Server socket was created on port %d.\n", port));
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
    public void connect() throws IOException {
        this.socket = this.serverSocket.accept();
        log.info(String.format("Incoming connection from a client at %s accepted.\n", this.socket.getRemoteSocketAddress().toString()));
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
    public String receive() {
        String message = this.in.nextLine();
        log.info(String.format("Message %s received.\n", message));
        return message;
    }

    @Override
    public int getPort() {
        return this.port;
    }
}
