package network;

import java.net.*; 
import java.io.*; 

public class SimpleClient {
	/*
    public static String enterName(BufferedReader br, PrintWriter out) {
        String str = null; 
        try{ 
            System.out.print("��ȭ�� �Է�: "); 
            str = br.readLine(); 
            out.println(str); 
        }catch(IOException ioe) { 
            ioe.printStackTrace(); 
        } 
        return str; 
    }
    public static void main(String[] args) {
        Socket socket = null; 
        BufferedReader keyboard = null; 
        PrintWriter out = null; 

        try{ 
            // 1. ������ ���� �۾��� ó�� - socket ���� 
            socket = new Socket("127.0.0.1", 5000); 

            // 2. ������ ���� ���� �д� ClientThread ��ü ����, ���� 
            ClientThread ct = new ClientThread(socket); 
            Thread t = new Thread(ct); 
            t.start(); 

            // 3. Ű����� ���� ���� �а� ������ ����. 
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(socket.getOutputStream(), true);
            enterName(keyboard, out); 
            String str = keyboard.readLine(); 
            while(str!=null){ 
                out.println(str); // ���� ���� 
                str = keyboard.readLine(); // Ű���忡�� �б� 
            } 
        }catch(IOException ioe){ 
            ioe.printStackTrace(); 
        }finally{ 
            // 4. ��Ʈ���� ������ ������ ���´�. 
            if(keyboard!=null) { 
                try{keyboard.close();}catch(IOException ioe){} 
            } 
            if(out!=null) {
                out.close(); 
            } 
            if(socket!=null) { 
                try{socket.close();}catch(IOException ioe){} 
            } 
        } 
    } 
    */
} 