package Preliminary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Controller implements ActionListener, ChangeListener {

	private Model model;

	public Controller(Model model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().contains("reset")) {
			model.resetOutput();
		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {

		Object source = e.getSource();

		if (source instanceof JSlider) {
			JSlider slider = (JSlider) source;
			String name = slider.getName();
			if ("speedSlider".equals(name)) {
				model.setAeroplaneSpeed(slider.getValue());
			} else if ("xCoordSlider".equals(name)) {
				model.setAeroplaneXCoord(slider.getValue());
			}
		}

	}

}
