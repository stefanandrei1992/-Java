import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class FeedFrame extends JFrame {

	JLabel FlightsId = new JLabel("FlightsID");
	JComboBox Source = new JComboBox();
	JComboBox Destination = new JComboBox();
	JLabel Days = new JLabel("Days");
	JTextArea Feed = new JTextArea();
	JLabel Feedback = new JLabel("Feedback");
	JButton Send = new JButton("Send Feedback");

	FeedFrame() {
		JPanel FeedPanel = new JPanel(null);
		FeedPanel.setPreferredSize(new Dimension(1400, 200));
		FeedPanel.setBackground(Color.WHITE);

		setExtendedState(JFrame.MAXIMIZED_BOTH);

		Feedback.setBounds(400, 200, 100, 40);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		Feed.setBorder(BorderFactory.createCompoundBorder(border,
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		Feed.setBounds(500, 200, 400, 200);
		Send.setBounds(400, 470, 150, 30);

		FeedPanel.add(Feedback);
		FeedPanel.add(Feed);
		FeedPanel.add(Send);

		getContentPane().add(Feedback);
		getContentPane().add(Feed);
		getContentPane().add(Send);
		getContentPane().add(FeedPanel);

		Send.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				User u = new User();
				final String f = Feed.getText();
				u.FeedBack(f);
				u.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
