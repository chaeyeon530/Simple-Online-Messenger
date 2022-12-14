package Project;
import Project.ChattingRoom_GUI_Client;

import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import java.awt.*;


class success extends JFrame{//success는 server에 작성
	 
	 DB db;
		
	 String id="";
	 String con="", ip="", port="", time_last_login="",time_last_logout="", number_of_login="";
	 String comment="";
	 
	 Date now = new Date();
	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd.HH.MM.SS");
	 String Now = formatter.format(now);
	 
	 Font f1,f2;
	 
	 //ip, socket은 client랑 연결해서 local정보 받아오기 (getLocalAddress, getLocalPort)
	
	
	 public success(String ID){

		 	super("User information");
			getContentPane().setBackground(Color.white);
			setSize(470, 710);
			setting();
			setinfoGUI();
			setVisible(true);
			setResizable(false);
			
			db= new DB();
			this.id = ID;
			
			System.out.print(id);
	 }
	 
	 void setting() {}
	 
	 void setinfoGUI() {
		 
		 f1 = new Font("Bodoni MT",Font.PLAIN,15); 
		 f2 = new Font("바탕",Font.PLAIN,10);
		 
		 getContentPane().setLayout(null);
		 
		 JLabel lb1 = new JLabel("connection: ");
		 JLabel lb2 = new JLabel("Comment: ");
		 JLabel lb3 = new JLabel("IP: ");
		 JLabel lb4 = new JLabel("Socket: ");
		 JLabel lb5 = new JLabel("last_login: ");
		 JLabel lb6 = new JLabel("last_logout");
		 JLabel lb7 = new JLabel("Number of login");
		 
		 lb1.setFont(f1);
		 lb2.setFont(f1);
		 lb3.setFont(f1);
		 lb4.setFont(f1);
		 lb5.setFont(f1);
		 lb6.setFont(f1);
		 lb7.setFont(f1);
		 
		 lb1.setBounds(60,100,100,25);
		 lb2.setBounds(60,140,100,25);
		 lb3.setBounds(60,180,100,25);
		 lb4.setBounds(60,220,100,25);
		 lb5.setBounds(60,260,150,25); 
		 lb6.setBounds(60,300,150,25);
		 lb7.setBounds(60,340,150,25);
		 
		 getContentPane().add(lb1);
		 getContentPane().add(lb2);
		 getContentPane().add(lb3);
		 getContentPane().add(lb4);
		 getContentPane().add(lb5);
		 getContentPane().add(lb6);
		 getContentPane().add(lb7);
		 
		
		 //server에서 직송으로 user_info 보내주기 
		 String user_info = db.userInfo(id);
		 
		 String[] info = user_info.split("/");
		 
		 /*
		 ip = info[0];
		 port = info[1];
		 con = info[2];
		 time_last_login = info[3];
		 number_of_login = info[4];
		 time_last_logout = info[5];
		 comment = info[6];
		 */

		 JLabel Con = new JLabel(con);
		 JTextField Comment = new JTextField(comment);
		 JLabel IP = new JLabel(ip);      
		 JLabel Port = new JLabel(port);
		 JLabel Time_last_login = new JLabel(time_last_login);
		 JLabel Time_last_logout = new JLabel(time_last_logout);
		 JLabel Number_of_login = new JLabel(number_of_login);
		 
		 Con.setFont(f1);
		 Comment.setFont(f1);
		 IP.setFont(f1);
		 Port.setFont(f1);
		 Time_last_login.setFont(f1);
		 Time_last_logout.setFont(f1);
		 Number_of_login.setFont(f1);
		 
		 Con.setBounds(200,100,180,25);
		 Comment.setBounds(200,140,180,25);
		 IP.setBounds(200,180,180,25);
		 Port.setBounds(200,220,180,25);
		 Time_last_login.setBounds(200,260,180,25); 
		 Time_last_logout.setBounds(200,300,180,25);
		 Number_of_login.setBounds(200,340,180,25);
		 
		 getContentPane().add(Con);
		 getContentPane().add(Comment);
		 getContentPane().add(IP);
		 getContentPane().add(Port);
		 getContentPane().add(Time_last_login);
		 getContentPane().add(Time_last_logout);
		 getContentPane().add(Number_of_login); 
		 
		 JButton logout = new JButton("logout");
		 logout.setFont(new Font("Arial Black", Font.PLAIN, 15));
		 logout.setBounds(100, 600, 100, 30);
		 getContentPane().add(logout);
		 logout.setBackground(Color.white);
		
		 
		 JButton main = new JButton("main");
		 main.setFont(new Font("Arial Black", Font.PLAIN, 15));
		 main.setBounds(250, 600, 100, 30);
		 getContentPane().add(main);
		 main.setBackground(Color.white);
	
		 main.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               //new main();
	                setVisible(false); // 창 안보이게 하기 
	            }
	        });
		 
		 logout.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               new Login();
	                setVisible(false); // 창 안보이게 하기 
	            }
	        });
			
		
	 }
	 
 }
			
	
 
		