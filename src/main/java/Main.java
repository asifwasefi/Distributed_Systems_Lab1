import java.io.IOException;

/**
 * Created by asif on 23/04/2021
 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!"); // Display the string.

        //Server threads
        Thread servthread1 = new Thread(new Serverthread(2000,1000,"send1.txt"));
        servthread1.start();
        Thread servthread2 = new Thread(new Serverthread(2500,1500,"send2.txt"));
        servthread2.start();

        //Client threads
        Thread clienthread1 = new Thread(new Clientthread(1000,"output1.txt"));
        clienthread1.start();
        Thread clienthread2 = new Thread(new Clientthread(1500,"output2.txt"));
        clienthread2.start();

    }





}
