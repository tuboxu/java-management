package cs.frame;

import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.table.*;

import oop.*;
public class UserFrame implements ActionListener{

	JFrame f1=new JFrame("�û��������");
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	JPanel p3=new JPanel();
	JTabbedPane ch=new JTabbedPane();
	JButton bu1[]={new JButton("����"),new JButton("ȡ��")};
	JButton bu2[]={new JButton("�޸�"),new JButton("ȡ��")};
	JButton bu3[]={new JButton("ɾ��"),new JButton("ȡ��"),new JButton("ˢ��")};
	JLabel la1[]={new JLabel("�û���"),new JLabel("����"),new JLabel("��ɫ")};
	JLabel la2[]={new JLabel("�û���"),new JLabel("����"),new JLabel("��ɫ")};
	JTextField te1[]={new JTextField(),new JTextField()};
	JTextField te2[]={new JTextField(),new JTextField()};
	String ro[]={"operator","browser","administrator"};
	JComboBox<Object> b1=new JComboBox<Object>(ro);
JComboBox<Object> b2=new JComboBox<Object>(ro);

		JTable ta=new JTable(20,3);
	TableModel mo=ta.getModel();
	String list[]={"�û���","����","��ɫ"};
	public UserFrame() throws Exception
	{
		
		setit();
	}
	public void setit() throws Exception
	{
		
			table();
			ch.add("�����û�",p1);
			ch.add("�޸��û�",p2);
			ch.add("ɾ���û�",p3);
			f1.add(ch);
			p1.setLayout(null);
			p2.setLayout(null);
			
			 
		    			 for(int j=0;j<2;j++)
			 {
				 bu1[j].setBounds(100+j*100,250,80,26);
				 bu1[j].addActionListener(this);
			 	bu2[j].setBounds(100+j*100,250,80,26);
			 	bu2[j].addActionListener(this);
			 	bu3[j].setBounds(100+j*100,250,80,26);
			 	bu3[j].addActionListener(this);
			 	 p3.add(bu3[j]);
			     p2.add(bu2[j]);
			     p1.add(bu1[j]);
			 }
		    			 bu3[2].setBounds(300,350,80,26);
		 			 	bu3[2].addActionListener(this);
		 			 	 p3.add(bu3[2]);		 
			 for(int i=0;i<3;i++)
			 {
			 	la1[i].setBounds(30,30+i*70,80,26);
			 	la2[i].setBounds(30,30+i*70,80,26);
			 	p2.add(la2[i]);
				p1.add(la1[i]);
			 }
			 for(int i=0;i<2;i++)
			 {
				 te1[i].setBounds(150,30+i*70,180,30);
			 te1[i].addActionListener(this);
			 p1.add(te1[i]);
			 te2[i].setBounds(150,30+i*70,180,30);
			 te2[i].addActionListener(this);
			 p2.add(te2[i]);
			 }
		
				 b1.setBounds(150,170,180,30);
				 b1.addActionListener(this);
				 p1.add(b1);
				 b2.setBounds(150,170,180,30);
				 b2.addActionListener(this);
				 p2.add(b2);
			 
			 p3.add(ta);
				f1.setBounds(100,100,500,500);
				f1.setVisible(true);
		
	}
	public void table() throws Exception
	{
		for(int i=0;i<3;i++)
		{
			mo.setValueAt(list[i], 0, i);
			
		}
		int i=1;
		
		ResultSet use;
		use=DataProcessing.getAllUser();
		User docu;
		int peNum=1;
		while(use.next())
		{
			
			String[] vec=new String[3];
			vec[1]=use.getString("password");
			vec[2]=use.getString("role");    
			ta.setValueAt(use.getString("username"), peNum, 0);
			for(i=1;i<3;i++){
			ta.setValueAt(vec[i], peNum, i);
			}
			peNum++;
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==bu1[1]||e.getSource()==bu2[1]||e.getSource()==bu3[1])
		{
			f1.dispose();
		}
		if(e.getSource()==bu1[0])
		{
			try {
				DataProcessing.insertUser(te1[0].getText(), te1[1].getText(), (String) b1.getSelectedItem());
			} catch (SQLException e1) {
				
				JOptionPane.showMessageDialog(null,"����ʧ��", "��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
			}
			JOptionPane.showMessageDialog(null,"�����ɹ�", "��ʾ��Ϣ",JOptionPane.NO_OPTION);
		}
		if(e.getSource()==bu2[0])
		{
			try {
				DataProcessing.updateUser(te2[0].getText(), te2[1].getText(), (String) b2.getSelectedItem());
			} catch (Exception e1) {
				
				JOptionPane.showMessageDialog(null,"�޸�ʧ��", "��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
			}
			JOptionPane.showMessageDialog(null,"�޸ĳɹ�", "��ʾ��Ϣ",JOptionPane.NO_OPTION);
		}
		if(e.getSource()==bu3[0])
		{
			try {
				int item=ta.getSelectedRow();
				String na=(String) mo.getValueAt(item, 0);
				if(DataProcessing.deleteUser(na)==true)
					JOptionPane.showMessageDialog(null,"ɾ���ɹ�", "��ʾ��Ϣ",JOptionPane.NO_OPTION);
			} catch (Exception e1) {
				
				JOptionPane.showMessageDialog(null,"ɾ��ʧ��", "��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
			}
			
		}
		if(e.getSource()==bu3[2])
		{
			try{
			table();
			}
			catch(Exception g)
			{
				JOptionPane.showMessageDialog(null,"ˢ��ʧ��", "��ʾ��Ϣ",JOptionPane.WARNING_MESSAGE);
			}
			}
	}

}
