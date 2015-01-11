import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class AdminFrame extends JFrame {

	JPanel adminPanel = new JPanel();
	JPanel backPanel = new JPanel();

	JLabel Comment = new JLabel("Admin Control Panel");

	JButton ViewFlights = new JButton("View Flights");
	JButton AddFlights = new JButton("Add Flights");
	JButton DeleteFlights = new JButton("Delete Flights");
	JButton ViewFeed = new JButton("View Feedback");
	JButton ViewBooks = new JButton("View Bookings");
	JButton ViewUsers = new JButton("View Users");
	JButton SetBagPrice = new JButton("Set Bag Price");

	JLabel FlightID = new JLabel("idFlight");
	JLabel Src = new JLabel("Depart From");
	JLabel Arr = new JLabel("Arrive At");

	JTextField FTF = new JTextField();
	JTextField STF = new JTextField();
	JTextField ATF = new JTextField();
	String SFTF, SSTF, SATF;

	JLabel wk1 = new JLabel("weekday1");
	JLabel wk2 = new JLabel("weekday2");
	JLabel wk3 = new JLabel("weekday3");
	JLabel bp = new JLabel("Business Price");
	JLabel fp = new JLabel("First Price");
	JLabel ep = new JLabel("Economy Price");
	JLabel tm = new JLabel("Time");
	JLabel sa = new JLabel("Seats Available");
	JLabel b1 = new JLabel("Bag Price/1");
	JLabel b2 = new JLabel("Bag Price/2");
	JLabel dt = new JLabel("Depart Time");
	JLabel at = new JLabel("Arrival Time");

	JTextField w1 = new JTextField();
	JTextField w2 = new JTextField();
	JTextField w3 = new JTextField();
	JTextField btf = new JTextField();
	JTextField ff = new JTextField();
	JTextField etf = new JTextField();
	JTextField tmtf = new JTextField();
	JTextField satf = new JTextField();
	JTextField b1tf = new JTextField();
	JTextField b2tf = new JTextField();
	JTextField dttf = new JTextField();
	JTextField adtf = new JTextField();

	public String id;
	JLabel DeleteLabel = new JLabel("Enter the flight's id you want to delete");
	JTextField DeleteTF = new JTextField();

	String Sw1, Sw2, Sw3, Stm, Sdt, Sat;
	int isa;// SeatAvailable
	int ibp, ifp, iep;// Prices
	int ib1, ib2;// BagPrices

	BufferedImage myPicture;
	JLabel picLabel = new JLabel();

	// LoadDataBase ld = new LoadDataBase();
	ArrayList columnNames = new ArrayList();
	ArrayList data = new ArrayList();

	ArrayList columnNamesU = new ArrayList();
	ArrayList dataU = new ArrayList();

	ArrayList row;
	ArrayList rowU;

	Vector columnNamesVector = new Vector();
	Vector dataVector = new Vector();

	Vector columnNamesVectorU = new Vector();
	Vector dataVectorU = new Vector();

	JTable tableU = new JTable(dataVectorU, columnNamesVectorU);
	JTable table = new JTable(dataVector, columnNamesVector);

	Vector columns = new Vector();
	Vector datas = new Vector();

	String feedback;
	JLabel feeds;

	LoadDataBase l = new LoadDataBase();
	Admin a = new Admin();
	LoadXML xml = new LoadXML();

	JButton GO = new JButton("GO");
	JButton Update = new JButton("Update");
	JButton ButtonDel = new JButton("Delete");
	JButton Back = new JButton("Back");
	JButton LogOut = new JButton("Log out");

	String idFlight, Source, Destination, weekday1, weekday2, weekday3,
			BusinessPrice, FirstPrice, EconomyPrice, Time, BagPrice1,
			BagPrice2, SeatsAvailable, DepartTime, ArrivalTime;

	String userDB, passDB;
	String User, Pass;
	ArrayList<String> lines = new ArrayList();
	ArrayList<String> linesPass = new ArrayList();

	JFrame admin;

	AdminFrame() throws IOException {
		super("Login");
		admin = this;
		backPanel.setBackground(Color.WHITE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		backPanel.setLayout(null);

		myPicture = ImageIO.read(new File("cloud.jpg"));
		picLabel = new JLabel(new ImageIcon(myPicture), JLabel.CENTER);
		picLabel.setBounds(0, 0, 1366, 720);
		// picLabel.setBounds(0,0,1366,768);
		getContentPane().add(picLabel);

		Comment.setBounds(100, 0, 700, 100);
		Font F = new Font("BELL MT", Font.BOLD, 50);
		Comment.setFont(F);

		ViewFlights.setBounds(30, 100, 150, 30);
		AddFlights.setBounds(180, 100, 150, 30);
		DeleteFlights.setBounds(330, 100, 150, 30);
		ViewFeed.setBounds(480, 100, 150, 30);
		ViewBooks.setBounds(630, 100, 150, 30);
		ViewUsers.setBounds(780, 100, 150, 30);
		SetBagPrice.setBounds(930, 100, 150, 30);

		GO.setBounds(400, 550, 100, 30);
		Back.setBounds(1000, 100, 150, 30);
		LogOut.setBounds(1150, 100, 150, 30);

		Back.setVisible(false);

		Update.setVisible(false);

		FlightID.setBounds(50, 200, 100, 20);
		Src.setBounds(50, 250, 100, 20);
		Arr.setBounds(50, 300, 100, 20);
		wk1.setBounds(50, 350, 100, 20);
		wk2.setBounds(50, 400, 100, 20);
		wk3.setBounds(50, 450, 100, 20);

		bp.setBounds(280, 200, 100, 20);
		fp.setBounds(280, 250, 100, 20);
		ep.setBounds(280, 300, 100, 20);
		tm.setBounds(280, 350, 100, 20);
		sa.setBounds(280, 400, 100, 20);

		b1.setBounds(520, 200, 100, 20);
		b2.setBounds(520, 250, 100, 20);
		dt.setBounds(520, 300, 100, 20);
		at.setBounds(520, 350, 100, 20);

		FTF.setBounds(160, 200, 100, 20);
		STF.setBounds(160, 250, 100, 20);
		ATF.setBounds(160, 300, 100, 20);
		w1.setBounds(160, 350, 100, 20);
		w2.setBounds(160, 400, 100, 20);
		w3.setBounds(160, 450, 100, 20);

		btf.setBounds(400, 200, 100, 20);
		ff.setBounds(400, 250, 100, 20);
		etf.setBounds(400, 300, 100, 20);
		tmtf.setBounds(400, 350, 100, 20);
		satf.setBounds(400, 400, 100, 20);

		b1tf.setBounds(640, 200, 100, 20);
		b2tf.setBounds(640, 250, 100, 20);
		dttf.setBounds(640, 300, 100, 20);
		adtf.setBounds(640, 350, 100, 20);

		DeleteLabel.setBounds(50, 200, 500, 20);
		DeleteTF.setBounds(50, 250, 100, 20);
		ButtonDel.setBounds(50, 300, 100, 20);
		ButtonDel.setVisible(false);

		picLabel.add(Update);
		picLabel.add(DeleteLabel);
		picLabel.add(DeleteTF);
		picLabel.add(ButtonDel);
		picLabel.add(SetBagPrice);

		DeleteLabel.setVisible(false);
		DeleteTF.setVisible(false);

		picLabel.add(ViewFlights);
		picLabel.add(AddFlights);
		picLabel.add(DeleteFlights);
		picLabel.add(ViewFeed);
		picLabel.add(ViewBooks);
		picLabel.add(ViewUsers);

		picLabel.add(FlightID);
		picLabel.add(FTF);
		picLabel.add(Src);
		picLabel.add(STF);
		picLabel.add(Arr);
		picLabel.add(ATF);
		picLabel.add(wk1);
		picLabel.add(w1);
		picLabel.add(wk2);
		picLabel.add(w2);
		picLabel.add(wk3);
		picLabel.add(w3);

		picLabel.add(bp);
		picLabel.add(btf);

		picLabel.add(fp);
		picLabel.add(ff);

		picLabel.add(ep);
		picLabel.add(etf);

		picLabel.add(tm);
		picLabel.add(tmtf);

		picLabel.add(sa);
		picLabel.add(satf);

		picLabel.add(b1);
		picLabel.add(b1tf);

		picLabel.add(b2);
		picLabel.add(b2tf);

		picLabel.add(dt);
		picLabel.add(dttf);

		picLabel.add(at);
		picLabel.add(adtf);

		picLabel.add(GO);
		picLabel.add(Back);
		picLabel.add(LogOut);

		FlightID.setVisible(false);
		FTF.setVisible(false);
		Src.setVisible(false);
		STF.setVisible(false);
		Arr.setVisible(false);
		ATF.setVisible(false);
		wk1.setVisible(false);
		w1.setVisible(false);
		wk2.setVisible(false);
		w2.setVisible(false);
		wk3.setVisible(false);
		w3.setVisible(false);
		bp.setVisible(false);
		btf.setVisible(false);
		fp.setVisible(false);
		ff.setVisible(false);
		ep.setVisible(false);
		etf.setVisible(false);
		tm.setVisible(false);
		tmtf.setVisible(false);
		sa.setVisible(false);
		satf.setVisible(false);
		b1.setVisible(false);
		b1tf.setVisible(false);
		b2.setVisible(false);
		b2tf.setVisible(false);
		dt.setVisible(false);
		dttf.setVisible(false);
		at.setVisible(false);
		adtf.setVisible(false);

		GO.setVisible(false);

		AddFlights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				FlightID.setVisible(true);
				FTF.setVisible(true);
				Src.setVisible(true);
				STF.setVisible(true);
				Arr.setVisible(true);
				ATF.setVisible(true);
				wk1.setVisible(true);
				w1.setVisible(true);
				wk2.setVisible(true);
				w2.setVisible(true);
				wk3.setVisible(true);
				w3.setVisible(true);
				bp.setVisible(true);
				btf.setVisible(true);
				fp.setVisible(true);
				ff.setVisible(true);
				ep.setVisible(true);
				etf.setVisible(true);
				tm.setVisible(true);
				tmtf.setVisible(true);
				sa.setVisible(true);
				satf.setVisible(true);
				b1.setVisible(true);
				b1tf.setVisible(true);
				b2.setVisible(true);
				b2tf.setVisible(true);
				dt.setVisible(true);
				dttf.setVisible(true);
				at.setVisible(true);
				adtf.setVisible(true);

				Back.setVisible(true);
				ViewFlights.setVisible(false);
				ViewFeed.setVisible(false);
				DeleteFlights.setVisible(false);
				// AddFlights.setVisible(false);
				ViewBooks.setVisible(false);
				ViewUsers.setVisible(false);
				SetBagPrice.setVisible(false);

				GO.setVisible(true);
				GO.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent aet) {

						try {
							SFTF = FTF.getText();
							SSTF = STF.getText();
							SATF = ATF.getText();
							Sw1 = w1.getText();
							Sw2 = w2.getText();
							Sw3 = w3.getText();
							Stm = tmtf.getText();
							Sdt = dttf.getText();
							Sat = adtf.getText();
							String Ssa = satf.getText();

							isa = Integer.parseInt(Ssa);

							String Sbp, Sfp, Sep;
							Sbp = btf.getText();
							Sfp = ff.getText();
							Sep = etf.getText();
							ibp = Integer.parseInt(Sbp);
							ifp = Integer.parseInt(Sfp);
							iep = Integer.parseInt(Sep);

							String Sb1, Sb2;
							Sb1 = b1tf.getText();
							Sb2 = b2tf.getText();
							ib1 = Integer.parseInt(Sb1);
							ib2 = Integer.parseInt(Sb2);

							a.InsertFlights(SFTF, SSTF, SATF, Sw1, Sw2, Sw3,
									ibp, ifp, iep, Stm, isa, ib1, ib2, Sdt, Sat);
							JOptionPane.showMessageDialog(backPanel,
									"Flight added successfuly!");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
		});

		ViewUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {

					l.getUserDb(userDB, passDB, lines, linesPass);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ViewFlights.setVisible(false);
				ViewFeed.setVisible(false);
				DeleteFlights.setVisible(false);
				AddFlights.setVisible(false);
				ViewBooks.setVisible(false);
				SetBagPrice.setVisible(false);
				Back.setVisible(true);
				getContentPane().add(l.tablePanelU);
				l.tablePanelU.setVisible(true);
				picLabel.add(l.tablePanelU);
				revalidate();
			}
		});

		ViewFlights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				l.ShowFlights(idFlight, Source, Destination, weekday1,
						weekday2, weekday3, BusinessPrice, FirstPrice,
						EconomyPrice, Time, DepartTime, ArrivalTime);

				Back.setVisible(true);
				ViewFeed.setVisible(false);
				DeleteFlights.setVisible(false);
				AddFlights.setVisible(false);
				ViewBooks.setVisible(false);
				ViewUsers.setVisible(false);
				SetBagPrice.setVisible(false);
				getContentPane().add(l.tablePanel);
				l.tablePanel.setVisible(true);
				picLabel.add(l.tablePanel);
				revalidate();

			}

		});
		ViewFeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {

					a.ViewFeedback(feedback);
					ViewFlights.setVisible(false);
					DeleteFlights.setVisible(false);
					AddFlights.setVisible(false);
					ViewBooks.setVisible(false);
					ViewUsers.setVisible(false);
					SetBagPrice.setVisible(false);
					Back.setVisible(true);
					getContentPane().add(a.tablePanel);
					a.tablePanel.setVisible(true);
					picLabel.add(a.tablePanel);
					revalidate();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		DeleteFlights.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				DeleteLabel.setVisible(true);
				DeleteTF.setVisible(true);
				ButtonDel.setVisible(true);
				Back.setVisible(true);
				ViewFlights.setVisible(false);
				ViewFeed.setVisible(false);
				AddFlights.setVisible(false);
				ViewBooks.setVisible(false);
				ViewUsers.setVisible(false);
				SetBagPrice.setVisible(false);
				ButtonDel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {

						id = DeleteTF.getText();
						if (id.isEmpty()) {
							JOptionPane.showMessageDialog(backPanel,
									"Enter a flight id");
						} else {
							a.DeleteFlights(id);
							JOptionPane.showMessageDialog(backPanel,
									"Flight successfuly deleted!");
						}

					}
				});
			}
		});

		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ViewFlights.setVisible(true);
				ViewFeed.setVisible(true);
				DeleteFlights.setVisible(true);
				AddFlights.setVisible(true);
				ViewBooks.setVisible(true);
				ViewUsers.setVisible(true);
				SetBagPrice.setVisible(true);
				Update.setVisible(false);
				l.tablePanel.setVisible(false);
				l.tablePanelU.setVisible(false);
				a.tablePanel.setVisible(false);
				FlightID.setVisible(false);
				FTF.setVisible(false);
				Src.setVisible(false);
				STF.setVisible(false);
				Arr.setVisible(false);
				ATF.setVisible(false);
				wk1.setVisible(false);
				w1.setVisible(false);
				wk2.setVisible(false);
				w2.setVisible(false);
				wk3.setVisible(false);
				w3.setVisible(false);
				bp.setVisible(false);
				btf.setVisible(false);
				fp.setVisible(false);
				ff.setVisible(false);
				ep.setVisible(false);
				etf.setVisible(false);
				tm.setVisible(false);
				tmtf.setVisible(false);
				sa.setVisible(false);
				satf.setVisible(false);
				b1.setVisible(false);
				b1tf.setVisible(false);
				b2.setVisible(false);
				b2tf.setVisible(false);
				dt.setVisible(false);
				dttf.setVisible(false);
				at.setVisible(false);
				adtf.setVisible(false);
				GO.setVisible(false);
				DeleteLabel.setVisible(false);
				DeleteTF.setVisible(false);
				ButtonDel.setVisible(false);
				Back.setVisible(false);
				revalidate();
			}
		});

		ViewBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae)

			{

				ViewFlights.setVisible(false);
				ViewFeed.setVisible(false);
				AddFlights.setVisible(false);
				DeleteFlights.setVisible(false);
				ViewUsers.setVisible(false);
				SetBagPrice.setVisible(false);
				Back.setVisible(true);
				String fileURL = "file:///C:/Users/Andrei/workspace/AirlineTicketService/createFile.xml";
				if (Desktop.isDesktopSupported()) {
					try {
						java.awt.Desktop.getDesktop().browse(
								new java.net.URI(fileURL));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				getContentPane().add(xml.tablePanel);
				xml.tablePanel.setVisible(true);
				picLabel.add(xml.tablePanel);
				revalidate();

			}

		});

		SetBagPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Back.setVisible(true);
				ViewFlights.setVisible(false);
				ViewFeed.setVisible(false);
				DeleteFlights.setVisible(false);
				AddFlights.setVisible(false);
				ViewBooks.setVisible(false);
				ViewUsers.setVisible(false);
				SetBagPrice.setVisible(false);

				Update.setVisible(true);
				FlightID.setVisible(true);
				FTF.setVisible(true);
				b1.setVisible(true);
				b1tf.setVisible(true);
				Back.setVisible(true);

				Update.setBounds(200, 280, 100, 20);

				FlightID.setText("Enter flight's id");
				FTF.setBounds(200, 200, 100, 20);
				b1.setText("Enter the new price");
				b1.setBounds(50, 230, 150, 20);
				b1tf.setBounds(200, 230, 100, 20);

				Update.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						SFTF = FTF.getText();
						String Sb1;
						Sb1 = b1tf.getText();
						ib1 = Integer.parseInt(Sb1);
						a.SetBagsPrice(ib1, SFTF);
						JOptionPane.showMessageDialog(backPanel,
								"Update successfuly!");

					}
				});

			}
		});

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

		backPanel.add(picLabel);
		getContentPane().add(Comment);
		getContentPane().add(backPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}
}
