package test;

import static org.junit.Assert.*;

import org.junit.Test;

import ui.CreateAccountFrame;

public class createUserTest {
	
	@Test
	public void test() {
		
		CreateAccountFrame frame = new CreateAccountFrame();
		emptyNameTest(frame);
		emptySurnameTest(frame);
		emptyUsernameTest(frame);
		emptyPasswordTest(frame);
		alreadyTakenUsernameTest(frame);
	}
	
	public void emptyNameTest(CreateAccountFrame frame) {
		
		assertFalse(frame.checkConstraints("", "some surname", "some username", "good password"));
	}
	
	public void emptySurnameTest(CreateAccountFrame frame) {
		
		assertFalse(frame.checkConstraints("nice name", "", "some username", "bad password"));
	}
	
	public void emptyUsernameTest(CreateAccountFrame frame) {
		
		assertFalse(frame.checkConstraints("namename", "some surname", "", "same password"));
	}
	
	public void emptyPasswordTest(CreateAccountFrame frame) {
		
		assertFalse(frame.checkConstraints("dorukdoruk", "some surname", "some username", ""));
	}
	
	public void alreadyTakenUsernameTest(CreateAccountFrame frame) {
		
		assertFalse(frame.checkConstraints("dorukdoruk", "some surname", "dornekci", "very good password"));
	}
	
}

