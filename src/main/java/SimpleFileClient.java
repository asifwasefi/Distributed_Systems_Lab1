import java.io.*;
import java.net.*;

/**
 * Created by asif on 23/04/2021
 */
public class SimpleFileClient {

    public final static int SOCKET_PORT = 12345;
    public final static String SERVER = "127.0.0.1";  // localhost
    public final static String
            FILE_TO_RECEIVED = "src/output.txt";

    public final static int FILE_SIZE = 6022386; // file size temporary hard coded
    // should bigger than the file to be downloaded

    public static void main (String [] args ) throws IOException {
        int bytesRead;
        int cursor = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        Socket sock = null;
        try {
            sock = new Socket(SERVER, SOCKET_PORT);
            System.out.println("Connecting...");

            // receive file
            byte [] buffer  = new byte [FILE_SIZE];
            InputStream is = sock.getInputStream();//input stream of server (connect this to fileoutput stream)
            bos = new BufferedOutputStream(new FileOutputStream(FILE_TO_RECEIVED));



            bytesRead = is.read(buffer,0,buffer.length);
            cursor = bytesRead;

            do {
                bytesRead =
                        is.read(buffer, cursor, (buffer.length-cursor));
                if(bytesRead >= 0) cursor += bytesRead;//slide the cursor to the new offset
            } while(bytesRead > -1);

            bos.write(buffer, 0 , cursor);
            bos.flush();
            System.out.println("File " + FILE_TO_RECEIVED
                    + " downloaded (" + cursor + " bytes read)");
        }
        finally {
            if (fos != null) fos.close();
            if (bos != null) bos.close();
            if (sock != null) sock.close();
        }
    }

}
