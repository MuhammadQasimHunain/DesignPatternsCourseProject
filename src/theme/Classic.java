package theme;

import java.awt.Color;
import java.util.ArrayList;

import pieces.Piece;

public class Classic extends Theme implements SubjectInterfaceObserverPattern {

	private Classic (){
		super();
//		this.darkCellColor = Color.black;
		this.darkCellColor = new Color((float)0.0, (float)0.0, (float)0.0, (float)0.3);
		this.lightCellColor = Color.white;
		this.pieceImageAddition = new String("Hershey-");
		this.observees = new ArrayList<Piece>();
		
	}

//	@Override
//	public Color getLightCellColor() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	public static Classic createClassicTheme() {
		return new Classic();
	}

	@Override
	public void setLightCellColor(Color lightCellColor) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public Color getDarkCellColor() {
//		// TODO Auto-generated method stub
//		return null;
//	}

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
