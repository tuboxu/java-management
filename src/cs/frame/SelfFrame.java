package cs.frame;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

import oop.*;

public class SelfFrame implements ActionListener {
	static String name=null;
	static String role=null;
	static String pass=null;
JFrame f1=new JFrame("个人信息管理");
JPanel p1=new JPanel();
JLabel l1[] ={new JLabel("用户名"),new JLabel("原口令"),new JLabel("新口令"),new JLabel("确认新口令"),new JLabel("角色")};
JTextField te[]={new JTextField(name),new JTextField(),new JTextField(),new JTextField(),new JTextField(role)};
JButton bu[]={new JButton("修改"),new JButton("返回")};
static void setString(User use)
 {
	name=use.getName();
	role=use.getRole();
	pass=use.getPassword();
 } 
	public SelfFrame()
	{
     setit();
	}
	public void setit()
 {
		f1.add(p1);
		p1.setLayout(null);
		te[0].setEnabled(false);
		te[4].setEnabled(false);
		for(int i=1;i<4;i++)
		{
			te[i].addActionListener(this);
		}
	for(int i=0;i<5;i++)
	{
		l1[i].setBounds(30,30+i*70,80,26);
		te[i].setBounds(150,30+i*70,180,30);
		p1.add(l1[i]);
		p1.add(te[i]);
		
	}
	for(int i=0;i<2;i++)
	{
		bu[i].setBounds(100+i*100,350,80,26);
		bu[i].addActionListener(this);
		p1.add(bu[i]);
	}
		f1.setBounds(100,100,500,500);
	f1.setVisible(true);
 }

	public void actionPerformed(ActionEvent e)
	{
		boolean re = false;
		if(e.getSource()==te[1])
		{
			te[2].requestFocus();
		}
		if(e.getSource()==te[2])
		{
			te[3].requestFocus();
		}
		 if(e.getSource()==bu[0])
		{
			if(te[1].getText().equals(pass)&&te[2].getText().equals(te[3].getText()))
			{
				try {
					re=DataProcessing.updateUser(name,te[2].getText(),role);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"口令不符", "提示消息",JOptionPane.WARNING_MESSAGE);
			}
		}
		if(e.getSource()==bu[1])
		{
			f1.dispose();
		}
		if(re==true)
			JOptionPane.showMessageDialog(null,"修改成功", "提示消息",JOptionPane.NO_OPTION);
	}
}
