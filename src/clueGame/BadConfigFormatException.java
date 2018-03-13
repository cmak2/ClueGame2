package clueGame;

import java.io.*;
import java.util.*;
import java.text.*;
/**
 * 
 * 
 * @author Calvin Mak
 *
 */

public class BadConfigFormatException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BadConfigFormatException() throws FileNotFoundException{
		writer = new PrintWriter(errorLog);
		Date Data = new Date();
		String currentTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(Data);
		System.out.println("Bad Configuration Format Detected.");
		writer.println("Bad Configuration Format Detected." + " " + currentTime);
	}
	
	BadConfigFormatException(Exception e, String msg) throws FileNotFoundException{
		writer = new PrintWriter(errorLog);
		Date Data = new Date();
		String currentTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(Data);
		System.out.println(msg);
		writer.println(e);
		writer.println(msg);
		writer.println(currentTime);
		
	}
	
	BadConfigFormatException(String msg) throws FileNotFoundException{
		writer = new PrintWriter(errorLog);
		Date Data = new Date();
		String currentTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(Data);
		System.out.println(msg);
		writer.println(msg + " " + currentTime);
		
	}
	
	private static String errorLog = "ErrorLog.txt";
	private static PrintWriter writer;
}
