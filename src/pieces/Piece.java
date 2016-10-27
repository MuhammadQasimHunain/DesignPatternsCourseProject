package pieces;

import java.io.Serializable;
import java.util.ArrayList;

import chess.Cell;

/**
 * This is the Piece Class. It is an abstract class from which all the actual
 * pieces are inherited. It defines all the function common to all the pieces
 * The move() function an abstract function that has to be overridden in all the
 * inherited class It implements Cloneable interface as a copy of the piece is
 * required very often
 */

public abstract class Piece implements Cloneable, Serializable {

	public static final int WHITE_COLOR = 0;
	public static final int BLACK_COLOR = 1;
	
	//Member Variables
    private int color;
    private String id = null;
    private String imagePath;
    protected ArrayList<Cell> possibleMoves = new ArrayList<Cell>();  //Protected (access from child classes)

    public abstract ArrayList<Cell> move(Cell pos[][], int x, int y);  //Abstract Function. Must be overridden

    //Id Setter
    public void setId(String id) {
        this.id = id;
    }

    //Path Setter
    public void setImagePath(String path) {
        this.imagePath = path;
    }

    //Color Setter
    public void setColor(int c) {
        this.color = c;
    }

    //Path getter
    public String getImagePath() {
        return imagePath;
    }

    //Id getter
    public String getId() {
        return id;
    }

    //Color Getter
    public int getcolor() {
        return this.color;
    }

    //Function to return the a "shallow" copy of the object. The copy has exact same variable value but different reference
    public Piece getcopy() throws CloneNotSupportedException {
        return (Piece) this.clone();
    }
}
