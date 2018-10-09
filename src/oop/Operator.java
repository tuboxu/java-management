package oop;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Operator extends User {
 public Operator(String na,String pa,String ro) {
	super(na,pa,ro);
   }
 public void showMenu() throws Exception
 {
	
	 String info="*****��ӭ�����û�ϵͳ*****\n    "+"1.�ϴ��ļ�\n"+"2.�����ļ�\n"+
			 "3.�ļ��б�\n"+"4.������Ϣ\n"+"5.�˳�\n"+"*******************";
	 System.out.println(info);
	 Scanner scan=new Scanner(System.in);
	 int index=scan.nextInt();
	 switch(index)
	 {
	 case 1:
		 uploadFile();break;
	 case 2:
		 
		 try {
			downloadFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		 break;
	 case 3:
		 try {
			showFileList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		 break;
	 case 4:
		 System.out.println("������Ҫ���ĵ�����");
		 String password=scan.next();
		 try {
			changeSelfInfo(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		 break;
	 case 5:
		 exitSystem();
		 break;
	 }
 }
 public void uploadFile() throws Exception
 {
	 Scanner get=new Scanner(System.in);
	 System.out.println("������Դ�ļ���ַ");
String route=get.next();
System.out.println("�����뵵����");
String num=get.next();
System.out.println("�����뵵������");
String des=get.next();
Timestamp timestamp = new Timestamp(System.currentTimeMillis());
int b=route.lastIndexOf("\\");
String name=route.substring(b+1);
if(DataProcessing.insertDoc(num, this.getName(), timestamp, des, name))
{
	File a=new File(route);
	FileReader read=new FileReader(a);
	File b1=new File("e:\\uploadfile\\",name);
	FileWriter write=new FileWriter(b1);
	int in=read.read();
	while(in!=-1)
	{
		write.write(in);
		in=read.read();
	}
	read.close();
	write.close();	
	System.out.println("�ϴ��ɹ�");
}

 }
}
