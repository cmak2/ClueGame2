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
		if (initial.length() > 1)
		{
			if (initial.charAt(1) == 'U') {
				return DoorDirection.UP;
			} else if (initial.charAt(1) == 'D') {
				return DoorDirection.DOWN;
			} else if (initial.charAt(1) == 'R') {
				return DoorDirection.RIGHT;
			} else if (initial.charAt(1) == 'L') {
				return DoorDirection.LEFT;
			}
		}
		return DoorDirection.NONE;
	}
	
	public DoorDirection getDoorDirection() {				//Same as method above to accommodate both test files.
		if (initial.length() > 1)
		{
			if (initial.charAt(1) == 'U') {
				return DoorDirection.UP;
			} else if (initial.charAt(1) == 'D') {
				return DoorDirection.DOWN;
			} else if (initial.charAt(1) == 'R') {
				return DoorDirection.RIGHT;
			} else if (initial.charAt(1) == 'L') {
				return DoorDirection.LEFT;
			}
		}
		return DoorDirection.NONE;
	}
	
	public String getInitial() {		//There could be more than one character for the initial, for example doors and other external elements.
		return initial;
	}
	
	//Variables
	
	int row;
	int column;
	String initial;	//From Legend
}
