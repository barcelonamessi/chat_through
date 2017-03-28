package controller;


import java.net.URL;
import java.util.ResourceBundle;

import database.DB_Module;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;

public class Join implements Initializable{
	@FXML
	private Button join;
	@FXML
	private Button back;
	@FXML
	private TextField join_user_id;
	@FXML
	private TextField join_user_pass;
	@FXML
	private TextField join_user_re_pass;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		join.setOnMouseClicked(event->{
			String id=join_user_id.getText();
			String pass=join_user_pass.getText();
			String re_pass=join_user_re_pass.getText();
			if(pass.equals(re_pass)){
				//회원가입해야함
				DB_Module db=new DB_Module();
				boolean res=db.registerMember(id, pass);
				if(res){
					System.out.println("가입성공");
					
				}else{
					System.out.println("가입실패");
				}
				db.getDm().pooling();
			}else{
				//팝업창으로 알려줌.
				
				
			}
			System.out.println(id);System.out.println(pass);
			System.out.println("joined");
		});
		back.setOnMouseClicked(event->{
			
			System.out.println("back");
		});
		
	}
	
	
	
}

