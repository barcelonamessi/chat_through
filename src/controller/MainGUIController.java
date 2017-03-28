package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ListResourceBundle;
import java.util.ResourceBundle;

import custom_object.Id;
import database.DB_Module;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import scene.CustomScene;


public class MainGUIController implements Initializable{
	@FXML
	private Button login;
	@FXML
	private Button join;
	@FXML
	private Button change;
	@FXML
	private Button delete;
	@FXML
	private TextField user_id;
	@FXML
	private TextField user_pass;
	
	private String id;
	
	Id cur_id=new Id();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public class MyResources extends ListResourceBundle {
		
		
	     protected Object[][] getContents() {
	         return new Object[][] {
	             // LOCALIZE THE SECOND STRING OF EACH ARRAY (e.g., "OK")
	             {"OkKey", "OK"},
	             {"CancelKey", "Cancel"},
	             // END OF MATERIAL TO LOCALIZE
	        };
	     }
	 }
	public void setUser(String id){
		this.id=id;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//ResourceBundle myResources=ResourceBundle.getBundle("); 
		//MyResources my=new MyResources();
		
		login.setOnMouseClicked(event->{
			String id=user_id.getText();
			
			String pass=user_pass.getText();
			
			DB_Module db=new DB_Module();
			boolean res=db.loginMember(id, pass);
			if(res){
				System.out.println("logged in");
				cur_id.setId(id);
				Stage nextStage=new Stage();
				Parent root=null;
				
				try {
					
					//myResources=ResourceBundle.getBundle("id", );
					//Controller custom=new Controller("")
					root=FXMLLoader.load(getClass().getResource("/room/room.fxml"));
					CustomScene scene=new CustomScene(root, id);
					//Scene scene=new Scene(root);
					root.setUserData(id);
					scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
					
					nextStage.setScene(scene);
					nextStage.show();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
				
			}else{
				System.out.println("login failed");
			}
			db.getDm().pooling();
			
		});
		join.setOnMouseClicked(event->{
			
			System.out.println("join");
			
			Stage nextStage=new Stage();
			Parent root=null;
			try {
				root=FXMLLoader.load(getClass().getResource("/application/join.fxml"));
				Scene scene=new Scene(root, 400, 400);
				
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				nextStage.setScene(scene);
				nextStage.show();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			
		});
		change.setOnMouseClicked(event->{
			System.out.println("change");
			Stage nextStage=new Stage();
			Parent root=null;
			try{
				root=FXMLLoader.load(getClass().getResource("/application/change.fxml"));
				Scene scene=new Scene(root, 400, 400);
				
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				nextStage.setScene(scene);
				nextStage.show();
			}catch(IOException ee){
				ee.printStackTrace();
			}
		});
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
		
	}
	
	
	
}
