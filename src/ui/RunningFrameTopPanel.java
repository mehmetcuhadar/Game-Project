package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;


public class RunningFrameTopPanel extends JPanel{
	//JButton startGame;
	JLabel pauseGame;
	JLabel score;
	JLabel livesString;
	JLabel live1;
	JLabel live2;
	JLabel live3;
	JLabel live4;
	JLabel live5;
	int ms = 50;
	Timer timer = new Timer();
	int time = 0;
	public RunningFrameTopPanel() {
		displayTopPanel();
	}
	
	public void displayTopPanel() {
		this.setLayout(new FlowLayout(FlowLayout.LEADING));
		
		this.setSize(1080,100);

		score= new JLabel("Score: "+UIController.getInstance().getScore());
		livesString = new JLabel("Lives:");
		Image expObsImg = null;
		Image pauseImg = null;
		try {
			expObsImg = ImageIO.read(new File("src/images/heart.png"));
			pauseImg = ImageIO.read(new File("src/images/pause.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ImageIcon expIcon = new ImageIcon(expObsImg);
		ImageIcon pauseIcon = new ImageIcon(pauseImg);
		pauseGame= new JLabel(pauseIcon);
		live1 = new JLabel(expIcon);
		live2 = new JLabel(expIcon);
		live3 = new JLabel(expIcon);
		live4 = new JLabel(expIcon);
		live5 = new JLabel(expIcon);
		live4.setVisible(false);
		live5.setVisible(false);
		//startGame.setSize(50,50);
		this.setBackground(Color.BLACK);
		pauseGame.setFocusable(false);
		pauseGame.addMouseListener(new MouseListener() {
			

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==pauseGame) {
					playSound("click");
					UIController.getInstance().setGameStopped(true);
					new PauseMenuFrame();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

		});
		this.setVisible(true);
		this.add(pauseGame,BorderLayout.CENTER);
		this.add(score);
		this.add(livesString);
		startSimulation();
		this.add(live1);
		this.add(live2);
		this.add(live3);
		this.add(live4);
		this.add(live5);
	}
	
	public void startSimulation() {
		TimerTask x = new TimerTask() {
			@Override
			public void run() {
				if (UIController.getInstance().getGameStopped() == false) {
					time++;
					score.setText("Score: " + UIController.getInstance().getScore());
					if(UIController.getInstance().getChances()==5) {
						live5.setVisible(true);
						live4.setVisible(true);
						live3.setVisible(true);
						live2.setVisible(true);
						live1.setVisible(true);
					}else if(UIController.getInstance().getChances()==4) {
						live5.setVisible(false);
						live4.setVisible(true);
						live3.setVisible(true);
						live2.setVisible(true);
						live1.setVisible(true);
					}else if(UIController.getInstance().getChances()==3) {
						live5.setVisible(false);
						live4.setVisible(false);
						live3.setVisible(true);
						live2.setVisible(true);
						live1.setVisible(true);
					}else if(UIController.getInstance().getChances()==2) {
						live5.setVisible(false);
						live4.setVisible(false);
						live3.setVisible(false);
						live2.setVisible(true);
						live1.setVisible(true);
					}else if(UIController.getInstance().getChances()==1) {
						live5.setVisible(false);
						live4.setVisible(false);
						live3.setVisible(false);
						live2.setVisible(false);
						live1.setVisible(true);
					}else if(UIController.getInstance().getChances()==0) {
						live5.setVisible(false);
						live4.setVisible(false);
						live3.setVisible(false);
						live2.setVisible(false);
						live1.setVisible(false);
					}
				}
				

			}
		};
		timer.schedule(x, 1000, ms);
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
