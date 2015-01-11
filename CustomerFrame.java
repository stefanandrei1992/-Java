import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

public class CustomerFrame extends JFrame {

	JPanel CustomerPanel = new JPanel();
	JPanel CustomerPanel1 = new JPanel();
	JTextField FirstName = new JTextField();
	JTextField LastName = new JTextField();
	JTextField PassNo = new JTextField();
	JTextField CityField = new JTextField();
	JTextField CountryF = new JTextField();
	JTextField PhoneF = new JTextField();

	String ecn, From, To, schild, sinfant, sadult, bag;
	String SeatsAv;
	JLabel FN = new JLabel("First Name");
	JLabel LN = new JLabel("Last Name");
	JLabel PassNr = new JLabel("Passport Number");
	JLabel City = new JLabel("City");
	JLabel Country = new JLabel("Country");
	JLabel Phone = new JLabel("Phone Number");

	JButton GetTicket = new JButton("Get Ticket");
	JButton GetBack = new JButton("Get Back");

	String nickname, Src, Dest, Seats;
	public String R;

	public String fn, ln, city, country, cphone, pasp;
	LoadXML xml = new LoadXML();
	public String suser;

	CustomerFrame() throws IOException {
		super("Login");
		CustomerPanel.setBackground(Color.WHITE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		CustomerPanel.setLayout(null);
		CustomerPanel.setBackground(Color.WHITE);

		BufferedImage myPicture = ImageIO.read(new File("cloud.jpg"));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture), JLabel.CENTER);
		picLabel.setBounds(0, 0, 1366, 720);
		// picLabel.setBounds(0,0,1366,768);
		getContentPane().add(picLabel);

		// TextFields
		FirstName.setBounds(400, 100, 100, 20);
		LastName.setBounds(400, 150, 100, 20);
		CityField.setBounds(400, 200, 100, 20);
		CountryF.setBounds(400, 250, 100, 20);
		PhoneF.setBounds(400, 300, 100, 20);
		PassNo.setBounds(400, 350, 100, 20);
		// Labels
		FN.setBounds(200, 100, 150, 20);
		LN.setBounds(200, 150, 150, 20);
		City.setBounds(200, 200, 150, 20);
		Country.setBounds(200, 250, 150, 20);
		Phone.setBounds(200, 300, 150, 20);
		PassNr.setBounds(200, 350, 150, 20);
		// Buttons
		GetTicket.setBounds(200, 400, 100, 30);
		GetBack.setBounds(200,450,100,30);
	
		Font Labels = new Font("Book Antiqua", Font.BOLD, 14);

		FN.setFont(Labels);
		LN.setFont(Labels);
		City.setFont(Labels);
		Country.setFont(Labels);
		Phone.setFont(Labels);
		PassNr.setFont(Labels);

		FirstName.setFont(Labels);
		LastName.setFont(Labels);
		CityField.setFont(Labels);
		CountryF.setFont(Labels);
		PhoneF.setFont(Labels);
		PassNo.setFont(Labels);

		getContentPane().add(FirstName);
		getContentPane().add(LastName);
		getContentPane().add(PassNo);
		getContentPane().add(CityField);
		getContentPane().add(CountryF);
		getContentPane().add(PhoneF);

		getContentPane().add(FN);
		getContentPane().add(LN);
		getContentPane().add(PassNr);
		getContentPane().add(City);
		getContentPane().add(Country);
		getContentPane().add(Phone);
		getContentPane().add(GetTicket);
		getContentPane().add(GetBack);

		getContentPane().add(CustomerPanel);
		CustomerPanel.add(picLabel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		GetBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try {
					Flight f = new Flight();
					dispose();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		GetTicket.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
							
				User u = new User();
				fn = FirstName.getText();
				ln = LastName.getText();
				city = CityField.getText();
				country = CountryF.getText();
				cphone = PhoneF.getText();
				pasp = PassNo.getText();
				


				xml.Firstname = fn;
				xml.Lastname = ln;
				xml.Dept = From;
				xml.Arivt = To;
				xml.Cls = ecn;
				xml.Nost = SeatsAv;
				xml.adult = sadult;
				xml.child = schild;
				xml.inf = sinfant;
				xml.town = city;
				xml.origin = country;
				xml.cphone = cphone;
				xml.paspNo = pasp;
				xml.bags = bag;

				
				if(!((fn.isEmpty())||(ln.isEmpty())||(city.isEmpty())||(country.isEmpty())||(cphone.isEmpty())||(pasp.isEmpty()))){
				
					Ticket t = new Ticket();	

					t.FirstName.setText(fn);
					t.LastName.setText(ln);
					t.ClassName.setText(ecn);
					t.Depart.setText(From);
					t.Arrive.setText(To);
					t.NoAdults.setText(sadult);
					t.NoChild.setText(schild);
					t.NoInfant.setText(sinfant);
					t.Adults.setVisible(true);
					t.NoAdults.setVisible(true);
					t.Children.setVisible(true);
					t.NoChild.setVisible(true);
					t.Infants.setVisible(true);
					t.NoInfant.setVisible(true);
					t.NoBags.setVisible(true);
					t.Bags.setVisible(true);
					t.NoBags.setText(bag);
					
				try {
					xml.process(fn, ln, From, To, ecn, SeatsAv, sadult, schild,
							sinfant, suser, city, country, cphone, pasp, bag);
					//xml.CreateXML(fn, ln, From, To, ecn, SeatsAv, sadult, schild, sinfant, suser, city, country, cphone, pasp, bag);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DOMException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				int confirm = JOptionPane.showOptionDialog(CustomerPanel,
                        "Do you want to leave a feedback?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == JOptionPane.YES_OPTION) {
                	FeedFrame f = new FeedFrame();
    				dispose();                    
                }
                else{
                	dispose();
                }
				
				try {
					MainPage mp = new MainPage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
			}
				else{
					try {
						dispose();
						JOptionPane.showMessageDialog(CustomerPanel, "Do not leave any field blank!");
						CustomerFrame c = new CustomerFrame();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				
			}
		});

	}	 
}
