package database;
import java.sql.*;
public class DB_Module {
	DB_Pool dm;
	DB_Connection dc;
	public DB_Module(){
		dm=DB_Pool.getInstance(false);
		dc=dm.getDbConn();
		
	}
	public DB_Pool getDm() {
		return dm;
	}
	public void setDm(DB_Pool dm) {
		this.dm = dm;
	}
	public DB_Connection getDc() {
		return dc;
	}
	public void setDc(DB_Connection dc) {
		this.dc = dc;
	}
	public boolean showMember(){
		String query="select * from user";
		try{
			Statement stmt=dc.getStmt();
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()){
				System.out.println("id : " +rs.getString(1));
				
			}
		}catch(SQLException ee){
			return false;
		}
		return true;
	}
	public boolean registerMember(String id, String pass){
		
		String query="insert into user values (?, ?)";
		try{
			PreparedStatement pstmt=dc.getPstmt(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.executeUpdate();
		}catch(SQLException ee){
			System.err.println("회원 가입 실패");
			return false;
		}
		return true;
		
	}
	public boolean loginMember(String id, String pass){
		
		String query="select * from user where id=? and password=?";
		try{
			PreparedStatement pstmt=dc.getPstmt(query);
			
			pstmt.setString(1,  id);
			pstmt.setString(2, pass);
			ResultSet rs=pstmt.executeQuery();
			if(!rs.next()){
				rs.close();
				pstmt.close();
				return false;
			}
			rs.close();
			pstmt.close();
		}catch(SQLException ee){
			System.err.println("로그인 실패");
			return false;
		}
		
		return true;
	}
	
	public boolean editMember(String id, String pass){
		String query="update user set password=? where id=?";
		try{
			PreparedStatement pstmt=dc.getPstmt(query);
			pstmt.setString(1, pass);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(SQLException ee){
			System.err.println("회원 정보 수정 실패");
			return false;
		}
		return true;
		
	}
	public boolean deleteMember(String id){
		String query="delete from user where id=?";
		try{
			PreparedStatement pstmt=dc.getPstmt(query);
			pstmt.setString(1,  id);
			pstmt.executeUpdate();
			pstmt.close();
		}catch(SQLException ee){
			System.err.println("회원 삭제 실패");
			return false;
		}
		return true;
		
	}
}
