package Project;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.*;

public class ChattingRoom_GUI_Client extends JFrame {
	private JTextArea messageArea = new JTextArea();
	private JTextArea textArea = new JTextArea();
	private JButton sendBT = new JButton("전송");
	private Socket socket;
	private BufferedReader in;
	private OutputStream out;

	ChattingRoom_GUI_Client() {
		JFrame frm = new JFrame("ChattingRoom");
		frm.setSize(470, 710);
		frm.setLocationRelativeTo(null);
		frm.setResizable(false);

		Container contentPane = getContentPane();

		// 상단
		JPanel upper = new JPanel();
		upper.setPreferredSize(new Dimension(400, 60));

		//상단 세칸으로 나누기
		JPanel upperleft = new JPanel();
		JPanel uppercenter = new JPanel();
		JPanel upperright = new JPanel();

		upperleft.setPreferredSize(new Dimension(200, 48));
		uppercenter.setPreferredSize(new Dimension(70, 48));
		upperright.setPreferredSize(new Dimension(70, 48));

		JLabel up = new JLabel("TALK&TALK"); //채팅 프로그램 이름
		Font font = new Font("TALK&TALK", Font.BOLD, 20);
		up.setFont(font);

		JButton exitBT = new JButton("나가기");
		exitBT.setPreferredSize(new Dimension(80, 40));

		JButton inviteBT = new JButton("친구추가");
		inviteBT.setPreferredSize(new Dimension(90, 40));

		upperleft.add(up);
		upper.add(upperleft);

		uppercenter.add(exitBT);
		upper.add(uppercenter);

		upperright.add(inviteBT);
		upper.add(upperright);


		//중앙 - JScrollPane으로 대화창
		JPanel center = new JPanel();
		center.setPreferredSize(new Dimension(400, 400));

		//JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);

		JScrollPane chat = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chat.setPreferredSize(new Dimension(390, 390));

		center.add(chat);

		//하단 - 메세지 입력 창
		JPanel bottom = new JPanel();
		bottom.setPreferredSize(new Dimension(400, 100));

		//JTextArea messageArea = new JTextArea();		
		messageArea.setLineWrap(true);
		JScrollPane message = new JScrollPane(messageArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		message.setPreferredSize(new Dimension(310, 30));

		//sendBT.setText("전송");
		sendBT.setPreferredSize(new Dimension(60, 30));

		bottom.add(message);
		bottom.add(sendBT);


		contentPane.add(upper, BorderLayout.NORTH);
		contentPane.add(center, BorderLayout.CENTER);
		contentPane.add(bottom, BorderLayout.SOUTH);

		frm.add(contentPane);

		frm.setVisible(true);

		//this.getContentPane().add(sendBT);

	}

	public void addListener() {

		sendBT.addActionListener((ActionListener) this);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			socket = new Socket("192.168.0.28", 9996);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = socket.getOutputStream();

		} catch (Exception e1) {
			System.out.println("접속 오류:" + e1);
		}

		try {
			out.write((messageArea.getText() + "\n").getBytes("euc-kr"));
			messageArea.setText("");
			messageArea.requestFocus();
		} catch (Exception e1) {
			System.out.println("메세지 전송 오류" + e1);
		}

	}


	public static void main(String[] args) {
		new ChattingRoom_GUI_Client();
	}
}


