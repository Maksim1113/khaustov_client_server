import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer extends Thread {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
   /* private BufferedReader in;
    private BufferedWriter out;*/

    public ChatServer(Socket socket) throws IOException {
        this.socket = socket;
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        /*in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));*/
        start();
    }

    @Override
    public void run() {
        Message say;
        try {
            try {
                while (true) {

                    try {
                        say = (Message)in.readObject();

                        System.out.println(say);
                        for (ChatServer vr : ServerChat.serverList) {
                            if (vr == this){
                                continue;
                            }
                            vr.send(say);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                        /*if (say.msg.equals("end")) {
                            break;
                        }*/




                }
            } catch (NullPointerException ignored) {
            }


        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    private void send(Message msg) {
        try {
            out.writeObject(msg + "\n");
            out.flush();
        } catch (IOException e) {

        }

    }
}

