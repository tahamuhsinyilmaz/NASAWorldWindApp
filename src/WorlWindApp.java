import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas; 

public class WorlWindApp extends WorldWindowGLCanvas {
	OptionsPanel p;
	public WorlWindApp() {
		this.setPreferredSize(new java.awt.Dimension(1000, 800));
		this.setModel(new BasicModel());
		p=new OptionsPanel();
		p.addPanel(this);
		
	}	
}
