package oop;
//import java.sql.SQLException;
import java.util.*;
public class Main{


public static void main(String []args) {
	User us=null;

	String info="*****��ӭ���뵵������ϵͳ*****\n\t    "+"1.��¼\n  \t"+"    2.�˳�\n"+"***********************";
	Scanner scan=new Scanner(System.in);
	int index=0;
	do
	 {
System.out.println(info);
System.out.println("��ѡ��˵���");
index=scan.nextInt();
switch(index)
       {
	case 1:
		System.out.println("�������û���");
		String na=scan.next();
		System.out.println("����������");
		String pa=scan.next();
		System.out.println("�������ɫ");
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
			System.out.println("�û����������������");
		}
	} catch (Exception e) {
	
		System.out.println(e.getMessage());
		
	}
		break;
	case 2:
		System.out.println("�˳��ɹ�");
		System.exit(0);break;
	default:
		System.out.println("��ѡ����ȷѡ��");
		break;
      }
    }while(index!=2&&index!=1);
	scan.close();
}

}