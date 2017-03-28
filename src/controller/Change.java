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

public class Change implements Initializable{
	@FXML
	private Button join;
	@FXML
	private Button back;
	@FXML
	private TextField user_id;
	@FXML
	private TextField before_user_pass;
	@FXML
	private TextField pass_next;
	@FXML
	private TextField pass_next_confirm;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		join.setOnMouseClicked(event->{
			String id=user_id.getText();
			String before_pass=before_user_pass.getText();
			String re_pass=pass_next.getText();
			String re_pass_confirm=pass_next_confirm.getText();
			DB_Module db=new DB_Module();
			boolean res=db.loginMember(id, before_pass);
			if(res){
				if(re_pass.equals(re_pass_confirm)){
					boolean res1=db.editMember(id, re_pass);
					if(res1){
						System.out.println("changed new password");
					}else{
						System.out.println("not changed");
					}
				}else{
					System.out.println("re confirm new password");
				}
			}else{
				System.out.println("아이디와 비밀번호 재확인 필요");
				
			}
			db.getDm().pooling();
			
			
		});
		back.setOnMouseClicked(event->{
			
			System.out.println("back");
		});
		
	}
	
	
	
}

