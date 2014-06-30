import javax.swing.JApplet;


public class SalpakanApplet extends JApplet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init(){
		SalpakanGui g = new SalpakanGui();
		this.add(g);
		this.setSize(685, 660);
	}
}