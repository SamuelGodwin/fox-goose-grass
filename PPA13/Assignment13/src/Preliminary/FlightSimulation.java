package Preliminary;

public class FlightSimulation {

	public static void main(String[] args) {

		Aeroplane aeroplane = new Aeroplane(5, 0);
		Model model = new Model(aeroplane);
		Controller controller = new Controller(model);
		View view = new View(controller, aeroplane);

		model.addObserver(view);
		model.run();

	}

}
