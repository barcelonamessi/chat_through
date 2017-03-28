package database;
import java.util.*;
import java.sql.*;
import java.io.*;
public class DB_Pool {
	private static Vector db_conn;
	private static Vector db_status;
	private static Vector db_bool;
	static{
		if(db_conn==null || db_status==null || db_bool ==null){
			db_conn=new Vector();
			db_status=new Vector();
			db_bool=new Vector();
		}
	}
	
	private int pos=-1;
	public DB_Pool(boolean bool){
		pos=-1;
		for(int i=0; i<db_bool.size(); i++){
			if(db_bool.elementAt(i).equals(String.valueOf(bool))){
				if(db_status.elementAt(i).equals(String.valueOf("0"))){
					pos=i;
					break;
				}
			}
		}
		if(pos==-1){
			DB_Connection dc=DB_Connection.getInstance(bool);
			db_conn.add(dc);
			db_status.add("0");
			db_bool.add(String.valueOf(bool));
			pos=db_conn.size()-1;
		}
	}
	public static DB_Pool getInstance(boolean bool){
		return new DB_Pool(bool);
	}
	public DB_Connection getDbConn(){
		DB_Pool.db_status.setElementAt("1", pos);
		return (DB_Connection)DB_Pool.db_conn.elementAt(pos);
	}
	public void pooling(){
		db_bool.setElementAt("0", pos);
	}
}
