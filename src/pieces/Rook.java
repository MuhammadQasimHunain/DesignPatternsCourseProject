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
    public ArrayList<Cell> calculateAllPossibleMoves(Cell state[][], int xAxisPosition, int yAxisPosition) {
        //Rook can move only horizontally or vertically
        this.possibleMoves.clear();
        int tempx = xAxisPosition - 1;
        possibleMovesLeftSide(tempx, state, yAxisPosition);
        
        tempx = xAxisPosition + 1;
        possibleMovesRightSide(tempx, state, yAxisPosition);
        
        int tempy = yAxisPosition - 1;
        possibleMovesDown(tempy, state, xAxisPosition);
        
        tempy = yAxisPosition + 1;
        possibleMovesUpSide(tempy, state, xAxisPosition);
        
        return this.possibleMoves;
    }

    private void possibleMovesUpSide(int tempy, Cell[][] state, int xAxisPosition) {
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

    private void possibleMovesRightSide(int tempx, Cell[][] state, int yAxisPosition) {
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

    private void possibleMovesLeftSide(int tempx, Cell[][] state, int yAxisPosition) {
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
    public void playSoundForKill() {
    	try {
            // Open an audio input stream.           
    		String path = new File("src/pieces/ComputerError.wav").getAbsolutePath();
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
