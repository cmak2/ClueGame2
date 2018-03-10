package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import experiment.*;
import clueGame.*;
import java.lang.*;

class ClueGameTest_File {

	//Variables for testing size based on my config files
	private static final int NUMROWS = 29;
	private static final int NUMCOLUMNS = 29;
	private static final int LEGENDSIZE = 14;
	
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
		Map<Character, String> legend = Board.getLegend();
	}
	
	@Test
	public void testBoardDimensions() {
		assertEquals(NUMROWS, board.getNumRows());
		assertEquals(NUMCOLUMNS, board.getNumColumns());
	}
	

	
}
