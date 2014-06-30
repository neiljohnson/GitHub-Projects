import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SalpakanGui extends JFrame {

	private static final long serialVersionUID = 6928110041166900391L;

	private static JButton ready;
	private static JButton randomForm;
	private static JButton changeColor;
	private JButton newGame;
	private JButton reveal;
	private JButton style;
	private static JComboBox level;
	private JComboBox forms;
	private static JLabel top;
	private static JLabel top2;
	private JPanel contentPane;
	
	public static String[] names = { "Random", "Right Fake", "Left Fake",
			"Fortess", "Frontal Assualt", "Defensive" };
	public static String[] levels = { "Easy", "Medium",
		"Hard", "Expert", "Legendary" };
	private boolean form = false;

	private PlayingArea area;

	public Deck deck1;
	public Deck deck2;

	
	public SalpakanGui() {
		super();
		initializeComponent();
		startGame();
		this.setVisible(true);
	}

	private void initializeComponent() {
		MyListener ml = new MyListener();
		forms = new JComboBox(names);
		forms.addActionListener(ml);
		ready = new JButton();
		ready.addActionListener(ml);
		newGame = new JButton();
		newGame.addActionListener(ml);
		style = new JButton();
		style.addActionListener(ml);
		randomForm = new JButton();
		randomForm.addActionListener(ml);
		changeColor = new JButton();
		changeColor.addActionListener(ml);
		reveal = new JButton();
		reveal.addActionListener(ml);
		level = new JComboBox(levels);
		level.addActionListener(ml);
		top = new JLabel();
		top2 = new JLabel();
		contentPane = (JPanel) this.getContentPane();
		area = new PlayingArea();
		
		randomForm.setMargin(new Insets(2, 8, 2, 8));
		randomForm.setText("Randomize Formation");
		randomForm
		.setToolTipText("Cycles through several premaid arrangements.");
		
		changeColor.setText("Toggle Piece Color");
		changeColor.setToolTipText("Cycles through differnent colors.");

		ready.setMargin(new Insets(1, 14, 1, 14));
		ready.setText("Ready");
		ready.setToolTipText("Are you ready to start?");
		ready.setEnabled(false);

		reveal.setMargin(new Insets(1, 14, 1, 14));
		reveal.setText("Reveal Pieces");
		reveal.setToolTipText("Click for help.");

		//forms.setMargin(new Insets(1, 14, 1, 14));
		//forms.setText("Change Difficulty");
		forms.setToolTipText("Easy, Medium, or Hard");
	
		newGame.setMargin(new Insets(1, 14, 1, 14));
		newGame.setText("New Game");
		newGame.setToolTipText("Click to resart a new game");
		
		style.setText("Change Piece Style");
		
		top.setFont(new Font("Arial", Font.BOLD, 15));
		top.setForeground(Color.blue);
		top.setText("Start setting up your pieces");
		top2.setFont(new Font("Arial", Font.BOLD, 15));
		top2.setForeground(Color.magenta);
		top2.setText("Hit ready when done");
		
		contentPane.setLayout(null);

		addComponent(contentPane, area, 10, 30, 506, 610);
		addComponent(contentPane, top, 10, 0, 249, 28);
		addComponent(contentPane, top2, 300, 0, 149, 28);
		addComponent(contentPane, reveal, 525, 30, 149, 28);
		addComponent(contentPane, forms, 525, 407, 149, 28);
		addComponent(contentPane, randomForm, 525, 370, 149, 28);
		addComponent(contentPane, level, 525, 70, 149, 28);
		addComponent(contentPane, changeColor, 525, 207, 149, 28);
		addComponent(contentPane, style, 525, 260, 149, 28);
		addComponent(contentPane, ready, 525, 486, 149, 46);
		addComponent(contentPane, newGame, 525, 550, 149, 46);

		this.setTitle("Salpakan Gui");
		this.setLocation(new Point(500, 50));
		this.setSize(new Dimension(685, 680));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void addComponent(Container container, Component c, int x, int y,
			int width, int height) {
		c.setBounds(x, y, width, height);
		container.add(c);
	}
	
	public static void changeTop(String s){
		top.setText(s);
	}
	
	public static void changeTop2(String s){
		top2.setText(s);
	}
	
	public static String getLevel(){
		return level.getActionCommand();
	}
	
	public String getForm(){
		return forms.getActionCommand();
	}
	
	public static void setReady(boolean b) {
		ready.setEnabled(b);
	}

	public static void setFormation(boolean b) {
		randomForm.setEnabled(b);
	}

	public static void setColor(boolean b) {
		changeColor.setEnabled(b);
	}

	private void placeComputerPieces() {
		area.resetP2Area();
	}
	
	private void addStartingPiecesP1(boolean isFaceUp) {
		deck1 = new Deck();
		for (int i = 0; i < 21; i++) {
			Piece p1 = deck1.getTop();
			if (isFaceUp) {
				p1.turnPieceOver();
			}
			area.addPieceToSideArea(p1);
		}
	}

	private void addStartingPiecesP2(boolean isFaceUp) {
		deck2 = new Deck();
		for (int i = 0; i < 21; i++) {
			Piece p2 = deck2.getTop();
			if (isFaceUp) {
				p2.turnPieceOver();
			}
			area.addPieceToSideAreaP2(p2);
		}
	}

	private void startGame() {
		area.reset();
		addStartingPiecesP1(true);
		addStartingPiecesP2(false);
		if (!form)
			new Sound("NewGame.wav").start();
	}
	
	private class MyListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == ready) {
				new Sound("Up.wav").start();
				placeComputerPieces();
				area.setReady(true);
				ready.setEnabled(false);
				randomForm.setEnabled(false);
				forms.setEnabled(false);
				top.setText("Move");
				top2.setText("Show your stuff");
				new Sound("Connect.wav").start();
				form = false;
				
			} else if (e.getSource() == newGame) {
				startGame();
				randomForm.setEnabled(true);
				forms.setEnabled(true);
				ready.setEnabled(false);
				form = false;
				
			} else if (e.getSource() == randomForm) {
				new Sound("Up.wav").start();
				if (!form) {
					area.resetP1Area();
					form = true;
					ready.setEnabled(true);
				} else {
					startGame();
					area.resetP1Area();
					changeColor.setEnabled(true);
				}
				
			} else if (e.getSource() == changeColor) {
				new Sound("Up.wav").start();
				area.changeColor();
				
			} else if (e.getSource() == reveal) {
				area.printForms();
				area.turnOver();
				new Sound("Up.wav").start();
				
			} else if (e.getSource() == forms) {
				area.changeFormation();
				ready.setEnabled(true);
				new Sound("Up.wav").start();
				
			} else if (e.getSource() == style) {
				area.changeStyle();
				
			} else if (e.getSource() == level) {
				System.out.println(level.toString());
			}else
				System.out.println("You pressed an unhandled object");
		}
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception ex) {
			System.out.println("Failed loading L&F: ");
			System.out.println(ex);
		}
		new SalpakanGui();
	}
}
