package pieces;

import java.util.ArrayList;

import chess.Cell;

/**
 * This is the Rook class inherited from abstract Piece class
 *
 */
public class Rook extends Piece{
	
	//Constructor
	public Rook(String i,String p,int c)
	{
		setId(i);
		setPath(p);
		setColor(c);
	}
	
	//Move function defined
	public ArrayList<Cell> move(Cell state[][],int xAxisPosition,int yAxisPosition)
	{
		//Rook can move only horizontally or vertically
		possibleMoves.clear();
		int tempx = xAxisPosition - 1;
		while(tempx >= 0)
		{
			if(state[tempx][yAxisPosition].getPiece() == null)
				possibleMoves.add(state[tempx][yAxisPosition]);
			else if(state[tempx][yAxisPosition].getPieceColour() == this.getcolor())
				break;
			else
			{
				possibleMoves.add(state[tempx][yAxisPosition]);
				break;
			}
			tempx--;
		}
		tempx = xAxisPosition + 1;
		while(tempx < 8)
		{
			if(state[tempx][yAxisPosition].getPiece()==null)
				possibleMoves.add(state[tempx][yAxisPosition]);
			else if(state[tempx][yAxisPosition].getPieceColour()==this.getcolor())
				break;
			else
			{
				possibleMoves.add(state[tempx][yAxisPosition]);
				break;
			}
			tempx++;
		}
		int tempy = yAxisPosition - 1;
		while(tempy>=0)
		{
			if(state[xAxisPosition][tempy].getPiece()==null)
				possibleMoves.add(state[xAxisPosition][tempy]);
			else if(state[xAxisPosition][tempy].getPieceColour()==this.getcolor())
				break;
			else
			{
				possibleMoves.add(state[xAxisPosition][tempy]);
				break;
			}
			tempy--;
		}
		tempy = yAxisPosition + 1;
		while(tempy < 8)
		{
			if(state[xAxisPosition][tempy].getPiece()==null)
				possibleMoves.add(state[xAxisPosition][tempy]);
			else if(state[xAxisPosition][tempy].getPieceColour()==this.getcolor())
				break;
			else
			{
				possibleMoves.add(state[xAxisPosition][tempy]);
				break;
			}
			tempy++;
		}
		return possibleMoves;
	}
}
