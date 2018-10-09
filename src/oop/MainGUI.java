package oop;
import cs.frame.*;


public class MainGUI 
{
	public static void main(String []args)
	{
		DataProcessing.connecttoDB();
		MainGUI a=new MainGUI();
      a.run();
      
	 
   }
	public  void run()
	{

		try{
			LoginFrame loginframe=new LoginFrame();
	        loginframe.setit();
				}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
	

