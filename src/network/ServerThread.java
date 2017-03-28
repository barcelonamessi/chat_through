package network;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.io.OutputStream; 
import java.io.PrintWriter; 
import java.net.Socket; 

public class ServerThread implements Runnable{
    private String clientID = null;
    private Socket socket = null;
    private BufferedReader br = null;
    private PrintWriter out = null;
    public ServerThread(Socket socket){ 
        this.socket = socket; 
    } 
    public void joinClient() {
        try{ 
            clientID = br.readLine(); 
            if(clientID==null||clientID.equals(" ")) { 
                clientID = "�մ�_" + Math.random(); 
            } 
        }catch(IOException ioe) { 
        }finally{ 
            SimpleServer.addClient(this); 

            String msg = SimpleServer.msgKey + clientID + "���� �����ϼ̽��ϴ�."; 
            SimpleServer.broadCasting(msg); 
            System.out.println(msg); 
        } 
    } 
    public void exitClient() {
        SimpleServer.removeClient(this); 

        String msg = SimpleServer.msgKey + clientID + "���� �����ϼ̽��ϴ�."; 
        SimpleServer.broadCasting(msg); 
        System.out.println(msg); 
    } 
    //Ŭ���̾�Ʈ�� ����� ��½�Ʈ���� ���� ����ϴ� �޼ҵ� 
    public void sendMessage(String message){ 
        out.println(message); 
    } 
    public void run(){         
        try{ 
            //1. ������ ���� ��Ʈ�� ����(Input, Output) 
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            joinClient(); 

            //2. Ŭ���̾�Ʈ�� ���� ���� �о� ���δ�. 
            String str = br.readLine(); 
            String strResult = null; 
            while(str!=null) { 
                //3. Ŭ���̾�Ʈ�� ���� ���� �ִ� ���� SimpleServer�� broadCasting()�޼ҵ带 ���� ����� ��� Ŭ���̾�Ʈ�鿡�� ���� �����Ѵ�. 
                //strResult = SimpleServer.broadCasting(clientID, str);
            	System.out.print(str);
                SimpleServer.broadCasting(str);
                //System.out.println(strResult); 
                str = br.readLine(); 
            } 
        }catch (IOException e) { 
//                e.printStackTrace(); 
        }finally{ 
            //4. io, socket ������ �����Ѵ�. 
            if(br!=null) { 
                try{br.close();}catch(IOException ioe){} 
            } 
            if(out!=null) {
                out.close(); 
            } 
            if(socket!=null) { 
                try{socket.close();}catch(IOException ioe){} 
            } 

            //5. SimpleServer�� removeClient() �� ���� �ڱ� �ڽ��� ��ȭ��󿡼� ���� �Ѵ�. 
            exitClient(); 

        }//end of finally 
    } 
} 

