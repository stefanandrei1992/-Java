import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.FontUIResource;

public class Login extends JFrame {

	JPanel LoginPanel = new JPanel();
	JPanel backgroundLoginPanel = new JPanel();
	JButton Login = new JButton("Log In");
	JButton Register = new JButton("Create Account");
	JLabel UserLabel = new JLabel("Username");
	JLabel PasswordLabel = new JLabel("Password");
	JTextField UserTF = new JTextField("User");
	JPasswordField PasswordTF = new JPasswordField("Password");

	String userDB, passDB, adminDB, admpassDB;

	ArrayList<String> lines = new ArrayList();
	ArrayList<String> linesAdmin = new ArrayList();
	ArrayList<String> linesAdminPass = new ArrayList();
	ArrayList<String> linesPass = new ArrayList();

	Login() throws IOException {

		super("Login");
		backgroundLoginPanel.setBackground(Color.WHITE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		backgroundLoginPanel.setLayout(null);

		BufferedImage myPicture = ImageIO.read(new File("cloud.jpg"));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture), JLabel.CENTER);
		picLabel.setBounds(0, 200, 1366, 400);
		// picLabel.setBounds(0,0,1366,768);
		getContentPane().add(picLabel);

		Font labels = new Font("BELL MT", Font.BOLD, 18);
		UserLabel.setFont(labels);
		PasswordLabel.setFont(labels);

		UserLabel.setForeground(Color.WHITE);
		PasswordLabel.setForeground(Color.WHITE);

		Font name = new Font("BELL MT", Font.ROMAN_BASELINE, 14);
		UserTF.setFont(name);
		PasswordTF.setFont(name);

		UserTF.setBorder(BorderFactory.createLineBorder(Color.black));
		PasswordTF.setBorder(BorderFactory.createLineBorder(Color.black));

		Font l = new Font("Book Antiqua", Font.BOLD, 15);
		PasswordLabel.add(PasswordTF);

		Login.setFont(l);
		Register.setFont(l);

		UIManager.put("ToolTip.font", new FontUIResource("BELL MT", Font.BOLD,
				12));
		Register.setToolTipText("Register a new account");

		UserTF.setBounds(950, 265, 190, 25);
		UserLabel.setBounds(1000, 235, 190, 25);

		PasswordTF.setBounds(950, 325, 190, 25);
		PasswordLabel.setBounds(1000, 295, 190, 25);

		Login.setBounds(950, 200, 190, 30);
		Register.setBounds(930, 270, 150, 30);

		Register.setContentAreaFilled(false);
		Register.setBorderPainted(false);
		Register.setOpaque(false);
		Register.setForeground(Color.WHITE);

		picLabel.add(Login);
		picLabel.add(Register);

		getContentPane().add(UserLabel);
		getContentPane().add(UserTF);
		getContentPane().add(PasswordLabel);
		getContentPane().add(PasswordTF);
		getContentPane().add(backgroundLoginPanel);
		backgroundLoginPanel.add(picLabel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		UserTF.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				UserTF.setText("");
			}
		});

		PasswordTF.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				PasswordTF.setText("");
			}
		});

		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				String UTF = UserTF.getText();
				String PTF = PasswordTF.getText();

				try {
					LoadDataBase DB = new LoadDataBase();
					DB.getAdminDb(adminDB, admpassDB, linesAdmin,
							linesAdminPass);
					DB.getUserDb(userDB, passDB, lines, linesPass);

					for (int countUser = 0; countUser < lines.size(); countUser++) {
						for (int countUserPass = 0; countUserPass < linesPass
								.size(); countUserPass++) {
							if ((UTF.equals(lines.get(countUser)))
									&& (PTF.equals(linesPass.get(countUserPass)))) {
								MainPage mp = new MainPage();
								mp.u = UTF;
								dispose();
							}
							else{
									UserTF.setText("");
									PasswordTF.setText("");
			
								}
						}
						
					}

					for (int count = 0; count < linesAdmin.size(); count++) {
						for (int counts = 0; counts < linesAdminPass.size(); counts++) {
							if ((UTF.equals(linesAdmin.get(count)))
									&& (PTF.equals(linesAdminPass.get(counts)))) {
								AdminFrame a = new AdminFrame();
								dispose();
							}
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				RegisterFrame f = new RegisterFrame();
				dispose();
			}
		});

	}

	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
	        public void run() {
		
				try {
					Login frame = new Login();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			});
	}
}
