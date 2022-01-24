package ui;

import java.awt.Color;
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

public class PauseMenuFrame extends JFrame {
	JLabel resumeButton;
	JLabel saveButton;
	JLabel exitButton;
	public PauseMenuFrame() {
		displayPauseFrame();
	}
	
	public void displayPauseFrame() {
		this.setLayout(new GridLayout(3,0));
		this.setUndecorated(true);
		this.getContentPane().setBackground(Color.BLACK);
		this.setLocation(600,300);
		Image resumeImg = null;
		Image saveImg = null;
		Image exitImg = null;
		try {
			resumeImg = ImageIO.read(new File("src/images/resume.png"));
			saveImg = ImageIO.read(new File("src/images/save.png"));
			exitImg = ImageIO.read(new File("src/images/exit.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ImageIcon resumeIcon = new ImageIcon(resumeImg);
		ImageIcon saveIcon = new ImageIcon(saveImg);
		ImageIcon exitIcon = new ImageIcon(exitImg);
		resumeButton = new JLabel(resumeIcon);
		saveButton = new JLabel(saveIcon);
		exitButton = new JLabel(exitIcon);
		this.add(resumeButton);
		resumeButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				playSound("click");
				resumeTheGame();
				dispose();
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
		}
		);
		this.add(saveButton);
		saveButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
					playSound("click");
					saveTheGame("Database");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
			
			
		});
		this.add(exitButton);
		exitButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
					playSound("click");
					saveTheGame("File");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
		}
			);
		this.setSize(300,300);
		this.setVisible(true);
	}
	
	public void resumeTheGame() {
		UIController.getInstance().setGameStopped(false);
	}
	
	public void saveTheGame(String saveType) throws IOException {
		String username=UIController.getInstance().getUsername();
		UIController.getInstance().setSaveLoadAdapter(saveType);
		UIController.getInstance().saveGame(UIController.getInstance().getSaveList(),username);
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
