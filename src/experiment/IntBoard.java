package experiment;
import java.util.*;


public class IntBoard {
	
	//Default Constructor
	IntBoard() {
		
	}
	
	//Calculates the Adjacency list for each grid Cell and stores results in a map
	void calcAdjacencies() {
		
	}
	
	//Getter
	Set<BoardCell> getAdjList() {
	
		return;
	}
	
	//Calculates targets that are pathLength distance from the startCell. The list of targets are stored as a Set in a variable.
	void calcTargets(int startCell, int pathLength) {
		
	}
	
	//Returns a list of targets as a set
	Set<BoardCell> getTargets() {
		
		return;
	}
	
	//Adjacent Matrix
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	
}
