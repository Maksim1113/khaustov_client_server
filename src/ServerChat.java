import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;


public class ServerChat {
    public static final int PORT = 8080;
    public static LinkedList<ChatServer> serverList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server is running");
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new ChatServer(socket));
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}
