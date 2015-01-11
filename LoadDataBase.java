import java.awt.BorderLayout;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.ResultSetMetaData;

import java.sql.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public final class LoadDataBase {

	String idFlight, Source, Destination, weekday1, weekday2, weekday3,
			BusinessPrice, FirstPrice, EconomyPrice, Time, BagPrice1,
			BagPrice2, SeatsAvailable, DepartTime, ArrivalTime;
	int Seats;
	String SeatsString;
	// String userDB;
	// String passDB;
	String adminDB;
	String admpassDB;
	String userDB;
	String passDB;
	String currentURL;
	Connection con = null;
	PreparedStatement pst = null;
	Statement stmt = null;
	ResultSet rs = null;
	String url = "jdbc:mysql://localhost:3306/database";
	String user = "root";
	String password = "andrei";
	ResultSetMetaData md;
	ArrayList<String> lines = new ArrayList<String>();

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

	JScrollPane scrollPane = new JScrollPane(table);

	JTextField jtfFilter = new JTextField();
	JButton jbtFilter = new JButton("Filter");


	JPanel buttonPanel = new JPanel();
	JPanel tablePanel = new JPanel();
	JPanel tablePanelU = new JPanel();

	/**
	 * This function is used to set the url in order to connect to the database
	 * It has one parameter of type String
	 * 
	 * @param  url stores the url of the database and it is an IN parameter
	 *
	 */
	
	public void setHostURL(String url) {
		if (url.equals(currentURL)) {
			return;
		}
		closeDB();
		initDB(url, user, password);
		currentURL = url;
	}

	/**
	 * This function initialize the database
	 * Has three parameters of type string
	 * @param url parameter represents the url of database
	 * @param user represents the username in order to connect to the database
	 * @param password represents the password in order to connect to the database
	 * This function is used in several other functions
	 * The parameters are IN parameters;
	 */
	
	public void initDB(String url, String user, String password) {
		try {
			con = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			System.out.println("Could not initialize the database.");
			e.printStackTrace();
		}
	}
	
	/**
	 * This function is used to close the database and has no parameter
	 */
	
	public void closeDB() {
		try {
			if (pst != null) {
				pst.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			System.out.println("Could not close the current connection.");
			e.printStackTrace();
		}
	}

	/**
	 * This function get the users and their passwords stored in the database
	 * Has 4 parameteres,2 of type String and 2 Array Lists 
	 * The parameters are the OUT type
	 * @param userDB gets the users from database
	 * @param passDB gets the passwords from database 
	 * @param lines stores the users
	 * @param linesPass stores the passwords
	 * This function is used in AdminFrame class where is used by admin to view all user in database and are displayed in a table;
	 * It is used also in Login class to check if the user and password inserted by customer exist in database and 
	 * it is used also in RegisterFrame class.In order to register a new account.In order to do that the new user must not already 
	 * exists in the database
	 * @return the users details from the database if possible else returns an error
	 * @throws IOException
	 */
	
	void getUserDb(String userDB, String passDB, ArrayList<String> lines,
			ArrayList<String> linesPass) throws IOException {

		// Integer i = 0;
		setHostURL(url);
		initDB(url, user, password);
		try {
			pst = con.prepareStatement("SELECT*  FROM customersinfo");
			rs = pst.executeQuery();
			md = (ResultSetMetaData) rs.getMetaData();
			int columns = md.getColumnCount();

			// Get column names
			for (int i = 1; i <= columns; i++) {
				columnNamesU.add(md.getColumnName(i));
			}
			while (rs.next()) {
				userDB = rs.getString("user");
				passDB = rs.getString("password");
				lines.add(userDB);
				linesPass.add(passDB);

				rowU = new ArrayList(columns);
				for (int i = 1; i <= columns; i++) {
					rowU.add(rs.getObject(i));
				}
				dataU.add(rowU);
			}
			pst.close();

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		Vector columnNamesVectorU = new Vector();
		Vector dataVectorU = new Vector();

		for (int i = 0; i < dataU.size(); i++) {
			ArrayList subArray = (ArrayList) dataU.get(i);
			Vector subVector = new Vector();
			for (int j = 0; j < subArray.size(); j++) {
				subVector.add(subArray.get(j));
			}
			dataVectorU.add(subVector);
		}

		for (int i = 0; i < columnNamesU.size(); i++)
			columnNamesVectorU.add(columnNamesU.get(i));

		// Create table with database data
		JTable tableU = new JTable(dataVectorU, columnNamesVectorU) {
			public Class getColumnClass(int column) {
				
				/**
				 * This function get the columns of the database and creates a table0
				 * @param column gets the number of columns
				 * @return number of columns if there are else return an error 
				 */	
				
				for (int rowU = 0; rowU < getRowCount(); rowU++) {
					Object o = getValueAt(rowU, column);

					if (o != null) {
						return o.getClass();
					}
				}

				return Object.class;
			}
		};
		tableU.setEnabled(false);
		tablePanelU.setLayout(new BorderLayout());
		tablePanelU.setBounds(450, 150, 500, 200);
		JScrollPane scrollPane = new JScrollPane(tableU);
		tablePanelU.add(scrollPane, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		tablePanelU.add(buttonPanel, BorderLayout.SOUTH);

	}
	
	/** 
	 * This function gets the admin users from database and store them in an arraylist
	 * Its functionality is to check if the admin username and password exist in database 
	 * in order to perform a login operation as administrator of this app
	 * This function has 4 IN-OUT parameters,2 of type String and 2 of type ArrayList
	 * @param adminDB represents the admin\s username 
	 * @param admpassDB represents the admin\s password
	 * @param linesAdmin is used to store de username
	 * @param linesAdminPass is used to store the password
	 * This function is used in Login class
	 * @return the details of admin from the database if possible else return an error
	 * @throws IOException
	 */
	
	void getAdminDb(String adminDB, String admpassDB,
			ArrayList<String> linesAdmin, ArrayList<String> linesAdminPass)
			throws IOException {

		setHostURL(url);
		initDB(url, user, password);

		try {
			pst = con.prepareStatement("SELECT*  FROM admin");
			rs = pst.executeQuery();

			while (rs.next()) {
				adminDB = rs.getString("Admin");
				admpassDB = rs.getString("Password");
				linesAdmin.add(adminDB);
				linesAdminPass.add(admpassDB);
			}
			pst.close();

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}

	}
	
	/** 
	 * The ShowFlights function allows both admin and user to view all flights from the database
	 * Its parameters are OUT.All of them are strings
	 * @param idFlight gets from database where are stored flight's id;
	 * @param Source gets from database where are stored the departure cities;
	 * @param Destination gets from database where are stored the destination cites;
	 * @param weekday1,weekday2,weekday3 get from database where are stored the schedule of every flight;
	 * @param BusinessPrice,FirstPrice,EconomyPrice get from database the price of a ticket at business class,first class and economy class;
	 * @param Time gets from database the duration of every flight;
	 * @param DepartTime get from database the time of departure; 
	 * @param ArrivalTime get from database the time of arrival;
	 * This function is used in Admin,AdminFrame,Flight and User class;
	 * Also the details are displayed in a table
	 * This function is used in Admin class,AdminFrame class and Flight class
	 * @return the details from the database else returns an error
	 */
	
	void ShowFlights(String idFlight, String Source, String Destination,
			String weekday1, String weekday2, String weekday3,
			String BusinessPrice, String FirstPrice, String EconomyPrice,
			String Time, String DepartTime, String ArrivalTime) {
		

		setHostURL(url);
		initDB(url, user, password);

		try {
			pst = con.prepareStatement("SELECT*  FROM flights");
			rs = pst.executeQuery();

			md = (ResultSetMetaData) rs.getMetaData();
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
			pst.close();

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
		// final JTable table = new JTable(model);
		final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				table.getModel());
		table.setRowSorter(sorter);
		table.setEnabled(false);
		tablePanel.setLayout(new BorderLayout());
		tablePanel.setBounds(50, 200, 1300, 420);
		JScrollPane scrollPane = new JScrollPane(table);
		jtfFilter.setBounds(60, 380, 100, 20);
		JLabel search = new JLabel("Search : ");
		search.setBounds(10, 375, 100, 30);
		tablePanel.add(search);
		tablePanel.add(jtfFilter);
		jtfFilter.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			
			/** 
			 * This function is used search in the flight's table a specific flight 
			 * Gives notification that there was an insert into the document. The range given by the DocumentEvent bounds 
			 * the freshly inserted region.
			 * @param e - the document event
			 */
			
			public void insertUpdate(DocumentEvent e) {
				String text = jtfFilter.getText();

				if (text.trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}
  
			/**
			 * Gives notification that a portion of the document has been removed. The range is given 
			 * in terms of what the view last saw (that is, before updating sticky positions).
			 * @param  e - the document event
			 */
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				String text = jtfFilter.getText();

				if (text.trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			/** 
			 * Gives notification that an attribute or set of attributes changed.
			 * @param e - the document event
	 		 */			
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("Not supported yet."); 
			}

		});

		tablePanel.add(scrollPane, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		tablePanel.add(buttonPanel, BorderLayout.SOUTH);
	}

	/*
	
	void getBagPrice(String BagPrice1, String BagPrice2) {

		setHostURL(url);
		initDB(url, user, password);
		try {
			pst = con.prepareStatement("SELECT*  FROM flights");
			rs = pst.executeQuery();

			while (rs.next()) {
				BagPrice1 = rs.getString("1Bag");
				BagPrice2 = rs.getString("2Bag");
				System.out.printf("Prices are in euro %s %s\n", BagPrice1,
						BagPrice2);
			}

			pst.close();

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
*/
	/*
	void getTime(String Time, String DepTime, String ArrTime) {

		setHostURL(url);
		initDB(url, user, password);

		try {
			pst = con.prepareStatement("SELECT*  FROM flights");
			rs = pst.executeQuery();
			while (rs.next()) {
				Time = rs.getString("Time");
				DepTime = rs.getString("DepartTime");
				ArrTime = rs.getString("ArrivalTime");
			}

			pst.close();

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}
	
	*/
	
	
	
	/**
	 * This function gets from database the departure city,arrival city and seats available of the flights
	 * Has 6 parameters;3 Strings and 3 Array lists
	 * @param s represents the departure city
	 * @param d represents the arrival city
	 * @param SeatsString represents the avaiable seats 
	 * @param sline is an array list used to store the departure cities in order to be performed other actions in other classes
	 * @param dline is an array list used to store the arrival cities in order to be performed other actions in other classes
	 * @param linesString is an array list used to store the available seats in order to be performed other actions in other classes
	 * @param function is used in Flight class
	 * All of the parameters are OUT parameters
	 * @return the details else return an error if could not get the details from database
	 */

	void getSourceDest(String s, String d, String SeatsString,
			ArrayList<String> sline, ArrayList<String> dline,
			ArrayList<String> linesString) {
		try {
			pst = con.prepareStatement("SELECT*  FROM flights");
			rs = pst.executeQuery();
			while (rs.next()) {
				s = rs.getString("Source");
				d = rs.getString("Destination");
				if (!(sline.contains(s)))
					sline.add(s);
				if (!(dline.contains(d)))
					dline.add(d);
				SeatsString = rs.getString("SeatsAvailable");
				linesString.add(SeatsString);
			}

			pst.close();

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		}
		closeDB();
	}

}
