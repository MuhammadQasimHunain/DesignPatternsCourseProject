package pieces;

import java.util.ArrayList;

public class PieceIterator implements Iterator {

	public Piece []pieces;
	int index;
	public PieceIterator(Piece[] arr) {
		// TODO Auto-generated constructor stub
		this.index = 0;
		this.pieces = arr;
	}

	
	@Override 
	public Piece getNext() {
		// TODO Auto-generated method stub
		if (this.pieces != null && this.index< this.pieces.length) {
			return this.pieces[this.index++];
		}
		else {
			return null;
		}
	}

	public boolean hasNext() {
		// TODO Auto-generated method stub
		return this.index < this.pieces.length;
	}

}
