package theme;

import pieces.Piece;

public interface SubjectInterfaceObserverPattern {
	
	public abstract void registerObserver(Piece p);
	public abstract void notifyAllObservers();

}
