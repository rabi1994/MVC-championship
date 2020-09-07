package Main;

import CONTROLLER.Controller;
import MODEL.Tournament;
import VIEW.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
// id :312327331 rabi rabi 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch();
	}

	@Override
	public void start(Stage myStage) throws Exception {
		// TODO Auto-generated method stub

		MainView main = new MainView(myStage);
		Tournament tournament = new Tournament();
		Controller controller = new Controller(main, tournament);
	}

}
