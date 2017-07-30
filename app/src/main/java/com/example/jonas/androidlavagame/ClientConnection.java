package com.example.jonas.androidlavagame;

/**
 * Created by Alexander on 2017-07-30.
 */
import android.os.AsyncTask;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientConnection extends AsyncTask<String, Integer, Boolean> {

    private RectPlayer player;

    public ClientConnection(RectPlayer player) {
        this.player = player;
    }

    @Override
    protected Boolean doInBackground(String... strings) {

        // get a datagram socket
        DatagramSocket socket;
        socket = null;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        // send request
        System.out.println(" // send request");
        byte[] buf = new byte[0];
        InetAddress address = null;
        try {
            address = InetAddress.getByName("192.168.1.2");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 27015);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // get response
        buf = new byte[1];
        packet = new DatagramPacket(buf, buf.length);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // display response
        String received = new String(packet.getData(), 0, packet.getLength());
        player.setId(Integer.valueOf(received));
        System.out.println("Player id: " + received);

        socket.close();
        return null;
    }
}
