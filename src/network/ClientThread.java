package network;

import java.net.Socket;
import javafx.scene.control.TextArea; 
import java.io.IOException; 
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
//import javafx.
public class ClientThread implements Runnable
{ 
    Socket socket = null;
    TextArea ta=null;
    String id=null;
    public ClientThread(Socket socket, TextArea ta) { 
        this.socket = socket; 
        this.ta=ta;
    } 
    public void run() { 
        if(socket!=null) { 
            BufferedReader br = null; 
            try{ 
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String str = br.readLine(); 
                while(str!=null) { 
                    System.out.println(str);
                    ta.appendText(str+"\n");
                    str = br.readLine();
                    
                } 
                System.out.println(">>> Client Thread end"); 
            }catch(IOException ioe){ 
    //            ioe.printStackTrace(); 
            }finally{ 
                if(br!=null) { 
                    try{br.close();}catch(IOException ioe){} 
                } 
                if(socket!=null){ 
                    try{socket.close();}catch(IOException ioe){} 
                } 
            } 
        } 
        else { 
            System.out.println("Client Socket is null"); 
        } 
    } 
} 
