import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D; //import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener; //import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.util.Random;

public class PlayingArea extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Graphics2D Graphics2D = null;
	private int p1Inc;
	private int p2Inc;
	private int p1x;
	private int p1y;
	private int p2x;
	private int p2y;
	private int x;
	private int y;
	private int movex;
	private int movey;
	private int xcord;
	private int ycord;
	private int xpos;
	private int ypos;
	private int oldxpos;
	private int oldypos;
	private int oldxcord;
	private int oldycord;
	private int readyInc;
	private int formInc;
	private int numTurns;
	private static int colorInc = 0;
	private int score;
	private boolean done;
	private boolean isP1Turn;
	private boolean ready;
	private boolean salpakan;
	private boolean p1HasWon;
	private boolean p2HasWon;
	private boolean inP1;
	private boolean pieceWon;
	private boolean moved;
	private Piece[][] grid;
	private Piece[][] gridP1;
	private Piece[][] gridP2;

	// private Vector<Piece> p1 = new Vector<Piece>();
	// private Vector<Piece> p2 = new Vector<Piece>();

	public PlayingArea() {
		super();
		moved = false;
		score = 0;
		numTurns = 0;
		p1x = 5;
		p1y = 490;
		p2x = 5;
		p2y = 5;
		p1Inc = 0;
		p2Inc = 0;
		readyInc = 0;
		formInc = 0;
		this
				.setSize(new Dimension(Piece.width * 11 + 5,
						Piece.height * 13 + 25));
		MyMouseListener l = new MyMouseListener();
		this.addMouseListener(l);
		this.addMouseMotionListener(l);
		grid = new Piece[9][8];
		gridP1 = new Piece[11][2];
		gridP2 = new Piece[11][2];
		isP1Turn = true;
		ready = false;
		salpakan = false;
		p1HasWon = false;
		p2HasWon = false;
		done = false;
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, Piece.width * 12 + 5, Piece.height * 14 - 44);
		g.setColor(Color.lightGray);
		g.fillRect(50, 110, Piece.width * 9, Piece.height * 8);
		g.setColor(Color.white);
		g.fillRect(5, 5, Piece.width * 11, Piece.height * 2);
		g.fillRect(5, 490, Piece.width * 11, Piece.height * 2);
		g.setColor(Color.black);
		g.drawRect(5, 5, Piece.width * 11, Piece.height * 2);
		g.drawRect(5, 490, Piece.width * 11, Piece.height * 2);
		for (int i = 1; i < 11; i++) {
			g.drawLine(Piece.width * i + 4, 5, Piece.width * i + 4, 580);
		}
		for (int i = 1; i < 8; i++) {
			g.drawLine(49, Piece.width * i + 109, 455, Piece.width * i + 109);
		}

		g.drawLine(5, 50, Piece.width * 11 + 5, 50);
		g.drawLine(5, 535, Piece.width * 11 + 5, 535);

		g.setColor(Color.white);
		g.drawLine(49, 289, 455, 289);

		g.setColor(Color.blue);
		g.drawLine(49, 244, 455, 244);

		g.setColor(Color.red);
		g.drawLine(49, 334, 455, 334);

		g.setColor(Color.gray);
		if (moved) {
			if (isP1Turn) {
				g.fillRect(oldxpos + 1, oldypos + 1, 44, 44);
				// g.fillRect(xpos, ypos, 44, 44);
			} else {
				g.fillRect(xcord, ycord, 44, 44);
			}
		}
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 2; j++) {
				if (gridP2[i][j] != null) {
					gridP2[i][j].setColor(Color.blue);
					gridP2[i][j].paint((Graphics2D) g);
				}
			}
		}
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 2; j++) {
				if (gridP1[i][j] != null) {
					if (colorInc == 0)
						gridP1[i][j].setColor(Color.red);
					else if (colorInc == 1)
						gridP1[i][j].setColor(Color.yellow);
					else if (colorInc == 2)
						gridP1[i][j].setColor(Color.green);
					else if (colorInc == 3)
						gridP1[i][j].setColor(Color.magenta);
					else if (colorInc == 4)
						gridP1[i][j].setColor(Color.cyan);
					else if (colorInc == 5)
						gridP1[i][j].setColor(Color.orange);
					else if (colorInc == 6) {
						gridP1[i][j].setColor(Color.pink);
					} else if (colorInc == 7) {
						gridP1[i][j].setColor(Color.black);
					}
					gridP1[i][j].paint((Graphics2D) g);
				}
			}
		}
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 9; i++) {
				if (grid[i][j] != null) {
					if (grid[i][j].getColor() != Color.blue) {
						if (colorInc == 0)
							grid[i][j].setColor(Color.red);
						else if (colorInc == 1)
							grid[i][j].setColor(Color.yellow);
						else if (colorInc == 2)
							grid[i][j].setColor(Color.green);
						else if (colorInc == 3)
							grid[i][j].setColor(Color.magenta);
						else if (colorInc == 4)
							grid[i][j].setColor(Color.cyan);
						else if (colorInc == 5)
							grid[i][j].setColor(Color.orange);
						else if (colorInc == 6) {
							grid[i][j].setColor(Color.pink);
						} else if (colorInc == 7) {
							grid[i][j].setColor(Color.black);
						}
					}
					grid[i][j].paint((Graphics2D) g);
				}
			}
		}
		if (moved) {
			g.setColor(Color.red);
			g.drawRect(movex, movey, 44, 44);
			g.drawRect(movex + 1, movey + 1, 42, 42);
		} 
		if (ready && grid[oldxcord][oldycord] != null)
			grid[oldxcord][oldycord].paint((Graphics2D) g);
		else if (!inP1) {
			if (grid[oldxcord][oldycord] != null) {
				grid[oldxcord][oldycord].paint((Graphics2D) g);
			}
		} else {
			if (gridP1[oldxcord][oldycord] != null && !ready) {
				gridP1[oldxcord][oldycord].paint((Graphics2D) g);
			}
		}
		// /*
		// * if (grid[oldxcord][oldycord] != null &&
		// * grid[oldxcord][oldycord].getColor() != Color.blue)
		// * grid[oldxcord][oldycord].paint((Graphics2D) g); } else if
		// * (gridP1[oldxcord][oldycord] != null && !inP1) {
		// * gridP1[oldxcord][oldycord].paint((Graphics2D) g);
		// */

		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.setColor(Color.black);
		g.drawString("Number of Turns: ", 0, 605);
		g.drawString("Piece Value Difference: ", 270, 605);
		g.setColor(Color.red);
		g.drawString(" " + numTurns, 140, 605);
		g.drawString("" + score, 460, 605);
	}

	public void changeColor() {
		++colorInc;
		if (colorInc > 7) {
			colorInc = 0;
		}
		repaint();
	}

	public void addPieceToSideArea(Piece p) {
		p.setPosition(p1x, p1y);
		p.setOldPosition(p1x, p1y);
		xcord = (p1x - 4) / 45;
		ycord = (p1y - 489) / 45;
		gridP1[xcord][ycord] = p;
		p1x += 45;
		p1Inc++;
		if (p1Inc % 11 == 0) {
			p1x = 5;
			p1y += 45;
		}
		if (p1Inc == 21) {
			p1Inc = 0;
			p1x = 5;
			p1y = 490;
		}
		repaint();
	}

	public void addPieceToSideAreaP2(Piece p) {
		p.setPosition(p2x, p2y);
		p.setOldPosition(p2x, p2y);
		xcord = (p2x - 4) / 45;
		ycord = (p2y - 4) / 45;
		gridP2[xcord][ycord] = p;
		p2x += 45;
		p2Inc++;
		if (p2Inc % 11 == 0) {
			p2x = 5;
			p2y += 45;
		}
		if (p2Inc == 21) {
			p2Inc = 0;
			p2x = 5;
			p2y = 5;
		}
		repaint();
	}

	public void setOK(boolean ok) {
		this.p2HasWon = ok;
	}

	public void setDone() {
		done = false;
	}

	public void reset() {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 2; j++) {

				gridP1[i][j] = null;
			}
		}
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 2; j++) {

				gridP2[i][j] = null;
			}
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 8; j++) {
				grid[i][j] = null;
			}
		}
		score = 0;
		readyInc = 0;
		formInc = 0;
		resetPos();
		isP1Turn = true;
		ready = false;
		salpakan = false;
		p1HasWon = false;
		p2HasWon = false;
		done = false;
		numTurns = 0;
		moved = false;
		SalpakanGui.changeTop("Start setting up your pieces");
		SalpakanGui.changeTop2("Hit ready when done");
	}

	public void resetPos() {
		p1x = 5;
		p1y = 490;
		p2x = 5;
		p2y = 5;
		p1Inc = 0;
		p2Inc = 0;
	}

	public void turnAllUp() {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 2; j++) {
				if (gridP2[i][j] != null && gridP2[i][j].isFaceUp() == false) {
					gridP2[i][j].flipPieceOver();
				}
			}
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 8; j++) {
				if (grid[i][j] != null && grid[i][j].isFaceUp() == false) {
					grid[i][j].flipPieceOver();
				}
			}
		}
		repaint();
	}

	public void flipOver() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 8; j++) {
				if (grid[i][j] != null && grid[i][j].isFaceUp() == false) {
					grid[i][j].flipPieceOver();
				}
			}
		}
		repaint();
	}

	public void turnOver() {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 2; j++) {
				if (gridP2[i][j] != null) {
					gridP2[i][j].flipPieceOver();
				}
			}
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 8; j++) {
				if (grid[i][j] != null && grid[i][j].getColor() == Color.blue) {
					grid[i][j].flipPieceOver();
				}
			}
		}
		repaint();
	}

	public int getX(int x) {
		return (x * 45) + 49;
	}

	public int getY(int y) {
		return (y * 45) + 109;
	}

	public void updateCords(int x, int y) {
		// System.out.println("Cords");
		xcord = (x - 50) / 45;
		ycord = (y - 110) / 45;
		xpos = (xcord * 45) + 49;
		ypos = (ycord * 45) + 109;
	}

	public void updateOldCords(int x, int y) {
		// System.out.println("OldCords");
		oldxcord = (x - 50) / 45;
		oldycord = (y - 110) / 45;
		oldxpos = (oldxcord * 45) + 49;
		oldypos = (oldycord * 45) + 109;
	}

	public void updateOldSet(int x, int y) {
		// System.out.println("OldSet");
		if (y > 480 && y < 570 && x > 5 && x < 545) {
			oldxcord = (x - 5) / 45;
			oldycord = (y - 489) / 45;
			oldxpos = (oldxcord * 45) + 5;
			oldypos = (oldycord * 45) + 490;
			inP1 = true;
		} else if (y > 330 && y < 455 && x > 50 && x < 450) {
			oldxcord = (x - 50) / 45;
			oldycord = (y - 110) / 45;
			oldxpos = (oldxcord * 45) + 49;
			oldypos = (oldycord * 45) + 109;
			inP1 = false;
		}
	}

	public void updateSet(int x, int y) {
		// System.out.println("Set");
		if (y < 470) {
			xcord = (x - 50) / 45;
			ycord = (y - 110) / 45;
			xpos = (xcord * 45) + 49;
			ypos = (ycord * 45) + 109;
		}
	}

	public void resetP2Area() {
		// System.out.println("resetP2Area");
		// places pieces on grid
		// System.out.println("Placing computer pieces");
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 2; j++) {
				if (gridP2[i][j] != null) {
					// System.out.println("Placing piece " + i + " " + j);
					setRandomP2(gridP2[i][j]);
				}
			}
		}
		// removes all pieces from gridP2
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 2; j++) {
				gridP2[i][j] = null;
			}
		}
		repaint();
	}

	public void setRandomP2(Piece p) {
		// System.out.println("setRandomP2");
		Random r = new Random();
		Boolean foundASpot = false;
		while (foundASpot == false) {
			int row = r.nextInt(3);
			int col = r.nextInt(9);
			if (grid[col][row] == null) {
				// System.out.println(" Setting a computer piece to " + col + "
				// " + row);
				grid[col][row] = p;
				p.setPosition(col * 45 + 49, row * 45 + 109);
				foundASpot = true;
			}
		}
	}

	public void resetP1Area() {
		// System.out.println("resetP1Area");
		// places pieces on grid
		// System.out.println("Placing computer pieces");
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 2; j++) {
				if (gridP1[i][j] != null) {
					// System.out.println("Placing piece " + i + " " + j);
					setRandomP1(gridP1[i][j]);
				}
			}
		}
		// removes all pieces from gridP1
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 2; j++) {
				gridP1[i][j] = null;
			}
		}
		repaint();
	}

	public void clearP1() {
		for (int i = 0; i < 9; i++) {
			for (int j = 5; j < 8; j++) {
				grid[i][j] = null;
			}
		}
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 2; j++) {
				gridP1[i][j] = null;
			}
		}
	}

	public void setRandomP1(Piece p) {
		// System.out.println("setRandomP1");
		Random r = new Random();
		Boolean foundASpot = false;
		while (foundASpot == false) {
			int row = r.nextInt(3) + 5;
			int col = r.nextInt(9);
			if (grid[col][row] == null) {
				grid[col][row] = p;
				p.setPosition(col * 45 + 49, row * 45 + 109);
				foundASpot = true;
			}
		}
	}

	public void changeFormation() {
		clearP1();
		if (formInc == 0)
			secRowFormation();
		else if (formInc == 1)
			cheapFormation();
		formInc++;
		if (formInc == 4)
			formInc = 0;
		flipOver();
		repaint();
	}

	public void cheapFormation() {
		for (int i = 0; i < 9; i++) {
			for (int j = 5; j < 8; j++) {
				newPiece(i, j, 12);
			}
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 3; j < 5; j++) {
				newPiece(i, j, 13);
			}
		}
	}

	public void secRowFormation() {
		newPiece(0, 5, 4);
		newPiece(1, 5, 7);
		newPiece(2, 5, 6);
		newPiece(3, 5, 1);
		newPiece(4, 5, 13);
		newPiece(5, 5, 14);
		newPiece(6, 5, 8);
		newPiece(7, 5, 9);
		newPiece(8, 5, 2);
		newPiece(0, 6, 13);
		newPiece(1, 6, 3);
		newPiece(2, 6, 14);
		newPiece(3, 6, 5);
		newPiece(4, 6, 10);
		newPiece(5, 6, 11);
		newPiece(6, 6, 12);
		newPiece(7, 6, 14);
		newPiece(8, 6, 14);
		newPiece(2, 7, 14);
		newPiece(4, 7, 14);
		newPiece(6, 7, 15);
	}

	public void otherFormation() {
		newPiece(0, 5, 9);
		newPiece(0, 6, 6);
		newPiece(0, 7, 14);
		newPiece(1, 5, 14);
		newPiece(1, 6, 12);
		newPiece(2, 5, 2);
		newPiece(2, 6, 13);
		newPiece(3, 5, 13);
		newPiece(3, 6, 3);
		newPiece(3, 7, 4);
		newPiece(4, 5, 8);
		newPiece(4, 6, 14);
		newPiece(4, 7, 15);
		newPiece(5, 5, 14);
		newPiece(5, 6, 10);
		newPiece(6, 5, 14);
		newPiece(6, 6, 5);
		newPiece(7, 5, 11);
		newPiece(7, 6, 14);
		newPiece(8, 5, 7);
		newPiece(8, 6, 1);
	}

	public void newPiece(int x, int y, int PieceID) {
		grid[x][y] = new Piece(PieceID, getX(x), getY(y));
	}

	public void hard() {
		boolean finished = false;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 8; j++) {
				if (grid[i][j] != null && grid[i][j].getColor() == Color.blue) {
					xcord = i;
					ycord = j;
					oldxcord = xcord;
					oldycord = ycord;
					oldxpos = (xcord * 45) + 49;
					oldypos = (ycord * 45) + 109;
					if (xcord < 8 && grid[xcord + 1][ycord] != null
							&& grid[xcord + 1][ycord].getColor() != Color.blue) {
						++xcord;
						System.out.println("1");
						test(oldxcord, oldycord, xcord, ycord);
						if (pieceWon) {
							battle(oldxcord, oldycord, xcord, ycord);
							System.err.println("SUCCESS");
							finished = true;
							break;
						}
						// 22222222
					} else if (ycord > 0 && grid[xcord][ycord - 1] != null
							&& grid[xcord][ycord - 1].getColor() != Color.blue) {
						--ycord;
						System.out.println("2");
						test(oldxcord, oldycord, xcord, ycord);
						if (pieceWon) {
							battle(oldxcord, oldycord, xcord, ycord);
							System.err.println("SUCCESS");
							finished = true;
							break;
						}
						// 33333333
					} else if (xcord > 0 && grid[xcord - 1][ycord] != null
							&& grid[xcord - 1][ycord].getColor() != Color.blue) {
						--xcord;
						System.out.println("3");
						test(oldxcord, oldycord, xcord, ycord);
						if (pieceWon) {
							battle(oldxcord, oldycord, xcord, ycord);
							System.err.println("SUCCESS");
							finished = true;
							break;
						}
					} else if (ycord < 7 && grid[xcord][ycord + 1] != null
							&& grid[xcord][ycord + 1].getColor() != Color.blue) {
						++ycord;
						test(oldxcord, oldycord, xcord, ycord);
						if (pieceWon) {
							System.out.println("4");
							battle(oldxcord, oldycord, xcord, ycord);
							System.err.println("SUCCESS");
							finished = true;
							break;
						}
					}
				}
				if (finished)
					break;
			}
			if (finished)
				break;
		}
		if (!finished) {
			medium();
		}
	}

	public void medium() {
		// System.out.println("compMove");
		xcord = (int) (Math.random() * 9.);
		ycord = (int) (Math.random() * 8.);
		oldxcord = xcord;
		oldycord = ycord;
		oldxpos = (xcord * 45) + 49;
		oldypos = (ycord * 45) + 109;
		if (grid[xcord][ycord] == null
				|| grid[xcord][ycord].getColor() != Color.blue) {
			medium();
		} else {
			// int a = grid[xcord][ycord].getPieceID();
			int direction = (int) (Math.random() * 6.);
			if (direction == 0) {
				xcord++;
			} else if (direction == 1) {
				xcord--;
			} else if (direction == 2) {
				ycord--;
			} else if (direction == 3 || direction == 4 || direction == 5) {
				ycord++;
			}

			if (xcord < 0 || xcord > 8 || ycord < 0 || ycord > 7) {
				medium();
			} else if (grid[xcord][ycord] == null) {
				System.out.println("Chose piece "
						+ grid[oldxcord][oldycord].getPieceID()
						+ " To move to " + xcord + " " + ycord);
				isP1Turn = !isP1Turn;
				System.err.println("\tNo Competition - computer");
				Piece p = grid[oldxcord][oldycord];
				grid[xcord][ycord] = p;
				grid[oldxcord][oldycord] = null;
				xpos = (xcord * 45) + 49;
				ypos = (ycord * 45) + 109;
				movex = xpos;
				movey = ypos;
				p.setPosition(xpos, ypos);
				repaint();
			} else if (grid[xcord][ycord].getColor() == Color.blue) {
				medium();
			} else {
				if (grid[oldxcord][oldycord].getPieceID() == 15) {
					medium();
				} else {
					System.out.println("Chose that piece "
							+ grid[oldxcord][oldycord].getPieceID()
							+ " To move to " + xcord + " " + ycord);
					battle(oldxcord, oldycord, xcord, ycord);
				}
			}
		}
	}

	public void easy() {
		// System.out.println("compMove");
		xcord = (int) (Math.random() * 9.);
		ycord = (int) (Math.random() * 8.);
		oldxcord = xcord;
		oldycord = ycord;
		oldxpos = (xcord * 45) + 50;
		oldypos = (ycord * 45) + 110;
		if (grid[xcord][ycord] == null
				|| grid[xcord][ycord].getColor() != Color.blue) {
			hard();
			// System.out.println("bad pick");
		} else if (xcord < 0 || xcord > 8 || ycord < 0 || ycord > 7) {
			hard();
			// System.out.println("bad cords");
		} else if (grid[xcord][ycord].getPieceID() != 15
				&& grid[xcord][ycord].getPieceID() != 14) {
			int a = grid[xcord][ycord].getPieceID();
			// 1111111
			if (xcord < 8 && grid[xcord + 1][ycord] != null
					&& grid[xcord + 1][ycord].getColor() != Color.blue
					&& grid[xcord + 1][ycord].getPieceID() < a) {
				++xcord;
				System.out.println("1");
				battle(oldxcord, oldycord, xcord, ycord);
				// 22222222
			} else if (ycord > 0 && grid[xcord][ycord - 1] != null
					&& grid[xcord][ycord - 1].getColor() != Color.blue
					&& grid[xcord][ycord - 1].getPieceID() < a) {
				--ycord;
				System.out.println("2");
				battle(oldxcord, oldycord, xcord, ycord);
				// 33333333
			} else if (xcord > 0 && grid[xcord - 1][ycord] != null
					&& grid[xcord - 1][ycord].getColor() != Color.blue
					&& grid[xcord - 1][ycord].getPieceID() < a) {
				--xcord;
				System.out.println("3");
				battle(oldxcord, oldycord, xcord, ycord);
				// 44
			} else if (ycord < 7 && grid[xcord][ycord + 1] != null
					&& grid[xcord][ycord + 1].getColor() != Color.blue
					&& grid[xcord][ycord + 1].getPieceID() < a) {
				++ycord;
				System.out.println("4");
				battle(oldxcord, oldycord, xcord, ycord);
			}
			// big else
		} else {
			System.out.println("BIG ELSE");
			int direction = (int) (Math.random() * 4.);
			if (direction == 0) {
				xcord++;
			} else if (direction == 1) {
				xcord--;
			} else if (direction == 2 || direction == 3) {
				ycord++;
			} else {
				ycord--;
			}

			if (xcord < 0 || xcord > 8 || ycord < 0 || ycord > 7) {
				hard();
			} else if (grid[xcord][ycord] == null) {
				System.out.println("Chose piece "
						+ grid[oldxcord][oldycord].getName() + " To move to "
						+ xcord + " " + ycord);
				isP1Turn = !isP1Turn;
				System.err.println("\tNo Competition - computer");
				Piece p = grid[oldxcord][oldycord];
				grid[xcord][ycord] = p;
				grid[oldxcord][oldycord] = null;
				p.setPosition((xcord * 45) + 49, (ycord * 45) + 109);
			} else if (grid[xcord][ycord].getColor() == Color.blue) {
				hard();
			} else {
				if (grid[oldxcord][oldycord].getPieceID() == 15) {
					hard();
				} else {
					System.out.println("Chose piece "
							+ grid[oldxcord][oldycord].getPieceID()
							+ " To move to " + xcord + " " + ycord);
					battle(oldxcord, oldycord, xcord, ycord);

				}
			}
		}
	}

	public void compMove() {
		String s = SalpakanGui.getLevel();
		System.out.println(s);
		if (s.equals("easy")) {
			easy();
		} else if (s.equals("medium")) {
			medium();
		} else if (s.equals("hard")) {
			hard();
		} else if (s.equals("expert")) {
			// expert();
		} else if (s.equals("legendary")) {
			// legendary();
		}
		hard();
	}

	/*
	 * public void drawMove(Graphics g){ if (grid[oldxcord][oldycord] != null &&
	 * grid[oldxcord][oldycord].getColor() != Color.blue && ready){
	 * g.setColor(Color.red); g.drawRect(movex + 1, movey + 1, 44, 44);
	 * g.drawRect(movex + 2, movey + 2, 42, 42); } repaint(); }
	 */

	public void addToGrave(Piece p) {
		System.out.println("Adding " + p.getSkill() + " to Grave");
		if (p.getColor() == Color.blue) {
			score += p.getValue();
			p.setPosition(p2x, p2y);
			xcord = (p2x - 4) / 45;
			ycord = (p2y - 4) / 45;
			gridP2[xcord][ycord] = p;
			p2x += 45;
			p2Inc++;
			if (p2Inc % 11 == 0) {
				p2x = 5;
				p2y += 45;
			}
			if (p2Inc == 21) {
				p2Inc = 0;
				p2x = 5;
				p2y = 5;
			}
		} else {
			score -= p.getValue();
			p.setPosition(p1x, p1y);
			xcord = (p1x - 4) / 45;
			ycord = (p1y - 489) / 45;
			gridP1[xcord][ycord] = p;
			p1x += 45;
			p1Inc++;
			if (p1Inc % 11 == 0) {
				p1x = 5;
				p1y += 45;
			}
			if (p1Inc == 21) {
				p1Inc = 0;
				p1x = 5;
				p1y = 5;
			}
		}
		repaint();
	}

	public void updatePiece(int oldXCord, int oldYCord, int xCord, int yCord) {
		movex = (xCord * 45) + 49;
		movey = (yCord * 45) + 109;
		if (pieceWon) {
			if (isP1Turn)
				new Sound("Kill.wav").start();
			else
				new Sound("Disconnect.wav").start();
			Piece a = grid[oldXCord][oldYCord];
			addToGrave(grid[xCord][yCord]);
			grid[oldXCord][oldYCord] = null;
			grid[xCord][yCord] = a;
			a.setPosition((xCord * 45) + 49, (yCord * 45) + 109);
		} else if (salpakan) {
			new Sound("Bomb.wav").start();
			Piece p1 = grid[oldXCord][oldYCord];
			Piece p2 = grid[xCord][yCord];
			grid[oldXCord][oldYCord] = null;
			grid[xCord][yCord] = null;
			addToGrave(p1);
			addToGrave(p2);
		} else {
			if (!isP1Turn)
				new Sound("Kill.wav").start();
			else
				new Sound("Disconnect.wav").start();
			Piece a = grid[xCord][yCord];
			addToGrave(grid[oldXCord][oldYCord]);
			grid[oldXCord][oldYCord] = null;
			grid[xCord][yCord] = a;
			a.setPosition((xCord * 45) + 49, (yCord * 45) + 109);
		}
		repaint();
	}

	public void test(int oldxcord, int oldycord, int newxcord, int newycord) {
		int p1Piece = grid[oldxcord][oldycord].getPieceID();
		int p2Piece = grid[newxcord][newycord].getPieceID();

		pieceWon = false;

		if (p2Piece == 15) {
			pieceWon = true;
		} else if (p1Piece == 15)
			pieceWon = false;
		else if (p1Piece == p2Piece)
			pieceWon = false;
		else if (p1Piece == 14) {
			if (p2Piece == 13)
				pieceWon = true;
			else
				pieceWon = false;
		} else if (p1Piece == 13) {
			if (p2Piece == 14)
				pieceWon = false;
			else
				pieceWon = true;
		} else {
			if (p1Piece > p2Piece || p2Piece == 14)
				pieceWon = true;
			else
				pieceWon = false;
		}
	}

	public void battle(int oldxcord, int oldycord, int newxcord, int newycord) {
		int p1Piece = grid[oldxcord][oldycord].getPieceID();
		int p2Piece = grid[newxcord][newycord].getPieceID();

		salpakan = false;
		pieceWon = false;

		if (p2Piece == 15) {
			p1HasWon = true;
		} else if (p1Piece == 15)
			p2HasWon = true;
		else if (p1Piece == p2Piece)
			salpakan = true;
		else if (p1Piece == 14) {
			if (p2Piece == 13)
				pieceWon = true;
			else
				pieceWon = false;
		} else if (p1Piece == 13) {
			if (p2Piece == 14)
				pieceWon = false;
			else
				pieceWon = true;
		} else {
			if (p1Piece > p2Piece || p2Piece == 14)
				pieceWon = true;
			else
				pieceWon = false;
		}

		if (p1HasWon && isP1Turn) {
			new Sound("Connect.wav").start();
			JOptionPane.showMessageDialog(null, "Gameover -- You Win!  "
					+ "\nYou Captured The Enemy's Flag!");
			turnAllUp();
			done = true;
		} else if (p1HasWon && !isP1Turn) {
			new Sound("Connect.wav").start();
			JOptionPane.showMessageDialog(null, "Gameover -- You Lose!  "
					+ "\nThe Enemy Captured Your Flag!");
			turnAllUp();
			done = true;
		} else if (p2HasWon && isP1Turn) {
			new Sound("Connect.wav").start();
			JOptionPane.showMessageDialog(null, "Gameover -- You Lose!  "
					+ "\nYou Attacked With The Flag And Lost!");
			turnAllUp();
			done = true;
		} else if (p2HasWon && !isP1Turn) {
			new Sound("Connect.wav").start();
			JOptionPane.showMessageDialog(null, "Gameover -- You Win!"
					+ "\nOpponent's Flag Attacked You!");
			turnAllUp();
			done = true;
		} else {
			updatePiece(oldxcord, oldycord, newxcord, newycord);
		}
		isP1Turn = !isP1Turn;
	}

	public boolean isLegalMove(int oldXCord, int oldYCord, int xcord,
			int ycord, int x, int y) {
		if (x > 450 || y > 470 || x < 30 || y < 100) {
			return false;
		} else if (grid[oldXCord][oldYCord] == null) {
			return false;
		} else if (grid[oldXCord][oldYCord].getColor() == Color.blue) {
			return false;
		} else if (oldXCord - xcord > 1 || oldXCord - xcord < -1
				|| oldYCord - ycord > 1 || oldYCord - ycord < -1
				|| oldXCord - xcord == 1 && oldYCord - ycord == 1
				|| oldXCord - xcord == 1 && oldYCord - ycord == -1
				|| oldXCord - xcord == -1 && oldYCord - ycord == 1
				|| oldXCord - xcord == -1 && oldYCord - ycord == -1) {
			// System.err.println("\tIllegal Move");
			return false;
		} else if (grid[oldXCord][oldYCord] != null
				&& grid[xcord][ycord] != null) {
			if (grid[oldXCord][oldYCord].getColor() == grid[xcord][ycord]
					.getColor()) {
				// System.err.println("\tSame Color");
				return false;
			} else {
				// System.out.println("Legal Move -- Battle");
				battle(oldXCord, oldYCord, xcord, ycord);
				return true;
			}

		} else {
			isP1Turn = !isP1Turn;
			// System.out.println("Legal Move -- No Competition");
			Piece p = grid[oldXCord][oldYCord];
			grid[oldXCord][oldYCord] = null;
			grid[xcord][ycord] = p;
			p.setPosition((xcord * 45) + 49, (ycord * 45) + 109);
			return true;
		}
	}

	public boolean isLegalSet(int oldXCord, int oldYCord, int xcord, int ycord,
			int x, int y) {
		// System.out.println("isLegalSet");
		if (x > 450 || y > 469 || x < 35 || y < 335) {
			return false;
		} else if (y > 470 && y < 569) {
			if (gridP1[oldXCord][oldYCord] == null) {
				return false;
			} else {
				return true;
			}
		} else
			return true;
	}

	public boolean getTurn() {
		return isP1Turn;
	}

	public void changeStyle() {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 2; j++) {
				if (gridP1[i][j] != null) {
					gridP1[i][j].changeStyle();
				}
			}
		}
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 2; j++) {
				if (gridP2[i][j] != null) {
					gridP2[i][j].changeStyle();
				}
			}
		}
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 8; j++) {
				if (grid[i][j] != null)
					grid[i][j].changeStyle();
			}
		}

		repaint();
	}

	public boolean getReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public void checkFlag() {
		for (int i = 0; i < 9; i++) {
			if (grid[i][0] != null && grid[i][0].getColor() != Color.blue
					&& grid[i][0].getPieceID() == 15) {
				p1HasWon = true;
				if (p1HasWon) {
					new Sound("Connect.wav").start();
					JOptionPane.showMessageDialog(null, "Gameover -- You Win!"
							+ "\nYou Got Your Flag To The Enemy's Side!");
					turnAllUp();
					done = true;
					break;
				}
			}
		}
		for (int i = 0; i < 9; i++) {
			if (grid[i][7] != null && grid[i][7].getColor() == Color.blue
					&& grid[i][7].getPieceID() == 15) {
				p2HasWon = true;
				if (p2HasWon) {
					new Sound("Connect.wav").start();
					JOptionPane.showMessageDialog(null, "Gameover -- You Lose!"
							+ "\nThe Enemy Got His Flag To Your Side!");
					turnAllUp();
					done = true;
					break;
				}
			}
		}
	}

	public void printForms() {
		for (int xcord = 0; xcord < 9; xcord++) {
			for (int ycord = 5; ycord < 8; ycord++) {
				if (grid[xcord][ycord] != null)
					System.out.println("newPiece(" + xcord + ", " + ycord
							+ ", " + grid[xcord][ycord].getPieceID() + "); ");
			}
		}
	}

	private class MyMouseListener implements MouseListener, MouseMotionListener {

		public void mousePressed(MouseEvent e) {

			if (!done) {
				isP1Turn = true;
				x = (int) e.getPoint().getX();
				y = (int) e.getPoint().getY();
				if (ready) {
					updateOldCords(x, y);
				} else {
					updateOldSet(x, y);
				}
				new Sound("Up.wav").start();
				repaint();
			}
		}

		public void mouseDragged(MouseEvent e) {
			if (!done) {
				x = (int) e.getPoint().getX();
				y = (int) e.getPoint().getY();
				if (ready) {
					if (grid[oldxcord][oldycord] == null) {

					} else {
						if (grid[oldxcord][oldycord].getColor() != Color.blue) {
							grid[oldxcord][oldycord].setPosition(x
									- Piece.width / 2, y - Piece.height / 2);
						}
					}
				} else {
					if (!inP1) {
						if (grid[oldxcord][oldycord] != null) {
							grid[oldxcord][oldycord].setPosition(x
									- Piece.width / 2, y - Piece.height / 2);
						}
					} else {
						if (gridP1[oldxcord][oldycord] != null) {

							gridP1[oldxcord][oldycord].setPosition(x
									- Piece.width / 2, y - Piece.height / 2);
						}
					}
				}
				repaint();
			}
		}

		public void mouseReleased(MouseEvent e) {
			if (!done) {
				x = (int) e.getPoint().getX();
				y = (int) e.getPoint().getY();
				if (ready) {
					moved = true;

					updateCords(x, y);
					if (isLegalMove(oldxcord, oldycord, xcord, ycord, x, y)) {
						new Sound("Tick.wav").start();
						++numTurns;
						if (!p1HasWon && !p2HasWon) {
							compMove();
							checkFlag();
						}
					} else {
						if (grid[oldxcord][oldycord] != null && y > 105) 
							grid[oldxcord][oldycord].setPosition(oldxpos,
									oldypos);
					}
				}

				else {
					updateSet(x, y);
					if (isLegalSet(oldxcord, oldycord, xcord, ycord, x, y)) {
						new Sound("Tick.wav").start();
						if (!inP1) {
							if (grid[oldxcord][oldycord] != null) {
								if (grid[xcord][ycord] != null) {
									// swapping pieces
									Piece fresh = grid[xcord][ycord];
									Piece old = grid[oldxcord][oldycord];
									old.setPosition(xpos, ypos);
									fresh.setPosition(oldxpos, oldypos);
									grid[xcord][ycord] = old;
									grid[oldxcord][oldycord] = fresh;
								} else {
									// just setting the piece to a new location
									grid[oldxcord][oldycord].setPosition(xpos,
											ypos);
									grid[xcord][ycord] = grid[oldxcord][oldycord];
									grid[oldxcord][oldycord] = null;
								}
							}
						} else {
							if (gridP1[oldxcord][oldycord] != null) {
								if (grid[xcord][ycord] != null) {
									// swapping a piece with an unset piece
									Piece fresh = grid[xcord][ycord];
									Piece old = gridP1[oldxcord][oldycord];
									old.setPosition(xpos, ypos);
									fresh.setPosition(fresh.getOx(), fresh
											.getOy());
									grid[xcord][ycord] = old;
									gridP1[fresh.getxcord()][fresh.getycord()] = fresh;
								} else {
									// actually setting a piece
									if (gridP1[oldxcord][oldycord] != null) {
										gridP1[oldxcord][oldycord].setPosition(
												xpos, ypos);
										grid[xcord][ycord] = gridP1[oldxcord][oldycord];
										// System.out.println("newPiece(" +xcord
										// + ", " + ycord + ", " +
										// grid[xcord][ycord].getPieceID() + ");
										// ");
										gridP1[oldxcord][oldycord] = null;
										++readyInc;
									}
								}
							}
						}
					} else if (inP1) {
						if (gridP1[oldxcord][oldycord] != null)
							gridP1[oldxcord][oldycord].setPosition(oldxpos,
									oldypos);
					} else {
						if (grid[oldxcord][oldycord] != null)
							grid[oldxcord][oldycord].setPosition(oldxpos,
									oldypos);
					}
				}
				if (readyInc == 21) {
					SalpakanGui.setReady(true);
				}
				// System.out.println("Done = " + scoreTotal);
				repaint();
			}
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseMoved(MouseEvent e) {
		}

		public void mouseClicked(MouseEvent e) {
			/*
			 * compMove(); repaint();
			 */
		}
	}

	/*
	 * public static void main (String[ ] args){ PlayingArea a = new
	 * PlayingArea(); p1Battle( 1, 15); System.out.println();
	 * //System.out.println(a.isLegalMove(1, 1, 5, 1, 50, 280)); }
	 */
	/*
	 * private class PieceMoves { private boolean up; private boolean down;
	 * private boolean left; private boolean right; private boolean better;
	 * private Vector<Piece> v1 = new Vector<Piece>(); private Vector<Piece>
	 * v2 = new Vector<Piece>();
	 * 
	 * public PieceMoves(Piece p) { if (p.getColor() == Color.blue) { v2.add(p); }
	 * else { v1.add(p); } }
	 * 
	 * public void update() { v1.clear(); v2.clear(); for (int i = 0; i < 9;
	 * i++) { for (int j = 0; j < 8; j++) { if (grid[i][j] != null) { if
	 * (grid[i][j].getColor() == Color.blue) { v2.add(grid[i][j]); } else {
	 * v1.add(grid[i][j]); } } } } } }
	 */

}
