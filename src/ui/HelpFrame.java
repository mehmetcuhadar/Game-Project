package ui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class HelpFrame extends JFrame {
	JLabel gameDescription;
	public HelpFrame(int width, int height) {
		//displayText(width, height);
		displayHelpFrame();
	}
	
	public void displayHelpFrame() {
		this.getContentPane().setBackground(Color.BLACK);
		Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
		Image gameDesImg = null;
		
		try {
			gameDesImg = ImageIO.read(new File("src/images/game_description.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ImageIcon gdIcon = new ImageIcon(gameDesImg);	
		gameDescription = new JLabel(gdIcon);
		this.add(gameDescription);
		this.setLocation((int)(screenDim.getWidth()-500)/2,(int)(screenDim.getHeight()-500)/2);
		this.setSize(500,500);
		this.setVisible(true);


		
		
	}

}