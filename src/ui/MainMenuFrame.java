package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuFrame extends JFrame {

	JLabel gameName;
	JLabel menu;
	JLabel bmButton;
	JLabel loadButton;
	JLabel helpButton;
	JLabel quitButton;
	private final String username;
	JFrame frame = null;

	public MainMenuFrame(String username) {
		this.username = username;
		displayFrame();
	}

//	
	public void displayFrame() {
		setTitle("Welcome back " + username + " !");
		frame = new JFrame();
		frame.setBackground(Color.black);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setLayout(new BorderLayout(10, 10));
		frame.setVisible(true);
		Image nameImg = null;
		Image menuImg = null;
		Image bmImg = null;
		Image loadImg = null;
		Image helpImg = null;
		Image quitImg = null;
		try {
			nameImg = ImageIO.read(new File("src/images/game_name.png"));
			menuImg = ImageIO.read(new File("src/images/menu.png"));
			bmImg = ImageIO.read(new File("src/images/buildingmode_button.png"));
			loadImg = ImageIO.read(new File("src/images/load_button.png"));
			helpImg = ImageIO.read(new File("src/images/help_button.png"));
			quitImg = ImageIO.read(new File("src/images/quit_button.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ImageIcon gameIcon = new ImageIcon(nameImg);
		ImageIcon menuIcon = new ImageIcon(menuImg);
		ImageIcon bmIcon = new ImageIcon(bmImg);
		ImageIcon loadIcon = new ImageIcon(loadImg);
		ImageIcon helpIcon = new ImageIcon(helpImg);
		ImageIcon quitIcon = new ImageIcon(quitImg);
		gameName = new JLabel(gameIcon);
		menu = new JLabel(menuIcon);
		bmButton = new JLabel(bmIcon);
		loadButton = new JLabel(loadIcon);
		helpButton = new JLabel(helpIcon);
		quitButton = new JLabel(quitIcon);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		panel1.setBackground(Color.black);
		panel2.setBackground(Color.black);
		panel3.setBackground(Color.black);
		panel4.setBackground(Color.black);
		panel5.setBackground(Color.DARK_GRAY);
		panel5.setLayout(new BorderLayout());
		panel1.setPreferredSize(new Dimension(100, 100));
		panel2.setPreferredSize(new Dimension(150, 100));
		panel3.setPreferredSize(new Dimension(150, 100));
		panel4.setPreferredSize(new Dimension(100, 100));
		panel5.setPreferredSize(new Dimension(100, 100));
		frame.add(panel1, BorderLayout.NORTH);
		frame.add(panel2, BorderLayout.WEST);
		frame.add(panel3, BorderLayout.EAST);
		frame.add(panel4, BorderLayout.SOUTH);
		frame.add(panel5, BorderLayout.CENTER);
		panel5.setLayout(new GridLayout(5, 1));
		panel5.add(gameName);
		// panel5.add(menu);
		panel5.add(bmButton);
		panel5.add(loadButton);
		panel5.add(helpButton);
		panel5.add(quitButton);
		bmButton.addMouseListener(new MouseListener() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				playSound("click");
				new BuildingEntryFrame();
				frame.dispose();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		loadButton.addMouseListener(new MouseListener() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				playSound("click");
				new LoadFrame();
				frame.dispose();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		helpButton.addMouseListener(new MouseListener() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				playSound("click");
				new HelpFrame(frame.getWidth(), frame.getHeight());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		quitButton.addMouseListener(new MouseListener() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				playSound("click");
				frame.dispose();
				System.exit(0);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

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