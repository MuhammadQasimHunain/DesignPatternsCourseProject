package chess;

import java.io.Serializable;

public class Move extends Object implements Serializable{

	// previuos x position
	public int prevCellX;
	
	//previuos y position
	public int prevCellY;
	
	// next x position
	public int nextCellX;
	
	//next y position
	public int nextCellY;
	
	// Constructor of the class
	public Move(final Cell previous, final Cell cell) {
		// TODO Auto-generated constructor stub
		super();
		this.prevCellX = previous.xAxisPosition;
		this.prevCellY = previous.yAxisPosition;
		this.nextCellX = cell.xAxisPosition;
		this.nextCellY = cell.yAxisPosition;
	}
}
