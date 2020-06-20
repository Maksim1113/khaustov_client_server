public class MainClient {
    public static String adressIp = "localhost";
    public static int port = 8080;

    public static void main(String[] args) {
        new Client(adressIp, port);

    }

}
