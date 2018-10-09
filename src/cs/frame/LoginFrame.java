package cs.frame;
import java.awt.event.*;
import javax.swing.*;

import oop.*;

public class LoginFrame implements ActionListener
{


	JFrame f1=new JFrame("用户登录界面");
	JPanel p1=new JPanel();
	JTextField Te=new JTextField();
	JLabel []La={new JLabel("用户名"),new JLabel("密码") };
	JPasswordField Pa=new JPasswordField();

	JButton []Bu={new JButton("确定"),new JButton("取消")};

	public void setit() throws Exception
 {
f1.add(p1);
p1.setLayout(null);
for(int i=0;i<2;i++)
  {
	La[i].setBounds(30,20+i*50,80,26);
	Bu[i].setBounds(110*i+50,130,80,26);
	p1.add(La[i]);
	p1.add(Bu[i]);
	Bu[i].addActionListener(this);
  }
Te.setBounds(80,20,180,30);
p1.add(Te);
Te.addActionListener(this);
Pa.setBounds(80,70,180,30);
Pa.setEchoChar('*');
p1.add(Pa);
Pa.addActionListener(this);
f1.setBounds(100,100,300,250);
f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f1.setVisible(true);
 }
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==Te)
			{
			Pa.requestFocus();
			}
		else if(e.getSource()==Bu[1])	
		{
			Te.setText("");
			Pa.setText("");
		Te.requestFocus();
		}
		else 
		{
			try
			{
				User use=DataProcessing.searchUser(Te.getText(),String.valueOf(Pa.getPassword()));
				if(use!=null)
				{
				f1.dispose();
				new MainFrame(use);
				
				}else{JOptionPane.showMessageDialog(null,"登录失败", "提示消息",JOptionPane.WARNING_MESSAGE);}
			}
			catch(Exception eq)
		  {
				System.out.println(eq.getMessage());
				
		  }
			
		}
	}

}
