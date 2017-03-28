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
				//ȸ�������ؾ���
				DB_Module db=new DB_Module();
				boolean res=db.registerMember(id, pass);
				if(res){
					System.out.println("���Լ���");
					
				}else{
					System.out.println("���Խ���");
				}
				db.getDm().pooling();
			}else{
				//�˾�â���� �˷���.
				
				
			}
			System.out.println(id);System.out.println(pass);
			System.out.println("joined");
		});
		back.setOnMouseClicked(event->{
			
			System.out.println("back");
		});
		
	}
	
	
	
}

