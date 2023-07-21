

import java.net.*;
import java.io.*;

/**
 * this class is the task for the client thread
 * each thread will do this task
 * read from the client and sent message to client or other client base on the message
 */

public class ClientTask implements Runnable
{
    private Socket client;
    private Socket other;
    private ServerSocket server;
    private int randomNum;        // this is the random number of the card  which is loose
    private boolean startFirst;   // is the client start first

    /**
     * the constractor of the class set up the variable that may need
     * @param server the server side
     * @param client the target client who will be read the message from
     * @param other  the other client that may need to sent the message to
     * @param number the random number the made by the server
     * @param start is the target client should start first
     */
    ClientTask(ServerSocket server, Socket client, Socket other,int number,boolean start)
    {
        this.client = client;
        this.server = server;
        this.other = other;
        randomNum = number;
        this.startFirst = start;
    }

    /**
     * the method override the method in Runnable
     * the method is the task that each thread need to do
     */
    public void run()
    {
        InputStream input = null;
        OutputStream output = null;
        InputStream otherInput = null;
        OutputStream otherOutput = null;

        InputStreamReader reader = null;
        OutputStreamWriter writer = null;
        InputStreamReader otherReader = null;
        OutputStreamWriter otherWriter = null;
        char[] message = new char[20];  // the array to contain the message from inputStream
        int readNum = 0;  // how many character read from the inputStream


        try
        {
            input = client.getInputStream();
            output = client.getOutputStream();
            otherInput = client.getInputStream();
            otherOutput = other.getOutputStream();

            reader = new InputStreamReader(input);
            writer = new OutputStreamWriter(output);
            otherReader = new InputStreamReader(otherInput);
            otherWriter = new OutputStreamWriter(otherOutput);
        }
        catch(IOException e)
        {
            System.out.println("cannot get input or output");
        }

        try
        {
            // if the client should start fist tell them

            while(true)
            {
                try
                {
                    String str = null; // the message read from client

                    readNum = reader.read(message);
                    str = new String(message,0,readNum);  // read from char array
                    String[] mgs = str.split("~");  // ~ mean end of each message
                    // use split because it may read 2 message at a time

                    for(int i = 0; i < mgs.length; i++)  // each message due once
                    {
                        System.out.println("the String is " +  mgs[i]);
                        control(mgs[i],writer,otherWriter,otherReader);
                    }

                }
                catch(IOException e)
                {
                    throw e;  // can trace the program and exit while loop
                }
                catch(StringIndexOutOfBoundsException e)
                {
                    System.out.println("read fail");
                    throw e;
                }
            }
        }
        catch (IOException e)  // if the client exit the program
        {
            System.out.println("exiting");
            try
            {
                otherWriter.write("WIN~");
                otherWriter.flush();
            }
            catch(IOException ex)
            {
                System.out.println("other socket close");
            }

        }
        catch(StringIndexOutOfBoundsException e)  // from reading fail
        {
            System.out.println("exiting");
        }
        finally
        {
            try
            {
                closeAll(input,output,reader,writer);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }

    }



    ///////////////////////////////////////////////////////////////////////

    /**
     * the method close all the input stream and out put stream
     * @param input the input stream
     * @param output the output stream
     * @param reader the reader
     * @param writer the writer
     * @throws IOException the exception that will throw because the stream may not there
     */
    public void closeAll(InputStream input, OutputStream output, InputStreamReader reader, OutputStreamWriter writer)
            throws IOException
    {
        if(output != null)
            input.close();

        if(output != null)
            output.close();

        if(reader != null)
            input.close();

        if(writer != null)
            input.close();

    }


    ///////////////////////////////////////////////////////////////////////

    /**
     * the method is the guide that when the server get message from client what is the next step it need to do
     * base on the message it get it will sent other message to the target client or the other client
     * @param str the message get from client
     * @param writer the writer to the client
     * @param otherWriter the writer to other client
     * @param otherReader the reader from other client
     * @throws IOException
     */
    public void control(String str, OutputStreamWriter writer, OutputStreamWriter otherWriter, InputStreamReader otherReader) throws IOException
    {
        String[] mgs = str.split(" "); // the message may contain a space

        try
        {
            switch(mgs[0])
            {
                case "RANDOM" : writer.write("RANDOM " + randomNum + "~"); // get the random to client
                                break;

                 //when server get this message the server ask the other client "is it your term"
                case "WHOTERM" : otherWriter.write("YOURTERM~");
                                 otherWriter.flush();
                                 break;

                // when the server know the the client term tell other client do not start the term
                case "MYTERM" : otherWriter.write("NOTSTART~");
                                break;

                //when the server know is the the client term tell other client start its term
                case "NOTMINE" : otherWriter.write("START~");
                                 break;

                //the server will pass the message to other client
                case "CARD" : otherWriter.write(str + "~");
                              break;

                //server know the client term is end tell other client to start
                case "END" : otherWriter.write("START~");
                             break;

                //server know the client is loose tell other client that it is the winner
                case "LOOSE" : otherWriter.write("WIN~");
                               break;

                //the server know sclient is connect respond that to the client that server is connect
                case "READY" : writer.write("CONNECT~");  // tell the player are connect
                              writer.flush();
//                              Thread.sleep(100);
//                              System.out.println("flushed");

                                if(startFirst) // if is it term to start
                                    writer.write("START~");
                                 else
                                     writer.write("NOTSTART~");

                                writer.flush();
                                break;

                default : System.out.println("cannot read this message: " + str); break;
            }  // end switch
        }  // end try

        finally
        {
            writer.flush();
            otherWriter.flush();
        }

    }  //end method


}  // end class
