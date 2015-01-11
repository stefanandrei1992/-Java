import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.JList;

public class Ticket extends JFrame {

	JLabel ClassLabel = new JLabel("Class");
	JLabel DepartureLabel = new JLabel("Departure");
	JLabel Depart = new JLabel();
	JLabel ArrivalLabel = new JLabel("Arrival");
	JLabel Arrive = new JLabel();
	JLabel Class = new JLabel("Class");
	JLabel ClassName = new JLabel();
	JLabel DurationLabel = new JLabel("Duration");
	JLabel CheckinLabel = new JLabel("PASSENGER AND BAGGAGE CHECK IN");
	JLabel BoardingPass = new JLabel("*****BOARDING PASS*****");
	JLabel Name = new JLabel("NAME OF PASSSENGER");
	JLabel Priority = new JLabel("PRIORITY ACCESS");
	JLabel Duration = new JLabel("Duration");
	JLabel FirstName = new JLabel();
	JLabel LastName = new JLabel();
	JLabel Seat = new JLabel("Seat");
	JLabel SeatNo = new JLabel();
	JLabel Gate = new JLabel("Gate");
	JLabel GateNo = new JLabel();
	JLabel Adults = new JLabel("Adults");
	JLabel NoAdults = new JLabel();
	JLabel Children = new JLabel("Children");
	JLabel NoChild = new JLabel();
	JLabel Infants = new JLabel("Infants");
	JLabel NoInfant = new JLabel();
	JLabel Bags = new JLabel("Bags");
	JLabel NoBags = new JLabel();

	JPanel ticketPanel = new JPanel();
	JPanel RestPanel = new JPanel();

	int randomNum, RandomNum;
	int minimum = 10;
	int maximum = 160;
	int min = 1;
	int max = 20;
	Border border;

	Ticket() {
		JFrame f = new JFrame();

		f.setSize(800, 400);

		ticketPanel.setBackground(Color.WHITE);
		ticketPanel.setLayout(null);

		Name.setBounds(50, 70, 150, 30);
		FirstName.setBounds(50, 90, 150, 30);
		LastName.setBounds(100, 90, 150, 30);
		DepartureLabel.setBounds(70, 110, 70, 30);
		Depart.setBounds(100, 130, 70, 30);
		ArrivalLabel.setBounds(70, 150, 70, 30);
		Arrive.setBounds(100, 170, 70, 30);
		Class.setBounds(70, 200, 70, 30);
		ClassName.setBounds(100, 220, 70, 30);
		Seat.setBounds(350, 70, 150, 30);
		SeatNo.setBounds(350, 90, 150, 30);
		Gate.setBounds(600, 70, 100, 20);
		GateNo.setBounds(600, 90, 100, 20);
		Adults.setBounds(600, 130, 100, 20);
		NoAdults.setBounds(600, 150, 100, 20);
		Children.setBounds(600, 170, 100, 20);
		NoChild.setBounds(600, 190, 100, 20);
		Infants.setBounds(600, 210, 100, 20);
		NoInfant.setBounds(600, 230, 100, 20);
		Bags.setBounds(600, 250, 100, 20);
		NoBags.setBounds(600, 270, 100, 20);

		randomNum = minimum + (int) (Math.random() * maximum);
		Random r = new Random();
		char c = (char) (r.nextInt(3) + 'a');
		String scr = Integer.toString(randomNum) + c;

		SeatNo.setText(scr);

		RandomNum = min + (int) (Math.random() * max);
		String rmn = Integer.toString(RandomNum);

		GateNo.setText(rmn);

		Priority.setBounds(240, 300, 900, 20);
		Font Prior = new Font("Arial Black", Font.BOLD, 25);
		Priority.setFont(Prior);

		BoardingPass.setBounds(280, 200, 500, 30);
		Font board = new Font("Arial", Font.BOLD, 16);
		BoardingPass.setFont(board);

		CheckinLabel.setBounds(30, 0, 500, 30);
		Font Checkin = new Font("Arial Black", Font.BOLD, 10);
		CheckinLabel.setFont(Checkin);

		ticketPanel.add(ClassLabel);
		ticketPanel.add(DepartureLabel);
		ticketPanel.add(FirstName);
		ticketPanel.add(LastName);
		ticketPanel.add(ArrivalLabel);
		ticketPanel.add(Class);
		ticketPanel.add(ClassName);
		ticketPanel.add(DurationLabel);
		ticketPanel.add(CheckinLabel);
		ticketPanel.add(Name);
		ticketPanel.add(Seat);
		ticketPanel.add(SeatNo);
		ticketPanel.add(Priority);
		ticketPanel.add(BoardingPass);
		ticketPanel.add(Gate);
		ticketPanel.add(GateNo);
		ticketPanel.add(Depart);
		ticketPanel.add(Arrive);

		ticketPanel.add(Children);
		ticketPanel.add(NoChild);
		ticketPanel.add(Infants);
		ticketPanel.add(NoInfant);
		ticketPanel.add(Bags);
		ticketPanel.add(NoBags);

		Children.setVisible(false);
		NoChild.setVisible(false);
		Infants.setVisible(false);
		NoInfant.setVisible(false);

		f.add(ticketPanel);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
