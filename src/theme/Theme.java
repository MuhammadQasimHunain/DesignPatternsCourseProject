package theme;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observer;

import pieces.Piece;
public abstract class Theme implements SubjectInterfaceObserverPattern{

	Color lightCellColor;
	Color darkCellColor;
	String pieceImageAddition;
	
	protected ArrayList<Piece> observees;

	public Color getLightCellColor() {
		return lightCellColor;
	}
	public abstract void setLightCellColor(Color lightCellColor) ;

	public Color getDarkCellColor() {
		return darkCellColor;
	}
	public abstract void setDarkCellColor(Color darkCellColor) ;


	public String getPieceImageAddition() {
		return pieceImageAddition;
	}

	
}
