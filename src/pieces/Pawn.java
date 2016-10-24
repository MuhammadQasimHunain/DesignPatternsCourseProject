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
}
