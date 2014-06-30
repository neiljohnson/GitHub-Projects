import java.awt.Color; //import java.awt.Font;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Piece extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6566679763922131181L;
	private int pieceID;
	private int x;
	private int y;
	private int oldx;
	private int oldy;
	private int oldxcord;
	private int oldycord;
	private int xcord;
	private int ycord;
	public static int width = 45;
	public static int height = 45;
	public static int pad = 1;
	private BufferedImage i;
	private boolean isFaceUp;
	private boolean style = true;
	public static int lastNumber = 1;
	private static boolean done = false;
	private static int num = 0;

	private Color c;

	public static String[] ranks = { "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "10", "11", "12", "S", "P", "F" };
	public static String[] skills = { "Sergeant", "2nd Lieutenant",
			"1st Lieutenant", "Two-star General", "One-star General",
			"Colonel", "Captain", "Major", "Lt. Colonel", "Colonel",
			"One-star General", "Two-star General", "Three-star General",
			"Four-star General", "Five-Star General", "Spy", "Private", "Flag" };
	public static int[] values = { 120, 155, 195, 240, 290, 345, 405,
			470, 540, 615, 695, 780, 750, 137, 0 };
	public static String[] images = { "12.gif", "11.gif", "10.gif", "9.gif",
			"8.gif", "7.gif", "6.gif", "5.gif", "4.gif", "3.gif", "2.gif",
			"1.gif", "13.gif", "14.gif", "15.gif" };

	public Piece(int pieceID, int x, int y, Color c) {
		if (!setPieceID(pieceID)) {
			this.pieceID = 15;
		}

		this.x = x;
		this.y = y;
		this.c = c;
	}

	public Piece(int pieceID) {
		this(pieceID, 5, 5, Color.red);

	}

	public Piece(int pieceID, int x, int y) {
		this(pieceID, x, y, Color.red);

	}

	public Piece() {

		this(lastNumber, 5, 5, Color.red);

		if (lastNumber == 14 && num < 5) {
			lastNumber = 13;
			num++;
		}
		lastNumber++;
		if (lastNumber == 14 && (!done)) {
			lastNumber = 13;
			done = true;
		}
		if (lastNumber == 16) {
			done = false;
			num = 0;
		}
	}

	public void changeStyle() {
		style =! style;
	}

	public int getPieceID() {
		return pieceID;
	}

	public String getSkill() {
		String skill = skills[pieceID - 1];
		return skill;
	}

	public void setOldPosition(int oldx, int oldy) {
		this.oldx = oldx;
		this.oldy = oldy;
		oldxcord = (oldx - 5) / 45;
		oldycord = (oldy - 489) / 45;
	}

	public int getOx() {
		return oldx;
	}

	public int getOy() {
		return oldy;
	}

	public int getxcord() {
		return oldxcord;
	}

	public int getycord() {
		return oldycord;
	}

	public String getRank() {
		String rank = ranks[pieceID - 1];
		return rank;
	}

	public int getValue() {
		int value = values[pieceID - 1];
		return value;
	}

	public String getImage() {
		String image = images[pieceID - 1];
		return image;
	}

	public Color getColor() {
		return c;
	}

	public void setColor(Color c) {
		this.c = c;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		xcord = (x - 50) / 45;
		ycord = (y - 110) / 45;
	}

	public void setCords(int xcord, int ycord) {
		this.xcord = xcord;
		this.ycord = ycord;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public int getXcord() {
		return xcord;
	}

	public int getYcord() {
		return ycord;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public boolean setPieceID(int pieceID) {
		if (0 < pieceID && pieceID < 16) {
			this.pieceID = pieceID;
			return true;
		}
		return false;
	}

	public Piece turnPieceOver() {
		this.isFaceUp = !this.isFaceUp;
		return this;
	}

	public void flipPieceOver() {
		this.isFaceUp = !this.isFaceUp;
	}

	public boolean isFaceUp() {
		return this.isFaceUp;
	}

	public void paint(Graphics2D g) {
		if (style) {
			if (isFaceUp) {
				try {
					i = ImageIO.read(new File(getImage()));
				} catch (IOException e) {
					System.err.println("Unable to read the image");
				}
				g.drawImage(i, x, y, null);
				g.setColor(c);
				g.drawRect(x, y, width, height);
			} else {
				g.setColor(c);
				g.fillRect(x + 1, y + 1, width - 1, height - 1);
				g.setColor(Color.black);
				g.drawRect(x, y, width, height);

				g.setColor(c);
				g.fillRect(x + 1, y + 1, width - 1, height - 1);
				g.setColor(Color.black);
				g.drawRect(x, y, width, height);
			}
		} else {
			g.setColor(c);
			g.fillRect(x + 1, y + 1, width - 1, height - 1);
			g.setColor(Color.black);
			g.drawRect(x, y, width, height);

			g.setColor(c);
			g.fillRect(x + 1, y + 1, width - 1, height - 1);
			g.setColor(Color.black);
			g.drawRect(x, y, width, height);
			if (isFaceUp) {
				if (pieceID > 0 && pieceID < 13) { //
					g.setColor(Color.white);
					g.setColor(Color.white);
				} else if (pieceID == 13)
					g.setColor(Color.darkGray);
				else if (pieceID == 14)
					g.setColor(Color.black);
				else
					g.setColor(Color.lightGray);
				g.setFont(new Font("Arial", Font.BOLD, 15));
				if (pieceID > 9 && pieceID < 13) {
					g.drawString(getRank(), x + 15, y + 29);
				} else {
					g.drawString(getRank(), x + 19, y + 29);
				}
				return;
			}
		}
	}

	public void drawAllPieces() {
		Canvas c = new Canvas("Piece", 980, 500, Color.white);
		Piece p1 = new Piece(13, 5, 5, Color.red);
		p1.turnPieceOver();
		c.addPiece(p1);

		for (int i = 1; i < 13; i++) {
			Piece p = new Piece(i, i * (width + pad + pad), 5, Color.red);
			p.turnPieceOver();
			c.addPiece(p);
		}
		for (int i = 13; i < 19; i++) {
			Piece p = new Piece(15, i * (width + pad + pad), 5, Color.green);
			p.turnPieceOver();
			c.addPiece(p);
		}
		for (int i = 19; i < 21; i++) {
			Piece p = new Piece(14, i * (width + pad), 5, Color.cyan);
			p.turnPieceOver();
			c.addPiece(p);
		}
		for (int j = 1; j < 9; j++) {
			for (int i = 1; i < 10; i++) {
				Piece p = new Piece(i, i * (width + pad + pad), j
						* (height + pad + pad), Color.blue);
				p.turnPieceOver();
				c.addPiece(p);
			}
		}
	}

	public static void main(String[] args) {
		Piece s = new Piece();
		Canvas c = new Canvas("Piece", 980, 500, Color.white);
		c.addPiece(s);
		s.turnPieceOver();
		s.drawAllPieces();
		new Sound("Bomb.wav").start();
	}
}
