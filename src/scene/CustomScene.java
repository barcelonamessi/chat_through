package scene;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class CustomScene extends Scene{
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public CustomScene(Parent root, String id) {
		super(root);
		this.id=id;
		// TODO Auto-generated constructor stub
	}

	
	
}
