package Preliminary;

public class Aeroplane {
	private int xCoord, yCoord, speed, elevation;
	public final int RUNWAY_LENGTH = 100; // Length of Runway
	public final int RUNWAY_WIDTH = 10; // Width of Runway
	private int leftLength = RUNWAY_LENGTH; // Set the initial value of
											// leftLength
	private boolean takeOff;

	// Constructor to set Initial X and Y coordinates
	public Aeroplane(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}

	public boolean isTakeOff() {
		return takeOff;
	}
	
	public void resetFields(){
		yCoord=0;elevation=0;takeOff=false;
	}

	public void setTakeOff(boolean takeOff) {
		this.takeOff = takeOff;
	}

	// Speed getter method
	public int getSpeed() {
		return speed;
	}

	// Speed Setter method
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	// X coordinate Setter method
	public void setXCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	// X coordinate getter method
	public int getxCoord() {
		return xCoord;
	}

	// Y coordinates getter method
	public int getyCoord() {
		return yCoord;
	}

	// Elevation getter method
	public int getElevation() {
		return elevation;
	}

	// Variable to count how longs speed has exceeded enough to gain elevation
	private int elevationCounter = 0;

	// Update method which updates Y,X and Elevation of plane every second
	public void update() {
		yCoord += speed;
		leftLength -= speed;

		if (speed >= 10) {
			elevationCounter++;
			if (elevationCounter >= 5)
				elevation++;
		}

	}

	// Method to check if plane takes off or on
	public void takeOff() {
		takeOff = getLeftLength() <= 0;
	}

	// Method to get distance left from RUNWAY LNEGTH
	public int getLeftLength() {
		return leftLength;
	}

	// Overriding toString method
	public String toString() {
		return "X:" + xCoord + "  Y:" + yCoord + "  Speed:" + speed + "  Elevation:" + elevation;
	}
}
