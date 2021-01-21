package View;

/**
 * @author Samuel & Kevin
 * 
 *         This class models the View in our MVC solution. It information for widgets and setting up the GUI.
 *         The space for movement in game is divided into a WestPanel, EastPanel, SouthPanel and a waterPanel
 *         containing a WestWaterPanel & EastWaterPanel. The LayoutManager for our main frame is therefore BorderLayout.
 *         This class and its methods are public, so are accessible anywhere.
 *
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.ButtonController;
import Model.Model;

public class MainFrame extends JFrame implements Observer {

	ButtonController controller;
	Model model;

	ArrayList<JLabel> epA, ewpA, wwpA, wpA;

	WestPanel wp;
	JPanel waterPanel;
	WestWaterPanel wwp;
	EastWaterPanel ewp;
	EastPanel ep;
	SouthPanel sp;

	public MainFrame(ButtonController controller, Model model) {

		super("");
		this.controller = controller;
		this.model = model;

		setLayout(new BorderLayout());
		wp = new WestPanel();
		waterPanel = new JPanel();
		wwp = new WestWaterPanel();
		ewp = new EastWaterPanel();
		ep = new EastPanel();
		sp = new SouthPanel();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1050, 200));
		addPanels();
		pack();
		setPosition();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public void setPosition() {

		for (JLabel label : model.getEastPanelIcons()) {
			ep.add(label);

		}

		for (JLabel label : model.getEastWaterPanelIcons()) {
			ewp.add(label);
		}

		for (JLabel label : model.getWestWaterPanelIcons()) {
			wwp.add(label);
		}

		for (JLabel label : model.getWestPanelIcons()) {
			wp.add(label);
		}
	}

	private void addPanels() {

		wp.setPreferredSize(new Dimension(170, 500));
		add(wp, BorderLayout.LINE_START);

		waterPanel.setLayout(new GridLayout(1, 2));
		waterPanel.setPreferredSize(new Dimension(650, 500));
		waterPanel.add(wwp);
		waterPanel.add(ewp);

		add(waterPanel, BorderLayout.CENTER);
		ep.setPreferredSize(new Dimension(170, 500));

		ep.setLayout(new BoxLayout(ep, BoxLayout.Y_AXIS));

		add(ep, BorderLayout.LINE_END);

		add(sp, BorderLayout.SOUTH);
		
		//setPosition();

	}

	public class EastPanel extends JPanel {

		public ImageIcon grass = new ImageIcon("Images/grass.png");

		@Override
		protected void paintComponent(Graphics g) {

			super.paintComponent(g);
			grass.paintIcon(this, g, 0, 0);
		}

	}

	public class EastWaterPanel extends JPanel {

		public ImageIcon water = new ImageIcon("Images/water.png");

		@Override
		protected void paintComponent(Graphics g) {

			super.paintComponent(g);
			water.paintIcon(this, g, 0, 0);
		}

	}

	public class SouthPanel extends JPanel {

		JLabel boatL = new JLabel("Boat:");
		JButton boatLeft = new JButton("<");
		JButton boatRight = new JButton(">");
		

		JLabel foxL = new JLabel("Fox:");
		JButton foxLeft = new JButton("<");
		JButton foxRight = new JButton(">");

		JLabel gooseL = new JLabel("Goose:");
		JButton gooseLeft = new JButton("<");
		JButton gooseRight = new JButton(">");

		JLabel beansL = new JLabel("Beans:");
		JButton beansLeft = new JButton("<");
		JButton beansRight = new JButton(">");

		JLabel farmerL = new JLabel("Farmer:");
		JButton farmerLeft = new JButton("<");
		JButton farmerRight = new JButton(">");

		public SouthPanel() {
			setLayout(new FlowLayout());
			addComponents();
			boatLeft.addActionListener(controller);
			boatLeft.setActionCommand("boatLeft");
			boatRight.addActionListener(controller);
			boatRight.setActionCommand("boatRight");

			foxLeft.addActionListener(controller);
			foxLeft.setActionCommand("foxLeft");
			foxRight.addActionListener(controller);
			foxRight.setActionCommand("foxRight");

			gooseLeft.addActionListener(controller);
			gooseLeft.setActionCommand("gooseLeft");
			gooseRight.addActionListener(controller);
			gooseRight.setActionCommand("gooseRight");

			beansLeft.addActionListener(controller);
			beansLeft.setActionCommand("beansLeft");
			beansRight.addActionListener(controller);
			beansRight.setActionCommand("beansRight");

			farmerLeft.addActionListener(controller);
			farmerLeft.setActionCommand("farmerLeft");
			farmerRight.addActionListener(controller);
			farmerRight.setActionCommand("farmerRight");

		}

		private void addComponents() {
			add(boatL);
			add(boatLeft);
			add(boatRight);

			add(foxL);
			add(foxLeft);
			add(foxRight);

			add(gooseL);
			add(gooseLeft);
			add(gooseRight);

			add(beansL);
			add(beansLeft);
			add(beansRight);

			add(farmerL);
			add(farmerLeft);
			add(farmerRight);

		}
	}

	public class WestPanel extends JPanel {

		public ImageIcon grass = new ImageIcon("Images/grass.png");

		@Override
		protected void paintComponent(Graphics g) {

			super.paintComponent(g);
			grass.paintIcon(this, g, 0, 0);
		}

	}

	public class WestWaterPanel extends JPanel {

		public ImageIcon water = new ImageIcon("Images/water.png");

		@Override
		protected void paintComponent(Graphics g) {

			super.paintComponent(g);
			water.paintIcon(this, g, 0, 0);
		}

	}
	
	

	@Override
	public void update(Observable o, Object arg) {

		epA = new ArrayList<JLabel>(); // Contains Icons should appear on East
										// Panel at any moment
		ewpA = new ArrayList<JLabel>(); // Contains Icons should appear on East
										// Water Panel at any moment
		wwpA = new ArrayList<JLabel>(); // Contains Icons should appear on West
										// Water Panel at any moment
		wpA = new ArrayList<JLabel>(); // Contains Icons should appear on West
			
		if(model.getGameOver()) this.disable();
		
		setTitle(model.getGameStatus());

		setPosition();

		repaint();
	}

}
