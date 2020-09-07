package VIEW;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TournamentType extends AbstractView{
	private String TournamentType;
	private Button confirmButton;
	private RadioButton tennisButton;
	private RadioButton footballButton;
	private RadioButton basketballButton ;

	public  TournamentType() {
		 stage = new Stage();
		GridPane typePane = this.createGridPane();
		typePane.add(new Label(), 0, 0);
		typePane.add(new Label("Tournament Type"), 1, 0);
		typePane.add(new Label(), 2, 0);
		ToggleGroup group = new ToggleGroup();
		 tennisButton = new RadioButton("Tennis");
		 footballButton = new RadioButton("Football");
		 basketballButton = new RadioButton("Basketball");
		tennisButton.setToggleGroup(group);
		footballButton.setToggleGroup(group);
		basketballButton.setToggleGroup(group);
		typePane.add(new Label(), 0, 1);
		typePane.add(new Label("please choose tournament type"), 1, 1);
		typePane.add(new Label(), 2, 1);

		GridPane radioPane =this.createGridPane();
		radioPane.add(basketballButton, 0, 0);
		radioPane.add(footballButton, 1, 0);
		radioPane.add(tennisButton, 2, 0);
		typePane.add(radioPane, 1, 2);
		 confirmButton = new Button("lets go!");
		

		GridPane middleButtonPane = this.createGridPane();
		middleButtonPane.add(new Label(), 0, 0);
		middleButtonPane.add(confirmButton, 0, 1);
		middleButtonPane.add(new Label(), 0, 2);

		typePane.add(middleButtonPane, 1, 3);
		Scene typeScence = new Scene(typePane, 400, 200);
		stage.setScene(typeScence);
	}
	
	public void setButtonAction(Stage nextStage,MatchGame[] games,MainView view) {
		confirmButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (basketballButton.isSelected() || tennisButton.isSelected() || footballButton.isSelected()) {
					if (basketballButton.isSelected())
						TournamentType = "Basketball";
					if (tennisButton.isSelected())
						TournamentType = "Tennis";
					if (footballButton.isSelected())
						TournamentType = "Football";
					for (int i = 0; i < games.length; i++) {
						games[i].ButtonSetOnAction(TournamentType,view,i);
					}
					view.updateType(TournamentType);
					nextStage.show();
					stage.close();
				}
				else {
					JOptionPane.showMessageDialog(null, "please choose tournament type");
				}
			}

		});
	}
	
	public String getTournamentType() {
		return TournamentType;
	}
	public void setTournamentType(String tournamentType) {
		TournamentType = tournamentType;
	}

}
