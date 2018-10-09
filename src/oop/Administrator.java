package oop;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Enumeration;
public class Administrator extends User{

public Administrator(String na,String pa,String ro)
    {
	   super(na,pa,ro);
   
    }
public Administrator(User use)
{
	super(use.getName(),use.getPassword(),use.getRole());
}
	public void showMenu() throws Exception
 {
		int index;
		String info="*****欢迎进入管理员系统*****\n"+"1.修改用户\n"+"2.删除用户\n"+
 "3.新增用户\n"+"4.列出用户\n"+"5.下载文件\n"+"6.文件列表\n"+"7.修改（本人）密码\n"+"0.退出\n"+"*******************";
do{
		System.out.println(info);
Scanner scan=new Scanner(System.in);
index=scan.nextInt();
switch(index)
{
case 1:
	changeUserInfo();break;
case 2:
	delUser();break;
case 3:
	addUser();break;
case 4:
	listUser();break;
case 5:
	
	 try {
		downloadFile();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
		continue;
	}
	 break;
case 6:
	 try {
		showFileList();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());

	}
	 break;
case 7:
	 System.out.println("请输入要更改的密码");
	 String password=scan.next();
	 try {
		changeSelfInfo(password);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
		}
	 break;
case 0:
	 exitSystem();
	 break;
}
}while(index!=0);
 }
	public boolean changeUserInfo() throws Exception
	{
		System.out.println("请输入要跟更改的用户信息");
		Scanner scan=new Scanner(System.in);
		String na=scan.next();
		String pa=scan.next();
		String ro=scan.next();
		boolean result = false;
		try {
			result = DataProcessing.updateUser(na,pa,ro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		if (result==true){
			
			System.out.println("修改成功");
			return true;
		}else
			System.out.println("修改失败");
			return false;
	}
	public boolean delUser()
	{
		System.out.println("请输入要删除的用户名");
		Scanner scan=new Scanner(System.in);
		String na=scan.next();
		boolean result = false;
		try {
			result = DataProcessing.deleteUser(na);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		if (result==true){
			System.out.println("修改成功");
			return true;
		}else
			System.out.println("修改失败");
			return false;
		
	}
	public boolean addUser()
	{
		System.out.println("请输入要添加的用户信息");
		Scanner scan=new Scanner(System.in);
		String na=scan.next();
		String pa=scan.next();
		String ro=scan.next();
		boolean result = false;
		try {
			result = DataProcessing.insertUser(na, pa, ro);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		if (result==true){
			System.out.println("修改成功");
			return true;
		}else
			System.out.println("修改失败");
			return false;
	}
	public void listUser() throws Exception
	{
		ResultSet p;
		p=DataProcessing.getAllUser();
		while(p.next())
		{
		System.out.println("Name:"+p.getString("username")+"       Password:"
				+p.getString("password")+"       Role:"+p.getString("role"));
		}
		
	}
}
