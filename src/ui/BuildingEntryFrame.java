package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class BuildingEntryFrame extends JFrame {
	private static final Color BACKGROUND_COLOR = new Color(140, 140, 140);
	 
    
	JPanel entryPanel;
	

	JLabel simple_label = new JLabel("Number of Simple Obstacles:");
	JLabel firm_label = new JLabel("Number of Firm Obstacles:");
	JLabel exp_label = new JLabel("Number of Explosive Obstacles:");
	JLabel gift_label = new JLabel("Number of Gift Obstacles:");

	JTextField simple_obs = new JTextField(5);
	JTextField firm_obs = new JTextField(5);
	JTextField exp_obs = new JTextField(5);
	JTextField gift_obs = new JTextField(5);

	JLabel placeObstacle;
	
	int minSimObs=75;
	int minFirmObs=10;
	int minExpObs=5;
	int minGiftObs=10;
	


	

	public BuildingEntryFrame() {
		super("Entry Frame");
		
		setBounds(450, 150, 500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.black);
		BufferedImage simple_obs_pic = null;
		BufferedImage firm_obs_pic = null;
		BufferedImage exp_obs_pic = null;
		BufferedImage gift_obs_pic = null;

		try {
			simple_obs_pic = ImageIO.read(new File("src/images/simple_obstacle.png"));
			firm_obs_pic = ImageIO.read(new File("src/images/firm_obstacle5.png"));
			exp_obs_pic = ImageIO.read(new File("src/images/explosive_obstacle.png"));
			gift_obs_pic = ImageIO.read(new File("src/images/gift_obstacle.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel simPicLabel = new JLabel(new ImageIcon(simple_obs_pic));
		JLabel firmPicLabel = new JLabel(new ImageIcon(firm_obs_pic));
		JLabel expPicLabel = new JLabel(new ImageIcon(exp_obs_pic));
		JLabel giftPicLabel = new JLabel(new ImageIcon(gift_obs_pic));

		GridBagConstraints gbc = new GridBagConstraints();
		entryPanel = new JPanel(new GridBagLayout());
		entryPanel.setBackground(Color.cyan);
		this.setBackground(BACKGROUND_COLOR);
		
		

		gbc.gridx = 0;
		gbc.gridy = 0;
		entryPanel.add(simPicLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		entryPanel.add(simple_label, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		entryPanel.add(simple_obs, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		entryPanel.add(firmPicLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		entryPanel.add(firm_label, gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		entryPanel.add(firm_obs, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		entryPanel.add(expPicLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		entryPanel.add(exp_label, gbc);
		gbc.gridx = 2;
		gbc.gridy = 2;
		entryPanel.add(exp_obs, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		entryPanel.add(giftPicLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		entryPanel.add(gift_label, gbc);
		gbc.gridx = 2;
		gbc.gridy = 3;
		entryPanel.add(gift_obs, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 3;
		Image placeObstacleImg = null;
		try {
			placeObstacleImg = ImageIO.read(new File("src/images/placeObstacle.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ImageIcon expIcon = new ImageIcon(placeObstacleImg);
		placeObstacle = new JLabel(expIcon);
		entryPanel.add(placeObstacle, gbc);
		add(entryPanel, BorderLayout.CENTER);
		
		setVisible(true);
		
		placeObstacle.addMouseListener(new MouseListener() {
			
		
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				playSound("click");
				int numOfSimObs = Integer.parseInt(simple_obs.getText());				
				int numOfFirmObs = Integer.parseInt(firm_obs.getText());				
				int numOfExpObs = Integer.parseInt(exp_obs.getText());				
				int numOfGiftObs = Integer.parseInt(gift_obs.getText());
				if(constraintCheck(numOfSimObs,numOfFirmObs,numOfExpObs,numOfGiftObs)==true) {
				try {
					dispose();
					new BuildingModeFrame(numOfSimObs,numOfFirmObs,numOfExpObs,numOfGiftObs);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
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

		
	}
	public boolean constraintCheck(int const1,int const2,int const3,int const4) {
		//requires const1,const2,const3,const4 > 0
		//modifies it does not modifies anything
		//effects returns true or false and shows message dialog according to number of obstacles specified
		int check=0;
		if(const1 < 0 || const2 <0 || const3 < 0 || const4 <0) {
			throw new IllegalArgumentException();
		}
		if (const1<minSimObs) {
			JOptionPane.showMessageDialog(this, "Number of Simple Obstacles must be greater or equal to "+minSimObs+" .");
			check++;
		}
		if (const2<minFirmObs) {
			JOptionPane.showMessageDialog(this, "Number of Firm Obstacles must be greater or equal to "+minFirmObs+" .");
			check++;
		}
		if (const3<minExpObs) {
			JOptionPane.showMessageDialog(this, "Number of Explosive Obstacles must be greater or equal to "+minExpObs+" .");
			check++;
		}
		if (const4<minGiftObs || const4 > 20) {
			JOptionPane.showMessageDialog(this, "Number of Gift Obstacles must be between "+minGiftObs+" and 20 .");
			check++;
		}
		if (const1+const2+const3+const4 > 140) {
			JOptionPane.showMessageDialog(this, "Number of Obstacles must be lower than "+ 141 +" .");
			check++;
		}
		if (check==0) {
			return true;
		}else {
			return false;
		}
		
		
	}
	
	public static void main(String[] args) {
		new BuildingEntryFrame();
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