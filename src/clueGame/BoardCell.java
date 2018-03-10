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
	BoardCell(int r, int c) {
		row = r;
		column = c;
	}
	BoardCell getCell(int r, int c) {
		return new BoardCell(r,c);
	}
	int row;
	int column;
}
