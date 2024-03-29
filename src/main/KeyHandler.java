package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	boolean checkdrawTime = false;
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}		
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}		
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_T) {
			if(checkdrawTime == false) {
				checkdrawTime = true;
			}
			else if (checkdrawTime == true) {
				checkdrawTime = false;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}		
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}		
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}

}
