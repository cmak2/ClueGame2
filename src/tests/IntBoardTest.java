package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

//import experiment.IntBoard;

import clueGame.Board;
import clueGame.BoardCell;
/**
 * 
 * @author Calvin Mak
 * Description: Testing ClueGame
 * 
 */



public class IntBoardTest {
	@Before
	public void makeBoard() {
		board = Board.getInstance();
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
	 * Testing Adjacent lists (but called it targets)
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
	
	@Test
	public void testTargets1() {
		BoardCell cell = board.getCell(0,0);
		board.calcTargets(cell,3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(3,0)));
	}
	public void testTargets2() {
		BoardCell cell = board.getCell(1,0);
		board.calcTargets(cell,1);
		Set<BoardCell> targets = board.getTargets();
		assertTrue(targets.contains(board.getCell(1,1)));
	}
	
	public void testTargets3() {
		BoardCell cell = board.getCell(4,1);
		board.calcTargets(cell,3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(0, targets.size());
	}
	
	public void testTargets4() {
		BoardCell cell = board.getCell(1,1);
		board.calcTargets(cell,2);
		Set<BoardCell> targets = board.getTargets();
		assertTrue(targets.contains(board.getCell(0,0)));
		assertTrue(targets.contains(board.getCell(3,1)));
	}
	
	public void testTargets5() {
		BoardCell cell = board.getCell(3,0);
		board.calcTargets(cell,5);
		Set<BoardCell> targets = board.getTargets();
		assertTrue(targets.contains(board.getCell(0,2)));
		assertTrue(targets.contains(board.getCell(1,3)));
	}
	
	
	public void testTargets6() {
		BoardCell cell = board.getCell(2,2);
		board.calcTargets(cell,1);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(1,2)));
		assertTrue(targets.contains(board.getCell(2,3)));
		assertTrue(targets.contains(board.getCell(3,2)));
		assertTrue(targets.contains(board.getCell(2,1)));
	}
	
	public void testTargets7() {
		BoardCell cell = board.getCell(0,1);
		board.calcTargets(cell,3);
		Set<BoardCell> targets = board.getTargets();
		assertTrue(targets.contains(board.getCell(3,1)));
		assertTrue(targets.contains(board.getCell(1,3)));
		assertTrue(targets.contains(board.getCell(2,2)));
		assertTrue(targets.contains(board.getCell(2,0)));
	}
	
	private static Board board;
}