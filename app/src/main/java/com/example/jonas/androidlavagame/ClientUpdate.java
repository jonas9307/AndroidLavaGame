package com.example.jonas.androidlavagame;

/**
 * Created by Alexander on 2017-07-30.
 */

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.AsyncTask;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientUpdate extends AsyncTask<String, Integer, Boolean> {

    private RectPlayer player;
    private HashMap<String, RectPlayer> enemyPlayers;

    public ClientUpdate(RectPlayer player, HashMap<String, RectPlayer> enemyPlayers) {
        this.player = player;
        this.enemyPlayers = enemyPlayers;
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
        byte[] buf = new byte[256];
        String message = Integer.toString(player.getId()) + "," + player.getPositionX() + "," + player.getPositionY();
        buf = message.getBytes();
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
        buf = new byte[256];
        packet = new DatagramPacket(buf, buf.length);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // display response
        String received = new String(packet.getData(), 0, packet.getLength());
        if(received.equals("")) {
            socket.close();
            return null;
        }
        String[] stringArray = received.split(",");
        RectPlayer ep = null;
        int id, x, y, n;
        for (int i = 0; i < stringArray.length; i += 3) {
            id = Integer.valueOf(stringArray[i]);
            x = Integer.valueOf(stringArray[i+1]);
            y = Integer.valueOf(stringArray[i+2]);
            //n = enemyPlayers.size();
            if (enemyPlayers.containsKey(String.valueOf(id))) {
                ep = enemyPlayers.get(String.valueOf(id));
                Point p = new Point(x, y);
                ep.update(p);
            } else {
                enemyPlayers.put(String.valueOf(String.valueOf(id)), new RectPlayer(
                    new Rect(0, 0, 200, 200), Constants.COLORS[id]
                ));
                enemyPlayers.get(String.valueOf(id)).update(new Point(x, y));
            }
        }


        socket.close();
        return null;
    }
}