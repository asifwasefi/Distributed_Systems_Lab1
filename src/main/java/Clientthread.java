import java.io.IOException;

/**
 * Created by asif on 23/04/2021
 */
public class Clientthread implements Runnable {


    private int SOCKET_PORT;
    private String FILE_TO_RECEIVE;

    public Clientthread(int SOCKET_PORT, String FILE_TO_RECEIVE) {
        this.SOCKET_PORT = SOCKET_PORT;
        this.FILE_TO_RECEIVE = FILE_TO_RECEIVE;
    }

    @Override
    public void run() {

        SimpleFileClient client = new SimpleFileClient(SOCKET_PORT,FILE_TO_RECEIVE);
        try {
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
