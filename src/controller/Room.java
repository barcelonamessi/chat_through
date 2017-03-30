package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import custom_class.Conversation;
import network.CustomObject;
import controller.MainGUIController;
import database.DB_Module;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import network.ClientThread;
public class Room implements Initializable{
	@FXML
	private TextArea record;
	@FXML
	private ListView<String> list=new ListView<String>();

	ObservableList<String> items=FXCollections.observableArrayList();

	@FXML
	private TextArea input_text;
	@FXML
	private Button send;
	private Socket socket=null;
	private BufferedReader keyboard=null;
	private PrintWriter out;
	private BufferedReader socket_in=null;
	private ObjectOutputStream oos=null;
	private String cur_id;
	private CustomObject cur_msg;
	Room(String id){
		cur_id=id;
	}
	
	
	
	public String getCur_id() {
		return cur_id;
	}



	public void setCur_id(String cur_id) {
		this.cur_id = cur_id;
	}



	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//items.add(getCur_id());
		//list.setItems(items);
		
        try{ 
            // 1. 서버와 연결 작업을 처리 - socket 생성 
        	//InetAddress ia=InetAddress.getByName("localhost");
        	//System.out.println(ia.toString());
			//soc=new Socket(ia, 12345);
            socket = new Socket("14.32.19.246", 5000); 
            System.out.println("socket here");
            // 2. 서버가 보낸 글을 읽는 ClientThread 객체 생성, 실행 
            ClientThread ct = new ClientThread(socket, record, list); 
            Thread t = new Thread(ct); 
            System.out.println("made thread");
            t.start(); 
            System.out.println("start thread");
            
            // 3. 키보드로 부터 글을 읽고 서버에 전송. 
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            //out = new PrintWriter(socket.getOutputStream(), true);
            oos=new ObjectOutputStream(socket.getOutputStream());
            System.out.println("cur id = " + cur_id);
            
            oos.writeObject(new CustomObject(1, cur_id));
            
            //out.println(cur_id);
            //id전송해줌.
            //socket_in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //enterName(keyboard, out); 
            System.out.println("hello");
            /*
            String str = keyboard.readLine(); 
            while(str!=null){ 
                out.println(str); // 서버 전송 
                str = keyboard.readLine(); // 키보드에서 읽기 
            } 
            */
            
        }catch(IOException ioe){ 
            ioe.printStackTrace(); 
        }
        
        record.textProperty().addListener(new ChangeListener<Object>(){

			@Override
			public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {

				record.setScrollTop(Double.MAX_VALUE);
				
				
			}
        	
        });
        
        
        input_text.setOnKeyPressed(event->{
        	if(event.getCode()==KeyCode.ENTER){
        		String str=cur_id+ " : " +input_text.getText().trim();
        		try {
					oos.writeObject(new CustomObject(1, str));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		//out.println(cur_id+ " : " +str);
    			
    			input_text.setText("");
    			
    			
    			//input_text.setText(input_text.getText().replaceAll("(?!\\r)\\n", "\r\n"));
    			
    			
        	}
        });
        /*finally{ 
            // 4. 스트림과 소켓의 연결을 끊는다. 
            if(keyboard!=null) { 
                try{keyboard.close();}catch(IOException ioe){} 
            } 
            if(out!=null) {
                out.close(); 
            } 
            if(socket!=null) { 
                try{socket.close();}catch(IOException ioe){} 
            } 
        }*/ 
		send.setOnMouseClicked(event->{
			System.out.println("send clicked");
			String str=cur_id+ " : " +input_text.getText().trim();
			try {
				oos.writeObject(new CustomObject(1, str));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//out.println(cur_id+ " : " +str);
			//
			input_text.setText("");
			
			//record.setText(record.getText()+str);
		});
		/*
		delete.setOnMouseClicked(event->{
			System.out.println("delete");
			Stage nextStage=new Stage();
			Parent root=null;
			try{
				root=FXMLLoader.load(getClass().getResource("/application/delete.fxml"));
				Scene scene=new Scene(root, 400, 400);
				
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				nextStage.setScene(scene);
				nextStage.show();
			}catch(IOException ee){
				ee.printStackTrace();
			}
		});
		*/
		
	}
	
	
	
}