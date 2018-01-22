/**
 * 
 */
package me.mayet.yussuf.next45.challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author  Yussuf M.Y. Mayet
 *
 */
public class Rover {

	int intPosX = 0;
	int intPosY = 0;
	char chrPos = 'N';
	int intBoundsX = 0;
	int intBoundsY = 0;

	/**
	 * Constructor to initialize Rover, and start Rover. Begins getting input commands and processing sequences.
	 */
	public Rover() {
		String strInput = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		do {
			System.out.println("Input a Zone Size: ");
			strInput = getInput(reader);
		} while (initRover(strInput));
		do {
			System.out.println("Input Rover's Start Position: ");
			strInput = getInput(reader);
		} while (initPos(strInput));
		boolean boolRun = true;
		while(boolRun){
			System.out.println("Input Commands: ");
			strInput = getInput(reader);
			for (char c : strInput.toCharArray()) {
				boolRun = actOnCommand(c);
				if(!boolRun)
					break;
			}
			System.out.println("Current Rover Position: " + intPosX + " " + intPosY + " " + chrPos);
		}
	}
	/**
	 * 
	 * @param s String containing an integer and character that determines the Rover's initial position and orientation.
	 * @return True if the initial position was set successfully.
	 */
	private boolean initPos(String s) {
		String strNum = s.replaceAll("\\D+","");
		if(strNum.length() > 1) {
			intPosX = Integer.parseInt(strNum.substring(0, (strNum.length()/2)));
			intPosY = Integer.parseInt(strNum.substring((strNum.length()/2), strNum.length()));
		}else if(strNum.length() == 1){
			intPosX = Integer.parseInt(strNum);
			intPosY = Integer.parseInt(strNum);
		}else {
			return true;
		}
		if(intPosX > intBoundsX || intPosY > intBoundsY || intPosX <= 0 || intPosY <= 0)
			return true;
		
		String strWords= s.replaceAll(".?[^NSWE]","");
		if(strNum.length() > 0){
			chrPos = strWords.charAt(0);
		}else {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param s String containing an integer that determines Mar's initial size.
	 * @return true if finished successfully.
	 */
	private boolean initRover(String s) {
		String strNum = s.replaceAll("\\D+","");
		if(strNum.length() > 1) {
			intBoundsX = Integer.parseInt(strNum.substring(0, (strNum.length()/2)));
			intBoundsY = Integer.parseInt(strNum.substring((strNum.length()/2), strNum.length()));
		}else if(strNum.length() == 1){
			intBoundsX = intBoundsY = Integer.parseInt(strNum);
		}else {
			return true;
		}
		return false;
	}

	/**
	 * @param r the BufferedReader used to read the user's console inputs.
	 * @return the String retrieved from the console that the user inputed.
	 */
	private String getInput(BufferedReader r) {
		try {
	        return r.readLine().toUpperCase();
		} catch (IOException e) {
			e.printStackTrace();
		}
	return "";
	}
	
	/**
	 * @param c the command sent to the rover by the user
	 * @return false to disconnect to the rover
	 */
	private boolean actOnCommand(char c) {
		switch (c) {
		case 'L':
			rotateLeft();
			break;
		case 'R':
			rotateRight();
			break;
		case 'M':
			move();
			break;
		case 'Q':
			return false;
		}
		return true;
	}
	
	/**
	 * Method used to rotate the rover's orientation anti-clockwise.
	 */
	private void rotateLeft() {
		switch (chrPos) {
		case 'N':
		case 'n':
			chrPos = 'W';
			break;
		case 'S':
		case 's':
			chrPos = 'E';
			break;
		case 'E':
		case 'e':
			chrPos = 'N';
			break;
		case 'W':
		case 'w':
			chrPos = 'S';
			break;
		}
	}
	
	/**
	 * Method used to rotate the rover's orientation clockwise.
	 */
	private void rotateRight() {
		switch (chrPos) {
		case 'N':
		case 'n':
			chrPos = 'E';
			break;
		case 'S':
		case 's':
			chrPos = 'W';
			break;
		case 'E':
		case 'e':
			chrPos = 'S';
			break;
		case 'W':
		case 'w':
			chrPos = 'N';
			break;
		}
	}
	
	/**
	 * Method used to move the rover forward.
	 */
	private void move() {
		switch (chrPos) {
		case 'N':
		case 'n':
			if(intPosY < (intBoundsY)){
				++intPosY;
			}
			break;
		case 'S':
		case 's':
			if(intPosY > 1){
				--intPosY;
			}
			break;
		case 'E':
		case 'e':
			if(intPosX < (intBoundsX)){
				++intPosX;
			}
			break;
		case 'W':
		case 'w':
			if(intPosX > 1){
				--intPosX;
			}
			break;
		}
	}

	
	
}
