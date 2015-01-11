import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Flight extends JFrame {

	JComboBox CBClass, CBAdult, CBChildren, CBInfant, CBBookingDate, CBFrom,
			CBTo, CBBags;
	JLabel LBookingDate, LClass, LAdult, LChildren, LInfant, LBookingDetails,
			LPassengerDetails, LDate, LImg1, LImg2, LNotes, LBags;
	JLabel userLog = new JLabel();
	JLabel Log = new JLabel("You are logged in as : ");
	JLabel legend = new JLabel();
	JLabel legend1 = new JLabel();
	JLabel LFrom = new JLabel("From");
	JLabel LTo = new JLabel("To");
	JLabel LFlight = new JLabel("Flight's id");
	

	JTextField TFFrom = new JTextField();
	JTextField TFTo = new JTextField();
	JTextField TFid = new JTextField();

	JTextField TFBookingDate;
	Icon img1, img2;
	JButton BBookFlight;
	JButton back = new JButton("Back");
	JPanel PPanel1, PPanel2;
	String idFlight, Source, Destination, weekday1, weekday2, weekday3,
			BusinessPrice, FirstPrice, EconomyPrice, Time, BagPrice1,
			BagPrice2, SeatsAvailable, DepartTime, ArrivalTime;
	String s, d;
	String SeatsString, id;

	String[] sItem3 = { "Economic", "Business", "First" };
	String[] sBags = { "0", "1", "2", "3" };
	ArrayList<String> sline = new ArrayList();
	ArrayList<String> dline = new ArrayList();
	ArrayList<String> linesString = new ArrayList();
	// public String frm,to;
	// public String nick = "hk";
	int NumberOfSeats = 0;
	public String fuser;

	String[] dates = { "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY",
			"SATURDAY", "SUNDAY" };

	Container c = getContentPane();

	Flight() throws IOException {

		c.setLayout(new BorderLayout());

		PPanel1 = new JPanel(null);
		PPanel1.setPreferredSize(new Dimension(1400, 200));

		legend.setText("* Taxes for children are half from adults taxes");
		legend1.setText("* There are no taxes for infants");
		legend.setBounds(900, 620, 300, 20);
		legend1.setBounds(900, 650, 300, 20);

		LBookingDate = new JLabel("Booking Date");
		LClass = new JLabel("Class");
	

		CBClass = new JComboBox(sItem3);
		CBBookingDate = new JComboBox(dates);
		CBBags = new JComboBox(sBags);

		LAdult = new JLabel("Adults");

		LChildren = new JLabel("Children");
		LInfant = new JLabel("Infants");

		String[] item4 = { "0", "1", "2", "3" };
		CBAdult = new JComboBox(item4);

		String[] item5 = { "0", "1", "2", "3" };
		CBChildren = new JComboBox(item5);

		String[] item6 = { "0", "1", "2", "3" };
		CBInfant = new JComboBox(item6);

		LDate = new JLabel("(DD/MM/YYYY)");
		LDate.setForeground(Color.red);

		BBookFlight = new JButton("Book");

		JLabel LChildrenAge = new JLabel("2-11 yrs");
		Font LabelAge = new Font("BELL MT", Font.PLAIN, 16);
		LChildrenAge.setFont(LabelAge);

		JLabel LInfantAge = new JLabel("under 2 yrs");
		LInfantAge.setFont(LabelAge);

		Font details = new Font("Book Antiqua", Font.BOLD, 15);
		LBookingDate.setFont(details);
		LClass.setFont(details);

		LBookingDate.setBounds(20, 60, 100, 20);
		CBBookingDate.setBounds(20, 80, 100, 20);

		LClass.setBounds(150, 60, 100, 20);
		CBClass.setBounds(150, 80, 150, 20);

		LAdult.setBounds(350, 60, 100, 20);
		CBAdult.setBounds(350, 80, 40, 20);

		LChildren.setBounds(440, 60, 100, 20);
		CBChildren.setBounds(440, 80, 40, 20);
		LChildrenAge.setBounds(480, 80, 80, 20);

		LInfant.setBounds(550, 60, 100, 20);
		CBInfant.setBounds(550, 80, 40, 20);
		LInfantAge.setBounds(590, 80, 80, 20);

		BBookFlight.setBounds(20, 160, 100, 20);
		back.setBounds(150, 160, 100, 20);			
		
		LFrom.setBounds(20, 110, 100, 25);
		LTo.setBounds(150, 110, 100, 25);
		LFlight.setBounds(280, 110, 100, 25);
		
		TFFrom.setBounds(20,130,100,20);
		TFTo.setBounds(150,130,100,20);
		TFid.setBounds(280,130,100,20);

		Font newFont = new Font("Cambria", Font.BOLD, 18);
		userLog.setFont(newFont);
		Log.setFont(newFont);
		Log.setBounds(10, 10, 200, 30);
		userLog.setBounds(210, 10, 200, 30);

		JLabel LBags = new JLabel("Bags");
		LBags.setFont(details);
		LBags.setBounds(670, 60, 100, 20);
		CBBags.setBounds(670, 80, 40, 20);

		img2 = new ImageIcon("airplane.jpg");
		LImg2 = new JLabel(img2);
		LImg2.setBounds(0, 0, 1390, 750);

		LAdult.setFont(details);
		LChildren.setFont(details);
		LInfant.setFont(details);

		final LoadDataBase r = new LoadDataBase();
		r.ShowFlights(idFlight, Source, Destination, weekday1, weekday2,
				weekday3, BusinessPrice, FirstPrice, EconomyPrice, Time,
				DepartTime, ArrivalTime);
		getContentPane().add(r.tablePanel);
		PPanel1.add(r.tablePanel);

		PPanel1.add(userLog);
		PPanel1.add(Log);
		PPanel1.add(TFid);
		
		PPanel1.add(LFrom);
		PPanel1.add(LTo);
		PPanel1.add(LFlight);
		
		PPanel1.add(LBookingDate);
		PPanel1.add(CBBookingDate);
		PPanel1.add(LDate);
		PPanel1.add(LClass);
		PPanel1.add(CBClass);
		PPanel1.add(BBookFlight);

		PPanel1.add(LAdult);
		PPanel1.add(LChildren);
		PPanel1.add(LChildrenAge);
		PPanel1.add(LInfant);
		PPanel1.add(LInfantAge);
		PPanel1.add(CBAdult);
		PPanel1.add(CBChildren);
		PPanel1.add(CBInfant);
		PPanel1.add(legend);
		PPanel1.add(legend1);
		PPanel1.add(TFFrom);
		PPanel1.add(TFTo);
		PPanel1.add(LBags);
		PPanel1.add(CBBags);
		PPanel1.add(back);
		PPanel1.add(LImg2);
		PPanel1.setBackground(Color.white);
		c.add(PPanel1, BorderLayout.WEST);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(795, 580);
		setVisible(true);
		
		
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try {
					MainPage mp = new MainPage();
					dispose();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		BBookFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				CustomerFrame cf = null;
				final String frm, to;
				// final String nick = "hk";

				try {

					cf = new CustomerFrame();
					r.getSourceDest(s, d, SeatsString, sline, dline,
							linesString);
					frm = TFFrom.getText();
					to = TFTo.getText();
					id = TFid.getText();
					cf.suser = fuser;

					if (CBBags.getSelectedItem().equals("0")) {
						cf.bag = "0";
					}
					if (CBBags.getSelectedItem().equals("1")) {
						cf.bag = "1";
					}
					if (CBBags.getSelectedItem().equals("2")) {
						cf.bag = "2";
					}
					if (CBBags.getSelectedItem().equals("3")) {
						cf.bag = "3";
					}

					if (CBClass.getSelectedItem().equals("Economic")) {
						cf.ecn = "Economy";
					
					} else {
						if (CBClass.getSelectedItem().equals("Business")) {
							cf.ecn = "Business";
							
						} else {
							cf.ecn = "First";
						
						}
					}
					if (CBAdult.getSelectedItem().equals("0")) {
						cf.sadult = "0";

					}

					if (CBAdult.getSelectedItem().equals("1")) {
						cf.sadult = "1";
						NumberOfSeats++;
					}

					if (CBAdult.getSelectedItem().equals("2")) {
						cf.sadult = "2";
						NumberOfSeats = NumberOfSeats + 2;
					}

					if (CBAdult.getSelectedItem().equals("3")) {
						cf.sadult = "3";
						NumberOfSeats = NumberOfSeats + 3;
					}

					if (CBChildren.getSelectedItem().equals("0")) {
						cf.schild = "0";
					}

					if (CBChildren.getSelectedItem().equals("1")) {
						cf.schild = "1";
						NumberOfSeats = NumberOfSeats + 1;
					}
					if (CBChildren.getSelectedItem().equals("2")) {
						cf.schild = "2";
						NumberOfSeats = NumberOfSeats + 2;
					}
					if (CBChildren.getSelectedItem().equals("3")) {
						cf.schild = "3";
						NumberOfSeats = NumberOfSeats + 3;
					}

					if (CBInfant.getSelectedItem().equals("0")) {
						cf.sinfant = "0";
					}

					if (CBInfant.getSelectedItem().equals("1")) {
						cf.sinfant = "1";
						NumberOfSeats = NumberOfSeats + 1;
					}
					if (CBInfant.getSelectedItem().equals("2")) {
						cf.sinfant = "2";
						NumberOfSeats = NumberOfSeats + 2;
					}
					if (CBInfant.getSelectedItem().equals("3")) {
						cf.sinfant = "3";
						NumberOfSeats = NumberOfSeats + 3;
					}

					cf.SeatsAv = Integer.toString(NumberOfSeats);

					Admin admin = new Admin();
					admin.UpdateSeats(NumberOfSeats, id);

					for (int count = 0; count < sline.size(); count++) {
						for (int counts = 0; counts < dline.size(); counts++) {
							for (int c = 0; c < linesString.size(); c++) {
								if ((frm.equals(sline.get(count)))
										&& (to.equals(dline.get(counts)))) {
									cf.From = frm;
									cf.To = to;
									s = sline.get(count);
									d = dline.get(counts);

								}

							}
						}
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				cf.setVisible(true);
				dispose();
			}
		});
	}
}
