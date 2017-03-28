package network;
import java.io.IOException; 
import java.net.ServerSocket; 
import java.net.Socket; 
import java.util.ArrayList; 

public class SimpleServer{ 
    public static final String msgKey = "�˸�) "; 
    //����� Ŭ���̾�Ʈ���� ������ �����ϴ� ArrayList - ��ȭ�����(List) 
    private static ArrayList<ServerThread> list = new ArrayList<ServerThread>(); 
    //private static HashMap<String, ServerThread> map = new HashMap<String, ServerThread>(); 
    //Ŭ���̾�Ʈ�� ��ȭ����Ͽ��� �����ϴ� �޼ҵ� 
    //ServerThread���� Ŭ���̾�Ʈ�� ������ ����� �� ȣ���Ѵ�. 
    public static void removeClient(ServerThread st){ 
        list.remove(st); 
    } 
    //������ Ŭ���̾�Ʈ�� ��ȭ����Ͽ� �߰��ϴ� �޼ҵ� 
    //Ŭ���̾�Ʈ�� ����ɶ� ȣ��ȴ�. 
    public static void addClient(ServerThread st){ 
        list.add(st); 
    } 
    //��ȭ������� ��� Ŭ���̾�Ʈ�鿡�� �μ��� ���� ���� �����ϴ� �޼ҵ� 
    //Ŭ���̾�Ʈ�� ���� �޼���(��)�� ������ ServerThread�� ���� ȣ��ȴ�. 
    public static void broadCasting(String message){ 
        ServerThread st = null; 
        System.out.println("Ŭ�� ���� : " + list.size());
        for(int i=0; i<list.size(); i++) { 
            st = list.get(i); 
            st.sendMessage(message); 
        } 
    } 
    public static String broadCasting(String clientID, String message){ 
        String sendMSG = clientID + ": " + message; 
        ServerThread st = null;
        
        for(int i=0; i<list.size(); i++) { 
            st = list.get(i); 
            st.sendMessage(sendMSG); 
        } 

        return sendMSG; 
    } 
    public static void main(String args[]) {
        ServerSocket ss=null; 
        Socket socket = null; 
        int i=0; 
        try{ 
            System.out.println("<Lee Wan-Geun's Simple Chatting Server>"); 
            //1. server ���ϻ��� 
            ss = new ServerSocket(5000);

            while(true) { 
                //2. Ŭ���̾�Ʈ�� ������ ���� �ݺ��ϸ� ��ٸ���. 
                System.out.println(msgKey + "Ŭ���̾�Ʈ ������ ��� ���Դϴ�."); 
                socket = ss.accept(); 

                //3. Ŭ���̾�Ʈ�� ����Ǹ� ServerThread�� ������ �� Thread�� �����Ų��. 
                ServerThread st = new ServerThread(socket); 
                Thread t = new Thread(st); 
                t.start(); 

                //4. ����� Ŭ���̾�Ʈ�� ����(ServerThread)�� ��ȭ�����(ArrayList)�� �߰� ��Ų��.' 
                // ServerThread���� �߰� 
            } 
        }catch(IOException ioe){ 
            ioe.printStackTrace(); 
        }finally{ 
            if(socket!=null) { 
                try{socket.close();}catch(IOException ioe){} 
            } 
        } 
    }//end of method 
} 
