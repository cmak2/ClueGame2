package tests;

/**
 * 
 * @author Calvin Mak
 * Description: Testing ClueGame
 * 
 */







import java.util.*;

import experiment.BoardCell;
import experiment.IntBoard;

import org.junit.*;

public class IntBoardTests {
	@Before
	public void makeBoard() {
		board = new IntBoard();
	}
	
	public void testSize() {
		BoardCell Cell = board.getCell(0,0);
		Set<BoardCell> testList = board.getAdjList(Cell);
		
	}
	
	
	/**
	 * Testing Targets
	 */
	
	//TL Means top left
	public void testTargetsTL() {
		
	}
	
	//BRC means Bottom Right Corner
	public void testTargetsBRC() {
		
	}
	
	//RE Means Right Edge
	public void testTargetsRE() {
		
	}
	
	//LE Means Left Edge
	public void testTargetsLE() {
		
	}
	
	//SCM means Second Column Middle of Grid
	public void testTargetsSCM() {
		
	}
	
	//SFLC means Second From Last Column
	public void testTargetsSFLC() {
		
	}
	
	private IntBoard board;
}
