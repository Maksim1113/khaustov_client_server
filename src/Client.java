import java.io.*;
import java.net.Socket;


public class Client {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
   /* private BufferedReader in;
    private BufferedWriter out;*/
    private BufferedReader reader;
    private String addressIp;
    private int port;



    public Client(String addressIp, int port) {
        this.addressIp = addressIp;
        this.port = port;
        try {
            this.socket = new Socket(addressIp, port);
        } catch (IOException e) {
            System.out.println("Error");
        }
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            new MessageRead().start();
            new MessageWrite().start();
        } catch (IOException e) {
            System.out.println("error");
        }
    }


    private class MessageRead extends Thread {
        @Override
        public void run() {

            Message str;
            try {
                while (true) {

                    try {
                        str = (Message) in.readObject();
                        System.out.println(str);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }


                    /*if (str.equals("end")) {
                        break;
                    }*/


                }
            } catch (IOException e) {
                System.out.println("Error");
            }
        }
    }

        public class MessageWrite extends Thread {

            @Override
            public void run() {

                while (true) {
                    Message msg;
                    String userSay;
                    try {
                        System.out.println("Enter words");
                        userSay = reader.readLine();
                        if (userSay.equals("end")) {
                        /*msg = new Message(userWord);
                        out.writeObject(msg);*/
                            break;
                        } else {
                            msg = new Message(userSay);
                            out.writeObject(userSay + "\n");
                            out.flush();
                        }

                    } catch (IOException e) {
                        System.out.println("Error");

                    }

                }
            }
        }

}



