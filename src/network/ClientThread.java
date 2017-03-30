package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Vector;

import network.CustomObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea; 
//import javafx.
public class ClientThread implements Runnable
{ 
    Socket socket = null;
    TextArea ta=null;
    String id=null;
    ListView<String> list=null;
    ObservableList<String> items=null;
    Vector<String> current_id=null;
    public ClientThread(Socket socket, TextArea ta, ListView<String> list) { 
        this.socket = socket; 
        this.ta=ta;
        this.list=list;
        this.items=FXCollections.observableArrayList();
        list.setItems(items);
        current_id=new Vector<String>();
        
    } 
    public void run() { 
        if(socket!=null) { 
            //BufferedReader br = null; 
            ObjectInputStream ois=null;
            //DataInputStream dis=null;
            //DataOutputStream dos=null;
            try{ 
              //  br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ois=new ObjectInputStream(socket.getInputStream());
                CustomObject cur_obj=null;
                
                
				try {
					cur_obj=(CustomObject)ois.readObject();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
                while(cur_obj!=null){
                	if(cur_obj.version==1){
                		String str=cur_obj.text;
                		ta.appendText(str+"\n");
                	}else if(cur_obj.version==2){
                		System.out.println(id + "new client");
                		System.out.println(cur_obj.text);
                		current_id.add(cur_obj.text);
                		items=FXCollections.observableList(current_id);
                		//items.add(cur_obj.text);
                		list.setItems(items);
                		
                	}else if(cur_obj.version==3){
                		//items.remove(cur_obj.text);
                		
                		System.out.println("removed id : " + cur_obj.text);
                		current_id.removeElement(cur_obj.text);
                		items=FXCollections.observableList(current_id);
                		list.setItems(items);
                	}else if(cur_obj.version==4){
                		
                		Vector<String> cur_list=cur_obj.userlist;
                		for(int i=0; i<cur_list.size(); i++){
                			current_id.add(cur_list.get(i));
                		}
                		items=FXCollections.observableList(current_id);
                		list.setItems(items);
                		
                	}else{
                		
                	}
                	try {
						cur_obj=(CustomObject)ois.readObject();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
                }
                /*
                if(cur_obj.version==1){
                String str = br.readLine(); 
                while(str!=null) { 
                    System.out.println(str);
                    ta.appendText(str+"\n");
                    str = br.readLine();
                    
                } 
                */
                System.out.println(">>> Client Thread end"); 
            }catch(IOException ioe){ 
    //            ioe.printStackTrace(); 
            }finally{ 
                if(ois!=null) { 
                    try{ois.close();}catch(IOException ioe){} 
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
