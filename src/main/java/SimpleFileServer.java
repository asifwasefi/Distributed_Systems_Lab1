import java.io.*;
import java.net.*;

/**
 * Created by asif on 23/04/2021
 */
public class SimpleFileServer {

    public final static int SOCKET_PORT = 12345;
    public final static String FILE_TO_SEND = "src/tosend.txt";

    public static void main (String [] args ) throws IOException {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        ServerSocket servsock = null;
        Socket sock = null;
        try {
            servsock = new ServerSocket(SOCKET_PORT);
            while (true) {
                System.out.println("Waiting...");


                try {
                    sock = servsock.accept();//block until client requests connection
                    System.out.println("Accepted connection : " + sock);
                    // send file
                    File myFile = new File (FILE_TO_SEND);
                    byte [] buffer  = new byte [(int)myFile.length()];//buffer
                    bis = new BufferedInputStream(new FileInputStream(myFile));
                    bis.read(buffer,0,buffer.length);
                    os = sock.getOutputStream();//output stream of server (should be sourced by fileinputstream)
                    System.out.println("Sending " + FILE_TO_SEND + "(" + buffer.length + " bytes)");
                    os.write(buffer,0,buffer.length);
                    os.flush();
                    System.out.println("Done.");
                }
                finally {
                    if (bis != null) bis.close();
                    if (os != null) os.close();
                    if (sock!=null) sock.close();
                }
            }
        }
        finally {
            if (servsock != null) servsock.close();
        }
    }
}

