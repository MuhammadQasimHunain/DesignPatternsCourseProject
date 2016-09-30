package pieces;

import java.util.ArrayList;

import chess.Cell;

/**
 * This is the Queen Class inherited from the abstract Piece class
 *
 */
public class Queen extends Piece{
	
	//Constructors
	public Queen(String i,String p,int c)
	{
		setId(i);
		setPath(p);
		setColor(c);
	}
	
	//Move Function Defined
	public ArrayList<Cell> move(Cell state[][],int xAxisPosition,int yAxisPosition)
	{
		//Queen has most number of possible moves
		//Queen can move any number of steps in all 8 direction
		//The possible moves of queen is a combination of Rook and Bishop
		possibleMoves.clear();
		
		//Checking possible moves in vertical direction
		int tempx = xAxisPosition - 1;
		while(tempx >= 0)
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
			tempx--;
		}
		
		tempx = xAxisPosition+1;
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
		
		
		//Checking possible moves in horizontal Direction
		int tempy = yAxisPosition-1;
		while(tempy >= 0)
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
		tempy = yAxisPosition+1;
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
		
		//Checking for possible moves in diagonal direction
		tempx = xAxisPosition + 1;
                tempy = yAxisPosition - 1;
		while(tempx < 8 && tempy >= 0)
		{
			if(state[tempx][tempy].getPiece()==null)
				possibleMoves.add(state[tempx][tempy]);
			else if(state[tempx][tempy].getPieceColour()==this.getcolor())
				break;
			else
			{
				possibleMoves.add(state[tempx][tempy]);
				break;
			}
			tempx++;
			tempy--;
		}
		tempx = xAxisPosition - 1;
                tempy = yAxisPosition + 1;
		while(tempx >= 0&& tempy < 8)
		{
			if(state[tempx][tempy].getPiece()==null)
				possibleMoves.add(state[tempx][tempy]);
			else if(state[tempx][tempy].getPieceColour()==this.getcolor())
				break;
			else
			{
				possibleMoves.add(state[tempx][tempy]);
				break;
			}
			tempx--;
			tempy++;
		}
		tempx = xAxisPosition - 1;
                tempy = yAxisPosition - 1;
		while(tempx >= 0 && tempy >= 0)
		{
			if(state[tempx][tempy].getPiece()==null)
				possibleMoves.add(state[tempx][tempy]);
			else if(state[tempx][tempy].getPieceColour()==this.getcolor())
				break;
			else
			{
				possibleMoves.add(state[tempx][tempy]);
				break;
			}
			tempx--;
			tempy--;
		}
		tempx = xAxisPosition + 1;
                tempy = yAxisPosition + 1;
		while(tempx <8 && tempy <8)
		{
			if(state[tempx][tempy].getPiece()==null)
				possibleMoves.add(state[tempx][tempy]);
			else if(state[tempx][tempy].getPieceColour()==this.getcolor())
				break;
			else
			{
				possibleMoves.add(state[tempx][tempy]);
				break;
			}
			tempx++;
			tempy++;
		}
		return possibleMoves;
	}
}