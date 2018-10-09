package oop;
import java.util.Enumeration;
import java.util.Hashtable;
import java.sql.*;

import javax.swing.*;

/*public  class DataProcessing {

	private static boolean connectToDB=false;
	
	static Hashtable<String, User> users;
	static Hashtable<String, Doc> docs;

	static {
		users = new Hashtable<String, User>();
		users.put("jack", new Operator("jack","123","operator"));
		users.put("rose", new Browser("rose","123","browser"));
		users.put("kate", new Administrator("kate","123","administrator"));
		Init();
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
		docs = new Hashtable<String,Doc>();
		docs.put("0001",new Doc("0001","jack",timestamp,"Doc Source Java","a.txt"));
		
		
	}
	
	public static  void Init(){
		// connect to database
		
		// update database connection status
//		if (Math.random()>0.2)
//			connectToDB = true;
//		else
//			connectToDB = false;
	}
	
	public static Doc searchDoc(String ID)  {
		if (docs.containsKey(ID)) {
			Doc temp =docs.get(ID);
			return temp;
		}
		return null;
	}
	
	public static Enumeration<Doc> getAllDocs() throws SQLException{		
		Enumeration<Doc> e  = docs.elements();
		return e;
	} 
	
	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException{
		Doc doc;		
	
		if (docs.containsKey(ID))
			return false;
		else{
			doc = new Doc(ID,creator,timestamp,description,filename);
			docs.put(ID, doc);
			return true;
		}
	} 
	
	public static User searchUser(String name) throws SQLException{
//		if ( !connectToDB ) 
//			throw new SQLException( "Not Connected to Database" );
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Query" );
		
		if (users.containsKey(name)) {
			return users.get(name);			
		}
		return null;
	}
	
	public static User searchUser(String name, String password) throws SQLException {
//		if ( !connectToDB ) 
//	        throw new SQLException( "Not Connected to Database" );
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Query" );
		
		if (users.containsKey(name)) {
			User temp =users.get(name);
			if ((temp.getPassword()).equals(password))
				return temp;
		}
		return null;
	}
	
	public static Enumeration<User> getAllUser() throws SQLException{
//		if ( !connectToDB ) 
//	        throw new SQLException( "Not Connected to Database" );
//		
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Query" );
		
		Enumeration<User> e  = users.elements();
		return e;
	}
	
	
	
	public static boolean updateUser(String name, String password, String role) throws SQLException{
		User user;
//		if ( !connectToDB ) 
//	        throw new SQLException( "Not Connected to Database" );
//		
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Update" );
		
		if (users.containsKey(name)) {
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else
				user = new Browser(name,password, role);
			users.put(name, user);
			return true;
		}else
			return false;
	}
	
	public static boolean insertUser(String name, String password, String role) throws SQLException{
		User user;
		
//		if ( !connectToDB ) 
//	        throw new SQLException( "Not Connected to Database" );
//		
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Insert" );
		
		if (users.containsKey(name))
			return false;
		else{
			if (role.equalsIgnoreCase("administrator"))
				user = new Administrator(name,password, role);
			else if (role.equalsIgnoreCase("operator"))
				user = new Operator(name,password, role);
			else
				user = new Browser(name,password, role);
			users.put(name, user);
			return true;
		}
	}
	
	public static boolean deleteUser(String name) throws SQLException{
//		if ( !connectToDB ) 
//	        throw new SQLException( "Not Connected to Database" );
//		
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in excecuting Delete" );
		
		if (users.containsKey(name)){
			users.remove(name);
			return true;
		}else
			return false;
		
	}	
            
	public static void disconnectFromDB() {
		if ( connectToDB ){
			// close Statement and Connection            
			try{
//				if (Math.random()>0.5)
//					throw new SQLException( "Error in disconnecting DB" );      
//			}catch ( SQLException sqlException ){                                            
//			    sqlException.printStackTrace();           
			}finally{                                            
				connectToDB = false;              
			}                             
		} 
   }           

	
	
	
}*/
public class DataProcessing 
{
private static Connection connection; 	
private static Statement statement;
private static PreparedStatement preparedstatement;
private static ResultSet resultset;
private static boolean connectedToDatabase=false;

public static void connecttoDB()
{
	String drivername="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/document";
	String user="root";
	String password="19981004";
	try
	{
	Class.forName(drivername);
	connection=DriverManager.getConnection(url,user,password);
	statement=connection.createStatement();
	System.out.println("连接成功");
	}
	catch(Exception e)
	{
		e.getMessage();
		
	}
	connectedToDatabase=true;
}

public static void disconnectfromDB()
{
	if(connectedToDatabase)
	{
		try
		{
			resultset.close();
			statement.close();
			connection.close();
		}
		catch(Exception e)
		{
		e.getStackTrace();
		}
		finally
		{
			connectedToDatabase=false;
		}
	}
}
public static Doc searchDoc(String DocID) throws Exception
{
	Doc temp=null;
	if(!connectedToDatabase)
	throw new Exception("Not connect to database");
	statement=connection.createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_READ_ONLY);
			String sql="select *from doc_info";
			resultset=statement.executeQuery(sql);
			while(resultset.next())
			{
				if(DocID.equals(resultset.getString("Id")))
				{String ID=resultset.getString("Id");
				String creator=resultset.getString("creator");
				Timestamp timestamp=resultset.getTimestamp("timestamp");
				String description=resultset.getString("description");
				String filename=resultset.getString("filename");
				temp=new Doc(ID,creator,timestamp,description,filename);
				return temp;
				}
			}
			return null;
			
}
public static boolean deleteUser(String name) throws Exception
{String sql="delete from user_info where username=?";
preparedstatement=connection.prepareStatement(sql);
preparedstatement.setString(1, name);
int effect=preparedstatement.executeUpdate();
if(effect>0)
	return true;
else return false;	
}
public static boolean updateUser(String name,String pass,String role) throws Exception {
	System.out.println(name+"    "+pass+"   "+role);
	
	String sql="update user_info set password=? ,role=? where username=?";
	preparedstatement=connection.prepareStatement(sql);
	preparedstatement.setString(1, pass);
	preparedstatement.setString(2, role);
	preparedstatement.setString(3, name);
	int effect=preparedstatement.executeUpdate();
	if(effect>0)
		return true;
	else return false;}
public static boolean insertUser(String name, String password, String role) throws SQLException
{
	String sql="insert into user_info values(?,?,?)";
	 preparedstatement=connection.prepareStatement(sql);
	 preparedstatement.setString(1, name);
	 preparedstatement.setString(2, password);
	 preparedstatement.setString(3,role);
	int effect= preparedstatement.executeUpdate();
	if(effect>0)
		return true;
	else return false;
		
}

public static ResultSet getAllUser() throws SQLException{
	String usersql="select * from user_info";
	resultset = statement.executeQuery(usersql);
	return resultset;
}
		public static User searchUser(String Username, String password) throws SQLException
		{
			/*if ( !connectedToDatabase ) 
		        throw new IllegalStateException( "Not Connected to Database" );
			String sql="select * from user_info where username='"+Username+"'";
			resultset=statement.executeQuery(sql);
			if(resultset.next()) {
			String Name=resultset.getString("username");
			String Pass=resultset.getString("password");
			String Role=resultset.getString("role");
			if (!Pass.equals(password)) {System.out.println("输入信息有误");return null;}

			
	         return new User(Name,Pass,Role);
			
			}
			else  {System.out.println("输入信息有误");return null;}*/
			String usersql="select * from user_info";
			statement = connection.createStatement( 
			         ResultSet.TYPE_SCROLL_INSENSITIVE,
			         ResultSet.CONCUR_READ_ONLY );		
			resultset = statement.executeQuery(usersql);
			User user;
			while(resultset.next()){
				if(resultset.getString("username").equals(Username))
				{
					user=new User(resultset.getString(1),resultset.getString(2),resultset.getString(3));
					
					if(user.getPassword().equals(password))
					{return user;}
					else return null;				
				}
			}	
			return null;
			
		}
		
		public static User searchUser(String Username) throws SQLException
		{
			System.out.println("成功进入查找");
			String usersql="select * from user_info";
			statement = connection.createStatement( 
			         ResultSet.TYPE_SCROLL_INSENSITIVE,
			         ResultSet.CONCUR_READ_ONLY );	
			resultset = statement.executeQuery(usersql);
			User user=null;
			while(resultset.next()){
				System.out.println(resultset.getString("username"));
				if(resultset.getString("username").equals(Username))
				{
					user.setName(resultset.getString("username"));
					user.setPassword(resultset.getString("password"));
					user.setRole(resultset.getString("role"));
					return user;
				}
			}
			return null;
			
		}
		public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException
		{
			Doc doc=null;		
			doc = new Doc(ID,creator,timestamp,description,filename);
			
			String sql="insert into doc_info values(?,?,?,?,?)";
			preparedstatement=connection.prepareStatement(sql);
			preparedstatement.setString(1, ID);
			preparedstatement.setString(2, creator);
			preparedstatement.setString(3,String.valueOf(timestamp));
			preparedstatement.setString(4,description);
			preparedstatement.setString(5,filename);
			int effect=preparedstatement.executeUpdate();
			if(effect>0)
				return true;
			else return false;
			
		}
		public static ResultSet getAllDocs() throws SQLException{		
			
			String sql="select * from doc_info";
			statement = connection.createStatement( 
			         ResultSet.TYPE_SCROLL_INSENSITIVE,
			         ResultSet.CONCUR_READ_ONLY );	
			resultset = statement.executeQuery(sql);
			return resultset;
		} 
	
}


