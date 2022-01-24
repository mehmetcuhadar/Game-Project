package ui;

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

public class GameOverFrame extends JFrame{
	JLabel label;
	JLabel exit;
	public GameOverFrame(Boolean bool) {
		displayGameOverFrame(bool);
	}
	
	public void displayGameOverFrame(Boolean bool) {
		this.setBounds(450, 150, 500, 500);
		this.setSize(600,400);
		this.setLayout(new GridLayout(0,1));
		Image exitImg = null;
		try {
			exitImg = ImageIO.read(new File("src/images/exit.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ImageIcon exitIcon = new ImageIcon(exitImg);
		
		exit= new JLabel(exitIcon);
		exit.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				playSound("click");
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
		});
		
		if(bool) {
			label = new JLabel("You exploded all the obstacles!! You win the game. Congratulations :) Your score is: " + UIController.getInstance().getScore());
			playSound("win");
		}else {
			label= new JLabel("Game is over but nice try :/ Your score is: " + UIController.getInstance().getScore());
			playSound("lose");
		}
		
		add(label);
		add(exit);
		this.setVisible(true);
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
