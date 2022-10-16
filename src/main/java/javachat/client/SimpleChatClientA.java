package javachat.client;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SimpleChatClientA {

    JTextField outgoing;
    PrintWriter writer;
    Socket sock;

    // GUI and Register a Listener
    public void go() {

        JFrame frame = new JFrame("Java-Chat Client");
        JPanel mainPanel = new JPanel() ;
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(400, 500);
        frame.setVisible(true);

    }

    private void setUpNetworking(){

        try{
            sock = new Socket("localhost", 5000);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("network established");
        }catch(IOException ex){
            ex.printStackTrace();
        }

    }

    public class SendButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{

                writer.println(outgoing.getText());
                writer.flush();

            }catch(Exception ex){
                ex.printStackTrace();
            }

            outgoing.setText("");
            outgoing.requestFocus();
        }
    }

    public static void main(String[] args){
        new SimpleChatClientA().go();
    }


}
