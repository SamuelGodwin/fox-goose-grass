package Model;

/**
 * @author Samuel
 *
 *         This class models the Model in my solution. It features fields of final int data type. 
 *         These fields are used as parameters for setting an icon's location, as well as for other
 *         calculations. This class and its methods are public, so are accessible anywhere.
 *         
 */

import java.util.ArrayList;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import View.MainFrame.EastWaterPanel;

public class Model extends Observable {

	public final int EAST_PANEL = 150, EAST_WATER_PANEL = 100, WEST_WATER_PANEL = 50, WEST_PANEL = 0, LEFT = -10,
			RIGHT = 10, BOAT_LEFT = -1, BOAT_RIGHT = 1, FOX_LEFT = -2, FOX_RIGHT = 2, GOOSE_LEFT = -3, GOOSE_RIGHT = 3,
			BEANS_LEFT = -4, BEANS_RIGHT = 4, FARMER_LEFT = -5, FARMER_RIGHT = 5, NEGATIVE_SCORE = -1;

	private ArrayList<JLabel> eastPanel, eastWaterPanel, westWaterPanel, westPanel;

	private int score;
	private boolean gameOver;
	private String status,gameStatusScript;
	Boat boat;
	Element farmer, fox, goose, beans;

	ImageIcon boatimg = new ImageIcon("Images/boat.png");
	ImageIcon farmerimg = new ImageIcon("Images/farmer.png");
	ImageIcon beansimg = new ImageIcon("Images/beans.png");
	ImageIcon foximg = new ImageIcon("Images/fox.png");
	ImageIcon gooseimg = new ImageIcon("Images/goose.png");

	JLabel boatIcon = new JLabel(boatimg);
	JLabel farmerIcon = new JLabel(farmerimg);
	JLabel beansIcon = new JLabel(beansimg);
	JLabel foxIcon = new JLabel(foximg);
	JLabel gooseIcon = new JLabel(gooseimg);

	public Model() {
		createObjects();
		eastPanel = new ArrayList<JLabel>();
		eastWaterPanel = new ArrayList<JLabel>();
		westWaterPanel = new ArrayList<JLabel>();
		westPanel = new ArrayList<JLabel>();
		updateAllPositions();
	}

	private void createObjects() {

		boat = new Boat();
		boat.setjLabel(boatIcon);
		boat.setPosition(EAST_WATER_PANEL);

		fox = new Element();
		fox.setjLabel(foxIcon);
		fox.setPosition(EAST_PANEL);

		goose = new Element();
		goose.setjLabel(gooseIcon);
		goose.setPosition(EAST_PANEL);

		beans = new Element();
		beans.setjLabel(beansIcon);
		beans.setPosition(EAST_PANEL);

		farmer = new Element();
		farmer.setjLabel(farmerIcon);
		farmer.setPosition(EAST_PANEL);

	}

	public void setGameStatus() {
		String str = Integer.toString(getScore());
		String gameOverStatus = "";
		if (gameOver){
			gameOverStatus = "Game Over";
			str = "GAME OVER";
		}
		gameStatusScript = "Score : " + str;
		setChanged();
		notifyObservers();
	}
	
	public String getGameStatus(){
		return gameStatusScript;
	}

	private void scoreCounter() {
		score += NEGATIVE_SCORE;
	}

	public int getScore() {
		return score;
	}

	public void getButtonPressed(int order) {

		switch (order) {

		case BOAT_LEFT:
			moveBoat(LEFT);
			break;

		case BOAT_RIGHT:
			moveBoat(RIGHT);
			break;

		case FARMER_LEFT:
			moveFarmer(LEFT);
			break;

		case FARMER_RIGHT:
			moveFarmer(RIGHT);
			break;

		case FOX_LEFT:
			moveFox(LEFT);
			break;

		case FOX_RIGHT:
			moveFox(RIGHT);
			break;

		case GOOSE_LEFT:
			moveGoose(LEFT);
			break;

		case GOOSE_RIGHT:
			moveGoose(RIGHT);
			break;

		case BEANS_LEFT:
			moveBeans(LEFT);
			break;

		case BEANS_RIGHT:
			moveBeans(RIGHT);
			break;

		}

		gameStatus();
		setGameStatus();
		updateAllPositions();

		setChanged();
		notifyObservers();

	}

	public void moveBoat(int order) {

		if (boat.isFarmerInside() && boat.getElementArrayList().size() <= 2) {

			if (order == LEFT) {
				if (boat.getPosition() == EAST_WATER_PANEL) {
					boat.setPosition(WEST_WATER_PANEL);
					for (Element element : boat.getElementArrayList()) {
						eastWaterPanel.remove(element.getjLabel());
						element.setPosition(WEST_WATER_PANEL);
					}
					eastWaterPanel.remove(boat.getjLabel());
					scoreCounter();
				}
			} else if (order == RIGHT) {
				if (boat.getPosition() == WEST_WATER_PANEL ) {
					westWaterPanel.remove(boat.getjLabel());
					boat.setPosition(EAST_WATER_PANEL);
					for (Element element : boat.getElementArrayList()) {
						westWaterPanel.remove(element.getjLabel());
						element.setPosition(EAST_WATER_PANEL);
					}
					
					scoreCounter();

				}
			}
		}
		
		updateAllPositions();
	}

	
	public void moveFarmer(int order) {

		if (order == LEFT) {
			if (boat.isFarmerInside()) {
				if (farmer.getPosition() == WEST_WATER_PANEL) {
					farmer.setPosition(WEST_PANEL);
					westWaterPanel.remove(farmer.getjLabel());
					boat.getElementArrayList().remove(farmer);
					boat.setFarmerInside(false);
				}
			} else if (!boat.isFarmerInside()) {
				if (farmer.getPosition() == EAST_PANEL) {
					farmer.setPosition(EAST_WATER_PANEL);
					boat.getElementArrayList().add(farmer);
					boat.setFarmerInside(true);
					eastPanel.remove(farmer.getjLabel());
				}
			}

		}

		else if (order == RIGHT) {

			if (boat.isFarmerInside()) {
				
				if (farmer.getPosition() == EAST_WATER_PANEL) {
					farmer.setPosition(EAST_PANEL);
					eastWaterPanel.remove(farmer.getjLabel());
					boat.getElementArrayList().remove(farmer);
					boat.setFarmerInside(false);
				}
			} else if (!boat.isFarmerInside()) {
				if (farmer.getPosition() == WEST_PANEL) {
					farmer.setPosition(WEST_WATER_PANEL);
					boat.getElementArrayList().add(farmer);
					boat.setFarmerInside(true);
					westPanel.remove(farmer.getjLabel());
				}
			}
		}

		updateAllPositions();
	}

	
	public void moveFox(int order) {

		if (order == LEFT) {
			if (fox.getPosition() == WEST_WATER_PANEL) {
				fox.setPosition(WEST_PANEL);
				westWaterPanel.remove(fox.getjLabel());
				boat.getElementArrayList().remove(fox);
			} else if (fox.getPosition() == EAST_PANEL && boat.getPosition() == EAST_WATER_PANEL) {
				fox.setPosition(EAST_WATER_PANEL);
				boat.getElementArrayList().add(fox);
				eastPanel.remove(fox.getjLabel());
			}

		}

		else if (order == RIGHT) {
			if (fox.getPosition() == EAST_WATER_PANEL) {
				fox.setPosition(EAST_PANEL);
				eastWaterPanel.remove(fox.getjLabel());
				boat.getElementArrayList().remove(fox);
			} else if (fox.getPosition() == WEST_PANEL && boat.getPosition() == WEST_WATER_PANEL) {
				westPanel.remove(fox.getjLabel());
				fox.setPosition(WEST_WATER_PANEL);
				boat.getElementArrayList().add(fox);
			}
		}
		
		updateAllPositions();

	}

	public void moveGoose(int order) {

		if (order == LEFT) {
			if (goose.getPosition() == WEST_WATER_PANEL) {
				goose.setPosition(WEST_PANEL);
				westWaterPanel.remove(goose.getjLabel());
				boat.getElementArrayList().remove(goose);
			} else if (goose.getPosition() == EAST_PANEL && boat.getPosition() == EAST_WATER_PANEL) {
				goose.setPosition(EAST_WATER_PANEL);
				boat.getElementArrayList().add(goose);
				eastPanel.remove(goose.getjLabel());
			}

		}

		else if (order == RIGHT) {
			if (goose.getPosition() == EAST_WATER_PANEL) {
				goose.setPosition(EAST_PANEL);
				eastWaterPanel.remove(goose.getjLabel());
				boat.getElementArrayList().remove(goose);
			} else if (goose.getPosition() == WEST_PANEL && boat.getPosition() == WEST_WATER_PANEL) {
				westPanel.remove(goose.getjLabel());
				goose.setPosition(WEST_WATER_PANEL);
				boat.getElementArrayList().add(goose);
			}
		}
		
		updateAllPositions();
	}

	public void moveBeans(int order) {

		if (order == LEFT) {
			if (beans.getPosition() == WEST_WATER_PANEL) {
				westWaterPanel.remove(beans.getjLabel());
				beans.setPosition(WEST_PANEL);
				boat.getElementArrayList().remove(beans);
			} else if (beans.getPosition() == EAST_PANEL && boat.getPosition() == EAST_WATER_PANEL) {
				beans.setPosition(EAST_WATER_PANEL);
				boat.getElementArrayList().add(beans);
				eastPanel.remove(beans.getjLabel());
			}

		}

		else if (order == RIGHT) {
			if (beans.getPosition() == EAST_WATER_PANEL) {
				beans.setPosition(EAST_PANEL);
				eastWaterPanel.remove(beans.getjLabel());
				boat.getElementArrayList().remove(beans);
			} else if (beans.getPosition() == WEST_PANEL && boat.getPosition() == WEST_WATER_PANEL) {	
				westPanel.remove(beans.getjLabel());
				beans.setPosition(WEST_WATER_PANEL);
				boat.getElementArrayList().add(beans);
			}
		}
		
		updateAllPositions();
	}
	

	
	public void updateAllPositions() {

		if (farmer.getPosition() == EAST_PANEL) {
			if (!eastPanel.contains(farmer.getjLabel()))
				eastPanel.add(farmer.getjLabel());
		}

		else if (farmer.getPosition() == WEST_PANEL) {
			if (!westPanel.contains(farmer.getjLabel()))
				westPanel.add(farmer.getjLabel());
		}

		if (fox.getPosition() == EAST_PANEL) {
			if (!eastPanel.contains(fox.getjLabel()))
				eastPanel.add(fox.getjLabel());
		}

		else if (fox.getPosition() == WEST_PANEL) {
			if (!westPanel.contains(fox.getjLabel()))
				westPanel.add(fox.getjLabel());
		}

		if (goose.getPosition() == EAST_PANEL) {
			if (!eastPanel.contains(goose.getjLabel()))
				eastPanel.add(goose.getjLabel());
		}

		else if (goose.getPosition() == WEST_PANEL) {
			if (!westPanel.contains(goose.getjLabel()))
				westPanel.add(goose.getjLabel());
		}

		if (beans.getPosition() == EAST_PANEL) {
			if (!eastPanel.contains(beans.getjLabel()))
				eastPanel.add(beans.getjLabel());
		}

		else if (beans.getPosition() == WEST_PANEL) {
			if (!westPanel.contains(beans.getjLabel()))
				westPanel.add(beans.getjLabel());
		}

		
		if (boat.getPosition() == EAST_WATER_PANEL) {
			if (!eastWaterPanel.contains(boat.getjLabel()))
				eastWaterPanel.add(boat.getjLabel());
			for (Element element : boat.getElementArrayList()) {
				westWaterPanel.remove(element.getjLabel());
				if (!eastWaterPanel.contains(element.getjLabel())){
					eastWaterPanel.add(element.getjLabel());
				}
			}
		} else if (boat.getPosition() == WEST_WATER_PANEL) {
			if (!westWaterPanel.contains(boat.getjLabel()))
				westWaterPanel.add(boat.getjLabel());
			for (Element element : boat.getElementArrayList()) {
				eastWaterPanel.remove(element.getjLabel());
				if (!westWaterPanel.contains(element.getjLabel()))
					westWaterPanel.add(element.getjLabel());
			}
		}

	}

	public ArrayList<JLabel> getEastPanelIcons() {
		return eastPanel;
	}

	public ArrayList<JLabel> getEastWaterPanelIcons() {
		return eastWaterPanel;
	}

	public ArrayList<JLabel> getWestWaterPanelIcons() {
		return westWaterPanel;
	}

	public ArrayList<JLabel> getWestPanelIcons() {
		return westPanel;
	}

	public void gameStatus() {
		
		if (eastPanel.contains(fox.getjLabel()) && eastPanel.contains(goose.getjLabel())
				&& !eastPanel.contains(farmer.getjLabel()) && !eastWaterPanel.contains(farmer.getjLabel())) {
			status = "Fox ate Goose";
			gameOver = true;

		} else if (eastPanel.contains(goose.getjLabel()) && eastPanel.contains(beans.getjLabel())
				&& !eastPanel.contains(farmer.getjLabel()) && !eastWaterPanel.contains(farmer.getjLabel())) {
			status = "Goose ate beans";
			gameOver = true;

		} else if (westPanel.contains(fox.getjLabel()) && westPanel.contains(goose.getjLabel())
				&& westPanel.contains(farmer.getjLabel()) && !westWaterPanel.contains(farmer.getjLabel())) {
			status="Fox ate goose";
			gameOver = true;

		} else if (westPanel.contains(goose.getjLabel()) && westPanel.contains(beans.getjLabel())
				&& !westPanel.contains(farmer.getjLabel()) && !westWaterPanel.contains(farmer.getjLabel())) {
			status = "Goose ate beans";
			gameOver = true;

		}

	}
	
	public boolean getGameOver(){
		return gameOver;
	}

}
