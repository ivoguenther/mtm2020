package com.example.mtm;

import java.io.ByteArrayOutputStream;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.ChannelExec;

public class ConnectionHandler {

    public static Connection connect(String username, String password, String host, int port) throws Exception {
        Connection con = new Connection();
        try {
            con.session = new JSch().getSession(username, host, port);
            con.session.setPassword(password);
            con.session.setConfig("StrictHostKeyChecking", "no");
            con.session.connect();
        } finally {
            System.out.println("Connection to Mainframe successfull");
        }
        return con;
    }

    public String send(Connection con, String command) throws JSchException, InterruptedException {
        con.channel = (ChannelExec) con.session.openChannel("exec");
        con.channel.setCommand(command);
        ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
        con.channel.setOutputStream(responseStream);
        con.channel.connect();

        while (con.channel.isConnected()) {
            Thread.sleep(100);
        }

        String responseString = new String(responseStream.toByteArray());
        return responseString;
    }

    public static void disconnect(Connection con) {
        System.out.println("Connection to Mainframe disconnected");
        con.session.disconnect();
        con.channel.disconnect();
    }

}
