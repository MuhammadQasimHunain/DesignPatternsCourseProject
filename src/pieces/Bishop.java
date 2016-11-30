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

public class Bishop extends Piece {

    //Constructor
    public Bishop(String id, String path, int color) {
        setId(id);
        setImagePath(path);
        setColor(color);
    }

    //move function defined. It returns a list of all the possible destinations of a Bishop
    //The basic principle of Bishop Movement on chess board has been implemented
    public ArrayList<Cell> calculateAllPossibleMoves(Cell state[][], int xPosition, int yPosition) {
        //Bishop can Move diagonally in all 4 direction (NW,NE,SW,SE)
        //This function defines that logic
        this.possibleMoves.clear();
        int tempX = xPosition + 1;
        int tempY = yPosition - 1;
        // HAIGA
        // The below function should only return the possible moves
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
        
        return this.possibleMoves;
    }

    private void possibleMovesSouthEast(int tempX, int tempY, Cell[][] state) {
        while (tempX < 8 && tempY < 8) {
            if (state[tempX][tempY].getPiece() == null) {
                this.possibleMoves.add(state[tempX][tempY]);
            } else if (state[tempX][tempY].getPieceColor() == this.getcolor()) {
                break;
            } else {
                this.possibleMoves.add(state[tempX][tempY]);
                break;
            }
            tempX++;
            tempY++;
        }
    }

    private void possibleMovesSouthWest(int tempX, int tempY, Cell[][] state) {
        while (tempX >= 0 && tempY >= 0) {
            if (state[tempX][tempY].getPiece() == null) {
                this.possibleMoves.add(state[tempX][tempY]);
            } else if (state[tempX][tempY].getPieceColor() == this.getcolor()) {
                break;
            } else {
                this.possibleMoves.add(state[tempX][tempY]);
                break;
            }
            tempX--;
            tempY--;
        }
    }

    private void possibleMovesNortWest(int tempX, int tempY, Cell[][] state) {
        while (tempX >= 0 && tempY < 8) {
            if (state[tempX][tempY].getPiece() == null) {
                this.possibleMoves.add(state[tempX][tempY]);
            } else if (state[tempX][tempY].getPieceColor() == this.getcolor()) {
                break;
            } else {
                this.possibleMoves.add(state[tempX][tempY]);
                break;
            }
            tempX--;
            tempY++;
        }
    }

    private void posibleMovesNorthEast(int tempX, int tempY, Cell[][] state) {
        while (tempX < 8 && tempY >= 0) {
            if (state[tempX][tempY].getPiece() == null) {
                this.possibleMoves.add(state[tempX][tempY]);
            } else if (state[tempX][tempY].getPieceColor() == this.getcolor()) {
                break;
            } else {
                this.possibleMoves.add(state[tempX][tempY]);
                break;
            }
            tempX++;
            tempY--;
        }
    }
    public void playSoundForKill() { 
    	try {
            // Open an audio input stream.           
    		String path = new File("src/pieces/9mmGunshot.wav").getAbsolutePath();
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
