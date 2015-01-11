import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import java.awt.Component;
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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.FontUIResource;

public class MainPage extends JFrame {

	JPanel backgroundPanel = new JPanel();
	JButton BookAFlight = new JButton("BOOK A FLIGHT");
	//JButton ManageABooking = new JButton("MANAGE A BOOKING");
	//JButton CheckIn = new JButton("CHECK-IN ONLINE");
	JButton LogOut = new JButton("Log out");
	String u;

	public MainPage() throws IOException {
		super("Leg Airways");
		backgroundPanel.setBackground(Color.WHITE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		BufferedImage myPicture = ImageIO.read(new File("airplane.jpg"));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		picLabel.setBounds(0, 100, 1390, 750);

		Font font = new Font("Book Antiqua", Font.BOLD, 14);

		BookAFlight.setFont(font);
		//ManageABooking.setFont(font);
		//CheckIn.setFont(font);
		LogOut.setFont(font);

		BookAFlight.setBounds(100, 0, 180, 40);
		//ManageABooking.setBounds(280, 0, 200, 40);
		//CheckIn.setBounds(480, 0, 180, 40);
		LogOut.setBounds(480, 0, 180, 40);

		BookAFlight.setBackground(new Color(55, 25, 70));
		//ManageABooking.setBackground(new Color(55, 25, 70));
		//CheckIn.setBackground(new Color(55, 25, 70));
		LogOut.setBackground(new Color(55, 25, 70));

		BookAFlight.setForeground(Color.WHITE);
		//ManageABooking.setForeground(Color.WHITE);
		//CheckIn.setForeground(Color.WHITE);
		LogOut.setForeground(Color.WHITE);

		picLabel.add(BookAFlight);
		//picLabel.add(ManageABooking);
		//picLabel.add(CheckIn);
		picLabel.add(LogOut);

		backgroundPanel.setLayout(null);
		backgroundPanel.add(picLabel);

		getContentPane().add(backgroundPanel);

		BookAFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				Flight ff = null;
				try {
						ff = new Flight();
						ff.userLog.setText(u);
						ff.fuser = u;
					} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ff.setVisible(true);
				dispose();
			}
		});
		
	/*	ManageABooking.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try {
						Flight f = new Flight();
						f.back.setVisible(true);
						
						
						
						
						dispose();
						f.back.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent ae){
									try {										
											MainPage mp = new MainPage();
											dispose();
										} 
									catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}	
							}
						});
					} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				
			}
		});
		*/
		LogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					dispose();
					Login log = new Login();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
