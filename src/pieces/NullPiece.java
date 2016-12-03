package pieces;

import java.util.ArrayList;

import chess.Cell;

public class NullPiece extends Piece { //Null object design pattern (one of the types of behavioural design pattern.)

	@Override
	public ArrayList<Cell> calculateAllPossibleMoves(Cell[][] pos, int x, int y) { 
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void playSoundForKill() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
