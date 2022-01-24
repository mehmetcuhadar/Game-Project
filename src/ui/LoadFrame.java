package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoadFrame extends JFrame {
	UIController game = UIController.getInstance();
	JLabel savedGames = new JLabel("SAVED GAMES FOR " + game.getUsername());
	JButton loadLastButton = new JButton("Load Your Last Game");
	JButton loadButton = new JButton("Load");
	String savedGamesArray[];
	JComboBox savedGamesBox;
	JPanel mainPanel;

	public LoadFrame() {
		displayLoadFrame();
	}

	public void displayLoadFrame() {
		this.setLayout(new BorderLayout());
		this.getContentPane().setBackground(Color.blue);
		this.add(savedGames, BorderLayout.NORTH);
		this.setSize(400, 400);
		this.setBounds(450, 150, 500, 500);
		this.setVisible(true);
		GridBagConstraints gbc = new GridBagConstraints();
		mainPanel = new JPanel(new GridBagLayout());
		game.initializeNumOfSavedGames();
		int numOfSavedGame = game.getNumOfSavedGames();
		savedGamesArray = new String[numOfSavedGame];
		System.out.println();
		for (int i = 0; i < game.getNumOfSavedGames(); i++) {
			if (i == 0) {
				savedGamesArray[i] = "saved_games";
			} else {
				savedGamesArray[i] = "saved_games_" + i;
			}

		}

		savedGamesBox = new JComboBox<Object>(savedGamesArray);

		gbc.gridx = 0;
		gbc.gridy = 0;
		mainPanel.add(savedGamesBox, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		mainPanel.add(loadButton, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		mainPanel.setSize(400, 250);
		mainPanel.setBackground(Color.blue);
		mainPanel.add(loadLastButton, gbc);
		this.add(mainPanel);
		loadButton.addActionListener(new buttonHandler());
		loadLastButton.addActionListener(new buttonHandler());
		
	}

	public class buttonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == loadButton) {
				playSound("click");
				UIController.getInstance().setSaveLoadAdapter("Database");
				String username = UIController.getInstance().getUsername();
				String selected = savedGamesBox.getSelectedItem().toString();
				game.setSelectedSavedGame(selected);
				try {
					UIController.getInstance().loadGame(UIController.getInstance().getLoadList(), username);
					new RunningModeFrame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			if (e.getSource() == loadLastButton) {
				playSound("click");
				UIController.getInstance().setSaveLoadAdapter("File");
				String username = UIController.getInstance().getUsername();
				try {
					UIController.getInstance().loadGame(UIController.getInstance().getLoadList(), username);
					new RunningModeFrame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
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