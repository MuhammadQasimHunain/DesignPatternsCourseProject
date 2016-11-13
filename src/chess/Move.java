package chess;

import java.io.Serializable;

public class Move extends Object implements Serializable{
	public Move(Cell previous, Cell cell) {
		// TODO Auto-generated constructor stub
		this.prevCellX = previous.xAxisPosition;
		this.prevCellY = previous.yAxisPosition;
		this.nextCellX = cell.xAxisPosition;
		this.nextCellY = cell.yAxisPosition;
	}
	public int prevCellX;
	public int prevCellY;
	public int nextCellX;
	public int nextCellY;
}
