package test;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ui.BuildingPanel;
import ui.RunningPanel;

public class IntersectsTest {
	
	
	@Test
	public void test() throws HeadlessException, IOException {
		BuildingPanel panel=new BuildingPanel(75,10,10,10,1250,750,new JFrame());
		bothComponentsAreNullTest(panel);
		firstComponentIsNullTest(panel);
		secondComponentIsNullTest(panel);
		componentsIntersectTest(panel);
		componentsDoNotIntersectTest(panel);
		
	}
	
	public void bothComponentsAreNullTest(BuildingPanel panel) {
		assertThrows(NullPointerException.class, ()->
			{panel.intersects(null, null);
		});
	}
	
	public void firstComponentIsNullTest(BuildingPanel panel) {
		JLabel component2 = new JLabel("sadasds");
		component2.setSize(20,35);
		component2.setLocation(10,0);
		assertThrows(NullPointerException.class, ()->
		{panel.intersects(null,component2);
	});
	}
	
	
	public void secondComponentIsNullTest(BuildingPanel panel) {
		JLabel component1 = new JLabel("sadasds");
		component1.setSize(15,25);
		component1.setLocation(0,0);
		assertThrows(NullPointerException.class, ()->
		{panel.intersects(component1,null);
	});
	}
	

	public void componentsIntersectTest(BuildingPanel panel) throws HeadlessException, IOException {
		
		JLabel component1 = new JLabel("sadasds");
		JLabel component2 = new JLabel("sadasds");
		component1.setSize(15,25);
		component1.setLocation(0,0);
		component2.setSize(20,35);
		component2.setLocation(10,0);
		assertTrue(panel.intersects(component2, component1));
	}
	

	public void componentsDoNotIntersectTest(BuildingPanel panel) throws HeadlessException, IOException {
		JLabel component1 = new JLabel("sadasds");
		JLabel component2 = new JLabel("sadasds");
		component1.setSize(1,2);
		component1.setLocation(0,0);
		component2.setSize(3,5);
		component2.setLocation(4,7);
		assertFalse(panel.intersects(component2, component1));
	}
	
	

	

}
