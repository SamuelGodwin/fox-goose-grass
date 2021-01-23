package Model;

import java.util.ArrayList;

import javax.swing.JLabel;

public class Boat {
	
	private int position;
	private boolean farmerInside;
	private JLabel jLabel;
	private ArrayList<Element> onBoard;
	
	public Boat(){
		onBoard = new ArrayList<Element>();
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isFarmerInside() {
		return farmerInside;
	}

	public void setFarmerInside(boolean farmerInside) {
		this.farmerInside = farmerInside;
	}

	public JLabel getjLabel() {
		return jLabel;
	}

	public void setjLabel(JLabel jLabel) {
		this.jLabel = jLabel;
	}
	
	public void getInBoat(Element element){
		onBoard.add(element);
	}
	public void getOffBoat(Element element){
		onBoard.remove(element);
	}
	public ArrayList<Element> getElementArrayList(){
		return onBoard;
	}
	
	

}
