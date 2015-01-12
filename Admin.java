import java.awt.BorderLayout;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class Admin extends JFrame {

	// Database needs//
	String url = "jdbc:mysql://localhost:3306/database";
	String user = "root";
	String password = "andrei";
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	Statement stmt = null;
	String feedback;

	ResultSetMetaData md;
	ArrayList<String> lines = new ArrayList<String>();
	ArrayList columnNames = new ArrayList();
	ArrayList data = new ArrayList();
	ArrayList row;
	Vector columnNamesVector = new Vector();
	Vector dataVector = new Vector();

	JPanel tablePanel = new JPanel();
	JPanel p = new JPanel();

	String idFlight, Source, Destination, weekday1, weekday2, weekday3,
			BusinessPrice, FirstPrice, EconomyPrice, Time, BagPrice1,
			BagPrice2, SeatsAvailable, DepartTime, ArrivalTime;
	String User, Pass;

	Scanner scn = new Scanner(System.in);
	LoadDataBase l = new LoadDataBase();

/** 
 * The ShowFlights function allows admin to view all flights from the database
 * Its parameters are OUT.All of them are strings
 * @param idFlight gets from database where are stored flight's id;
 * @param Source gets from database where are stored the departure cities;
 * @param Destination gets from database where are stored the destination cites;
 * @param weekday1 gets from database where are stored the schedule of every flight;
 * @param weekday2 gets from database where are stored the schedule of every flight;
 * @param weekday3 gets from database where are stored the schedule of every flight;
 * @param BusinessPrice gets from database the price of a ticket at business class;
 * @param FirstPrice gets from database the price of a ticket at first class;
 * @param EconomyPrice gets from database the price of a ticket at economy class;
 * @param Time gets from database the duration of every flight;
 * @param DepartTime gets from database the time of departure;
 * @param ArrivalTime gets from database the time of arrival;
 * This function is called from AdminFrame class*/
	
	public void ShowFlights(String idFlight, String Source, String Destination,
			String weekday1, String weekday2, String weekday3,
			String BusinessPrice, String FirstPrice, String EconomyPrice,
			String Time, String DepartTime, String ArrivalTime) {
		
			l.ShowFlights(idFlight, Source, Destination, weekday1, weekday2,
				weekday3, BusinessPrice, FirstPrice, EconomyPrice, Time,
				DepartTime, ArrivalTime);
	}
	
/**
 * This function allows the admin to insert in the database a new flight;
 * In order to do that the admin must provide some details about the flight;
 * The parameters of this function are strings and integers and are IN parameters;
 * @param SFTF is the flight's id;
 * @param SSTF is the departure city;
 * @param SATF is the arrival city;
 * @param Sw1 are the day of the week when the flight is available;
 * @param Sw2 are the day of the week when the flight is available;
 * @param Sw3 are the day of the week when the flight is available;
 * @param ibp is the price for a ticket at business class;
 * @param ifp is the price for a ticket at first class;
 * @param iep is the price for a ticket at economy class;
 * @param Stm is the duration of the flight;
 * @param isa represents the available seats;
 * @param ib1 is the price for 1 bag;
 * @param ib2 is the price for 2 bags;
 * @param Sdt represents the departure time of the flight;
 * @param Sat represents the arrival time of the flight;
 * @exception IOException
 * This function is called in AdminFrame class;
 */
	public void InsertFlights(String SFTF, String SSTF, String SATF,
			String Sw1, String Sw2, String Sw3, int ibp, int ifp, int iep,
			String Stm, int isa, int ib1, int ib2, String Sdt, String Sat)
			throws IOException

	{

		l.setHostURL(url);	/*This function is implemented in the LoadDataBase class and sets the url in order to connect at the database
							*url is a String and represents the url itself
							*/
		l.initDB(url, user, password); /*This function is implemented in the LoadDataBase class and initialize the database
		 								*Its parameters are Strings and are predefined;
		 								*url is the url of database;
		 								*user and password are the credentials used in order to connect and intialize the database
		 								*/

		try {

			con = DriverManager.getConnection(url, user, password);
			stmt = (Statement) con.createStatement();
			String sql = "INSERT INTO flights (idFlights,Source,Destination,weekday1,weekday2,weekday3,"
					+ "BusinessPrice,FirstPrice,EconomyPrice,Time,SeatsAvailable,1Bag,2Bag,DepartTime,ArrivalTime ) "
					+ "VALUES ('"
					+ SFTF
					+ "','"
					+ SSTF
					+ "','"
					+ SATF
					+ "','"
					+ Sw1
					+ "','"
					+ Sw2
					+ "','"
					+ Sw3
					+ "','"
					+ ibp
					+ "','"
					+ ifp
					+ "','"
					+ iep
					+ "','"
					+ Stm
					+ "','"
					+ isa
					+ "','"
					+ ib1
					+ "','"
					+ ib2 + "','" + Sdt + "','" + Sat + "')";

			stmt.executeUpdate(sql);
			stmt.close();

			l.closeDB();

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
			System.out.println("Connection Failed! Check output console");
			return;
		}

	}

/**
 * This function allows the admin to delete a specific flight;
 * Admin must insert the flight's id in order to delete the flight
 * The function has one parameter;
 * @param  id represents the id of the flight
 * This function is called in AdminFrame class;
 */	
	
	public void DeleteFlights(String id) {
		l.setHostURL(url);
		l.initDB(url, user, password);
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = (Statement) con.createStatement();
			stmt.execute("DELETE FROM flights WHERE idFlights = '" + id + "' ");
			l.closeDB();
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
			System.out.println("Connection Failed! Check output console");
			return;

		}
	}
	
	/**
	 * This function allows the administrator to set a new price for a bag;
	 * Has two parameters of type IN;
	 * @param bag represents the cost for one bag
	 * @param idFlight represents the flight's id where the new bag price should be
	 * This function is called in AdminFrame
	 */ 

	public void SetBagsPrice(int bag, String idFlight) {

		l.setHostURL(url);
		l.initDB(url, user, password);
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = (Statement) con.createStatement();
			String sql = "UPDATE flights SET 1bag = '" + bag
					+ "' WHERE idFlights = '" + idFlight + "' ";
			stmt.executeUpdate(sql);
			stmt.close();
			l.closeDB();
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
			System.out.println("Connection Failed! Check output console");
			return;
		}
	}
	
/** This function functionality is to update the seats available for a flight after a booking has been made
 * Its parameteres are an integer and a string;
 * 
 * @param seats represents the number of seats booked for a flight;
 * @param idFlight represents the flight's id where the seats were booked;
 * 
 * This function is called in AdminFrame class;
 */
	
	public void UpdateSeats(int seats, String idFlight) {

		l.setHostURL(url);
		l.initDB(url, user, password);
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = (Statement) con.createStatement();
			String sql = "UPDATE flights SET SeatsAvailable = SeatsAvailable - "+seats+"  WHERE idFlights =  '"+idFlight+"' ";
			stmt.executeUpdate(sql);
			stmt.close();
			l.closeDB();
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
			System.out.println("Connection Failed! Check output console");
			return;
		}

	}
	/**
	 * This function is used by admin in order to view the feedbacks left by customers;
	 * Has one parameter of type string which get from database the feedbacks;
	 * This function also display the feedbacks in a table;
	 * @param feedback is an OUT parameter
	 * @exception IOException
	 * This function is called in AdminFrame class;
	 */
	
	public void ViewFeedback(String feedback) throws IOException {
		l.setHostURL(url);
		l.initDB(url, user, password);
		try {

			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement("SELECT*  FROM feedback");
			rs = pst.executeQuery();
			ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
			int columns = md.getColumnCount();

			// Get column names
			for (int i = 1; i <= columns; i++) {
				columnNames.add(md.getColumnName(i));
			}

			while (rs.next()) {
				row = new ArrayList(columns);

				for (int i = 1; i <= columns; i++) {
					row.add(rs.getObject(i));
				}

				data.add(row);
			}

			// l.closeDB();

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		Vector columnNamesVector = new Vector();
		Vector dataVector = new Vector();

		for (int i = 0; i < data.size(); i++) {
			ArrayList subArray = (ArrayList) data.get(i);
			Vector subVector = new Vector();
			for (int j = 0; j < subArray.size(); j++) {
				subVector.add(subArray.get(j));
			}
			dataVector.add(subVector);
		}

		for (int i = 0; i < columnNames.size(); i++)
			columnNamesVector.add(columnNames.get(i));

		// Create table with database data
		JTable table = new JTable(dataVector, columnNamesVector) {
			
		/**
		 * This function get the columns of the database and creates a table0
		 * @param column gets the number of columns
		 * @return number of columns if there are else return an error 
		 */	
			public Class getColumnClass(int column) {
				for (int row = 0; row < getRowCount(); row++) {
					Object o = getValueAt(row, column);

					if (o != null) {
						return o.getClass();
					}
				}

				return Object.class;
			}
		};

		tablePanel.setLayout(new BorderLayout());
		tablePanel.setBounds(400, 150, 700, 250);
		JScrollPane scrollPane = new JScrollPane(table);
		tablePanel.add(scrollPane, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		tablePanel.add(buttonPanel, BorderLayout.SOUTH);
		table.setEnabled(false);

	}
}
