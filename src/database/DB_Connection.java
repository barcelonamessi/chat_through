package database;
import java.sql.*;
import java.util.*;
import java.io.*;

public class DB_Connection {
	private Connection conn;
	private static PrintWriter log_out;
	static{
		try{
			log_out=new PrintWriter(new BufferedWriter(new FileWriter(new File("debug.log"), true)));
		}catch(IOException ee){}
	}
	public Connection getConn(){return conn;}
	
	private DB_Connection(boolean local){
		String driver="", url="", id="", pass="";
		if(local){//local에서 실행
			BufferedReader in=null;
			try{
				in=new BufferedReader(new FileReader(new File("db.property")));
				
				driver=in.readLine();
				url=in.readLine();
				id=in.readLine();
				pass=in.readLine();
				in.close();
				log_out.println(new java.util.Date() + " : db.property read!\n");
				log_out.flush();
			}catch(IOException ee){
				log_out.println(new java.util.Date() + " : db.property Error = " + ee.toString() + "\n");
				log_out.flush();
			}
		}else{
			//driver="org.gjt.mm.mysql.Driver";
			driver="com.mysql.jdbc.Driver";
			url="jdbc:mysql://14.32.19.246:3307/chat?autoReconnect=true&useSSL=false";
			
			id="root";
			pass="1234";
			log_out.println(new java.util.Date() + " : auto set success!\n");
			log_out.flush();
		}
		try{
			Class.forName(driver);
			log_out.println(new java.util.Date() + " : Driver Search Success!\n");
			log_out.flush();
		}catch(ClassNotFoundException ee){
			System.err.println(ee.toString());
			log_out.println(new java.util.Date() + " : Driver Search Fail = " + ee.toString() + "\n");
			log_out.flush();
		}
		try{
			conn=DriverManager.getConnection(url, id, pass);
			log_out.println(new java.util.Date() + " : Conn Create Success!\n");
			log_out.flush();
		}catch(SQLException ee){
			System.err.println(ee.toString());
			log_out.println(new java.util.Date() + " :  Conn Create Fail = " + ee.toString() + "\n");
			log_out.flush();
		}
	}
	
	public static DB_Connection getInstance(boolean bool){
		log_out.println(new java.util.Date() + " : DbConn Instance Create!\n");
		log_out.flush();
		return new DB_Connection(bool);
	}
	public void close(){
		if(conn!=null){
			try{
				if(!conn.isClosed()){
					conn.close();
					log_out.println(new java.util.Date()+ " : conn Close Success!\n");
					log_out.flush();
				}
			}catch(SQLException ee){
				log_out.println(new java.util.Date() + " : conn Close Fail = " + ee.toString() + "\n");
				log_out.flush();
			}
			conn=null;
		}
	}
	public Statement getStmt(){
		Statement stmt=null;
		try{
			stmt=conn.createStatement();
			log_out.println(new java.util.Date() + " : Statement Create success!\n");
			log_out.flush();
		}catch(SQLException ee){
			log_out.println(new java.util.Date() + " : Statement Create Fail = " + ee.toString()+ "\n");
			log_out.flush();
		}
		return stmt;
	}
	public PreparedStatement getPstmt(String query){
		PreparedStatement pstmt=null;
		try{
			pstmt=conn.prepareStatement(query);
			log_out.println(new java.util.Date() + " : PreparedStatement Create success!\n");
			log_out.flush();
		}catch(SQLException ee){
			log_out.println(new java.util.Date() + " : PreparedStatement Create Fail = " + ee.toString()+ "\n");
			log_out.flush();
		}
		return pstmt;
	}
	public CallableStatement getCall(String query){
		CallableStatement call=null;
		try{
			call=conn.prepareCall(query);
			log_out.println(new java.util.Date() + " : CallableStatement Create success!\n");
			log_out.flush();
		}catch(SQLException ee){
			log_out.println(new java.util.Date() + " : CallableStatement Create Fail = " + ee.toString()+ "\n");
			log_out.flush();
		}
		return call;
	}

}
