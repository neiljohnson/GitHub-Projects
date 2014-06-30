import java.awt.Point;
import java.util.Vector;


public class Grid {
	private Vector<Piece> player;
	private Vector<Piece> comp;
	private Vector<Point> a;
	private Vector<Point> b;
	
	
	public Grid(){
		player = new Vector<Piece>(21);
		comp = new Vector<Piece>(21);
		a = new Vector<Point>(21);
		b = new Vector<Point>(21);
	}
	
	public void addP(Piece p){
		player.add(p);
		a.add(new Point(p.getX(),p.getY()));
	}
	
	public void addC(Piece c){
		comp.add(c);
	}
}
