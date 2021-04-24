import java.io.*;
import java.net.*;

/**
 * Created by asif on 23/04/2021
 */
public class SimpleFileClient {

    private int SOCKET_PORT;
    private String FILE_TO_RECEIVE;


    public final static int FILE_SIZE = 6022386; // file size temporary hard coded
    // should bigger than the file to be downloaded


    public SimpleFileClient(int SOCKET_PORT, String FILE_TO_RECEIVE) {
        this.SOCKET_PORT = SOCKET_PORT;
        this.FILE_TO_RECEIVE = "src/"+FILE_TO_RECEIVE;
    }

    public void start() throws IOException {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock = null;
        try {

            // receive file
            byte [] buffer  = new byte [FILE_SIZE];
            DatagramSocket dsoc=new DatagramSocket(SOCKET_PORT);
            fos=new FileOutputStream(FILE_TO_RECEIVE);

            DatagramPacket dp=new DatagramPacket(buffer,buffer.length);
            dsoc.receive(dp);
            System.out.println(new String(dp.getData(),0,dp.getLength()));
            fos.write(dp.getData(),0,dp.getLength());

            fos.close();
        }
        finally {
            if (fos != null) fos.close();
            if (bos != null) bos.close();
            if (sock != null) sock.close();
        }
    }

}
