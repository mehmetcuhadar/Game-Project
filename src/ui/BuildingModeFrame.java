package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;




public class BuildingModeFrame extends JFrame{
	private static final Color BACKGROUND_COLOR = new Color(140, 140, 140);	
	JPanel buildingPanel;
	BuildingModeTopPanel buildingModeTopPanel;
	public BuildingModeFrame(int numOfSimObs,int numOfFirmObs,int numOfExpObs,int numOfGiftObs) throws IOException {
		super("Building Frame");	
		setBounds(100, 50, 1250, 750);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		buildingModeTopPanel = new BuildingModeTopPanel(this);
		buildingPanel=new BuildingPanel(numOfSimObs,numOfFirmObs,numOfExpObs,numOfGiftObs,1250,650,this);
		buildingPanel.setSize(1250,700);
		buildingPanel.setBackground(Color.black);
		add(buildingPanel, BorderLayout.CENTER);
		add(buildingModeTopPanel, BorderLayout.SOUTH);
		setVisible(true);
		JPanel panel = new JPanel();
		
	}
	
		
}