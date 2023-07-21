


import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

/**
 * the class is the main porgram for player
 * it connect the server and start the game
 */

public class ClientMain
{
    public static void main(String[] args)
    {
        Socket server = null;
        try
        {
            server = new Socket("localhost", 10081);
            GameWindow window = new GameWindow(server);  // start game
        }
        catch(UnknownHostException e) // cannot find server
        {
            System.out.println("cannot find the server");
            JOptionPane.showMessageDialog(null,"cannot find server");
        }
        catch(IOException e) // cannot connect server
        {
            System.out.println("cannot get the message");
            JOptionPane.showMessageDialog(null,"connect fail");
        }


    }
}
