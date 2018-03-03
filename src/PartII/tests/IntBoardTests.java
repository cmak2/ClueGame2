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
	
	//@Test
	/*public void testSize() {
		BoardCell Cell = board.getGrid(0,0);
		Set<BoardCell> testList = board.getAdjList(Cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
		
	}*/
	
	
	/**
	 * Testing Targets
	 */
	
	//TL Means top left
	@Test
	public void testTargetsTL() {
		BoardCell cell = board.getCell(0,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
		return;
	}
	
	//BRC means Bottom Right Corner
	@Test
	public void testTargetsBRC() {
		BoardCell cell = board.getCell(3,3);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertEquals(2, testList.size());
		return;
	}
	
	//RE Means Right Edge
	@Test
	public void testTargetsRE() {
		BoardCell cell = board.getCell(1,3);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(0, 3)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertEquals(3, testList.size());
	}
	
	//LE Means Left Edge
	@Test
	public void testTargetsLE() {
		BoardCell cell = board.getCell(2,0);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(3, 0)));
		assertEquals(3, testList.size());
	}
	
	//SCM means Second Column Middle of Grid
	@Test
	public void testTargetsSCM() {
		BoardCell cell = board.getCell(1,1);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertEquals(4, testList.size());
	}
	
	//SFLC means Second From Last Column
	@Test
	public void testTargetsSFLC() {
		BoardCell cell = board.getCell(2,2);
		Set<BoardCell> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertEquals(4, testList.size());
	}
	
	private IntBoard board;
	
	//Main method
	public static void main(String[] args) {
		makeBoard();
		//Tests
		testTargetsTL();
		testTargetsBRC();
		testTargetsRE();
		testTargetsLE();
		testTargetsSCM();
		testTargetsSFLC();
	}
}
