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
		String info="*****��ӭ�������Աϵͳ*****\n"+"1.�޸��û�\n"+"2.ɾ���û�\n"+
 "3.�����û�\n"+"4.�г��û�\n"+"5.�����ļ�\n"+"6.�ļ��б�\n"+"7.�޸ģ����ˣ�����\n"+"0.�˳�\n"+"*******************";
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
	 System.out.println("������Ҫ���ĵ�����");
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
		System.out.println("������Ҫ�����ĵ��û���Ϣ");
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
			
			System.out.println("�޸ĳɹ�");
			return true;
		}else
			System.out.println("�޸�ʧ��");
			return false;
	}
	public boolean delUser()
	{
		System.out.println("������Ҫɾ�����û���");
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
			System.out.println("�޸ĳɹ�");
			return true;
		}else
			System.out.println("�޸�ʧ��");
			return false;
		
	}
	public boolean addUser()
	{
		System.out.println("������Ҫ��ӵ��û���Ϣ");
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
			System.out.println("�޸ĳɹ�");
			return true;
		}else
			System.out.println("�޸�ʧ��");
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
