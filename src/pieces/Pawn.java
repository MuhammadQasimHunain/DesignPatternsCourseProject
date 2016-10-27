package pieces;

import java.util.ArrayList;

import chess.Cell;

/**
 * This is the Pawn Class inherited from the piece
 *
 */
public class Pawn extends Piece {

    //COnstructors
    public Pawn(String i, String p, int c) {
        setId(i);
        setImagePath(p);
        setColor(c);
    }

	//Move Function Overridden
    public ArrayList<Cell> move(Cell state[][], int xAxisPostion, int yAxisPosition) {
        //Pawn can move only one step except the first chance when it may move 2 steps
        //It can move in a diagonal fashion only for attacking a piece of opposite color
        //It cannot move backward or move forward to attack a piece

        this.possibleMoves.clear();
        if (getcolor() == Piece.WHITE_COLOR) {
            if (xAxisPostion == 0) {
                return this.possibleMoves;
            }
            if (state[xAxisPostion - 1][yAxisPosition].getPiece() == null) {
                this.possibleMoves.add(state[xAxisPostion - 1][yAxisPosition]);
                if (xAxisPostion == 6) {
                    if (state[4][yAxisPosition].getPiece() == null) {
                        this.possibleMoves.add(state[4][yAxisPosition]);
                    }
                }
            }
            if ((yAxisPosition > 0) && (state[xAxisPostion - 1][yAxisPosition - 1].getPiece() != null) && (state[xAxisPostion - 1][yAxisPosition - 1].getPieceColor() != this.getcolor())) {
                this.possibleMoves.add(state[xAxisPostion - 1][yAxisPosition - 1]);
            }
            if ((yAxisPosition < 7) && (state[xAxisPostion - 1][yAxisPosition + 1].getPiece() != null) && (state[xAxisPostion - 1][yAxisPosition + 1].getPieceColor() != this.getcolor())) {
                this.possibleMoves.add(state[xAxisPostion - 1][yAxisPosition + 1]);
            }
        } else {
            if (xAxisPostion == 8) {
                return this.possibleMoves;
            }
            if (state[xAxisPostion + 1][yAxisPosition].getPiece() == null) {
                this.possibleMoves.add(state[xAxisPostion + 1][yAxisPosition]);
                if (xAxisPostion == 1) {
                    if (state[3][yAxisPosition].getPiece() == null) {
                        this.possibleMoves.add(state[3][yAxisPosition]);
                    }
                }
            }
            if ((yAxisPosition > 0) && (state[xAxisPostion + 1][yAxisPosition - 1].getPiece() != null) && (state[xAxisPostion + 1][yAxisPosition - 1].getPieceColor() != this.getcolor())) {
                this.possibleMoves.add(state[xAxisPostion + 1][yAxisPosition - 1]);
            }
            if ((yAxisPosition < 7) && (state[xAxisPostion + 1][yAxisPosition + 1].getPiece() != null) && (state[xAxisPostion + 1][yAxisPosition + 1].getPieceColor() != this.getcolor())) {
                this.possibleMoves.add(state[xAxisPostion + 1][yAxisPosition + 1]);
            }
        }
        return this.possibleMoves;
    }
}
