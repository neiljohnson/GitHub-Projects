import javax.swing.*;
import java.awt.Color;
import java.awt.*;

import java.util.*;

public class Canvas {
	private static Canvas canvasSingleton;
	private JFrame frame;
	private CanvasPane canvas;
	private Graphics2D graphic;
	private Color backgroundColour;
	private Vector<Piece> thePieces;
	private Image canvasImage;

	public static Canvas getCanvas() {

		if (canvasSingleton == null) {
			canvasSingleton = new Canvas("BlueJ Shapes Demo", 300, 300,
					Color.white);

		}
		canvasSingleton.setVisible(true);
		return canvasSingleton;
	}

	public Canvas(String title, int width, int height, Color bgColour) {
		frame = new JFrame();
		canvas = new CanvasPane();
		frame.setContentPane(canvas);
		frame.setTitle(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas.setPreferredSize(new Dimension(width, height));
		backgroundColour = bgColour;
		frame.pack();
		thePieces = new Vector<Piece>();
		setVisible(true);
	}

	public Color getColor() {
		return backgroundColour;
	}

	public void setVisible(boolean visible) {
		if (graphic == null) {
			Dimension size = canvas.getSize();
			canvasImage = canvas.createImage(size.width, size.height);
			graphic = (Graphics2D) canvasImage.getGraphics();
			graphic.setColor(backgroundColour);
			graphic.fillRect(0, 0, size.width, size.height);
			redraw();
		}
		frame.setVisible(visible);
	}

	public void addPiece(Piece p) {
		thePieces.remove(p); // just in case it was already there
		thePieces.add(p); // add at the end
		redraw();

	}

	public void erase(Piece p) {
		thePieces.remove(p); // just in case it was already there
		redraw();
	}

	public void setForegroundColor(String colorString) {
		if (colorString.equals("red"))
			graphic.setColor(Color.red);
		else if (colorString.equals("black"))
			graphic.setColor(Color.black);
		else if (colorString.equals("blue"))
			graphic.setColor(Color.blue);
		else if (colorString.equals("yellow"))
			graphic.setColor(Color.yellow);
		else if (colorString.equals("green"))
			graphic.setColor(Color.green);
		else if (colorString.equals("magenta"))
			graphic.setColor(Color.magenta);
		else if (colorString.equals("white"))
			graphic.setColor(Color.white);
		else
			graphic.setColor(Color.black);
	}

	public void wait(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (Exception e) {

		}

	}

	private void redraw() {
		erase();
		for (int i = 0; i < thePieces.size(); i++) {
			((Piece) thePieces.get(i)).paint(graphic);
		}
		canvas.repaint();
	}

	private void erase() {
		Color original = graphic.getColor();
		graphic.setColor(backgroundColour);
		Dimension size = canvas.getSize();
		graphic.fill(new Rectangle(0, 0, size.width, size.height));
		graphic.setColor(original);
	}

	private class CanvasPane extends JPanel {
		private static final long serialVersionUID = 1L;

		public void paint(Graphics g) {
			g.drawImage(canvasImage, 0, 0, null);
		}
	}
}
/*
 * ImageIO.read(new File("zelda_U.jpg"));
 * try{
 * catch(Exception e){
 * public void draw{Graphics2D.g){
 * 
 * 
 */

