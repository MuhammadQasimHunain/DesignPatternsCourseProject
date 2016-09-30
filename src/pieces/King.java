package pieces;

import java.util.ArrayList;

import chess.Cell;

public class King extends Piece{
	
	private int xAxisPosition,yAxisPosition; //Extra variables for King class to keep a track of king's position
	
	//King Constructor
	public King(String i,String p,int c,int x,int y)
	{
		setXAxisPosition(x);
		setYAxisPosition(y);
		setId(i);
		setPath(p);
		setColor(c);
	}
	
	//general value access functions
	public void setXAxisPosition(int x)
	{
		this.xAxisPosition=x;
	}
	public void setYAxisPosition(int y)
	{
		this.yAxisPosition=y;
	}
	public int getXAxisPosition()
	{
		return xAxisPosition;
	}
	public int getYAxisPosition()
	{
		return yAxisPosition;
	}
	//Move Function for King Overridden from Pieces
	public ArrayList<Cell> move(Cell state[][],int x,int y)
	{
		//King can move only one step. So all the adjacent 8 cells have been considered.
		possibleMoves.clear();
		int posx[]={x,x,x+1,x+1,x+1,x-1,x-1,x-1};
		int posy[]={y-1,y+1,y-1,y,y+1,y-1,y,y+1};
		for(int i=0;i<8;i++)
			if((posx[i]>=0&&posx[i]<8&&posy[i]>=0&&posy[i]<8))
				if((state[posx[i]][posy[i]].getPiece()==null||state[posx[i]][posy[i]].getPieceColour()!=this.getcolor()))
					possibleMoves.add(state[posx[i]][posy[i]]);
		return possibleMoves;
	}
	
	
	
	//Function to check if king is under threat
	//It checks whether there is any piece of opposite color that can attack king for a given board state
	public boolean isInDanger(Cell state[][])
    {
		
		//Checking for attack from left,right,up and down
    	for(int i=xAxisPosition+1;i<8;i++)
    	{
    		if(state[i][yAxisPosition].getPiece()==null)
    			continue;
    		else if(state[i][yAxisPosition].getPiece().getcolor()==this.getcolor())
    			break;
    		else
    		{
    			if ((state[i][yAxisPosition].getPiece() instanceof Rook) || (state[i][yAxisPosition].getPiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
    	for(int i=xAxisPosition-1;i>=0;i--)
    	{
    		if(state[i][yAxisPosition].getPiece()==null)
    			continue;
    		else if(state[i][yAxisPosition].getPiece().getcolor()==this.getcolor())
    			break;
    		else
    		{
    			if ((state[i][yAxisPosition].getPiece() instanceof Rook) || (state[i][yAxisPosition].getPiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
    	for(int i=yAxisPosition+1;i<8;i++)
    	{
    		if(state[xAxisPosition][i].getPiece()==null)
    			continue;
    		else if(state[xAxisPosition][i].getPiece().getcolor()==this.getcolor())
    			break;
    		else
    		{
    			if ((state[xAxisPosition][i].getPiece() instanceof Rook) || (state[xAxisPosition][i].getPiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
    	for(int i=yAxisPosition-1;i>=0;i--)
    	{
    		if(state[xAxisPosition][i].getPiece()==null)
    			continue;
    		else if(state[xAxisPosition][i].getPiece().getcolor()==this.getcolor())
    			break;
    		else
    		{
    			if ((state[xAxisPosition][i].getPiece() instanceof Rook) || (state[xAxisPosition][i].getPiece() instanceof Queen))
    				return true;
    			else
    				break;
    		}
    	}
    	
    	//checking for attack from diagonal direction
    	int tempx=xAxisPosition+1,tempy=yAxisPosition-1;
		while(tempx<8&&tempy>=0)
		{
			if(state[tempx][tempy].getPiece()==null)
			{
				tempx++;
				tempy--;
			}
			else if(state[tempx][tempy].getPieceColour()==this.getcolor())
				break;
			else
			{
				if (state[tempx][tempy].getPiece() instanceof Bishop || state[tempx][tempy].getPiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}
		tempx=xAxisPosition-1;
                tempy=yAxisPosition+1;
		while(tempx>=0&&tempy<8)
		{
			if(state[tempx][tempy].getPiece()==null)
			{
				tempx--;
				tempy++;
			}
			else if(state[tempx][tempy].getPieceColour()==this.getcolor())
				break;
			else
			{
				if (state[tempx][tempy].getPiece() instanceof Bishop || state[tempx][tempy].getPiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}
		tempx=xAxisPosition-1;
                tempy=yAxisPosition-1;
		while(tempx>=0&&tempy>=0)
		{
			if(state[tempx][tempy].getPiece()==null)
			{
				tempx--;
				tempy--;
			}
			else if(state[tempx][tempy].getPieceColour()==this.getcolor())
				break;
			else
			{
				if (state[tempx][tempy].getPiece() instanceof Bishop || state[tempx][tempy].getPiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}
		tempx=xAxisPosition+1;
                tempy=yAxisPosition+1;
		while(tempx<8&&tempy<8)
		{
			if(state[tempx][tempy].getPiece()==null)
			{
				tempx++;
				tempy++;
			}
			else if(state[tempx][tempy].getPieceColour()==this.getcolor())
				break;
			else
			{
				if (state[tempx][tempy].getPiece() instanceof Bishop || state[tempx][tempy].getPiece() instanceof Queen)
    				return true;
    			else
    				break;
			}
		}
		
		//Checking for attack from the Knight of opposite color
		int posx[]={xAxisPosition+1,xAxisPosition+1,xAxisPosition+2,xAxisPosition+2,xAxisPosition-1,xAxisPosition-1,xAxisPosition-2,xAxisPosition-2};
		int posy[]={yAxisPosition-2,yAxisPosition+2,yAxisPosition-1,yAxisPosition+1,yAxisPosition-2,yAxisPosition+2,yAxisPosition-1,yAxisPosition+1};
		for(int i=0;i<8;i++)
			if((posx[i]>=0&&posx[i]<8&&posy[i]>=0&&posy[i]<8))
				if(state[posx[i]][posy[i]].getPiece()!=null && state[posx[i]][posy[i]].getPieceColour()!=this.getcolor() && (state[posx[i]][posy[i]].getPiece() instanceof Knight))
				{
					return true;
				}
		
		
		//Checking for attack from the Pawn of opposite color
		int pox[]={xAxisPosition+1,xAxisPosition+1,xAxisPosition+1,xAxisPosition,xAxisPosition,xAxisPosition-1,xAxisPosition-1,xAxisPosition-1};
		int poy[]={yAxisPosition-1,yAxisPosition+1,yAxisPosition,yAxisPosition+1,yAxisPosition-1,yAxisPosition+1,yAxisPosition-1,yAxisPosition};
		{
			for(int i=0;i<8;i++)
				if((pox[i]>=0&&pox[i]<8&&poy[i]>=0&&poy[i]<8))
					if(state[pox[i]][poy[i]].getPiece()!=null && state[pox[i]][poy[i]].getPieceColour()!=this.getcolor() && (state[pox[i]][poy[i]].getPiece() instanceof King))
					{
						return true;
					}
		}
		if(getcolor()==0)
		{
			if(xAxisPosition>0&&yAxisPosition>0&&state[xAxisPosition-1][yAxisPosition-1].getPiece()!=null&&state[xAxisPosition-1][yAxisPosition-1].getPieceColour()==1&&(state[xAxisPosition-1][yAxisPosition-1].getPiece() instanceof Pawn))
				return true;
			if(xAxisPosition>0&&yAxisPosition<7&&state[xAxisPosition-1][yAxisPosition+1].getPiece()!=null&&state[xAxisPosition-1][yAxisPosition+1].getPieceColour()==1&&(state[xAxisPosition-1][yAxisPosition+1].getPiece() instanceof Pawn))
				return true;
		}
		else
		{
			if(xAxisPosition<7&&yAxisPosition>0&&state[xAxisPosition+1][yAxisPosition-1].getPiece()!=null&&state[xAxisPosition+1][yAxisPosition-1].getPieceColour()==0&&(state[xAxisPosition+1][yAxisPosition-1].getPiece() instanceof Pawn))
				return true;
			if(xAxisPosition<7&&yAxisPosition<7&&state[xAxisPosition+1][yAxisPosition+1].getPiece()!=null&&state[xAxisPosition+1][yAxisPosition+1].getPieceColour()==0&&(state[xAxisPosition+1][yAxisPosition+1].getPiece() instanceof Pawn))
				return true;
		}
    	return false;
    }
}