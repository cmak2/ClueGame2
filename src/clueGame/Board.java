package clueGame;

import java.awt.*;
import java.util.*;

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
		roomConfigFile = filename2;
	}
	
	//Process Files
	public void loadRoomConfig() {
		
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
	
	public Set<BoardCell> getTargets() {
		return targets;
	}
	
	public int getDoors() {
		return numDoors;
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
	private String boardConfigFile;		//Game Board
	private String roomConfigFile;		//Legend
}
