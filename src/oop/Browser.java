package oop;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Browser extends User {
public Browser(String na,String pa,String ro)
 {
super(na,pa,ro);	
 }
public void showMenu() throws Exception
{
	 String info="*****欢迎进入浏览系统*****\n"+"1.下载文件\n"+
			 "2.文件列表\n"+"3.修改密码\n"+"4.退出\n"+"*******************";
	 System.out.println(info);
	 Scanner scan=new Scanner(System.in);
	 int index=scan.nextInt();
	 switch(index)
	 {
	
	 case 1:
		 
		 try {
			downloadFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		 break;
	 case 2:
		 try {
			showFileList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		 break;
	 case 3:
		 System.out.println("请输入要更改的密码");
		 String password=scan.next();
		 try {
			changeSelfInfo(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		 break;
	 case 4:
		 exitSystem();
		 break;
	 }
}

}
