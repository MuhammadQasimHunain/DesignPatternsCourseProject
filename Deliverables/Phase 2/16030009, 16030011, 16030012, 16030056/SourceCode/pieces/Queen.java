package pieces;

import java.util.ArrayList;

import chess.Cell;

/**
 * This is the Queen Class inherited from the abstract Piece class
 *
 */
public class Queen extends Piece {

    //Constructors
    public Queen(String i, String p, int c) {
        setId(i);
        setImagePath(p);
        setColor(c);
    }

    //Move Function Defined
    public ArrayList<Cell> move(Cell state[][], int xAxisPosition, int yAxisPosition) {
        //Queen has most number of possible moves
        //Queen can move any number of steps in all 8 direction
        //The possible moves of queen is a combination of Rook and Bishop
        this.possibleMoves.clear();

        //Checking possible moves in vertical direction
        int tempx = xAxisPosition - 1;
        possibleMovesLeft(tempx, state, yAxisPosition);

        tempx = xAxisPosition + 1;
        possibleMovesRight(tempx, state, yAxisPosition);

        //Checking possible moves in horizontal Direction
        int tempy = yAxisPosition - 1;
        possibleMovesDown(tempy, state, xAxisPosition);
        
        tempy = yAxisPosition + 1;
        possibleMovesUp(tempy, state, xAxisPosition);

        //Checking for possible moves in diagonal direction
        tempx = xAxisPosition + 1;
        tempy = yAxisPosition - 1;
        possibleMovesNorthEast(tempx, tempy, state);
        
        tempx = xAxisPosition - 1;
        tempy = yAxisPosition + 1;
        possibleMovesNorthWest(tempx, tempy, state);
        
        tempx = xAxisPosition - 1;
        tempy = yAxisPosition - 1;
        possibleMovesSouthWest(tempx, tempy, state);
        
        tempx = xAxisPosition + 1;
        tempy = yAxisPosition + 1;
        possibleMovesSouthEast(tempx, tempy, state);
        
        return this.possibleMoves;
    }

    private void possibleMovesSouthEast(int tempx, int tempy, Cell[][] state) {
        while (tempx < 8 && tempy < 8) {
            if (state[tempx][tempy].getPiece() == null) {
                this.possibleMoves.add(state[tempx][tempy]);
            } else if (state[tempx][tempy].getPieceColor() == this.getcolor()) {
                break;
            } else {
                this.possibleMoves.add(state[tempx][tempy]);
                break;
            }
            tempx++;
            tempy++;
        }
    }

    private void possibleMovesSouthWest(int tempx, int tempy, Cell[][] state) {
        while (tempx >= 0 && tempy >= 0) {
            if (state[tempx][tempy].getPiece() == null) {
                this.possibleMoves.add(state[tempx][tempy]);
            } else if (state[tempx][tempy].getPieceColor() == this.getcolor()) {
                break;
            } else {
                this.possibleMoves.add(state[tempx][tempy]);
                break;
            }
            tempx--;
            tempy--;
        }
    }

    private void possibleMovesNorthWest(int tempx, int tempy, Cell[][] state) {
        while (tempx >= 0 && tempy < 8) {
            if (state[tempx][tempy].getPiece() == null) {
                this.possibleMoves.add(state[tempx][tempy]);
            } else if (state[tempx][tempy].getPieceColor() == this.getcolor()) {
                break;
            } else {
                this.possibleMoves.add(state[tempx][tempy]);
                break;
            }
            tempx--;
            tempy++;
        }
    }

    private void possibleMovesNorthEast(int tempx, int tempy, Cell[][] state) {
        while (tempx < 8 && tempy >= 0) {
            if (state[tempx][tempy].getPiece() == null) {
                this.possibleMoves.add(state[tempx][tempy]);
            } else if (state[tempx][tempy].getPieceColor() == this.getcolor()) {
                break;
            } else {
                this.possibleMoves.add(state[tempx][tempy]);
                break;
            }
            tempx++;
            tempy--;
        }
    }

    private void possibleMovesUp(int tempy, Cell[][] state, int xAxisPosition) {
        while (tempy < 8) {
            if (state[xAxisPosition][tempy].getPiece() == null) {
                this.possibleMoves.add(state[xAxisPosition][tempy]);
            } else if (state[xAxisPosition][tempy].getPieceColor() == this.getcolor()) {
                break;
            } else {
                this.possibleMoves.add(state[xAxisPosition][tempy]);
                break;
            }
            tempy++;
        }
    }

    private void possibleMovesDown(int tempy, Cell[][] state, int xAxisPosition) {
        while (tempy >= 0) {
            if (state[xAxisPosition][tempy].getPiece() == null) {
                this.possibleMoves.add(state[xAxisPosition][tempy]);
            } else if (state[xAxisPosition][tempy].getPieceColor() == this.getcolor()) {
                break;
            } else {
                this.possibleMoves.add(state[xAxisPosition][tempy]);
                break;
            }
            tempy--;
        }
    }

    private void possibleMovesRight(int tempx, Cell[][] state, int yAxisPosition) {
        while (tempx < 8) {
            if (state[tempx][yAxisPosition].getPiece() == null) {
                this.possibleMoves.add(state[tempx][yAxisPosition]);
            } else if (state[tempx][yAxisPosition].getPieceColor() == this.getcolor()) {
                break;
            } else {
                this.possibleMoves.add(state[tempx][yAxisPosition]);
                break;
            }
            tempx++;
        }
    }

    private void possibleMovesLeft(int tempx, Cell[][] state, int yAxisPosition) {
        while (tempx >= 0) {
            if (state[tempx][yAxisPosition].getPiece() == null) {
                this.possibleMoves.add(state[tempx][yAxisPosition]);
            } else if (state[tempx][yAxisPosition].getPieceColor() == this.getcolor()) {
                break;
            } else {
                this.possibleMoves.add(state[tempx][yAxisPosition]);
                break;
            }
            tempx--;
        }
    }
}
