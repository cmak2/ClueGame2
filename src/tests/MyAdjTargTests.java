package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class MyAdjTargTests {
	private static Board board;
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("CTest_ClueLayout.csv", "CTest_ClueLegend.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}
	
	//Green
	@Test
	public void testAdjacencyInsideRooms()
	{
		
		Set<BoardCell> testSet = board.getAdjList(0, 0);
		assertEquals(0, testSet.size());
		
		testSet = board.getAdjList(4, 8);
		assertEquals(0, testSet.size());
		
		
		testSet = board.getAdjList(8, 12);
		assertEquals(0, testSet.size());
	
		testSet = board.getAdjList(21, 0);
		assertEquals(0, testSet.size());
		
		testSet = board.getAdjList(18, 8);
		assertEquals(0, testSet.size());
	
		testSet = board.getAdjList(7, 19);
		assertEquals(0, testSet.size());
		
		testSet = board.getAdjList(11, 18);
		assertEquals(0, testSet.size());
	}

	// Ensure that the adjacency list from a doorway is only the
	// walkway. NOTE: This test could be merged with door 
	// direction test. 
	// These tests are Orange on the planning spreadsheet
	@Test
	public void testAdjacencyRoomExit()
	{
		// TEST DOORWAY RIGHT 
		Set<BoardCell> testList = board.getAdjList(17, 3);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(17, 4)));
		// TEST DOORWAY LEFT 
		testList = board.getAdjList(18, 6);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(18, 5)));
		//TEST DOORWAY DOWN
		testList = board.getAdjList(4, 7);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(5, 7)));
		//TEST DOORWAY UP
		testList = board.getAdjList(20, 21);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(19, 21)));
		
	}
	
	// Test adjacency at entrance to rooms
	// These tests are Purple in planning spreadsheet
	@Test
	public void testAdjacencyDoorways()
	{

		Set<BoardCell> testList = board.getAdjList(4, 2);
		assertTrue(testList.contains(board.getCellAt(4, 3)));
		assertTrue(testList.contains(board.getCellAt(4, 1)));
		assertTrue(testList.contains(board.getCellAt(3, 2)));
		assertEquals(3, testList.size());

		testList = board.getAdjList(24, 2);
		assertEquals(0, testList.size());
		
		testList = board.getAdjList(4, 4);
		assertTrue(testList.contains(board.getCellAt(4, 3)));
		assertTrue(testList.contains(board.getCellAt(3, 4)));
		assertTrue(testList.contains(board.getCellAt(5, 4)));
		assertTrue(testList.contains(board.getCellAt(5, 5)));
		assertEquals(4, testList.size());

		testList = board.getAdjList(5, 7);
		assertTrue(testList.contains(board.getCellAt(4, 7)));
		assertTrue(testList.contains(board.getCellAt(6, 7)));
		assertEquals(2, testList.size());
	}

	// Test a variety of walkway scenarios
	// These tests are Pink on the planning spreadsheet
	@Test
	public void testAdjacencyWalkways()
	{
		//Top Border
		Set<BoardCell> testList = board.getAdjList(0, 12);
		assertTrue(testList.contains(board.getCellAt(1, 12)));
		assertTrue(testList.contains(board.getCellAt(0, 13)));
		assertEquals(2, testList.size());
		

		testList = board.getAdjList(5, 3);
		assertTrue(testList.contains(board.getCellAt(5, 2)));
		assertTrue(testList.contains(board.getCellAt(5, 4)));
		assertTrue(testList.contains(board.getCellAt(6, 3)));
		assertEquals(3, testList.size());


		testList = board.getAdjList(13, 3);
		assertTrue(testList.contains(board.getCellAt(12, 3)));
		assertTrue(testList.contains(board.getCellAt(13, 4)));
		assertEquals(2, testList.size());

		// Test surrounded by 4 walkways
		testList = board.getAdjList(22,3);
		assertTrue(testList.contains(board.getCellAt(22, 4)));
		assertTrue(testList.contains(board.getCellAt(22, 2)));
		assertTrue(testList.contains(board.getCellAt(23, 3)));
		assertTrue(testList.contains(board.getCellAt(21, 3)));
		assertEquals(4, testList.size());
		

		testList = board.getAdjList(29, 25);
		assertTrue(testList.contains(board.getCellAt(28, 25)));
		assertTrue(testList.contains(board.getCellAt(29, 26)));
		assertEquals(2, testList.size());
		
	
		testList = board.getAdjList(7, 17);
		assertTrue(testList.contains(board.getCellAt(8, 17)));
		assertTrue(testList.contains(board.getCellAt(6, 17)));
		assertTrue(testList.contains(board.getCellAt(7, 18)));
		assertTrue(testList.contains(board.getCellAt(7, 16)));
		assertEquals(4, testList.size());

	}
	
	
	// Tests of just walkways, 1 step, includes on edge of board
	// and beside room
	// Have already tested adjacency lists on all four edges, will
	// only test two edges here
	// These are Teal on the planning spreadsheet
	@Test
	public void testTargetsOneStep() {
		board.calcTargets(0, 4, 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(1, 4)));
		assertTrue(targets.contains(board.getCellAt(0, 5)));	
		
		board.calcTargets(9, 4, 1);
		targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCellAt(8, 4)));
		assertTrue(targets.contains(board.getCellAt(9, 3)));	
		assertTrue(targets.contains(board.getCellAt(9, 5)));			
		
		//Target in room should be empty
		board.calcTargets(5, 20, 1);
		targets = board.getTargets();
		assertEquals(0, targets.size());
	}
	
	// Tests of just walkways, 2 steps
	// These are Teal on the planning spreadsheet
	@Test
	public void testTargetsTwoSteps() {
		board.calcTargets(0, 13, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCellAt(1, 12)));
		assertTrue(targets.contains(board.getCellAt(2, 13)));
		
	}
	
	// Tests of just walkways, 4 steps
	// These are Teal on the planning spreadsheet
	@Test
	public void testTargetsFourSteps() {
		board.calcTargets(9, 4, 4);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(9, targets.size());
		assertTrue(targets.contains(board.getCellAt(10, 1)));
		assertTrue(targets.contains(board.getCellAt(13, 4)));
		assertTrue(targets.contains(board.getCellAt(6, 3)));
		assertTrue(targets.contains(board.getCellAt(12, 5)));
		
		// Includes a path that doesn't have enough length
		board.calcTargets(0, 4, 4);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(4, 4)));
		assertTrue(targets.contains(board.getCellAt(3, 5)));
	}	
	
	// Tests of just walkways plus one door, 6 steps
	// These are Teal on the planning spreadsheet

	@Test
	public void testTargetsSixSteps() {
		//Test into room and Target 6 steps (Doesn't require all steps)
		board.calcTargets(0, 4, 6);
		Set<BoardCell> targets= board.getTargets();
		assertTrue(targets.contains(board.getCellAt(5, 3)));
		assertTrue(targets.contains(board.getCellAt(2, 5)));	
		assertTrue(targets.contains(board.getCellAt(6, 4)));	
	}	

	// Test getting out of a room
	// These are Teal on the planning spreadsheet
	@Test
	public void testRoomExit()
	{
		// To Door
		board.calcTargets(15, 2, 1);
		Set<BoardCell> targets = board.getTargets();
		
		
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(15, 3)));
		
		//Leaving Room
		board.calcTargets(15, 2, 2);
		targets= board.getTargets();
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(15, 4)));
	}

}

