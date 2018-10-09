package oop;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public  class User {
	private String name;
	private String password;
	private String role;
	
	User(String name,String password,String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	
	public boolean changeSelfInfo(String password) {
		//д�û���Ϣ���洢
		try {
			if (DataProcessing.updateUser(name, password, role)){
				this.password=password;
				System.out.println("�޸ĳɹ�");
				return true;
			}else
				return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean downloadFile() throws Exception{
		
		System.out.println("������Ҫ�����ļ�ID");
		Scanner scan=new Scanner(System.in);
		String fileID=scan.next();
		
		Doc afile=DataProcessing.searchDoc(fileID);
		if(afile!=null)
		{
			File src=new File("e:\\uploadfile\\",afile.getFilename());
			FileReader read=new FileReader(src);
			File sou=new File("e:\\downloadfile\\",afile.getFilename());
			
			FileWriter write=new FileWriter(sou);
			int in=read.read();
			while(in!=-1)
			{
				write.write(in);
				in=read.read();
			}
			read.close();
			write.close();
			System.out.println("�����ļ��ɹ�");
		}
		return true;
	}
	
	public void showFileList() throws SQLException{
		
		
		System.out.println("�б�... ...");
		ResultSet   p;
		Doc doc;
		p=DataProcessing.getAllDocs();
		while(p.next()){
			String time;
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			time=sdf.format(p.getString("timestamp"));
			System.out.println("�ļ������ţ�"+p.getString("Id")+"\t�����ߣ�"+p.getString("creator")+"\tʱ�䣺"+time
					+"\t�ļ�������"+p.getString("description")+"\t�ļ�����"+p.getString("filename"));
		}
	}
	
	public  void showMenu()throws Exception{} ;
	
	public void exitSystem(){
		System.out.println("ϵͳ�˳�, ллʹ�� ! ");
		System.exit(0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

}

