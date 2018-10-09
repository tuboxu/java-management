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
	
	 String info="*****欢迎进入用户系统*****\n    "+"1.上传文件\n"+"2.下载文件\n"+
			 "3.文件列表\n"+"4.更改信息\n"+"5.退出\n"+"*******************";
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
		 System.out.println("请输入要更改的密码");
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
	 System.out.println("请输入源文件地址");
String route=get.next();
System.out.println("请输入档案号");
String num=get.next();
System.out.println("请输入档案描述");
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
	System.out.println("上传成功");
}

 }
}
