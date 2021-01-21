package Game;
/**
 * @author Samuel & Kevin
 *
 *         'Main' class. This is the driver class for our PPA13 solution. It
 *         contains our 'main' method. This class is the only class that is to
 *         be compiled and run directly, as the compiler will automatically
 *         compile our other referenced classes. This class and its method are
 *         public, so are accessible anywhere.
 *         
 */
import Controller.ButtonController;
import Model.Model;
import View.MainFrame;

public class Game {

	public static void main(String[] args) {
		Model model = new Model();
		ButtonController controller = new ButtonController(model);
		MainFrame mf = new MainFrame(controller,model);
		model.addObserver(mf);


	}

}
