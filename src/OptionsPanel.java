import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import gov.nasa.worldwind.View;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.CrosshairLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.MarkerLayer;
import gov.nasa.worldwind.layers.ViewControlsLayer;
import gov.nasa.worldwind.layers.ViewControlsSelectListener;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.markers.BasicMarker;
import gov.nasa.worldwind.render.markers.BasicMarkerAttributes;
import gov.nasa.worldwind.render.markers.BasicMarkerShape;
import gov.nasa.worldwind.render.markers.Marker;
import gov.nasa.worldwind.render.markers.MarkerAttributes;
import gov.nasa.worldwindx.examples.ApplicationTemplate;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class OptionsPanel extends JPanel {
	XMLParser pdp1;
	XMLParser pdp2;
	XMLParser pdp3;
	XMLParser pdp4;
	public void addPanel(WorldWindowGLCanvas m) {
		this.setLayout(new BorderLayout());
		
		//Layer paneli
		JPanel layersPanel = new JPanel();
		layersPanel.setLayout(new BoxLayout(layersPanel, BoxLayout.Y_AXIS));
		Border layersBorder = BorderFactory.createTitledBorder("Layers");
		layersPanel.setBorder(layersBorder);
		
		// View Control Eklenmesi
		ViewControlsLayer viewControlsLayer = new ViewControlsLayer();
		m.getModel().getLayers().add(viewControlsLayer);
		m.addSelectListener(new ViewControlsSelectListener(m, viewControlsLayer));
		
		// Crosshair Eklenmesi
		CrosshairLayer crosshairLayer = new CrosshairLayer("crosshair.png");
		m.getModel().getLayers().add(crosshairLayer);	

		// Go Destination Panel
		JPanel goPanel = new JPanel();
		goPanel.setLayout(new BoxLayout(goPanel, BoxLayout.Y_AXIS));
		Border goBorder = BorderFactory.createTitledBorder("Go Destination");
		goPanel.setBorder(goBorder);
		JButton goButton = new JButton("GO");
		JTextField latTextField = new JTextField("Enter latitude");
		JTextField lonTextField = new JTextField("Enter longitude");
		JTextField altTextField = new JTextField("Enter Altitude");
		goPanel.add(latTextField);
		goPanel.add(lonTextField);
		goPanel.add(altTextField);
		goPanel.add(goButton);
		latTextField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				latTextField.setText("");

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		lonTextField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				lonTextField.setText("");

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		altTextField.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				altTextField.setText("");
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		goButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Angle lat = new Angle(Angle.fromDegreesLatitude(Double.parseDouble(latTextField.getText())));
				Angle lon = new Angle(Angle.fromDegreesLongitude(Double.parseDouble(lonTextField.getText())));
				LatLon latlon = new LatLon(lat, lon);
				if (latlon != null) {
					View view = m.getView();
					view.goTo(new Position(latlon, 0), Double.parseDouble(altTextField.getText()));

				}
			}
		});

		// PreDefined Positions Border
		JPanel pdpPanel = new JPanel();
		pdpPanel.setLayout(new BoxLayout(pdpPanel,BoxLayout.Y_AXIS));
		Border pdpBorder = BorderFactory.createTitledBorder("Pre-Defined Positions");
		pdpPanel.setBorder(pdpBorder);
		pdp1 = new XMLParser("pdp1.xml");
		pdp2 = new XMLParser("pdp2.xml");
		pdp3 = new XMLParser("pdp3.xml");
		pdp4 = new XMLParser("deneme.xml");
		JRadioButton pdp1Button = new JRadioButton("Pre-Defined Pos. 1");
		JRadioButton pdp2Button = new JRadioButton("Pre-Defined Pos. 2");
		JRadioButton pdp3Button = new JRadioButton("Pre-Defined Pos. 3");
		JRadioButton pdp4Button = new JRadioButton("Pre-Defined Pos. 4");
		ButtonGroup group = new ButtonGroup();
	    group.add(pdp1Button);
	    group.add(pdp2Button);
	    group.add(pdp3Button);
	    group.add(pdp4Button);
		JLabel currentLocLabel = new JLabel();
		currentLocLabel.setForeground(Color.red);
		pdpPanel.add(currentLocLabel);
		pdpPanel.add(pdp1Button);
		pdpPanel.add(pdp2Button);
		pdpPanel.add(pdp3Button);
		pdpPanel.add(pdp4Button);
		pdp1Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Angle lat = new Angle(Angle.fromDegreesLatitude(pdp1.getLatitude()));
				Angle lon = new Angle(Angle.fromDegreesLongitude(pdp1.getLongitude()));
				LatLon latlon = new LatLon(lat, lon);
				if (latlon != null) {
					View view = m.getView();
					view.goTo(new Position(latlon, 0), pdp1.getAltitude());
					currentLocLabel.setText(pdp1.getName());

				}
			}

		});

		pdp2Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Angle lat = new Angle(Angle.fromDegreesLatitude(pdp2.getLatitude()));
				Angle lon = new Angle(Angle.fromDegreesLongitude(pdp2.getLongitude()));
				LatLon latlon = new LatLon(lat, lon);
				if (latlon != null) {
					View view = m.getView();
					view.goTo(new Position(latlon, 0), pdp2.getAltitude());
					currentLocLabel.setText(pdp2.getName());

				}
			}

		});

		pdp3Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Angle lat = new Angle(Angle.fromDegreesLatitude(pdp3.getLatitude()));
				Angle lon = new Angle(Angle.fromDegreesLongitude(pdp3.getLongitude()));
				LatLon latlon = new LatLon(lat, lon);
				if (latlon != null) {
					View view = m.getView();
					view.goTo(new Position(latlon, 0), pdp3.getAltitude());
					currentLocLabel.setText(pdp3.getName());

				}
			}
			
		});
		pdp4Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Angle lat = new Angle(Angle.fromDegreesLatitude(pdp4.getLatitude()));
				Angle lon = new Angle(Angle.fromDegreesLongitude(pdp4.getLongitude()));
				LatLon latlon = new LatLon(lat, lon);
				if (latlon != null) {
					View view = m.getView();
					view.goTo(new Position(latlon, 0), pdp4.getAltitude());
					currentLocLabel.setText(pdp4.getName());
				}
			}
			
		});
		
		// Predefined Değiştirme butonu
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel,BoxLayout.X_AXIS));
		JPanel changePDPanel = new JPanel();
		changePDPanel.setLayout(new BorderLayout());
		Border changePdpBorder = BorderFactory.createTitledBorder("Set PDP4 Center Of Map");
		changePDPanel.setBorder(changePdpBorder);
		JButton pdpSetButton = new JButton("Set PDP 4");
		pdpSetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				View view = m.getView();
				Position p = view.getCurrentEyePosition();
				Angle lat = p.getLatitude();
				Angle lon = p.getLongitude();
		        pdp4.manupulateElements(Double.toString(lat.getDegrees()), XMLParser.CHANGE_LAT);
		        pdp4.manupulateElements(Double.toString(lon.getDegrees()), XMLParser.CHANGE_LONG);
		        pdp4=new XMLParser("deneme.xml");
			}	
					
		});
		
		changePDPanel.add(pdpSetButton,BorderLayout.CENTER);
		bottomPanel.add(changePDPanel);
		
		//Pin Marker Bölümü Eklenmesi
		JPanel pinMarkerPanel = new JPanel();
		pinMarkerPanel.setLayout(new BorderLayout());
		Border markerBorder = BorderFactory.createTitledBorder("Pin Marker");
		pinMarkerPanel.setBorder(markerBorder);
		JButton pinButton = new JButton("Pin the Marker"); 
		pinMarkerPanel.add(pinButton,BorderLayout.CENTER);
		bottomPanel.add(pinMarkerPanel);
		
		//Marker Eklenmesi
		ArrayList<Marker> markers = new ArrayList<Marker>();
		final MarkerLayer markerLayer = new MarkerLayer();
		markerLayer.setOverrideMarkerElevation(true);
		markerLayer.setKeepSeparated(false);
		markerLayer.setElevation(1000d);
		markerLayer.setMarkers(markers);
		ApplicationTemplate.insertBeforeCompass(m, markerLayer);
		
		pinButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MarkerAttributes attrs = new BasicMarkerAttributes(Material.YELLOW, BasicMarkerShape.CONE, 3d, 10, 5);
				View view = m.getView();
				Position position = view.getCurrentEyePosition();
				Marker marker = new BasicMarker(position, attrs);
				markers.add(marker);
				
			}
			
		});
		
		// Layer Oluşturulması
		ArrayList<String> layers = new ArrayList<String>();
		this.addRequiredLayers(layers);
		for (Layer layer : m.getModel().getLayers()) {
			//System.out.println(layer);
			for (String s : layers) {

				if (layer.getName().equals(s)) {
					LayerAction action = new LayerAction(layer, m, layer.isEnabled());
					JCheckBox jcb = new JCheckBox(action);
					jcb.setSelected(action.selected);
					layersPanel.add(jcb);
				}
			}
		}
		
		this.add(goPanel, BorderLayout.PAGE_START);
		this.add(layersPanel, BorderLayout.CENTER);
		this.add(pdpPanel, BorderLayout.LINE_START);
		this.add(bottomPanel, BorderLayout.PAGE_END);
	}

	public void addRequiredLayers(ArrayList<String> arrList) {
		arrList.add("Compass");
		arrList.add("Atmosphere");
		arrList.add("USGS Topo Base Map");
		arrList.add("Earth at Night");
		arrList.add("Scale bar");
		arrList.add("World Map");
		arrList.add("View Controls");
		arrList.add("Crosshairs");
		arrList.add("Marker Layer");		
	}

	protected static class LayerAction extends AbstractAction {
		WorldWindow wwd;
		protected Layer layer;
		protected boolean selected;

		public LayerAction(Layer layer, WorldWindow wwd, boolean selected) {
			super(layer.getName());
			this.wwd = wwd;
			this.layer = layer;
			this.selected = selected;
			this.layer.setEnabled(this.selected);
		}

		public void actionPerformed(ActionEvent actionEvent) {
			// Simply enable or disable the layer based on its toggle button.
			if (((JCheckBox) actionEvent.getSource()).isSelected())
				this.layer.setEnabled(true);
			else
				this.layer.setEnabled(false);

			wwd.redraw();
		}
	}

}
