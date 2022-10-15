package test.client;

import test.calculator.Calculator;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args){
        try{
            Socket chatSocket = new Socket("127.0.0.1", 1234);

            PrintWriter writer = new PrintWriter(chatSocket.getOutputStream(), true);

            Scanner sc = new Scanner(System.in);
            Calculator cal = new Calculator();

            System.out.println("Enter the First Variable: ");
            cal.setV1(sc.nextDouble());

            sc.nextLine();
            String op ;

            do{
                System.out.println("Enter an Operator");
                op = sc.nextLine();


            }while(!op.matches("[-+*/]"));
            cal.setOperator(op);
            System.out.println(op);

            System.out.println("Enter the Second Variable: ");
            cal.setV2(sc.nextDouble()) ;



            OutputStream outStream = chatSocket.getOutputStream();
            ObjectOutputStream objectInputStream = new ObjectOutputStream(outStream);
            objectInputStream.writeObject(cal);


            InputStreamReader stream = new InputStreamReader(chatSocket.getInputStream());

            BufferedReader br = new BufferedReader(stream);

            System.out.println("The calculated Value : " + br.readLine());

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
