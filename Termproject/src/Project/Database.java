package Project;
import java.sql.*;
public class Database 
{

	Connection con = null;
	Statement stmt = null;
	String url = "jdbc:mysql://localhost/chat";
	String user = "root";
	String passwd = "1234";

	
	Database() 
	{	
		try 
		{	
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, passwd);
			stmt = con.createStatement();
			System.out.println("[Server] MySQL ���� ���� ����");	
		}
		catch(Exception e) 
		{	
			System.out.println("[Server] MySQL ���� ���� ����> " + e.toString());
		}
	}
	

	
	boolean joinCheck(String _id, String _name, String _nick, String _p, String _pro, String _intro) 
	{
		boolean flag = false;	
		String id = _id;
		String name = _name;
		String nick = _nick;
		String pw = _p;
		String profil = _pro;
		String intro = _intro;
		
		try 
		{
			String insertStr = "INSERT INTO user VALUES('" + id + "', '" + name + "', '" + nick+ "', '" + pw + "', '" + profil + "', '" + intro + "')";
			stmt.executeUpdate(insertStr);
			
			flag = true;	
			System.out.println("[Server] ȸ������ ����");
		} catch(Exception e) 
		{	
			flag = false;
			System.out.println("[Server] ȸ������ ���� > " + e.toString());
		}
		
		return flag;	
	}
	
	boolean overCheck(String _a, String _v) 
	{
		boolean flag = false;	
		
		String att = _a;
		String val = _v;
		
		try {
			String selcectStr = "SELECT " + att + " FROM user";
			ResultSet result = stmt.executeQuery(selcectStr);
			
			int count = 0;
			while(result.next()) 
			{
				if(!val.equals(result.getString(att))) 
				{
					flag = true;
				}
				
				else 
				{	
					flag = false;
				}
				count++;
			}
			System.out.println("[Server] �ߺ� Ȯ�� ����");	
		} catch(Exception e) 
		{	
			System.out.println("[Server] �ߺ� Ȯ�� ���� > " + e.toString());
		}
		
		return flag;	
	}
	
	String viewInfo(String _id) 
	{
		String msg = "null";	
		
		String id = _id;
		
		try 
		{
			String viewStr = "SELECT name, nickname, profil, introduct FROM user WHERE id='" + id + "'";
			ResultSet result = stmt.executeQuery(viewStr);
			
			int count = 0;
			while(result.next()) 
			{
				msg = result.getString("name") + "(" + result.getString("nickname") + ")" + "/" +  result.getString("profil") + "//" +  result.getString("introduct");
				count++;
			}
			System.out.println("[Server] ȸ������ ��ȸ ����");	
		} catch(Exception e) 
		{	
			System.out.println("[Server] ȸ������ ��ȸ ���� > " + e.toString());
		}
		
		return msg;	
	}
	
	boolean changeInfo(String _id, String _a, String _v) 
	{
		boolean flag = false;	
		
		String id = _id;
		String att = _a;
		String val = _v;
		
		try 
		{
			String changeStr = "UPDATE user SET " + att + "='" + val + "' WHERE id='" + id +"'";
			stmt.executeUpdate(changeStr);
			
			flag = true;
			System.out.println("[Server] ȸ������ ���� ����");
		} catch(Exception e) 
		{	
			flag = false;
			System.out.println("[Server] ȸ������ ���� ���� > " + e.toString());
		}
		
		return flag;
	}
	
	
}


