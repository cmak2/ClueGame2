package experiment;
import java.util.*;

/**
 * 
 * 
 * @author Calvin Mak
 * Date: 3/1/2018
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
		gameSetting = Setting.STREAMLOAD;
		grid = new BoardCell[3][3];
		calcAdjacencies();
		init();
	}
	
	IntBoard(char c) {
		setSetting(c);
	}
	
	void init() {					//Temporary Stub to initialize game
		
	}
	
	//Calculates the Adjacency list for each grid Cell and stores results in a map
	void calcAdjacencies() {
		Set<BoardCell> adjList = null;
		
		if (cell.row - 1 >= 0 && cell.row - 1 < grid.length) {			//All columns will have same max length so 0 is ok for a default value
			if (cell.column - 1 >= 0 && cell.column - 1 < grid[0].length) {
				BoardCell cell2 = new BoardCell(cell.row - 1, cell.column - 1);
				adjList.add(cell2);
			}	
			if (cell.column + 1 >= 0 && cell.column + 1 < grid[0].length) {
				BoardCell cell2 = new BoardCell(cell.row - 1, cell.column + 1);
				adjList.add(cell2);
			}
		}
		if (cell.row + 1 >= 0 && cell.row + 1 < grid.length) {			//All columns will have same max length so 0 is ok for a default value
			if (cell.column - 1 >= 0 && cell.column - 1 < grid[0].length) {
				BoardCell cell2 = new BoardCell(cell.row + 1, cell.column - 1);
				adjList.add(cell2);
			}
			if (cell.column + 1 >= 0 && cell.column + 1 < grid[0].length) {
				BoardCell cell2 = new BoardCell(cell.row + 1, cell.column + 1);
				adjList.add(cell2);
			}
		}
		adjMatrix.put(cell,adjList);
	}
	
	//Getter
	BoardCell getCell(int x, int y) {
		BoardCell checkCell = new BoardCell(x,y);
		return checkCell;
	}
	
	//Getter
	Set<BoardCell> getAdjList(BoardCell cell2) {
		return adjMatrix.get(cell2);
	}
	
	//Calculates targets that are pathLength distance from the startCell. The list of targets are stored as a Set in a variable.
	void calcTargets(BoardCell startCell, int pathLength) {
		for (int i = (startCell.row - pathLength); i < (startCell.row + pathLength); i++) {	//Will scan a 2*pathlength * 2 * pathlength grid. 
			for (int j = (startCell.column - pathLength); j < (startCell.column + pathLength); j++) {
				if (i >= 0 && i < grid.length) {		//Constrains to within the Board Grid
					if (j >= 0 && j > grid.length) {
						if ((i - startCell.row) + (j - startCell.column) == pathLength) { //If distance from cell = pathlength, it is a target.
							BoardCell targCell = new BoardCell(i,j);
							targets.add(targCell);	
						}
					}
				}
			}
		}	
	}
	
	//Game Options
	void setSetting(char c) {
		if (c == 'p') { gameSetting = Setting.PRELOAD; }
		else { gameSetting = Setting.STREAMLOAD; }
	}
	
	//Returns a list of targets as a set
	Set<BoardCell> getTargets() {
		return targets;
	}
	
	enum Setting { PRELOAD, STREAMLOAD }; //Do I want to pre-calculate the map or calculate it after each move.	

	//Adjacent Matrix
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private BoardCell[][] grid;		//The Board Grid
	//private Map<int, Set<BoardCell>> TotalTargets;
	private BoardCell cell;			//Keeps track of current cell
	private Setting gameSetting;	
}
