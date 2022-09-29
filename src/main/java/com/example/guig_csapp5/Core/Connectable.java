package com.example.guig_csapp5.Core;

import java.io.IOException;

public interface Connectable {
    public void connect() throws IOException;
    public void send(String message);
    public void sendObj(Object object) throws IOException;
    public Object getObj() throws IOException, ClassNotFoundException;


    public String receive();
    public int getPort();
}
