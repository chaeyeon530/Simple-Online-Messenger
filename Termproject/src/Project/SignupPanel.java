package Project;
import Project.ChattingRoom_GUI_Client;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SignupPanel extends JFrame{
	
	ChattingRoom_GUI_Client.DBAccess db;
	
	JTextField idTf;
	JTextField emailTf;
	JPasswordField passTf;
	JPasswordField passReTf;
	JTextField nameTf;
	JTextField nicknameTf;
	JTextField yearTf;
	JTextField phoneTf;
	
	JPanel mainPanel;
	JPanel subPanel;
	JComboBox<String> monthComboBox;
	JComboBox<String> dayComboBox;
	JRadioButton menButton;
	JRadioButton girlButton;
	JButton registerButton;
	Container c;
	
	String year = "", month = "", day = "";
	String id = "", pass = "", passRe = "", name = "", sex = "", phone = "", email="", nickname="", date="";
	
	Font f1,f2;
	
	
	SignupPanel() {
		
		super("SignupPanel");
		getContentPane().setBackground(Color.white);
		setSize(470, 710);
		setting();
		setCreateGUI();
		setVisible(true);
		setResizable(false);
		
	}
	
	void setting() {
	}
	
	void setCreateGUI() {
		
		f1 = new Font("Bodoni MT",Font.PLAIN,12); 
		f2 = new Font("바탕",Font.PLAIN,10);
		
		getContentPane().setLayout(null);
		
		JLabel idLabel = new JLabel("ID : ");
		JLabel passLabel = new JLabel("PASSWORD : ");
		JLabel passReLabel = new JLabel("RE PASSWORD : ");
		JLabel emailLabel = new JLabel("EMAIL : ");
		JLabel nameLabel = new JLabel("NAME : ");
		JLabel nicknameLabel = new JLabel("NICKNAME : ");
		JLabel birthLabel = new JLabel("BIRTHDAY : ");
		JLabel phoneLabel = new JLabel("PHONE : ");
		JLabel sexLabel = new JLabel("GENDER : ");
		
		idTf = new JTextField(15);
		emailTf = new JTextField(15);
		passTf = new JPasswordField(15);
		passReTf = new JPasswordField(15);
		nameTf = new JTextField(15);
		nicknameTf = new JTextField(15);
		yearTf = new JTextField(4);
		phoneTf = new JTextField(11);

		monthComboBox = new JComboBox<String>(
				new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" });
		dayComboBox = new JComboBox<String>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
				"28", "29", "30", "31" });

		menButton = new JRadioButton("male");
		girlButton = new JRadioButton("female");
		ButtonGroup sexGroup = new ButtonGroup();
		sexGroup.add(menButton);
		sexGroup.add(girlButton);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(15, 5, 0, 0);
		
		//id
		idLabel.setBounds(60,100,70,25);
		idLabel.setFont(f1);
		idTf.setBounds(170, 100, 250, 25);
		getContentPane().add(idLabel);
		getContentPane().add(idTf);
		
		//password
		passLabel.setBounds(45,140,100,25);
		passLabel.setFont(f1);
		passTf.setBounds(170, 140, 250, 25);
		getContentPane().add(passLabel);
		getContentPane().add(passTf);

		//repassword
		passReLabel.setBounds(45,180,100,25);
		passReLabel.setFont(f1);
		passReTf.setBounds(170, 180, 250, 25);
		getContentPane().add(passReLabel);
		getContentPane().add(passReTf);
		
		//email
		emailLabel.setBounds(45,220,70,25);
		emailLabel.setFont(f1);
		emailTf.setBounds(170, 220, 250, 25);
		getContentPane().add(emailLabel);
		getContentPane().add(emailTf);
		
		//name
		nameLabel.setBounds(45,260,70,25);
		nameLabel.setFont(f1);
		nameTf.setBounds(170, 260, 250, 25);
		getContentPane().add(nameLabel);
		getContentPane().add(nameTf);
		
		//nickname
		nicknameLabel.setBounds(45,300,100,25);
		nicknameLabel.setFont(f1);
		nicknameTf.setBounds(170, 300, 250, 25);
		getContentPane().add(nicknameLabel);
		getContentPane().add(nicknameTf);
		
		//birthday
		birthLabel.setBounds(45,340,70,25);
		birthLabel.setFont(f1);
		yearTf.setBounds(170, 340, 80, 25);
		monthComboBox.setBounds(260, 340, 80, 25);
		dayComboBox.setBounds(350, 340, 80, 25);
		getContentPane().add(birthLabel);
		getContentPane().add(yearTf);
		getContentPane().add(monthComboBox);
		getContentPane().add(dayComboBox);
		
		//gender
		sexLabel.setBounds(45,380,70,25);
		sexLabel.setFont(f1);
		menButton.setBounds(170, 380, 100, 25);
		girlButton.setBounds(280, 380, 100, 25);
		getContentPane().add(sexLabel);
		getContentPane().add(menButton);
		getContentPane().add(girlButton);
		
		//phone
		phoneLabel.setBounds(45,420,70,25);
		phoneLabel.setFont(f1);
		phoneTf.setBounds(170, 420, 250, 25);
		getContentPane().add(phoneLabel);
		getContentPane().add(phoneTf);
		
		//create Button
		JButton create = new JButton("create");
		create.setFont(new Font("Arial Black", Font.PLAIN, 15));
		create.setBounds(170, 600, 100, 30);
		getContentPane().add(create);
		create.setBackground(Color.white);
	
		JLabel lb1 = new JLabel("<html><body><center>Thank you for signing up<br>Please enter the items below in order</center></body></html>");
		lb1.setHorizontalAlignment(SwingConstants.CENTER);
		lb1.setFont(new Font("Eras Bold ITC", Font.PLAIN, 18));
		lb1.setBackground(new Color(0, 0, 255));
		lb1.setBounds(35, 10, 400, 76);
		getContentPane().add(lb1);
		create.addActionListener(new EventHandler());
		
		monthComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == monthComboBox) {
					JComboBox monthBox = (JComboBox) e.getSource();
					month = (String) monthBox.getSelectedItem();
					System.out.println(month);
				}

			}
		});
		dayComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == dayComboBox) {
					JComboBox dayBox = (JComboBox) e.getSource();
					day = (String) dayBox.getSelectedItem();
					System.out.println(month);
				}
			}
		});

		menButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sex = e.getActionCommand();
			}
		});

		girlButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sex = e.getActionCommand();
			}
		});
		
	}
		
	class EventHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			date = yearTf.getText() + "-" + month + "-" + day;
			
			Date now = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
			String createday = formatter.format(now);
			
			
			id = idTf.getText();
			email = emailTf .getText();
			pass = passTf.getText();
			name = nameTf.getText();
			nickname = nicknameTf.getText();
			phone = phoneTf.getText();
			

			if (id.length() == 0 || pass.length() == 0 || name.length() == 0)
				JOptionPane.showMessageDialog(null, "type blank");

			if (db.checkUser(id)) {
				JOptionPane.showMessageDialog(null, "already exists ID"); // 이미 존재하는 ID입니다라고 메세지창
			} else {
				db.addUser( id,  pass,  email,  name,  nickname,  createday,  date,  phone,  sex); // 등록되었습니다 메시지와 함께 다시 로그인창 생성
				JOptionPane.showMessageDialog(null, "register your id");
				dispose();
				new Login();
			}
		}
	}

}
		
		