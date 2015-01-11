import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import com.mysql.fabric.Server;

public class LoadXML extends JFrame {

	int i = 1;
	String Increase = Integer.toString(i);
	public Vector datas;
	public Vector columns;
	JTable table = new JTable(datas, columns);
	JScrollPane scrollPane = new JScrollPane(table);

	String Firstname, Lastname, Dept, Arivt, Cls, Nost, adult, child, inf,
			userrr, town, origin, cphone, paspNo, bags;
	// getContentPane().add( scrollPane );
	public String us;
	JPanel buttonPanel = new JPanel();
	JPanel tablePanel = new JPanel();

	public void CreateXML(String Firstname, String Lastname, String Dept,
			String Arivt, String Cls, String Nost, String adult, String child,
			String inf, String us, String town, String origin, String cphone,
			String paspNo, String bags) throws IOException {

		try {
			File file = new File("createFile.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();

			Element rootElement = doc.createElement("BOOKINGS");
			doc.appendChild(rootElement);

			// staff elements
			Element staff = doc.createElement("Customer");
			rootElement.appendChild(staff);

			// set attribute to staff element

			// shorten way
			String Increase = Integer.toString(i);

			staff.setAttribute("id", Increase);

			Element firstname = doc.createElement("Firstname");
			firstname.appendChild(doc.createTextNode(Firstname));
			staff.appendChild(firstname);

			// lastname elements
			Element lastname = doc.createElement("Lastname");
			lastname.appendChild(doc.createTextNode(Lastname));
			staff.appendChild(lastname);

			Element Dep = doc.createElement("From");
			Dep.appendChild(doc.createTextNode(Dept));
			staff.appendChild(Dep);

			Element Ariv = doc.createElement("To");
			Ariv.appendChild(doc.createTextNode(Arivt));
			staff.appendChild(Ariv);

			Element Cl = doc.createElement("Class");
			Cl.appendChild(doc.createTextNode(Cls));
			staff.appendChild(Cl);

			Element Nos = doc.createElement("Seats");
			Nos.appendChild(doc.createTextNode(Nost));
			staff.appendChild(Nos);

			Element ad = doc.createElement("Adult");
			ad.appendChild(doc.createTextNode(adult));
			staff.appendChild(ad);

			Element ch = doc.createElement("Children");
			ch.appendChild(doc.createTextNode(child));
			staff.appendChild(ch);

			Element infant = doc.createElement("Infant");
			infant.appendChild(doc.createTextNode(inf));
			staff.appendChild(infant);

			Element usert = doc.createElement("Username");
			usert.appendChild(doc.createTextNode(us));
			staff.appendChild(usert);

			Element city = doc.createElement("City");
			city.appendChild(doc.createTextNode(town));
			staff.appendChild(city);

			Element country = doc.createElement("Country");
			country.appendChild(doc.createTextNode(origin));
			staff.appendChild(country);

			Element phone = doc.createElement("Telephone");
			phone.appendChild(doc.createTextNode(cphone));
			staff.appendChild(phone);

			Element pasp = doc.createElement("PassportNumber");
			pasp.appendChild(doc.createTextNode(paspNo));
			staff.appendChild(pasp);

			Element bag = doc.createElement("Bags");
			bag.appendChild(doc.createTextNode(bags));
			staff.appendChild(bag);

			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new FileWriter(
					"createFile.xml"));

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	/** 
	 * This function creates an xml file if not already exists in order to keep a database of all
	 * bookins.If an xml file already exists
	 * this function append new details in the file 
	 * Its parameters are Strings and are IN parameters
	 * @param Firstname represents the first name of customer who booked a ticket
	 * @param Lastname represents the last name of customer who booked a ticket
	 * @param Dept represents the departure city of the booked flight
	 * @param Arivt represents the arrival city of the booked flight
	 * @param Cls represents the class selected by customer
	 * @param Nost represents the number of seats booked
	 * @param adult represents the number of adults booked in a specific flight
 	 * @param child represents the number of children booked in a specific flight
	 * @param inf represents the number of infants booked in a specific flight
	 * @param us represents the username of the customer 
	 * @param town represents the city where customer leaves
	 * @param origin represents country where customer leaves
	 * @param cphone represents the phone number of customer
	 * @param paspNo represents the passsport number of customer
	 * @param bags represent the bags booked by customer
	 * @throws Exception  The base exception for unexpected processing errors. This Exception class is used to report well-formedness errors as well as unexpected processing conditions.
	 * @throws DOMException DOM operations only raise exceptions in "exceptional" circumstances, i.e., when an operation is impossible to perform (either for logical reasons, because data is lost, or because the implementation has become unstable). In general, DOM methods return specific error values in ordinary processing situations, such as out-of-bound errors when using NodeList.
	 * @throws ParserConfigurationException
	 * This function is used in CustomerFrame class
	 */
	
	public void process(String Firstname, String Lastname, String Dept,
			String Arivt, String Cls, String Nost, String adult, String child,
			String inf, String us, String town, String origin, String cphone,
			String paspNo, String bags) throws Exception, DOMException,
			ParserConfigurationException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		Document dct = db.parse(new File("createFile.xml"));

		Element childs = dct.createElement("Customer");
		Element rootElement = dct.getDocumentElement();
		rootElement.appendChild(childs);
		// set attribute to staff element

		// shorten way
		i++;
		String Increase = Integer.toString(i);
		childs.setAttribute("id", Increase);

		// firstname elements
		Element firstname = dct.createElement("Firstname");
		firstname.appendChild(dct.createTextNode(Firstname));
		childs.appendChild(firstname);

		// lastname elements
		Element lastname = dct.createElement("Lastname");
		lastname.appendChild(dct.createTextNode(Lastname));
		childs.appendChild(lastname);

		Element Dep = dct.createElement("From");
		Dep.appendChild(dct.createTextNode(Dept));
		childs.appendChild(Dep);

		Element Ariv = dct.createElement("To");
		Ariv.appendChild(dct.createTextNode(Arivt));
		childs.appendChild(Ariv);

		Element Cl = dct.createElement("Class");
		Cl.appendChild(dct.createTextNode(Cls));
		childs.appendChild(Cl);

		Element Nos = dct.createElement("Seats");
		Nos.appendChild(dct.createTextNode(Nost));
		childs.appendChild(Nos);

		Element ad = dct.createElement("Adult");
		ad.appendChild(dct.createTextNode(adult));
		childs.appendChild(ad);

		Element ch = dct.createElement("Children");
		ch.appendChild(dct.createTextNode(child));
		childs.appendChild(ch);

		Element infant = dct.createElement("Infant");
		infant.appendChild(dct.createTextNode(inf));
		childs.appendChild(infant);

		Element customer = dct.createElement("Username");
		customer.appendChild(dct.createTextNode(us));
		childs.appendChild(customer);

		Element city = dct.createElement("City");
		city.appendChild(dct.createTextNode(town));
		childs.appendChild(city);

		Element country = dct.createElement("Country");
		country.appendChild(dct.createTextNode(origin));
		childs.appendChild(country);

		Element phone = dct.createElement("Telephone");
		phone.appendChild(dct.createTextNode(cphone));
		childs.appendChild(phone);

		Element pasp = dct.createElement("PassportNumber");
		pasp.appendChild(dct.createTextNode(paspNo));
		childs.appendChild(pasp);

		Element bag = dct.createElement("Bags");
		bag.appendChild(dct.createTextNode(bags));
		childs.appendChild(bag);

		Document mainDoc = db.parse(new File("createFile.xml"));
		if (mainDoc.getDocumentElement() == null) {
			Element root1 = mainDoc.createElement("Root1");
			mainDoc.appendChild(root1);
		}
		mainDoc.getDocumentElement().appendChild(
				mainDoc.importNode(childs, true));

		Transformer transformer = TransformerFactory.newInstance()
				.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StreamResult result = new StreamResult(new FileWriter("createFile.xml"));
		DOMSource source = new DOMSource(mainDoc);
		transformer.transform(source, result);
		System.out.println("File saved!");
	}

}
