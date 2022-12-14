package Project;

import Project.ChattingRoom_GUI_Client;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;


public class GUI_findID extends JFrame {

	JTextField tf1;
	JTextField tf2;
	
	String name="", email="", ID="";
	
	
Font f1,f2;
DB db;
	
	GUI_findID(){
		
		setTitle("find PW frame");
		setSize(470,710);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f1 = new Font("Bodoni MT",Font.PLAIN,20); 
		f2 = new Font("Bodoni MT",Font.PLAIN,15);
		 
		
		db = new DB();
		
		//ID 글자 출력 
		JLabel lb1=new JLabel("Name:");    
		lb1.setBounds(20,60, 80,30);
		lb1.setFont(f2);
				
		// Name 받는 창 만들기 
		tf1 = new JTextField();  //JTextField(): 입력 받는 그 네모난 칸 
		tf1.setBounds(120,60, 200,30);
		tf1.setFont(f2);
		
		//email 글자 출력 
		JLabel lb2=new JLabel("email:");    
		lb2.setBounds(20,110, 80,30);
		lb2.setFont(f2);
						
		// email받는 창 만들기 
		tf2 = new JTextField();  //JTextField(): 입력 받는 그 네모난 칸 
		tf2.setBounds(120,110, 200,30);
		tf2.setFont(f2);
		
		//"search" 버튼 만들기 
		JButton bt1 = new JButton("search");    
		bt1.setBounds(120,200, 100,30);    
		bt1.setFont(f2);
		
		//"login" 버튼 만들기 
		JButton bt2 = new JButton("login");    
		bt2.setBounds(250,200, 100,30);    
		bt2.setFont(f2);
		
		
		JLabel lb3=new JLabel("find kakao ID");    
		lb3.setBounds(20,10, 350,30);
		lb3.setFont(f1);
		
		add(lb3);
		add(lb1);add(tf1);
		add(lb2);add(tf2);
		add(bt1);add(bt2);
	
		setVisible(true);
		
		bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	name = tf1.getText();
				email = tf2.getText();
				ID = db.showID(name, email);
			
				System.out.print(ID+"\n");
            }
        });
		
		
		bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new Login();
               setVisible(false); // 창 안보이게 하기 
            }
        });
		
		
		
	}

	
}
