package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

import experiment.BoardCell;
import experiment.IntBoard;
/**
 * 
 * @author Calvin Mak
 * Description: Testing ClueGame
 * 
 */



public class IntBoardTests {
	@Before
	public void makeBoard() {
		board = new IntBoard();
	}
	
	@Test
	public void testSize() {
		BoardCell Cell = board.getCell(0,0);
		Set<BoardCell> testList = board.getAdjList(Cell);
		
	}
	
	
	/**
	 * Testing Targets
	 */
	
	//TL Means top left
	@Test
	public void testTargetsTL() {
		
	}
	
	//BRC means Bottom Right Corner
	@Test
	public void testTargetsBRC() {
		
	}
	
	//RE Means Right Edge
	@Test
	public void testTargetsRE() {
		
	}
	
	//LE Means Left Edge
	@Test
	public void testTargetsLE() {
		
	}
	
	//SCM means Second Column Middle of Grid
	@Test
	public void testTargetsSCM() {
		
	}
	
	//SFLC means Second From Last Column
	@Test
	public void testTargetsSFLC() {
		
	}
	
	private IntBoard board;
}
