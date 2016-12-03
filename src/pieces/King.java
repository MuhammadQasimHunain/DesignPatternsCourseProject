package pieces;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import chess.Cell;

public class King extends Piece {

	public static final int WHITE_KING_X_AXIS_POSITION = 7;
	public static final int WHITE_KING_Y_AXIS_POSTION = 3;
	public static final int BLACK_KING_X_AXIS_POSITION = 0;
	public static final int BLACK_KING_Y_AXIS_POSITION = 3;
    private int xAxisPosition, yAxisPosition; //Extra variables for King class to keep a track of king's position

    //King Constructor
    public King(String id, String position, int color, int xPosition, int yPosition) {
        setXAxisPosition(xPosition);
        setYAxisPosition(yPosition);
        setId(id);
        setImagePath(position);
        setColor(color);
    }

    //general value access functions
    public void setXAxisPosition(int x) {
        this.xAxisPosition = x;
    }

    public void setYAxisPosition(int y) {
        this.yAxisPosition = y;
    }

    public int getXAxisPosition() {
        return xAxisPosition;
    }

    public int getYAxisPosition() {
        return yAxisPosition;
    }

    //Move Function for King Overridden from Pieces
    @Override
    public ArrayList<Cell> calculateAllPossibleMoves(Cell state[][], int xPosition, int yPosition) {
        //King can move only one step. So all the adjacent 8 cells have been considered.
        this.possibleMoves.clear();
        int posX[] = {xPosition, xPosition, xPosition + 1, xPosition + 1, xPosition + 1, xPosition - 1, xPosition - 1, xPosition - 1};
        int posY[] = {yPosition - 1, yPosition + 1, yPosition - 1, yPosition, yPosition + 1, yPosition - 1, yPosition, yPosition + 1};
        for (int i = 0; i < 8; i++) {
            if ((posX[i] >= 0 && posX[i] < 8 && posY[i] >= 0 && posY[i] < 8)) {
                if ((state[posX[i]][posY[i]].getPiece() == null
                        || state[posX[i]][posY[i]].getPieceColor() != this.getcolor())) {
                    this.possibleMoves.add(state[posX[i]][posY[i]]);
                }
            }
        }
        return this.possibleMoves;
    }

    //Function to check if king is under threat
    //It checks whether there is any piece of opposite color that can attack king for a given board state
    public boolean isInDanger(Cell state[][]) {

        if (isRookAttacking(state)
                || isBishopAttacking(state)
                || isKnightAttacking(state)
                || isPawnAttacking(state)) {
            return true;
        }
        return false;
    }

    //Checking for attack from the Pawn of opposite color
    private boolean isPawnAttacking(Cell[][] state) {
        int pox[] = {xAxisPosition + 1, xAxisPosition + 1, xAxisPosition + 1, xAxisPosition, xAxisPosition, xAxisPosition - 1, xAxisPosition - 1, xAxisPosition - 1};
        int poy[] = {yAxisPosition - 1, yAxisPosition + 1, yAxisPosition, yAxisPosition + 1, yAxisPosition - 1, yAxisPosition + 1, yAxisPosition - 1, yAxisPosition};
        {
            for (int i = 0; i < 8; i++) {
                if (pox[i] >= 0 && pox[i] < 8 && poy[i] >= 0 && poy[i] < 8) {
                    if (state[pox[i]][poy[i]].getPiece() != null
                            && state[pox[i]][poy[i]].getPieceColor() != this.getcolor()
                            && (state[pox[i]][poy[i]].getPiece() instanceof King)) {
                        return true;
                    }
                }
            }
        }
        if (getcolor() == Piece.WHITE_COLOR) {
            if (xAxisPosition > 0 && yAxisPosition > 0
                    && state[xAxisPosition - 1][yAxisPosition - 1].getPiece() != null
                    && state[xAxisPosition - 1][yAxisPosition - 1].getPieceColor() == Piece.BLACK_COLOR
                    && (state[xAxisPosition - 1][yAxisPosition - 1].getPiece() instanceof Pawn)) {
                return true;
            }
            if (xAxisPosition > 0 && yAxisPosition < 7
                    && state[xAxisPosition - 1][yAxisPosition + 1].getPiece() != null
                    && state[xAxisPosition - 1][yAxisPosition + 1].getPieceColor() == Piece.BLACK_COLOR
                    && (state[xAxisPosition - 1][yAxisPosition + 1].getPiece() instanceof Pawn)) {
                return true;
            }
        } else {
            if (xAxisPosition < 7 && yAxisPosition > 0
                    && state[xAxisPosition + 1][yAxisPosition - 1].getPiece() != null
                    && state[xAxisPosition + 1][yAxisPosition - 1].getPieceColor() == Piece.WHITE_COLOR
                    && (state[xAxisPosition + 1][yAxisPosition - 1].getPiece() instanceof Pawn)) {
                return true;
            }
            if (xAxisPosition < 7 && yAxisPosition < 7
                    && state[xAxisPosition + 1][yAxisPosition + 1].getPiece() != null
                    && state[xAxisPosition + 1][yAxisPosition + 1].getPieceColor() == Piece.WHITE_COLOR
                    && (state[xAxisPosition + 1][yAxisPosition + 1].getPiece() instanceof Pawn)) {
                return true;
            }
        }
        return false;
    }

    //Checking for attack from the Knight of opposite color
    private boolean isKnightAttacking(Cell[][] state) {
        int posx[] = {xAxisPosition + 1, xAxisPosition + 1, xAxisPosition + 2, xAxisPosition + 2, xAxisPosition - 1, xAxisPosition - 1, xAxisPosition - 2, xAxisPosition - 2};
        int posy[] = {yAxisPosition - 2, yAxisPosition + 2, yAxisPosition - 1, yAxisPosition + 1, yAxisPosition - 2, yAxisPosition + 2, yAxisPosition - 1, yAxisPosition + 1};
        for (int i = 0; i < 8; i++) {
            if (posx[i] >= 0 && posx[i] < 8 && posy[i] >= 0 && posy[i] < 8) {
                if (state[posx[i]][posy[i]].getPiece() != null
                        && state[posx[i]][posy[i]].getPieceColor() != this.getcolor()
                        && (state[posx[i]][posy[i]].getPiece() instanceof Knight)) {
                    return true;
                }
            }
        }
        return false;
    }

    //checking for attack from diagonal direction
    private boolean isBishopAttacking(Cell[][] state) {
        int tempx = xAxisPosition + 1;
        int tempy = yAxisPosition - 1;
        while (tempx < 8 && tempy >= 0) {
            if (state[tempx][tempy].getPiece() == null) {
                tempx++;
                tempy--;
            } else if (state[tempx][tempy].getPieceColor() == this.getcolor()) {
                break;
            } else if (state[tempx][tempy].getPiece() instanceof Bishop
                    || state[tempx][tempy].getPiece() instanceof Queen) {
                return true;
            } else {
                break;
            }
        }
        tempx = xAxisPosition - 1;
        tempy = yAxisPosition + 1;
        while (tempx >= 0 && tempy < 8) {
            if (state[tempx][tempy].getPiece() == null) {
                tempx--;
                tempy++;
            } else if (state[tempx][tempy].getPieceColor() == this.getcolor()) {
                break;
            } else if (state[tempx][tempy].getPiece() instanceof Bishop
                    || state[tempx][tempy].getPiece() instanceof Queen) {
                return true;
            } else {
                break;
            }
        }
        tempx = xAxisPosition - 1;
        tempy = yAxisPosition - 1;
        while (tempx >= 0 && tempy >= 0) {
            if (state[tempx][tempy].getPiece() == null) {
                tempx--;
                tempy--;
            } else if (state[tempx][tempy].getPieceColor() == this.getcolor()) {
                break;
            } else if (state[tempx][tempy].getPiece() instanceof Bishop
                    || state[tempx][tempy].getPiece() instanceof Queen) {
                return true;
            } else {
                break;
            }
        }
        tempx = xAxisPosition + 1;
        tempy = yAxisPosition + 1;
        while (tempx < 8 && tempy < 8) {
            if (state[tempx][tempy].getPiece() == null) {
                tempx++;
                tempy++;
            } else if (state[tempx][tempy].getPieceColor() == this.getcolor()) {
                break;
            } else if (state[tempx][tempy].getPiece() instanceof Bishop
                    || state[tempx][tempy].getPiece() instanceof Queen) {
                return true;
            } else {
                break;
            }
        }
        return false;
    }

    //Checking for attack from left,right,up and down
    private boolean isRookAttacking(Cell[][] state) {

    	if(this.isRookAttackingFromRight(state) ){
    		return true;
    	}
    	
    	if(this.isRookAttackingFromLeft(state)) {
    		return true;
    	}
    	
    	if(this.isRookAttackingFromAbove(state)) {
    		return true;
    	}
    	
    	if(this.isRookAttackingFromBelow(state)) {
    		return true;
    	}
    	
        return false;
    }
    
    // Helper Fucntions
    private boolean isRookAttackingFromRight(Cell[][] state) {

        for (int i = xAxisPosition + 1; i < 8; i++) {
            if (state[i][yAxisPosition].getPiece() == null) {
            } else if (state[i][yAxisPosition].getPiece().getcolor() == this.getcolor()) {
                break;
            } else if ((state[i][yAxisPosition].getPiece() instanceof Rook)
                    || (state[i][yAxisPosition].getPiece() instanceof Queen)) {
                return true;
            } else {
                break;
            }
        }

    	return false;
    }
    
    private boolean isRookAttackingFromLeft(Cell[][] state) {

    	for (int i = xAxisPosition - 1; i >= 0; i--) {
            if (state[i][yAxisPosition].getPiece() == null) {
            } else if (state[i][yAxisPosition].getPiece().getcolor() == this.getcolor()) {
                break;
            } else if ((state[i][yAxisPosition].getPiece() instanceof Rook)
                    || (state[i][yAxisPosition].getPiece() instanceof Queen)) {
                return true;
            } else {
                break;
            }
        }
    	return false;
    }
    

    private boolean isRookAttackingFromAbove(Cell[][] state) {

    	for (int i = yAxisPosition - 1; i >= 0; i--) {
            if (state[xAxisPosition][i].getPiece() == null) {
            } else if (state[xAxisPosition][i].getPiece().getcolor() == this.getcolor()) {
                break;
            } else if ((state[xAxisPosition][i].getPiece() instanceof Rook) || (state[xAxisPosition][i].getPiece() instanceof Queen)) {
                return true;
            } else {
                break;
            }
        }
    	return false;
    }


    private boolean isRookAttackingFromBelow(Cell[][] state) {

    	for (int i = yAxisPosition + 1; i < 8; i++) {
            if (state[xAxisPosition][i].getPiece() == null) {
            } else if (state[xAxisPosition][i].getPiece().getcolor() == this.getcolor()) {
                break;
            } else if ((state[xAxisPosition][i].getPiece() instanceof Rook) || (state[xAxisPosition][i].getPiece() instanceof Queen)) {
                return true;
            } else {
                break;
            }
        }
    	return false;
    }

    public void playSoundForKill() {
    	try {
            // Open an audio input stream.           
    		String path = new File("src/pieces/AlertSound.wav").getAbsolutePath();
    		File soundFile = new File(path); //you could also get the sound file with an URL
             AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
         } catch (UnsupportedAudioFileException e) {
             e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         } catch (LineUnavailableException e) {
            e.printStackTrace();
         }catch (Exception e) {
            e.printStackTrace();
         }

    	
    	
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
        this.setImagePath(this.imagePath);
		
	}
    
    
}
