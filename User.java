import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class User extends JFrame {

	public String user = "root";
	public String password = "andrei";
	public String url = "jdbc:mysql://localhost:3306/database";
	String userDB;
	String passDB;
	String idFlight, Source, Destination, weekday1, weekday2, weekday3,
			BusinessPrice, FirstPrice, EconomyPrice, Time, BagPrice1,
			BagPrice2, SeatsAvailable, DepartTime, ArrivalTime;
	LoadDataBase l = new LoadDataBase();
	Connection con = null;
	PreparedStatement pst = null;
	Statement stmt = null;
	ResultSet rs = null;

	/**
	 * This function allows a new customer to create an account and insert the new credentials in the database
	 * Has 2 IN parameters of type String
	 * @param NewUser is the username the customer wants to have
	 * @param NewPassword is the password of the desired username
	 * @throws IOException
	 * This function is used in CustomerFrame class
	 */
	
	public void CreateNewAccount(String NewUser, String NewPassword)
			throws IOException {

		l.setHostURL(url);
		l.initDB(url, user, password);
		try {
			con = DriverManager.getConnection(url, user, password);

			stmt = con.createStatement();

			String sql = "INSERT INTO customersinfo (user, password) "
					+ "VALUES ('" + NewUser + "','" + NewPassword + "')";

			stmt.executeUpdate(sql);

			stmt.close();

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
			System.out.println("Connection Failed! Check output console");
			return;
		}
	}

	/** 
	 * This function allows users to send a feedback about their experience with this app
	 * Has one IN parameter of type String
	 * @param Feed represents the feedback left by user
	 * This feedback is inserted in the database
	 * The function is used in FeedFrame class
	 */
	
	public void FeedBack(String Feed) {
		l.setHostURL(url);
		l.initDB(url, user, password);
		FeedFrame f = new FeedFrame();
		try {
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			String sql = "INSERT INTO feedback (FEEDBACK) " + "VALUES ('"
					+ Feed + "')";
			stmt.executeUpdate(sql);
			JOptionPane.showMessageDialog(f, "Thank You!");
			stmt.close();
			l.closeDB();

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(User.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
			System.out.println("Connection Failed! Check output console");
			return;
		}

	}

}