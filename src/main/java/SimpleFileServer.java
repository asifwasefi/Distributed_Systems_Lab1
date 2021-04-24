import java.io.*;
import java.net.*;

/**
 * Created by asif on 23/04/2021
 */
public class SimpleFileServer {


    private int SOCKET_PORT;
    private int SOCKET_PORT_CLIENT;
    private String FILE_TO_SEND;

    public SimpleFileServer(int SOCKET_PORT, int SOCKET_PORT_CLIENT, String FILE_TO_SEND) {
        this.SOCKET_PORT = SOCKET_PORT;
        this.SOCKET_PORT_CLIENT = SOCKET_PORT_CLIENT;
        this.FILE_TO_SEND = "src/"+FILE_TO_SEND;
    }

    public void start () throws IOException {
        DatagramSocket servsock = null;
        try {
            byte buffer[]=new byte[1024];
            FileInputStream fis=new FileInputStream(FILE_TO_SEND);
            DatagramSocket dsoc=new DatagramSocket(SOCKET_PORT);
            int i=0;
            while(fis.available()!=0)
            {
                buffer[i]=(byte)fis.read();//reads byte per byte
                i++;
            }
            fis.close();
            dsoc.send(new DatagramPacket(buffer,buffer.length,InetAddress.getLocalHost(),SOCKET_PORT_CLIENT));
        }
        finally {
            if (servsock != null) servsock.close();
        }
    }
}


