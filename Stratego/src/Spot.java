import java.util.Vector;


public class Spot {

	
	private int x;
	private int y;
	private int pieceID;
	private Piece a = new Piece();
	
	public Spot(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Spot (int pieceID, int x, int y){
		this.pieceID = pieceID;
		this.x = x;
		this.y = y;
	}
	
	public int getID(){
		return pieceID;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setID(int pieceID){
		this.pieceID = pieceID;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setPos(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public static void main(String[] args ){
		Vector<Spot> a = new Vector<Spot>();
		Spot spot = new Spot(2,3);
		a.add(spot);
		System.out.println(a.get(0));
	}
}
