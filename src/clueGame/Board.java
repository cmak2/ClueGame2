package clueGame;

import java.awt.*;
import java.util.*;
import java.io.*;

/**
 * 
 * @author 
 *
 */

/*
 * 
 * Notes:
 * Board class.
 * Contains only one instance of the gameboard. Therefore, the singleton method is used.
 * Max size is an estimate to allocate however much memory will be needed for the gameboard.
 * Framework
 * 
 */

public class Board {
	
	//public methods
	
	//Singleton
	public static Board getInstance() {
		if (boardz == null) {
			boardz = new Board();
		}
		return boardz;
	}
	
	//Initialize
	public void initialize() {	
		
	}
	
	//Get filenames
	public void setBoardConfig(String filename) {
		boardConfigFile = filename;
	}
	
	public void setRoomConfig(String filename) {
		roomConfigFile = filename;
	}
	
	public void setConfigFiles(String filename, String filename2) {
		boardConfigFile = filename;					//CSV
		roomConfigFile = filename2;					//Legend
	}
	
	
	//TODO: Figure out how to check MaxRows/MaxColumns
	//Process Files
	public void loadRoomConfig() throws BadConfigFormatException {
		
		//Code replicated from https://www.mkyong.com Formal Citation at the Bottom
		BufferedReader br = null;
		String ln;
		String splitString = ",";
		int _row = 0;
		int _column = 0;
		
		//A check method to make sure it loaded correctly,
		int maxRows = 0;
		int maxColumns = 0;							
		int currentMaxRow;
		int currentMaxCol;
		
		// String Parser
		try {
			
			br = new BufferedReader(new FileReader(boardConfigFile));
			while ((ln = br.readLine()) != null) {
				String[] Rooms = ln.split(splitString);
				currentMaxCol = Rooms.length - 1;
				
				if (maxColumns == 0) { maxColumns = currentMaxCol; }
				if (maxColumns != currentMaxCol) {
					throw new BadConfigFormatException("Error Loading Game Board Data");
				}
				
				for (_column = 0; _column < Rooms.length - 1; _column++) {			//Last Row and Column are numbers
					BoardCell cell = new BoardCell(_row, _column, Rooms[_column]);
					grid[_row][_column] = cell;
				}
				
				_row++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new BadConfigFormatException("File Not Found");		//Log Exception
		} catch (IOException e) {
			e.printStackTrace();
			throw new BadConfigFormatException("IOException");			//Log Exception
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new BadConfigFormatException("IOException: Failed to close file.");
				}
			}
		}
	}
	
	public void loadBoardConfig() {
	// Read each line, use comma separator to read Initial, name, card
	//Store it to legend map
	//Make another map for the "Card deck"
	}
	
	public void formCardDeck() {		//Method Stub for creating the Card Deck
		
	}
	
	public void calcAdjacencies() {
		// Checking if its an unreachable space is unnecessary currently since they don't have doors to access it. It is implied.
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				BoardCell cell = getCell(i,j);
				Set<BoardCell> adjList;
				if ((i - 1) >= 0) {
					BoardCell cell2 = getCell( i - 1, j);
					cell2.getInitial();
					if(cell2.isDoorway()) {					//Add to adjacent list if direction is appropriate
						if (cell2.getDoorDir() == DoorDirection.UP) {
							adjList.add(cell2);
						}
					} else {						//Else check if its the same initial
						if (cell2.getInitial().charAt(0) == cell.charAt(0)) {
							adjList.add(cell2);
						}
					}
				} else if ((i + 1) <= (numRows - 1)) {
					BoardCell cell2 = getCell( i + 1, j);
					cell2.getInitial();
					if(cell2.isDoorway()) {					//Add to adjacent list if direction is appropriate
						if (cell2.getDoorDir() == DoorDirection.DOWN) {
							adjList.add(cell2);
						}
					} else {						//Else check if its the same initial
						if (cell2.getInitial().charAt(0) == cell.charAt(0)) {
							adjList.add(cell2);
						}
					}
				} else if ((j - 1) >= 0) {
					BoardCell cell2 = getCell( i, j - 1);
					cell2.getInitial();
					if(cell2.isDoorway()) {					//Add to adjacent list if direction is appropriate
						if (cell2.getDoorDir() == DoorDirection.LEFT) {
							adjList.add(cell2);
						}
					} else {						//Else check if its the same initial
						if (cell2.getInitial().charAt(0) == cell.charAt(0)) {
							adjList.add(cell2);
						}
					}
				} else if ((j + 1) <= (numRows - 1)) {
					BoardCell cell2 = getCell( i, j + 1);
					cell2.getInitial();
					if(cell2.isDoorway()) {					//Add to adjacent list if direction is appropriate
						if (cell2.getDoorDir() == DoorDirection.RIGHT) {
							adjList.add(cell2);
						}
					} else {						//Else check if its the same initial
						if (cell2.getInitial().charAt(0) == cell.charAt(0)) {
							adjList.add(cell2);
						}
					}
				}
				adjMatrix[cell] = adjList;
			}
		}
	}
	
	public void calcTargets(BoardCell cell, int pathLength) {
		Set<BoardCell> visitedT;
		//Use an algorithm of deduction and reduction. Stick to same initial unless there is a door.
		//Start at the cell and branch out from each cardinal direction.
		
		//Check adjacency list of the cell, adjacency list will pre-filter rooms/walkways
	}
	
	public void recursiveCalcTargets(Set<BoardCell> visit, int pathLength) {
		
	}
	
	//Getters for JUnit testing
	
	public BoardCell getCell(int r, int c) {
		return grid[r][c];
	}
	
	public BoardCell getCellAt(int r, int c) {	//Duplicate Method to Pass other tests
		return grid[r][c];
	}
	
	public Set<BoardCell> getTargets() {
		return targets;
	}
	
	public Set<BoardCell> getAdjList(BoardCell cell) {
		return adjMatrix.get(cell);
	}
	
	public int getDoors() {
		return numDoors;
	}
	
	public int getNumRows() {		//Returns the number of rows
		return numRows;
	}
	
	public int getNumColumns() {
		return numColumns;
	}
	
	public Map<Character, String> getLegend() {
		return legend;
	}
	
	public Map<BoardCell, Set<BoardCell>> getadjMat() {
		return adjMatrix;
	}
	
	public void incDoors() {
		numDoors++;
	}
	
	
	//Creates constructor
	private Board() {}


	//Public Variables (Static and final)
	public static final int MAX_BOARD_SIZE = 50;		//50 x 50 gameboard
	
	//Private Variables and Datastructures
	private int numRows;
	private int numColumns;
	private int numDoors;				//For Testing

	private static Board boardz;
	private BoardCell board;
	private Map<Character, String> legend;            //Initial to Room Map
	private Map<BoardCell, Set<BoardCell>> adjMatrix; //All the adjacent spaces given a boardcell
	private Set<BoardCell> targets;			//Calculates Targets in real-time based on the PathLength
	private Set<BoardCell> visited;			//Will be called on initialize
	private Set<Cards> CardDeck;			//Prepping, will make a card class
	private BoardCell[][] grid;			//Game Board Grid Array. BoardCells have an initial from the legend and r,c which are corresponding rows and/or columns.
	private String boardConfigFile;		//Game Board
	private String roomConfigFile;		//Legend
}


/*
Works Cited:
[1] mkyong. "How to read and parse CSV file in Java". Mkyong.com. April 25, 2013. Obtained March 7, 2017. https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/.


		
*/
