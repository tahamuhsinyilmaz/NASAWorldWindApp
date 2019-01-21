import javax.swing.JFrame;

import gov.nasa.worldwind.util.StatusBar;

public class TermProject extends JFrame {

	private static final long serialVersionUID = -452394474442744337L;

	public TermProject() {
		WorlWindApp wwd = new WorlWindApp();
		StatusBar statusBar = new StatusBar();
		statusBar.setEventSource(wwd);
		this.getContentPane().add(wwd, java.awt.BorderLayout.CENTER);
		this.getContentPane().add(statusBar, java.awt.BorderLayout.PAGE_END);
		this.add(wwd.p, java.awt.BorderLayout.WEST);
	}
	
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new TermProject();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setVisible(true);
			}
		});
	}
}