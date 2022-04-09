package UserDao;

import java.sql.*;
import java.util.ArrayList;

import DBUtil.DBUtil;

public class hospitalDao {
	
	DBUtil dbu = new DBUtil();
	
	public void load() {
		dbu.start();
	}
	
	private String sex(int a) {
		if(a == 1)
			return "男";
		return "女";
	}
	
	private String iw_name(int a) throws Exception {
		String sql = "select * from inpatient_ward where ino=?";
		ResultSet rs;
		String name;
		rs = dbu.select(sql,a);
		rs.next();
		name = rs.getString("iname");
		rs.close();
		return name;
	}
	
	private String iw_location(int a) throws Exception {
		String sql = "select * from inpatient_ward where ino=?";
		ResultSet rs;
		String location;
		rs = dbu.select(sql,a);
		rs.next();
		location = rs.getString("location");
		rs.close();
		return location;
	}
	
	
	public void select(int pno) throws Exception {
		String sql = "SELECT * FROM patient where pno=?;";
		ResultSet rs;
		rs = dbu.select(sql,pno);
		if(rs.next()) {
			System.out.println("编号  姓名        性别    年龄    房名            病房       主治医师");
			 do{
				if(rs.getString("pname").length()<3)
					System.out.println(rs.getInt("pno")+"\t"+rs.getString("pname")+"  \t"+sex(rs.getInt("sex"))
			        +"       "+rs.getInt("age")+"       "+iw_name(rs.getInt("ino"))+"\t"+iw_location(rs.getInt("ino"))
					+"     "+rs.getString("doctor"));
				else
					System.out.println(rs.getInt("pno")+"\t"+rs.getString("pname")+"\t"+sex(rs.getInt("sex"))
			        +"       "+rs.getInt("age")+"       "+iw_name(rs.getInt("ino"))+"\t"+iw_location(rs.getInt("ino"))
					+"     "+rs.getString("doctor"));
				}while(rs.next());
		}
		else {
			System.out.println("未查询到此人!");
		}
		
		rs.close();
	}
	
	public void select(String pname) throws Exception {
		String sql = "SELECT * FROM patient where pname=?;";
		ResultSet rs;
		rs = dbu.select(sql,pname);
		if(rs.next()) {
			System.out.println("编号  姓名        性别    年龄    房名            病房       主治医师");
			 do{
				if(rs.getString("pname").length()<3)
					System.out.println(rs.getInt("pno")+"\t"+rs.getString("pname")+"  \t"+sex(rs.getInt("sex"))
			        +"       "+rs.getInt("age")+"       "+iw_name(rs.getInt("ino"))+"\t"+iw_location(rs.getInt("ino"))
					+"     "+rs.getString("doctor"));
				else
					System.out.println(rs.getInt("pno")+"\t"+rs.getString("pname")+"\t"+sex(rs.getInt("sex"))
			        +"       "+rs.getInt("age")+"       "+iw_name(rs.getInt("ino"))+"\t"+iw_location(rs.getInt("ino"))
					+"     "+rs.getString("doctor"));
				}while(rs.next());
		}
		else {
			System.out.println("未查询到此人!");
		}
		rs.close();
	}
	
	public ArrayList<String> deptName() throws Exception {
		String sql = "SELECT * FROM inpatient_ward";
		ResultSet rs;
		ArrayList<String> name= new ArrayList<>();
		rs=dbu.select(sql);
		while(rs.next()) {
			name.add(rs.getString("iname"));
		}
		rs.close();
		return name;
	}
	
	public void select() throws Exception {
		String sql = "SELECT * FROM patient;";
		ResultSet rs;
		rs = dbu.select(sql);
		if(rs.next()) {
			System.out.println("编号  姓名        性别    年龄    房名            病房       主治医师");
			 do{
				if(rs.getString("pname").length()<3)
					System.out.println(rs.getInt("pno")+"\t"+rs.getString("pname")+"  \t"+sex(rs.getInt("sex"))
			        +"       "+rs.getInt("age")+"       "+iw_name(rs.getInt("ino"))+"\t"+iw_location(rs.getInt("ino"))
					+"     "+rs.getString("doctor"));
				else
					System.out.println(rs.getInt("pno")+"\t"+rs.getString("pname")+"\t"+sex(rs.getInt("sex"))
			        +"       "+rs.getInt("age")+"       "+iw_name(rs.getInt("ino"))+"\t"+iw_location(rs.getInt("ino"))
					+"     "+rs.getString("doctor"));
				}while(rs.next());
		}
		else {
			System.out.println("没有数据!");
		}
		rs.close();
	}
	
	public void addData(String name,int sex,int age,int ino,String doctor) throws Exception {
		String sql = "insert into patient(pname,sex,age,ino,doctor) values(?,?,?,?,?);";
		dbu.setSQL(sql,name,sex,age,ino,doctor);
	}
	
	public void delData(int pno) throws Exception {
		String sql = "DELETE FROM patient WHERE pno=?;";
		dbu.setSQL(sql,pno);
	}
	
	public void updateData(int pno,String name,int sex,int age,int ino,String doctor) throws Exception {
		String sql="UPDATE patient SET pname=?,sex=?,age=?,ino=?,doctor=? WHERE pno=?;";
		dbu.setSQL(sql,name,sex,age,ino,doctor,pno);
	}
	
	public void addInpatient(String iname,String location) throws Exception {
		String sql = "insert into inpatient_ward(iname,location) values(?,?);";
		dbu.setSQL(sql,iname,location);
	}
	
	public void delInpatient(int ino) throws Exception {
		String sql = "DELETE FROM inpatient_ward WHERE ino=?;";
		dbu.setSQL(sql,ino);
	}
	
	
	
	public void updateInpatient(int pno,String name,String location) throws Exception {
		String sql="UPDATE inpatient_ward SET iname=?,location=? WHERE ino=?;";
		dbu.setSQL(sql,name,location);
	}
	
	public void close() throws Exception {
		dbu.close();
	}
}
