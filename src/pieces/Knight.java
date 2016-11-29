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
 * This is the Knight Class inherited from the Piece abstract class
 *
 *
 */
public class Knight extends Piece {

    //Constructor
    public Knight(String i, String p, int c) {
        setId(i);
        setImagePath(p);
        setColor(c);
    }

    //Move Function overridden
    //There are at max 8 possible moves for a knight at any point of time.
    //Knight moves only 2(1/2) steps
    public ArrayList<Cell> calculateAllPossibleMoves(Cell state[][], int x, int y) {
        possibleMoves.clear();
        int posx[] = {x + 1, x + 1, x + 2, x + 2, x - 1, x - 1, x - 2, x - 2};
        int posy[] = {y - 2, y + 2, y - 1, y + 1, y - 2, y + 2, y - 1, y + 1};
        for (int i = 0; i < 8; i++) {
            if ((posx[i] >= 0 && posx[i] < 8 && posy[i] >= 0 && posy[i] < 8)) {
                if ((state[posx[i]][posy[i]].getPiece() == null 
                        || state[posx[i]][posy[i]].getPieceColor() != this.getcolor())) {
                    possibleMoves.add(state[posx[i]][posy[i]]);
                }
            }
        }
        return possibleMoves;
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
}
