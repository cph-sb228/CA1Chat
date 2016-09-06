/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Zygi
 */
public class Server {

    static int port = 8080;
    static String ip = "localhost";

    public static void main(String[] args) throws IOException {
        if (args.length == 2) {
            ip = args[0];
            port = Integer.parseInt(args[1]);
        }
        ServerSocket ss = new ServerSocket();
        ss.bind(new InetSocketAddress(ip, port));
        System.out.println("Server started, listening on port: " + port + " bound to ip: " + ip);
        while (true) {

            Socket client = ss.accept();
            handleClient(client);
            System.out.println("New Client Connected");

        }
    }
        

    private static void handleClient(Socket client) throws IOException {
        PrintWriter pw = new PrintWriter(client.getOutputStream(), true);

        pw.println("Hello buddy");
        Scanner scn = new Scanner(client.getInputStream());
        String msg = "";
        boolean itsTrue = true;
        while (itsTrue) {
            msg = scn.nextLine();
            if (msg.equals("stop")) {
                itsTrue = false;
            } else {

                pw.println(msg.toUpperCase());
            }
        }

        client.close();
        pw.close();

    }
}
