package theme;

import pieces.Piece;

/* This is an interface 
 * for implementing 
 * observer pattern 
 */
public interface SubjectInterfaceObserverPattern {
	
	public abstract void registerObserver(Piece piece);
	public abstract void notifyAllObservers();
}
