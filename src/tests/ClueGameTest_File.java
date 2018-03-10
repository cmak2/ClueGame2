package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import experiment.*;
import clueGame.*;
import java.lang.*;
import java.util.*;
import java.awt.*;

class ClueGameTest_File {

	//Variables for testing size based on my config files
	private static final int NUMROWS = 29;
	private static final int NUMCOLUMNS = 29;
	private static final int LEGENDSIZE = 14;
	private static final int NUMDOORS = 38;
	
	private static Board board;
	
	@Before
	public static void initTest() {
		
		board = Board.getInstance();
		board.setBoardConfig("Design Layout.csv");
		board.setRoomConfig("ClueGameTest1Legend.txt");
		board.initialize();
	}
	
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
		
		assertEquals(NUMDOORS, board.getDoors());
	}
	

	
}
