package controller;


import java.net.URL;
import java.util.ResourceBundle;

import database.DB_Module;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Delete implements Initializable{
	@FXML
	private Button join;
	@FXML
	private Button back;
	@FXML
	private TextField join_user_id;
	@FXML
	private TextField join_user_pass;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		join.setOnMouseClicked(event->{
			String id=join_user_id.getText();
			String pass=join_user_pass.getText();
			DB_Module db=new DB_Module();
			
			boolean res=db.loginMember(id, pass);
			if(res){
				boolean res1=db.deleteMember(id);
				if(res1){
					System.out.println("deleted");
				}else{
					System.out.println("not deleted");
				}
			}else{
				System.out.println("re confirm id and password");
			}
			
			
		});
		back.setOnMouseClicked(event->{
			
			System.out.println("back");
		});
		
	}
	
	
	
}

