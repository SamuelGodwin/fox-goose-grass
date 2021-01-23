package Preliminary;

import java.util.Observable;

public class Model extends Observable {

	Aeroplane aeroplane;
	private String output;
	private int seconds;
	private boolean reset=false;

	public Model(Aeroplane aeroplane) {
		this.aeroplane = aeroplane;
	}

	public void setAeroplaneSpeed(int speed){
		aeroplane.setSpeed(speed);
	}
	
	public void setAeroplaneXCoord(int xCoord){
		aeroplane.setXCoord(xCoord);
	}
	
	// Update String output
	public void printOutput() {
		aeroplane.takeOff();

		if (aeroplane.isTakeOff())
			if (aeroplane.getElevation() >= 5 && aeroplane.getxCoord() == 5)
				output += "\n Plane in air";
			else
				output += "\n Take off Failed";
		else
			output = "Second : " + seconds + "\n" + aeroplane.toString() + "\n";

	}

	public void run() {
		while (true) {
			try {
				if (!aeroplane.isTakeOff()) {
					seconds++;
					aeroplane.update();
					setOutput();
				}

				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}

	// This method invoked when reset button is pressed in order to reset
	// content of Text Area
	public void resetOutput() {
		this.seconds = 0;
		aeroplane = new Aeroplane(5,0);
		this.reset = true;
		setChanged();
		notifyObservers(reset);
	}

	// Method which invoke printOutput to update output
	public void setOutput() {
		printOutput();
		setChanged();
		notifyObservers(output);

	}

}
