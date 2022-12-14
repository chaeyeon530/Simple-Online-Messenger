package Project;
import Project.ChattingRoom_GUI_Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Login {
	
	JFrame Login;
	Container c;
	String ID;
	String PW;
	JTextField idTextField;
	JPasswordField passTextField;
	DB db;

	Login() {
		
		Login = new JFrame("log in");
		Login.setSize(470, 710);
		db = new DB();
		c = Login.getContentPane();
		c.setBackground(Color.white);

		setLogin();
		Login.setVisible(true);
		Login.setResizable(false);
		Login.setLocation(100, 100);
		Login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	void setLogin() {
		
		
		JLabel lb1 = new JLabel("KAKAO TALK");
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setFont(new Font("Eras Bold ITC", Font.PLAIN, 30));
		lb1.setBackground(new Color(0, 0, 255));
		lb1.setBounds(100, 37, 280, 81);
		Login.getContentPane().add(lb1);
		
		JLabel idLabel = new JLabel("ID :", SwingConstants.RIGHT);
		idLabel.setFont(new Font("Arial Black", Font.PLAIN, 12));
		idLabel.setBounds(101, 128, 38, 23);
		
		idTextField = new JTextField(15);
		idTextField.setBounds(159, 128, 150, 23);
		
		JLabel passLabel = new JLabel("PW :", Label.RIGHT);
		passLabel.setFont(new Font("Arial Black", Font.PLAIN, 12));
		passLabel.setBounds(110, 166, 29, 23);
		
		passTextField = new JPasswordField(15);
		passTextField.setBounds(159, 166, 150, 23);

		Login.getContentPane().setLayout(null);
		c.add(idLabel);
		c.add(idTextField);
		c.add(passLabel);
		c.add(passTextField);
		
		
		JButton loginButton = new JButton("login");
		loginButton.setFont(new Font("Bodoni MT", Font.PLAIN, 15));
		loginButton.setLocation(130, 211);
		
		JButton signupButton = new JButton("create");
		signupButton.setFont(new Font("Bodoni MT", Font.PLAIN, 15));
		signupButton.setLocation(240, 211);
		
		JButton fineID = new JButton("fine ID");
		fineID.setFont(new Font("Bodoni MT", Font.PLAIN, 15));
		fineID.setLocation(130, 240);
		
		JButton finePW = new JButton("fine PW");
		finePW.setFont(new Font("Bodoni MT", Font.PLAIN, 15));
		finePW.setLocation(240, 240);
		
		loginButton.setSize(99, 25);
		loginButton.setBackground(Color.white);
		c.add(loginButton);
		
		signupButton.setSize(99, 25);
		signupButton.setBackground(Color.white);
		c.add(signupButton);
		
		fineID.setSize(99, 25);
		fineID.setBackground(Color.white);
		c.add(fineID);
		
		finePW.setSize(99, 25);
		finePW.setBackground(Color.white);
		c.add(finePW);
		
		
		
		
		loginButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	ID = idTextField.getText();
					PW = passTextField.getText();
					
					if (db.checkUser(ID, PW)) {
						Login.dispose();
						JOptionPane.showMessageDialog(null, "welcome !");
						
						success frame = new success(ID);
						frame.setVisible(true); // 창 안보이게 하기 
					}
					else{
						JOptionPane.showMessageDialog(null, "ID or PW is not correct !");}
					
				} 
					
	               
	               
	            });
		
		
		
		signupButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               new SignupPanel();
	                Login.setVisible(false); // 창 안보이게 하기 
	            }
	        });
		
		fineID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new GUI_findID();
                Login.setVisible(false); // 창 안보이게 하기 
            }
        });

		
		finePW.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new  GUI_findPW() ;
                Login.setVisible(false); // 창 안보이게 하기 
            }
        });

	
		
		

	}

	/*
	class EventHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("login")) {
				
				ID = idTextField.getText();
				PW = passTextField.getText();
				
				//System.out.println(ID + " " + PW);
				
				if (db.checkUser(ID, PW)) {
					Login.dispose();
					JOptionPane.showMessageDialog(null, "welcome !");
					//success successFrame = new success(ID);
					//successFrame.setVisible(true);
					new success(ID);
		
				} 
				
				else {
					JOptionPane.showMessageDialog(null, "ID or PW is not correct !");}
				
			} 
			
			else {
				Login.dispose();
				new SignupPanel();}
			
		}
	}*/

	public static void main(String[] args) {
		new Login();
	}
}
	
	
	

	

