package DBUtil;

import java.sql.*;

public class DBUtil {
	public static String URL = "jdbc:mysql://localhost:3306/hospital?serverTimezone=GMT";
	public static String User = "root";
	public static String passwd ="root";
	static Connection conn = null;
	static PreparedStatement stmt = null;
	static ResultSet rs = null;
	public void start() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL,User,passwd);                                                                                                                                         
		}
		catch(Exception e) {
			System.out.println("DBUtil---Start函数出错!");
		}
	}
	
	public ResultSet select(String sql) throws Exception {
		stmt = conn.prepareStatement(sql);
		rs = stmt.executeQuery();
		return rs;
	}
	
	public ResultSet select(String sql,Object...x) throws Exception {
		stmt = conn.prepareStatement(sql);
		if(x!=null) {
			for(int i=0; i<x.length; i++){
				stmt.setObject(i+1 , x[i]);
		    }
		}
		rs = stmt.executeQuery();
		return rs;
	}
	
	public void setSQL(String sql,Object...x) throws Exception{
		stmt = conn.prepareStatement(sql);
		if(x!=null) {
			for(int i=0; i<x.length; i++){
				stmt.setObject(i+1 , x[i]);
		    }
	    }
		stmt.execute();
	}
	
	public void close() throws Exception{
		if(rs!=null)
			rs.close();
		if(stmt!=null)
			stmt.close();
		if(conn!=null)
			conn.close();
	}
}
