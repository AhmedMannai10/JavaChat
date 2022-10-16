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

        // Creating the gui components
        JFrame frame = new JFrame("Java-Chat");
        JPanel mainPanel = new JPanel() ;
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");

        // Initialize the ActionListener with the inner class SendButtonListener that implements ActionListener
        sendButton.addActionListener(new SendButtonListener());

        // Adding the Components to the JPanel
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        // connection to the server
        setUpNetworking();

        frame.setSize(400, 500);
        frame.setVisible(true);

    }

    private void setUpNetworking(){

        try{
            // Establish Connection to the Server
            sock = new Socket("localhost", 5000);
            // Initializing the PrintWriter
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
                // when button.isPressed -> send message to the server
                // the inner class implements ActionListener while able to access the global class attributes
                writer.println(outgoing.getText());
                writer.flush();

            }catch(Exception ex){
                ex.printStackTrace();
            }

            // Resetting the Text Field to empty
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }

    public static void main(String[] args){
        new SimpleChatClientA().go();
    }


}
