package cs.frame;
import java.awt.event.*;

import javax.swing.*;

import oop.*;
public class MainFrame implements ActionListener {
	JMenuBar bar=new  JMenuBar();
	JMenu usem=new JMenu("用户管理");
	JMenu film=new JMenu("档案管理");
	JMenu selm=new JMenu("个人信息管理");
	JMenuItem d1=new JMenuItem("档案上传");
	JMenuItem d2=new JMenuItem("档案下载");
	JMenuItem s1=new JMenuItem("信息修改");
	JMenuItem u1=new JMenuItem("修改用户");
	JMenuItem u2=new JMenuItem("删除用户");
	JMenuItem u3=new JMenuItem("添加用户");
	JFrame f1=new JFrame();
public MainFrame(User use)
{
	if(use.getRole().equals("operator"))
	{
		SelfFrame.setString(use);
		FileFrame.setcreator(use);
		f1.setTitle("操作员管理界面");
		setit();
		usem.setEnabled(false);
		
	}
	else if(use.getRole().equals("browser"))
	{
		SelfFrame.setString(use);
		FileFrame.setcreator(use);
		f1.setTitle("浏览员管理界面");
		setit();
		usem.setEnabled(false);
		d1.setEnabled(false);
		
	}
	else if (use.getRole().equals("administrator"))
	{
		System.out.println(use.getRole());
		SelfFrame.setString(use);
		FileFrame.setcreator(use);
		f1.setTitle("管理员管理界面");
		setit();
		d1.setEnabled(false);
	}
}
public void setit()
{
	f1.setJMenuBar(bar);
	bar.add(usem);
	bar.add(selm);
	bar.add(film);
	usem.add(u1);
	usem.add(u2);
	usem.add(u3);
	selm.add(s1);
	film.add(d1);
	film.add(d2);
	s1.addActionListener(this);
	d1.addActionListener(this);
	d2.addActionListener(this);
	u1.addActionListener(this);
	u2.addActionListener(this);
	u3.addActionListener(this);
	f1.setSize(1000, 800);
	f1.setVisible(true);
}

public void actionPerformed(ActionEvent e) 
{
if(e.getSource()==s1)
 {
	new SelfFrame();
 }
else if(e.getSource()==u1)
 {
	try {
		new UserFrame();
	} catch (Exception e1) {
	
		e1.printStackTrace();
	}
 }
else if(e.getSource()==u2)
 {
	try {
		new UserFrame();
	} catch (Exception e1) {
	
		e1.printStackTrace();
	}
 }
else if(e.getSource()==u3)
{
	try {
		new UserFrame();
	} catch (Exception e1) {
	
		e1.printStackTrace();
	}
}
else if(e.getSource()==d1)
{
	try {
		new FileFrame();
	} catch (Exception e1) {
	
		e1.printStackTrace();
	}
}
else if(e.getSource()==d2)
{
	try {
		new FileFrame();
	} catch (Exception e1) {

		e1.printStackTrace();
	}
}

}
}
