package cs.frame;
import java.awt.event.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import oop.*;
import javax.swing.*;
import javax.swing.table.*;

public class FileFrame implements ActionListener {
	String uploadpath="F:\\Multiproject4\\src\\oop\\uploadfile\\";
	String downloadpath="F:\\Multiproject4\\src\\oop\\downloadfile\\";
JFrame f1=new JFrame("文件管理界面");
JPanel p1=new JPanel();
JPanel p2=new JPanel();
JTabbedPane ch=new JTabbedPane();
JButton bu1[]={new JButton("下载"),new JButton("返回"),new JButton("刷新")};
JButton bu2[]={new JButton("上传"),new JButton("取消")};
JLabel la[]={new JLabel("档案号"),new JLabel("档案描述"),new JLabel("档案文件名")};
JTextField te[]={new JTextField(),new JTextField(),new JTextField()};
JTable ta=new JTable(20,5);
TableModel mo=ta.getModel();
String list[]={"档案号","创建者","时间","文件名","描述"};
static void setcreator(User use)
{
	creator=use.getName();
};
static String creator;

public FileFrame() throws Exception
 {
	setit();
 }
public void table() throws Exception
{
	for(int i=0;i<5;i++)
	{
		mo.setValueAt(list[i], 0, i);
		
	}
	int i=1;
	
	ResultSet doc;
	doc=DataProcessing.getAllDocs();
	
	int fileNum=1;
	while(doc.next())
	{
		
		String[] vec=new String[5];
		vec[1]=doc.getString("creator");
		vec[2]=doc.getString("timestamp");    
		vec[4]=doc.getString("description");
		vec[3]=doc.getString("filename");
		ta.setValueAt(doc.getString("Id"), fileNum, 0);
		for(i=1;i<5;i++){
		ta.setValueAt(vec[i], fileNum, i);
		}
		fileNum++;
	}
}
public void setit() throws Exception
 {
    table();

	ch.add("文件下载",p1);
	ch.add("文件上传",p2);
	f1.add(ch);
	p2.setLayout(null);

for(int j=0;j<3;j++)
{
	bu1[j].setBounds(100+j*100,300,80,26);
	bu1[j].addActionListener(this);
    p1.add(bu1[j]);
}
for(int j=0;j<2;j++)
{
	bu2[j].setBounds(100+j*100,350,80,26);
	bu2[j].addActionListener(this);
    p2.add(bu2[j]);
}
for(int i=0;i<3;i++)
{
	la[i].setBounds(30,30+i*70,80,26);
	te[i].setBounds(150,30+i*70,180,30);	
	p2.add(la[i]);
	p2.add(te[i]);
	te[i].addActionListener(this);
}
    p1.add(ta);
	f1.setBounds(100,100,500,500);
	f1.setVisible(true);
 }

public void actionPerformed(ActionEvent e)  {
if(e.getSource()==bu1[0])
{
	int num=ta.getSelectedRow();
	String fileID=(String) mo.getValueAt(num, 0);
	try{
		Doc afile=DataProcessing.searchDoc(fileID);

	File src=new File(uploadpath,afile.getFilename());
	FileReader read=new FileReader(src);
	File sou=new File(downloadpath,afile.getFilename());
	
	FileWriter write=new FileWriter(sou);
	int in=read.read();
	while(in!=-1)
	{
		write.write(in);
		in=read.read();
	}
	read.close();
	write.close();
	if(afile!=null)JOptionPane.showMessageDialog(null,"下载成功", "提示消息",JOptionPane.NO_OPTION);
}
catch(Exception m)
{
	JOptionPane.showMessageDialog(null,"下载失败", "提示消息",JOptionPane.WARNING_MESSAGE);
	
}

}
if(e.getSource()==bu1[1])
{
f1.dispose();
}

if(e.getSource()==bu2[0])
{
	
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	int b=te[2].getText().lastIndexOf("\\");
	String name=te[2].getText().substring(b+1);
	try {
		boolean flag=DataProcessing.insertDoc(te[0].getText(), creator, timestamp, te[1].getText(),name);
	
	
	File a=new File(te[2].getText());
	FileReader read=new FileReader(a);
	File b1=new File(uploadpath,name);
	FileWriter write=new FileWriter(b1);
	int in=read.read();
	while(in!=-1)
	{
		write.write(in);
		in=read.read();
	}
	read.close();
	write.close();	
	
	
	if(flag!=false)
	JOptionPane.showMessageDialog(null,"上传成功", "提示消息",JOptionPane.NO_OPTION);
	}
	catch(Exception n)
	{
	n.getMessage();
	}
}
if(e.getSource()==bu2[1])
{
	f1.dispose();
}
if(e.getSource()==bu1[2])
{
	try {
		this.table();
		}
	catch (Exception e1) {
		e1.printStackTrace();
	}
}

}
}
