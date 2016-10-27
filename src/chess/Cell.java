package chess;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.*;

import pieces.*;

 


/**
 * This is the Cell Class. It is the token class of our GUI. There are total of
 * 64 cells that together makes up the Chess Board
 *
 */
public class Cell extends JPanel implements Cloneable,Serializable {

    //Member Variables
    private static final long serialVersionUID = 1L;
    private boolean isPossibleDestination;
    private JLabel content;
    private Piece piece;
    int xAxisPosition, yAxisPosition;                             //is public because this is to be accessed by all the other class
    private boolean isSelected = false;
    private boolean isCheck = false;
    
    
    //Constructors
    public Cell(int xPosition, int yPosition, Piece piece) {
        this.xAxisPosition = xPosition;
        this.yAxisPosition = yPosition;

        setLayout(new BorderLayout());

        if ((this.xAxisPosition + this.yAxisPosition) % 2 == 0) {
            setBackground(new Color(113, 198, 113));
        } else {
            setBackground(Color.white);
        }

        if (piece != null) {
            setPiece(piece);
        }
    }

    //A constructor that takes a cell as argument and returns a new cell will the same data but different reference
    public Cell(Cell cell) throws CloneNotSupportedException {
        this.xAxisPosition = cell.xAxisPosition;
        this.yAxisPosition = cell.yAxisPosition;

        setLayout(new BorderLayout());

        if ((xAxisPosition + yAxisPosition) % 2 == 0) {
            setBackground(new Color(113, 198, 113));
        } else {
            setBackground(Color.white);
        }
        if (cell.getPiece() != null) {
            setPiece(cell.getPieceCopy());
        } else {
            piece = null;
        }
    }

    public void setPiece(Piece p) //Function to inflate a cell with a piece
    {
        piece = p;
        if(!(p instanceof NullPiece)){
        ImageIcon imgIcon = new javax.swing.ImageIcon(this.getClass().getResource(p.getImagePath()));
        content = new JLabel(imgIcon);
        this.add(content);
        }
    }

    public void removePiece() //Function to remove a piece from the cell
    {
        if (piece instanceof King) {
            piece = null;
            this.remove(content);
        } else {
            piece = null;
            this.remove(content);
        }
    }

    public Piece getPiece() //Function to access piece of a particular cell
    {
        return this.piece;
    }

    public int getPieceColor() //Function to access piece's color
    {
        return this.piece.getcolor();
    }

    public ArrayList<Cell> movePiece(Cell positionArray[][], int xPosition, int yPosition) {
        return this.piece.move(positionArray, xPosition, yPosition);
    }

    public Piece getPieceCopy() {
        try {
            return this.piece.getcopy();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void select() //Function to mark a cell indicating it's selection
    {
        this.setBorder(BorderFactory.createLineBorder(Color.BLUE,3));
        this.isSelected = true;
    }

    public boolean isSelected() //Function to return if the cell is under selection
    {
        return this.isSelected;
    }

    public void deSelect() //Function to delselect the cell
    {
        this.setBorder(null);
        this.isSelected = false;
    }

    public void setPossibleDestination() //Function to highlight a cell to indicate that it is a possible valid move
    {
        this.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
        this.isPossibleDestination = true;
    }

    public void removePossibleDestination() //Remove the cell from the list of possible moves
    {
        this.setBorder(null);
        this.isPossibleDestination = false;
    }

    public boolean isPossibleDestination() //Function to check if the cell is a possible destination 
    {
        return this.isPossibleDestination;
    }

    public void setCheck() //Function to highlight the current cell as checked (For King)
    {
        this.setBackground(Color.RED);
        this.isCheck = true;
    }

    public void removeCheck() //Function to deSelect check
    {
        this.setBorder(null);
        if ((xAxisPosition + yAxisPosition) % 2 == 0) {
            setBackground(new Color(113, 198, 113));
        } else {
            setBackground(Color.white);
        }
        this.isCheck = false;
    }

    public boolean isCheck() //Function to check if the current cell is in check
    {
        return isCheck;
    }
}
