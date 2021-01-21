package Preliminary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

public class View extends JFrame implements Observer {

	private JTextArea textArea;
	private JSlider speedSlider, xCoordSlider;
	private JButton reset;
	
	

	// Creating an object of flightSimulator TO ONLY GET MAX XCOORDINATE SLIDER
	// VALUE
	// ACTUALLY DELETING IT WOULDNT AFFECT PROGRAM
	private Aeroplane aeroplane;
	private Controller controller;

	// Constructor to create our Frame
	public View(Controller controller, Aeroplane aeroplane) {
		super("Flight Simulator");
		this.controller = controller;
		this.aeroplane = aeroplane;
		this.setSize(700, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); // Make the frame to be centered in
											// the screen

		initWidget();

		reset.addActionListener(controller);
		speedSlider.addChangeListener(controller);
		xCoordSlider.addChangeListener(controller);
		

		setVisible(true);

	}

	// Method which creates Widget and set their layout and stick them to the
	// Frame
	public void initWidget() {
		JPanel panel1 = new JPanel(); // Create a new Panel
		panel1.setLayout(new GridLayout(1, 1)); // setLayout of the panel which
												// contains the TextArea
		textArea = new JTextArea();
		textArea.setEditable(false); // Disable Editable

		// Set Color of TextArea
		Color c = new Color(102, 198, 233);
		textArea.setBackground(c);

		// Make the textArea to display the last text as scroll goes down
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		reset = new JButton("reset");
		// Add Action Listener to reset button to reset all system as it's
		// clicked

		// create a vertical Slider to set the value of Speed
		speedSlider = new JSlider(JSlider.VERTICAL, 0, 10, 0);
		speedSlider.setName("speedSlider");

		// add the ticks on slider
		speedSlider.setMajorTickSpacing(2);
		speedSlider.setMinorTickSpacing(1);
		speedSlider.setPaintTicks(true);

		// create Slider to set the value of X Coordinate
		xCoordSlider = new JSlider(0, aeroplane.RUNWAY_WIDTH);
		xCoordSlider.setName("xCoordSlider");

		// add ticks on slider
		xCoordSlider.setMajorTickSpacing(2);
		xCoordSlider.setMinorTickSpacing(1);
		xCoordSlider.setPaintTicks(true);

		// add scroll on text area
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel1.add(scroll);

		// Create another panel to add Slider and reset button / setting
		// borderLayout
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		panel2.add(xCoordSlider, BorderLayout.NORTH);
		panel2.add(speedSlider, BorderLayout.CENTER);
		panel2.add(reset, BorderLayout.SOUTH);

		this.setLayout(new GridLayout(2, 1));
		this.add(panel1);
		this.add(panel2);

	}

	@Override
	public void update(Observable o, Object arg) {
	
		if (arg instanceof Boolean) {
			textArea.setText("");
			speedSlider.setValue(0);
			xCoordSlider.setValue(5);
		}
		else textArea.append((String) arg);

	}
}
