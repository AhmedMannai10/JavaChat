package javachat.client;
import java.util.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Client {

    JTextArea incoming;
    JTextField outgoing;
    BufferedReader reader;
    PrintWriter writer;

    Socket sock;

    // GUI and Register a Listener
    public void go() {

        // Creating the gui components
        JFrame frame = new JFrame("Java-Chat");
        JPanel mainPanel = new JPanel() ;
        incoming = new JTextArea(15, 30);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);

        // Building The Scroll bar
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");


        // Initialize the ActionListener with the inner class SendButtonListener that implements ActionListener
        sendButton.addActionListener(new Client.SendButtonListener());

        // Adding the Components to the JPanel
        mainPanel.add(qScroller) ;
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);

        // connection to the server
        setUpNetworking();

        Thread readerThread = new Thread((new IncomingReader()));
        readerThread.start();


        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(400, 500);
        frame.setVisible(true);

    }
    private void setUpNetworking(){

        try{
            // Establish Connection to the Server
            sock = new Socket("localhost", 5000);
            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);

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
        new Client().go();
    }

    public class IncomingReader implements Runnable{
        String message;
        @Override
        public void run() {
            try{
               while((message = reader.readLine()) != null) {
                   System.out.println("read " + message);
                   incoming.append(message + "\n");
               }
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}



