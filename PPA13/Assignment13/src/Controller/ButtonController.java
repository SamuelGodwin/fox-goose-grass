package Controller;
/**
 * @author Samuel & Kevin
 * 
 *         This class models the Controller in our solution. It contains a reference to Model and actionPerformed methods
 *         for each of the buttons in our View. This class and its
 *         methods are public, so are accessible anywhere.
 *
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JSlider;

import Model.Model;

public class ButtonController implements ActionListener {

	Model model;

	public ButtonController(Model model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().contains("boatLeft")) {
			model.getButtonPressed(model.BOAT_LEFT);
			
		}

		else if (e.getActionCommand().contains("boatRight")) {
			model.getButtonPressed(model.BOAT_RIGHT);
		}

		else if (e.getActionCommand().contains("foxLeft")) {
			model.getButtonPressed(model.FOX_LEFT);
		}

		else if (e.getActionCommand().contains("foxRight")) {
			model.getButtonPressed(model.FOX_RIGHT);
		}

		else if (e.getActionCommand().contains("gooseLeft")) {
			model.getButtonPressed(model.GOOSE_LEFT);
		}

		else if (e.getActionCommand().contains("gooseRight")) {
			model.getButtonPressed(model.GOOSE_RIGHT);
		}

		else if (e.getActionCommand().contains("beansLeft")) {
			model.getButtonPressed(model.BEANS_LEFT);
			System.out.println("beansLeft");
		}

		else if (e.getActionCommand().contains("beansRight")) {
			model.getButtonPressed(model.BEANS_RIGHT);
		}

		else if (e.getActionCommand().contains("farmerLeft")) {
			model.getButtonPressed(model.FARMER_LEFT);
		}

		else if (e.getActionCommand().contains("farmerRight")) {
			model.getButtonPressed(model.FARMER_RIGHT);
		}

	}

}
