package custom_class;
import java.io.*;
import java.sql.Timestamp;
public class Conversation implements Serializable{

	private static final long serialVersionUID = 1L;
	public String id;
	public Timestamp time;
	public String text;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
	
	
}
