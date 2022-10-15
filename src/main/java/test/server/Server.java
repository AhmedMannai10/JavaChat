package test.server;

import test.calculator.Calculator;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args){
        try{
            ServerSocket serverSocket = new ServerSocket(1234) ;

//            String msg =" ";
//            while (!msg.equals("q")){
//                // Establish Connection with the client
//                Socket sock = serverSocket.accept();
//                // listen to what the client is saying
//                InputStreamReader stream = new InputStreamReader(sock.getInputStream());
//                BufferedReader reader = new BufferedReader(stream);
//
//                msg = reader.readLine();
//                System.out.format("Client[%d]: %s\n",sock.getPort(), msg);
//
//                reader.close();
//
//            }

              // Establish Connection with the client
                Socket sock = serverSocket.accept();
                // listen to what the client is sending
                ObjectInputStream objectStream = new ObjectInputStream(sock.getInputStream());

                Calculator cal = (Calculator) objectStream.readObject();

                double result = cal.getResult();
            System.out.println(result);

                OutputStreamWriter writer = new OutputStreamWriter(sock.getOutputStream());

                PrintWriter printWriter = new PrintWriter(writer, true);

                printWriter.println(result);




            serverSocket.close();

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
