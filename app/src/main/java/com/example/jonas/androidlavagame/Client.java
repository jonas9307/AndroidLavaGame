package com.example.jonas.androidlavagame;

/**
 * Created by Alexander on 2017-07-30.
 */

import android.os.AsyncTask;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client extends AsyncTask<String, Integer, Boolean> {
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
        byte[] buf = new byte[256];
        InetAddress address = null;
        try {
            address = InetAddress.getByName("albu9304");
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
        packet = new DatagramPacket(buf, buf.length);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // display response
        String received = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Quote of the Moment: " + received);

        socket.close();
        return null;
    }
}
