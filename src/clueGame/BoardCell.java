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
	BoardCell(int r, int c, String i) {
		row = r;
		column = c;
		initial = i;
	}	
	
	BoardCell(int r, int c) {
		row = r;
		column = c;
	}
	
	//Methods
	public boolean isWalkway() {	//Is it a walkway?
		if (initial.charAt(0) == 'W')
			return true;
		return false;
	}
	
	public boolean isRoom() {		//Is it a room?
		if (initial.charAt(0) != 'W')
			return true;
		return false;
	}
	
	public boolean isDoorway() {    //Is it a Doorway?
		if (initial.charAt(1) == 'U' || initial.charAt(1) == 'L' || initial.charAt(1) == 'R' || initial.charAt(1) == 'D')
			return true;
		return false;
	}
	
	public int getRow() {			//Returns Row value
		return row;
	}
	
	public int getColumn() {		//Returns Column Value
		return column;
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
