package pieces;

import java.util.ArrayList;

import chess.Cell;

public class Bishop extends Piece {

    //Constructor
    public Bishop(String id, String path, int color) {
        setId(id);
        setPath(path);
        setColor(color);
    }

    //move function defined. It returns a list of all the possible destinations of a Bishop
    //The basic principle of Bishop Movement on chess board has been implemented
    public ArrayList<Cell> move(Cell state[][], int xPosition, int yPosition) {
        //Bishop can Move diagonally in all 4 direction (NW,NE,SW,SE)
        //This function defines that logic
        possibleMoves.clear();
        int tempX = xPosition + 1;
        int tempY = yPosition - 1;
        posibleMovesNorthEast(tempX, tempY, state);
        
        tempX = xPosition - 1;
        tempY = yPosition + 1;
        possibleMovesNortWest(tempX, tempY, state);
        
        tempX = xPosition - 1;
        tempY = yPosition - 1;
        possibleMovesSouthWest(tempX, tempY, state);
        
        tempX = xPosition + 1;
        tempY = yPosition + 1;
        possibleMovesSouthEast(tempX, tempY, state);
        
        return possibleMoves;
    }

    private void possibleMovesSouthEast(int tempX, int tempY, Cell[][] state) {
        while (tempX < 8 && tempY < 8) {
            if (state[tempX][tempY].getPiece() == null) {
                possibleMoves.add(state[tempX][tempY]);
            } else if (state[tempX][tempY].getPieceColor() == this.getcolor()) {
                break;
            } else {
                possibleMoves.add(state[tempX][tempY]);
                break;
            }
            tempX++;
            tempY++;
        }
    }

    private void possibleMovesSouthWest(int tempX, int tempY, Cell[][] state) {
        while (tempX >= 0 && tempY >= 0) {
            if (state[tempX][tempY].getPiece() == null) {
                possibleMoves.add(state[tempX][tempY]);
            } else if (state[tempX][tempY].getPieceColor() == this.getcolor()) {
                break;
            } else {
                possibleMoves.add(state[tempX][tempY]);
                break;
            }
            tempX--;
            tempY--;
        }
    }

    private void possibleMovesNortWest(int tempX, int tempY, Cell[][] state) {
        while (tempX >= 0 && tempY < 8) {
            if (state[tempX][tempY].getPiece() == null) {
                possibleMoves.add(state[tempX][tempY]);
            } else if (state[tempX][tempY].getPieceColor() == this.getcolor()) {
                break;
            } else {
                possibleMoves.add(state[tempX][tempY]);
                break;
            }
            tempX--;
            tempY++;
        }
    }

    private void posibleMovesNorthEast(int tempX, int tempY, Cell[][] state) {
        while (tempX < 8 && tempY >= 0) {
            if (state[tempX][tempY].getPiece() == null) {
                possibleMoves.add(state[tempX][tempY]);
            } else if (state[tempX][tempY].getPieceColor() == this.getcolor()) {
                break;
            } else {
                possibleMoves.add(state[tempX][tempY]);
                break;
            }
            tempX++;
            tempY--;
        }
    }
}
