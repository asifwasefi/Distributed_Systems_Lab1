import java.io.IOException;

/**
 * Created by asif on 23/04/2021
 */
public class Serverthread implements Runnable{

    private int SOCKET_PORT;
    private String FILE_TO_SEND;

    public Serverthread(int SOCKET_PORT, String FILE_TO_SEND) {
        this.SOCKET_PORT = SOCKET_PORT;
        this.FILE_TO_SEND = FILE_TO_SEND;
    }

    public void run()
    {
        SimpleFileServer server = new SimpleFileServer(SOCKET_PORT,FILE_TO_SEND);
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
