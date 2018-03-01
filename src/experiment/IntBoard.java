package experiment;
import java.util.*;

/**
 * 
 * 
 * @author Calvin Mak
 *
 */


/*
 * Author Notes:
 * About Clue:
 * Clue will be a large gameboard grid
 * By rolling a die, you calculate how many spaces you can move.
 * We will have one game board.
 * There is an array for movement, one space = one value
 * We cannot move backwards, so keep track of spaces
 * Targets represent all of the final spaces we can reach rolling the die.
 * Visited will be spaces visited to prevent going backwards
 */


/*
 * Info:
 * Adjacency list calculates each adjacent cell coordinates in the grid
 */

public class IntBoard {
	
	//Default Constructor
	IntBoard() {
		calcAdjacencies();
	}
	
	//Calculates the Adjacency list for each grid Cell and stores results in a map
	void calcAdjacencies() {
		
	}
	
	//Getter
	BoardCell getCell(int x, int y) {
		return cell;
	}
	
	//Getter
	Set<BoardCell> getAdjList(BoardCell cell2) {
		return adjMatrix.get(cell2);
	}
	
	//Calculates targets that are pathLength distance from the startCell. The list of targets are stored as a Set in a variable.
	void calcTargets(int startCell, int pathLength) {
		
	}
	
	//Returns a list of targets as a set
	Set<BoardCell> getTargets() {
		return targets;
	}
	
	//Adjacent Matrix
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private BoardCell cell;			//Keeps track of current cell
	
}
