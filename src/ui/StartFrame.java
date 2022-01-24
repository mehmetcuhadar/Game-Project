package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class StartFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JPanel panel;
	private final JLabel user_label, password_label, create_acc_mess, message;
	private final JTextField userName_text;
	private final JPasswordField password_text;
	private final JButton log_in, create_acc;






	public StartFrame() {

		
		setBounds(450, 150, 500, 500);
		setResizable(false);

		// User Label
		user_label = new JLabel();
		user_label.setText("User Name :");
		userName_text = new JTextField();

		// Password

		password_label = new JLabel();
		password_label.setText("Password :");
		password_text = new JPasswordField();

		// Log in

		log_in = new JButton("log in");

		// Create Account

		create_acc_mess = new JLabel();
		create_acc_mess.setText("Don't have an account?");
		create_acc = new JButton("Create Account");

		panel = new JPanel(new GridLayout(4, 1));

		panel.add(user_label);
		panel.add(userName_text);
		panel.add(password_label);
		panel.add(password_text);

		message = new JLabel();
		panel.add(message);
		panel.add(log_in);

		panel.add(create_acc_mess);
		panel.add(create_acc);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Adding the listeners to components..
		log_in.addActionListener(this);
		create_acc.addActionListener(this);

		add(panel, BorderLayout.CENTER);
		setTitle("Log in to your account!");
		setSize(600, 400);
		setVisible(true);

	}

	public static void main(String[] args) {
		new StartFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == log_in) {
			playSound("click");
			String username = userName_text.getText().trim();
			String password = password_text.getText().trim();
			UIController.getInstance().setUsername(username);
			boolean loginSuccess = UIController.getInstance().logIn(username, password);
			if(loginSuccess) {
				MainMenuFrame MainMenu = new MainMenuFrame(username);
				this.dispose();
			}else {
				message.setText("Wrong username or password. Try Again.");
			}

		}
		if (e.getSource() == create_acc) {
			playSound("click");
			this.dispose();
			CreateAccountFrame CreateAcc = new CreateAccountFrame();

		}
	}
	
	public static synchronized void playSound(String type) {
		  new Thread(new Runnable() {
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
		        File file = new File(path + "/src/sounds/"+type +".wav");
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
	}

}
