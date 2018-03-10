package clueGame;

/**
 * 
 * @author 
 *
 */

/*
 * 
 * Represents a cell on the game board
 * 
 */

public class BoardCell {
	
	//Constructors
	BoardCell(int r, int c) {
		row = r;
		column = c;
	}	
	
	//Methods
	public boolean isWalkway() {	//Is it a walkway?
		
		return true;
	}
	
	public boolean isRoom() {		//Is it a room?
		
		return true;
	}
	
	public boolean isDoorway() {    //Is it a Doorway?
		
		return true;
	}
	
	public DoorDirection getDoorDir() {	//What Direction
		return null;
	}
	
	public char getInitial() {
		return initial;
	}
	
	//Variables
	
	int row;
	int column;
	char initial;	//From Legend
}
