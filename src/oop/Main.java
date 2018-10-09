package oop;
//import java.sql.SQLException;
import java.util.*;
public class Main{


public static void main(String []args) {
	User us=null;

	String info="*****欢迎进入档案管理系统*****\n\t    "+"1.登录\n  \t"+"    2.退出\n"+"***********************";
	Scanner scan=new Scanner(System.in);
	int index=0;
	do
	 {
System.out.println(info);
System.out.println("请选择菜单：");
index=scan.nextInt();
switch(index)
       {
	case 1:
		System.out.println("请输入用户名");
		String na=scan.next();
		System.out.println("请输入密码");
		String pa=scan.next();
		System.out.println("请输入角色");
		String ro=scan.next();
		User a=null;
	try {
		if((a=DataProcessing.searchUser(na, pa))!=null)
		{
			
			switch(ro)
			{
			case"administrator":
				us=new Administrator(na,pa,a.getRole());
				break;
			case"browser":
				us=new Browser(na,pa,a.getRole());
				break;
			case"operator":
				us=new Operator(na,pa,a.getRole());
				break;
			
			}
			us.showMenu();
		}
		else
		{
			System.out.println("用户名或密码输入错误");
		}
	} catch (Exception e) {
	
		System.out.println(e.getMessage());
		
	}
		break;
	case 2:
		System.out.println("退出成功");
		System.exit(0);break;
	default:
		System.out.println("请选择正确选项");
		break;
      }
    }while(index!=2&&index!=1);
	scan.close();
}

}