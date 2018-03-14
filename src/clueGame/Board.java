package clueGame;

//import java.awt.*;
import java.util.*;
import java.io.*;

import tests.*;

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
	public void initialize(){
		
		//Initialize Variables
		    legend = new HashMap<Character, String>();            //Initial to Room Map
			adjMatrix = new HashMap<BoardCell, Set<BoardCell>>(); //All the adjacent spaces given a boardcell
			targets = new HashSet<BoardCell>();			//Calculates Targets in real-time based on the PathLength

		//Testing
		/*for (int i = 0; i < 12; i++) {
			legend.put('C', "Conservatory");
			legend.put('B', "Ballroom");
			legend.put('R', "Billiard Room");
		}*/
			
		try {
			loadBoardConfig();
			loadRoomConfig();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BadConfigFormatException e) {
			e = new BadConfigFormatException(e, "BadFormatException");
		}
		calcAdjacencies();
		
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
	public void loadBoardConfig() throws BadConfigFormatException, FileNotFoundException {
		
		//Code replicated from https://www.mkyong.com Formal Citation at the Bottom
		BufferedReader br = null;
		String ln;
		String splitString = ",";
		int _row = 0;
		int _column = 0;
		
		// String Parser
		try {
			
			br = new BufferedReader(new FileReader(boardConfigFile));
			while ((ln = br.readLine()) != null) {
				String[] Rooms = ln.split(splitString);
				if(isStringAnInt(Rooms[Rooms.length - 1])) {
					numColumns = Rooms.length - 1;
					for (_column = 0; _column < Rooms.length - 1; _column++) {			//Last Row and Column are numbers
						
						BoardCell cell = new BoardCell(_row, _column, Rooms[_column]);
						grid[_row][_column] = cell;
						if (cell.isDoorway()) {
							incDoors();
						}
						System.out.println(_row + " " +_column);
					}
				} else {
					numColumns = Rooms.length;
					for (_column = 0; _column < Rooms.length; _column++) {			//
						
						BoardCell cell = new BoardCell(_row, _column, Rooms[_column]);
						grid[_row][_column] = cell;
						if (cell.isDoorway()) {
							incDoors();
						}
						System.out.println(_row + " " +_column);
					}
				}
				
				
				_row++;	
			}
			numRows = _row;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new BadConfigFormatException(e, "File Not Found");		//Log Exception
		} catch (IOException e) {
			e.printStackTrace();
			throw new BadConfigFormatException(e, "IOException");			//Log Exception
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw new BadConfigFormatException(e, "NullPointerException");
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
		numRows = _row;
	}
	
	public boolean isStringAnInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public void loadRoomConfig() throws BadConfigFormatException,FileNotFoundException {
	// Read each line, use comma separator to read Initial, name, card
	//Store it to legend map
	//Make another map for the "Card deck"
		FileReader Reader = new FileReader(roomConfigFile);
		Scanner in = new Scanner(Reader);
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] splitLine = line.split(",");
			if(splitLine[0].length() > 1) {
				throw new BadConfigFormatException("Improper Legend Format.");
			}
			if(splitLine[2] != "Card" || splitLine[2] != "Other") {
				throw new BadConfigFormatException("Improper Legend Format.");
			}
			legend.put(splitLine[0].charAt(0),splitLine[1]);
			if (splitLine[2] == "Card") {
				formCardDeck(splitLine[1]);
			}
		}
		try {
			in.close();
			Reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new BadConfigFormatException(e, "IOException: Failed to close file.");
		}
	}
	
	public void formCardDeck(String card) {		//Method Stub for creating the Card Deck
		
	}
	
	public void calcAdjacencies() {
		// Checking if its an unreachable space is unnecessary currently since they don't have doors to access it. It is implied.
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				BoardCell cell = getCell(i,j);
				Set<BoardCell> adjList = new HashSet<BoardCell>();
				if ((i - 1) >= 0) {
					BoardCell cell2 = getCell( i - 1, j);
					String initial = cell2.getInitial();
					if(cell2.isDoorway()) {					//Add to adjacent list if direction is appropriate
						if (cell2.getDoorDir() == DoorDirection.UP) {
							adjList.add(cell2);
						}  else if (cell2.getDoorDir() == DoorDirection.DOWN && initial.charAt(0) == cell2.getInitial().charAt(0)) {
							adjList.add(cell2);
						}
					} else {						//Else check if its the same initial
						if (cell2.getInitial().charAt(0) == cell.getInitial().charAt(0)) {
							adjList.add(cell2);
						}
					}
				} else if ((i + 1) <= (numRows - 1)) {
					BoardCell cell2 = getCell( i + 1, j);
					String initial = cell2.getInitial();
					if(cell2.isDoorway()) {					//Add to adjacent list if direction is appropriate
						if (cell2.getDoorDir() == DoorDirection.DOWN) {
							adjList.add(cell2);
						}  else if (cell2.getDoorDir() == DoorDirection.UP && initial.charAt(0) == cell2.getInitial().charAt(0)) {
							adjList.add(cell2);
						}
					} else {						//Else check if its the same initial
						if (cell2.getInitial().charAt(0) == cell.getInitial().charAt(0)) {
							adjList.add(cell2);
						}
					}
				} else if ((j - 1) >= 0) {
					BoardCell cell2 = getCell( i, j - 1);
					String initial = cell2.getInitial();
					if(cell2.isDoorway()) {					//Add to adjacent list if direction is appropriate
						if (cell2.getDoorDir() == DoorDirection.LEFT && initial.charAt(0) == 'W') {
							adjList.add(cell2);
						} else if (cell2.getDoorDir() == DoorDirection.RIGHT && initial.charAt(0) == cell2.getInitial().charAt(0)) {
							adjList.add(cell2);
						}
					} else {						//Else check if its the same initial
						if (cell2.getInitial().charAt(0) == cell.getInitial().charAt(0)) {
							adjList.add(cell2);
						}
					}
				} else if ((j + 1) <= (numRows - 1)) {
					BoardCell cell2 = getCell( i, j + 1);
					String initial = cell2.getInitial();
					if(cell2.isDoorway()) {					//Add to adjacent list if direction is appropriate
						if (cell2.getDoorDir() == DoorDirection.RIGHT) {
							adjList.add(cell2);
						}  else if (cell2.getDoorDir() == DoorDirection.LEFT && initial.charAt(0) == cell2.getInitial().charAt(0)) {
							adjList.add(cell2);
						}
					} else {						//Else check if its the same initial
						if (cell2.getInitial().charAt(0) == cell.getInitial().charAt(0)) {
							adjList.add(cell2);
						}
					}
				}
				adjMatrix.put(cell, adjList);
			}
		}
	}
	
	public void calcTargets(BoardCell cell, int pathLength) {
		Set<BoardCell> visitedT = new HashSet<BoardCell>();
		//Use an algorithm of deduction and reduction. Stick to same initial unless there is a door.
		//Start at the cell and branch out from each cardinal direction.
		visitedT.add(cell);
		//Check adjacency list of the cell, adjacency list will pre-filter rooms/walkways
		if (pathLength < 1) {
			System.out.println("Invalid pathLength");
			return;
		}
		recursiveCalcTargets(visitedT, pathLength, cell);
	}
	
	public void calcTargets(int r, int c, int pathLength) {
		Set<BoardCell> visitedT = new HashSet<BoardCell>();
		//Use an algorithm of deduction and reduction. Stick to same initial unless there is a door.
		//Start at the cell and branch out from each cardinal direction.
		BoardCell cell = grid[r][c];
		visitedT.add(cell);
		//Check adjacency list of the cell, adjacency list will pre-filter rooms/walkways
		if (pathLength < 1) {
			System.out.println("Invalid pathLength");
			return;
		}
		recursiveCalcTargets(visitedT, pathLength, cell);
	}
	
	public void recursiveCalcTargets(Set<BoardCell> visit, int pathLength, BoardCell cell) { 		//Recursive method that calculates all the final targets
		if (pathLength == 0) { System.out.println("Target Complete."); targets.add(cell); return; } //End of path, add target cell to targets Also the base case
		if (adjMatrix.get(cell).contains(new BoardCell(cell.getRow() + 1, cell.getColumn()))) {
			BoardCell cell2 = getCell(cell.getRow() + 1, cell.getColumn());
			visit.add(cell2);
			if (cell2.isDoorway()) {																//Target Stops at a Doorway
				recursiveCalcTargets(visit, 0, cell2);
			}
			recursiveCalcTargets(visit, pathLength - 1, cell2);
		}
		if (adjMatrix.get(cell).contains(new BoardCell(cell.getRow() - 1, cell.getColumn()))) {
			BoardCell cell2 = getCell(cell.getRow() - 1, cell.getColumn());
			visit.add(cell2);
			if (cell2.isDoorway()) {
				recursiveCalcTargets(visit, 0, cell2);
			}
			recursiveCalcTargets(visit, pathLength - 1, cell2);
		}
		if (adjMatrix.get(cell).contains(new BoardCell(cell.getRow(), cell.getColumn() + 1))) {
			BoardCell cell2 = getCell(cell.getRow(), cell.getColumn() + 1);
			visit.add(cell2);
			if (cell2.isDoorway()) {
				recursiveCalcTargets(visit, 0, cell2);
			}
			recursiveCalcTargets(visit, pathLength - 1, cell2);
		}
		if (adjMatrix.get(cell).contains(new BoardCell(cell.getRow(), cell.getColumn() - 1))) {
			BoardCell cell2 = getCell(cell.getRow(), cell.getColumn() - 1);
			visit.add(cell2);
			if (cell2.isDoorway()) {
				recursiveCalcTargets(visit, 0, cell2);
			}
			recursiveCalcTargets(visit, pathLength - 1, cell2);
		}
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
	
	public Set<BoardCell> getAdjList(int r, int c) {
		BoardCell cell = grid[r][c];
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
	//private BoardCell board;
	private Map<Character, String> legend;            //Initial to Room Map
	private Map<BoardCell, Set<BoardCell>> adjMatrix; //All the adjacent spaces given a boardcell
	private Set<BoardCell> targets;			//Calculates Targets in real-time based on the PathLength
	//private Set<BoardCell> visited;			//Will be called on initialize
	//private Set<Cards> CardDeck;			//Prepping, will make a card class
	private BoardCell[][] grid;			//Game Board Grid Array. BoardCells have an initial from the legend and r,c which are corresponding rows and/or columns.
	private String boardConfigFile;		//Game Board
	private String roomConfigFile;		//Legend
}


/*
Works Cited:
[1] mkyong. "How to read and parse CSV file in Java". Mkyong.com. April 25, 2013. Obtained March 7, 2017. https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/.


		
*/
