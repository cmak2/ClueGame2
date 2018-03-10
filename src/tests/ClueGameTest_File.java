package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.*;

import clueGame.BoardCell;
import clueGame.Board;
import clueGame.BadConfigFormatException;
import clueGame.DoorDirection;
//import java.lang.*;
import java.util.*;
import java.awt.*;

class ClueGameTest_File {
	@Before
	public static void initTest() {
		
		board = Board.getInstance();
		board.setBoardConfig("Design Layout.csv");
		board.setRoomConfig("ClueGameTest1Legend.txt");
		board.initialize();
	}
	//Variables for testing size based on my config files
	private static final int NUMROWS = 29;
	private static final int NUMCOLUMNS = 29;
	private static final int LEGENDSIZE = 14;
	private static final int NUMDOORS = 38;
	
	private static Board board;
	

	
	@Test
	public void testLegend() {
		Map<Character, String> legend = board.getLegend();
		
		assertEquals(LEGENDSIZE, legend);			//Is the Size equivalent
		
		//Test Rooms
		assertEquals("Bedroom", legend.get('B'));	//First entry
		assertEquals("Guest Room", legend.get('G'));
		assertEquals("Hall", legend.get('H'));
		assertEquals("Living Room", legend.get('L'));
		assertEquals("Recreation Room", legend.get('R'));
		assertEquals("Washroom/Bathroom", legend.get('V'));
		assertEquals("Closet", legend.get('X'));	//Last Entry
		
	}
	
	@Test
	public void testBoardSize() {
		
		assertEquals(NUMROWS, board.getNumRows());			//Tests if the numbers of rows are equivalent
		assertEquals(NUMCOLUMNS, board.getNumColumns());	//Tests if the number of columns are equivalent
		
	}
	
	@Test
	public void testDoors() {
		
		BoardCell door = board.getCell(4, 3);
		
		assertEquals(NUMDOORS, board.getDoors());
		assertEquals(DoorDirection.RIGHT, door.getDoorDir());
		door = board.getCell(4, 7);
		assertEquals(DoorDirection.DOWN, door.getDoorDir());
		door = board.getCell(20, 21);
		assertEquals(DoorDirection.UP, door.getDoorDir());
		door = board.getCell(23, 3);
		assertFalse(door.isDoorway());
		door = board.getCell(14, 7);
		assertNotEquals(door.getDoorDir(), board.getCell(7, 13).getDoorDir());
		assertEquals(door.getDoorDir(), board.getCell(7,15).getDoorDir());
		assertNotEquals(door.getDoorDir(), board.getCell(21, 7).getDoorDir());
		door = board.getCell(12, 26);
		assertEquals(DoorDirection.LEFT,door.getDoorDir());
		door = board.getCell(34, 32);
		assertNull(door.getDoorDir());
	}
	
	@Test
	public void testCells() {
		BoardCell door = board.getCell(23, 3);
		assertTrue(door.isDoorway());
		assertEquals('W', door.getInitial());
		door = board.getCell(21,8);
		assertEquals('L', door.getInitial());
		assertEquals(door.getInitial(), board.getCell(21, 7).getInitial());
		door = board.getCell(29, 29);
		assertEquals('V', door.getInitial());
		door = board.getCell(30, 30);
		assertNull(door.getInitial());
		door = board.getCell(8,7);
		assertEquals('X', door.getInitial());
		door = board.getCell(26,12);
		assertEquals('H', door.getInitial());
		door = board.getCell(0,0);
		assertEquals('G', door.getInitial());
		assertEquals(board.getCell(14, 6).getInitial(), board.getCell(14,11));
	}

	
}
