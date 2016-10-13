package pieces;

import java.util.ArrayList;

import chess.Cell;

/**
 * This is the Rook class inherited from abstract Piece class
 *
 */
public class Rook extends Piece {

    //Constructor
    public Rook(String id, String path, int color) {
        setId(id);
        setImagePath(path);
        setColor(color);
    }

    //Move function defined
    public ArrayList<Cell> move(Cell state[][], int xAxisPosition, int yAxisPosition) {
        //Rook can move only horizontally or vertically
        possibleMoves.clear();
        int tempx = xAxisPosition - 1;
        possibleMovesLeftSide(tempx, state, yAxisPosition);
        
        tempx = xAxisPosition + 1;
        possibleMovesRightSide(tempx, state, yAxisPosition);
        
        int tempy = yAxisPosition - 1;
        possibleMovesDown(tempy, state, xAxisPosition);
        
        tempy = yAxisPosition + 1;
        possibleMovesUpSide(tempy, state, xAxisPosition);
        
        return possibleMoves;
    }

    private void possibleMovesUpSide(int tempy, Cell[][] state, int xAxisPosition) {
        while (tempy < 8) {
            if (state[xAxisPosition][tempy].getPiece() == null) {
                possibleMoves.add(state[xAxisPosition][tempy]);
            } else if (state[xAxisPosition][tempy].getPieceColor() == this.getcolor()) {
                break;
            } else {
                possibleMoves.add(state[xAxisPosition][tempy]);
                break;
            }
            tempy++;
        }
    }

    private void possibleMovesDown(int tempy, Cell[][] state, int xAxisPosition) {
        while (tempy >= 0) {
            if (state[xAxisPosition][tempy].getPiece() == null) {
                possibleMoves.add(state[xAxisPosition][tempy]);
            } else if (state[xAxisPosition][tempy].getPieceColor() == this.getcolor()) {
                break;
            } else {
                possibleMoves.add(state[xAxisPosition][tempy]);
                break;
            }
            tempy--;
        }
    }

    private void possibleMovesRightSide(int tempx, Cell[][] state, int yAxisPosition) {
        while (tempx < 8) {
            if (state[tempx][yAxisPosition].getPiece() == null) {
                possibleMoves.add(state[tempx][yAxisPosition]);
            } else if (state[tempx][yAxisPosition].getPieceColor() == this.getcolor()) {
                break;
            } else {
                possibleMoves.add(state[tempx][yAxisPosition]);
                break;
            }
            tempx++;
        }
    }

    private void possibleMovesLeftSide(int tempx, Cell[][] state, int yAxisPosition) {
        while (tempx >= 0) {
            if (state[tempx][yAxisPosition].getPiece() == null) {
                possibleMoves.add(state[tempx][yAxisPosition]);
            } else if (state[tempx][yAxisPosition].getPieceColor() == this.getcolor()) {
                break;
            } else {
                possibleMoves.add(state[tempx][yAxisPosition]);
                break;
            }
            tempx--;
        }
    }
}
