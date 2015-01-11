import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class RegisterFrame extends JFrame {
	

  

	JPanel regPanel = new JPanel();
	JPanel backregPanel = new JPanel();
	JLabel NewUserL = new JLabel("New User");
	JLabel PasswordL = new JLabel("Password");
	JLabel RePasswordL = new JLabel("Retype Passsword");
	JTextField NewUserTF = new JTextField();
	JPasswordField PasswordTF = new JPasswordField();
	JPasswordField RePasswordTF = new JPasswordField();
	JButton SignUp = new JButton("SignUp");
	JButton Back = new JButton("Back");

	String userDB1 = null;// = null;
	String passDB1 = null;
	String userDB;
	String passDB;
	ArrayList<String> lines = new ArrayList();
	ArrayList<String> linesPass = new ArrayList();


	RegisterFrame() {
		super("Register New Account");
		backregPanel.setBackground(Color.WHITE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		backregPanel.setLayout(null);
		
		Font labels = new Font("BELL MT", Font.BOLD, 16);
		NewUserL.setFont(labels);
		PasswordL.setFont(labels);
		RePasswordL.setFont(labels);

		Font name = new Font("BELL MT", Font.ROMAN_BASELINE, 14);
		NewUserTF.setFont(name);

		NewUserL.setBounds(50, 50, 150, 20);
		NewUserTF.setBounds(50, 100, 150, 20);
		PasswordL.setBounds(50, 150, 150, 20);
		PasswordTF.setBounds(50, 200, 150, 20);
		RePasswordL.setBounds(50, 250, 150, 20);
		RePasswordTF.setBounds(50, 300, 150, 20);
		SignUp.setBounds(50, 330, 100, 20);
		Back.setBounds(50,360,100,20);

		backregPanel.add(NewUserL);
		backregPanel.add(NewUserTF);
		backregPanel.add(PasswordL);
		backregPanel.add(PasswordTF);
		backregPanel.add(RePasswordTF);
		backregPanel.add(RePasswordTF);
		backregPanel.add(SignUp);
		backregPanel.add(Back);
		
		getContentPane().add(NewUserL);
		getContentPane().add(NewUserTF);
		getContentPane().add(PasswordL);
		getContentPane().add(PasswordTF);
		getContentPane().add(RePasswordL);
		getContentPane().add(RePasswordTF);
		getContentPane().add(SignUp);
		getContentPane().add(backregPanel);
		
		SignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				User newuser = new User();
				LoadDataBase ldb = new LoadDataBase();
				String UserReg = NewUserTF.getText();
				String PassReg = PasswordTF.getText();
				String RePassReg = RePasswordTF.getText();

				try {
					ldb.getUserDb(userDB, passDB, lines, linesPass);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				for (int count = 0; count < lines.size(); count++) {
					if (UserReg.equals(lines.get(count))) {
						JOptionPane.showMessageDialog(backregPanel,
								"User already exists in database\n"
										+ "Please try again!");
						NewUserTF.setText("");
						UserReg = NewUserTF.getText();
					}
				}

				if ((PassReg.isEmpty()) || (RePassReg.isEmpty()))
				{
					JOptionPane.showMessageDialog(backregPanel,
							"Please enter a password!");
				}
				else{
				if ((PassReg.matches(RePassReg)) && (!(PassReg.isEmpty()))
						&& (!(RePassReg.isEmpty()))) {
					try {
					   
						newuser.CreateNewAccount(UserReg, PassReg);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(newuser,
							"Your account has been created!");
					newuser.setDefaultCloseOperation(EXIT_ON_CLOSE);
					try {
						Login log = new Login();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					dispose();
				}
				else {
					JOptionPane.showMessageDialog(backregPanel,
							"Password doesn't match\n" + "Please try again!");
					// Retype password//
					if (!(PassReg.matches(RePassReg))) {

						PasswordTF.setText("");
						RePasswordTF.setText("");
						PassReg = PasswordTF.getText();
						RePassReg = RePasswordTF.getText();

					}
				}
			}		
		}
	});
		Back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				 try {
					Login lg = new Login();
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
