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
	
	//Process Files
	public void loadRoomConfig() throws BadConfigFormatException {
		
		//Code replicated from https://www.mkyong.com Formal Citation at the Bottom
		BufferedReader br = null;
		String ln;
		String splitString = ",";
		int _row = 0;
		int _column = 0;
		try {
			br = new BufferedReader(new FileReader(boardConfigFile));
			while ((ln = br.readLine()) != null) {
				String[] Rooms = ln.split(splitString);
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
		
	}
	
	public void calcAdjacencies() {
		
	}
	
	public void calcTargets(BoardCell cell, int pathLength) {
		
	}
	
	//Getters for JUnit testing
	
	public BoardCell getCell(int r, int c) {
		return new BoardCell(r, c);
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
	private Map<Character, String> legend;
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private BoardCell[][] grid;			//Game Board Grid Array. BoardCells have an initial from the legend and r,c which are corresponding rows and/or columns.
	private String boardConfigFile;		//Game Board
	private String roomConfigFile;		//Legend
}


/*
Works Cited:
[1] mkyong. "How to read and parse CSV file in Java". Mkyong.com. April 25, 2013. Obtained March 7, 2017. https://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/.


		
*/