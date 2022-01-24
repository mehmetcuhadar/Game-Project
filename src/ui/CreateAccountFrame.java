package ui;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class CreateAccountFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel panel;
	JLabel name_label, surname_label, user_label, password_label, message;
	JTextField name_text, surname_text, userName_text;
	JPasswordField password_text;
	JButton create_acc, cancel;

	static Logger mongoLogger;
	static MongoClient mongoClient;
	static MongoDatabase database;
	static MongoCollection<Document> collection;

	public static void dbConn() {
		mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE);
		mongoClient = MongoClients.create(
				"mongodb+srv://comp302_user:comp302pass@cluster0.jml5k.mongodb.net/COMP302?retryWrites=true&w=majority");
		database = mongoClient.getDatabase("COMP302");
		collection = database.getCollection("NeedForSpear");
	}

	public CreateAccountFrame() {

		setBounds(450, 300, 500, 500);
		setResizable(false);
		// Name Label
		name_label = new JLabel();
		name_label.setText("Name :");
		name_text = new JTextField();

		// Surname Label
		surname_label = new JLabel();
		surname_label.setText("Surname :");
		surname_text = new JTextField();

		// User Label
		user_label = new JLabel();
		user_label.setText("User Name :");
		userName_text = new JTextField();

		// Password

		password_label = new JLabel();
		password_label.setText("Password :");
		password_text = new JPasswordField();

		// Submit

		create_acc = new JButton("Create Account");

		panel = new JPanel(new GridLayout(5, 1));

		panel.add(name_label);
		panel.add(name_text);
		panel.add(surname_label);
		panel.add(surname_text);
		panel.add(user_label);
		panel.add(userName_text);
		panel.add(password_label);
		panel.add(password_text);

		message = new JLabel();
		panel.add(message);
		panel.add(create_acc);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Adding the listeners to components..
		create_acc.addActionListener(this);
		add(panel, BorderLayout.CENTER);
		setTitle("Create Account");
		setSize(450, 250);
		setVisible(true);

	}

	public static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String name = name_text.getText();
		String surname = surname_text.getText();
		String username = userName_text.getText();
		@SuppressWarnings("deprecation")
		String password = password_text.getText();
		
		 if (checkConstraints(name, surname, username, password)) {
			 
			 // Appending the account information
			 UIController.getInstance().createAccount(name,surname,username,password);
			 message.setText(" Your account is created " + username + " !");			
			 this.dispose();
			 StartFrame start = new StartFrame();
		 }
	}
	
	public boolean checkConstraints(String name, String surname, String username, String password) {
		
		boolean accountExists = UIController.getInstance().accountExists(username);
		boolean nameNull = UIController.getInstance().checkName(name);
		boolean surnameNull = UIController.getInstance().checkSurname(surname);
		boolean usernameNull = UIController.getInstance().checkUsername(username);
		boolean passwordNull = UIController.getInstance().checkPassword(password);
		
		if (nameNull) {
			
			message.setText("Name cannot be empty");
			return false;
		}
		
		else if (surnameNull) {
			
			message.setText("Surname cannot be empty");
			return false;
		}
		
		else if (usernameNull) {
			
			message.setText("Username cannot be empty");
			return false;
		}
		
		else if (passwordNull) {
			
			message.setText("Password cannot be empty");
			return false;
		}
		
		else if (accountExists) {
			
			//System.out.println(username+" is taken.");
			message.setText(username+" is already taken.Try another username.");
			return false;
		}
		
		else {

			return true;
		}
	}

}

