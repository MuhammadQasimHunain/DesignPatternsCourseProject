package theme;

import java.awt.Color;
import java.util.ArrayList;

import pieces.Piece;

public class BlueberryPie extends Theme implements SubjectInterfaceObserverPattern {

	private BlueberryPie() {
		// TODO Auto-generated constructor stub
		this.darkCellColor = Color.BLUE;
		this.lightCellColor = Color.WHITE;
		this.pieceImageAddition = new String("Blueberry-");
		this.observees = new ArrayList<Piece>();
	}
	
	public static BlueberryPie createBlueberryPieTheme() {
		return new BlueberryPie();
	}

	@Override
	public Color getLightCellColor() {
		// TODO Auto-generated method stub
		return this.lightCellColor;
	}

	@Override
	public void setLightCellColor(Color lightCellColor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getDarkCellColor() {
		// TODO Auto-generated method stub
		return this.darkCellColor;
	}

	@Override
	public void setDarkCellColor(Color darkCellColor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerObserver(Piece p) {
		// TODO Auto-generated method stub
		this.observees.add(p);
	}

	@Override
	public void notifyAllObservers() {
		// TODO Auto-generated method stub
		for (Piece piece : observees) {
			piece.update();
		}
		
	}


}
