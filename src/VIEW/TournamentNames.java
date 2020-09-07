package VIEW;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import Exceptions.NotFullTournamentException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TournamentNames extends AbstractView {
	private ArrayList<String> names;

	private Button nextButton;
	private TextField[] textFieldsNames;
	GridPane quarterPane;
	MatchGame[] quarterGames;

	public TournamentNames() {
		quarterPane = this.createGridPane();

		names = new ArrayList<String>();
		stage = new Stage();
		GridPane MainNamesPane = this.createGridPane();
		MainNamesPane.setAlignment(Pos.CENTER_LEFT);
		MainNamesPane.add(new Label("please enter the names of the particpants"), 0, 0);
		GridPane namesPane = this.createGridPane();
		textFieldsNames = new TextField[8];
		for (int i = 0; i < 8; i++) {
			namesPane.add(new Label("player " + (i + 1)), 0, i);
			textFieldsNames[i] = new TextField();
			namesPane.add(textFieldsNames[i], 1, i);
		}
		MainNamesPane.add(namesPane, 0, 1);
		GridPane ButtonPane = this.createGridPane();
		ButtonPane.add(new Label(), 0, 0);
		nextButton = new Button("lets Start !");

		ButtonPane.add(nextButton, 1, 0);
		ButtonPane.add(new Label(), 2, 0);
		MainNamesPane.add(ButtonPane, 0, 2);
		Scene namesScene = new Scene(MainNamesPane, 250, 400);
		stage.setScene(namesScene);

	}

	public void setOnActionButton(Stage nextStage, MatchGame[] matchGames, MainView view) {
		nextButton.setOnAction(new EventHandler<ActionEvent>() {
			boolean whileNotException = true;
			boolean emptyFields=false;
			String emptyFieldsText="";
			int result;

			boolean flag = true;
			int count = 0;

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				while (whileNotException) {
					try {
						emptyFields=false;
						emptyFieldsText="";

						for (int i = 0; i < 8; i++) {
							textFieldsNames[i].setText(textFieldsNames[i].getText().toString().trim());

							if (textFieldsNames[i].getText().isEmpty())
							{
								emptyFields=true;
								emptyFieldsText+="\nfield " + (i + 1) + " is empty";
							}

							names.add(textFieldsNames[i].getText().toString());
						}
						if(emptyFields==true)
							throw new NotFullTournamentException(emptyFieldsText);
						whileNotException = false;
					} catch (NotFullTournamentException e) {
						result = JOptionPane.showConfirmDialog(null, e.getMessage());
						names=new ArrayList<String>();
						if (result == 1) {
							JOptionPane.showMessageDialog(null, "have a good day");
							stage.close();

							return;
						} else {
							JOptionPane.showMessageDialog(null, "please enter 8 participants");

							return;
						}

					}
				}

				Collections.shuffle(names);

				for (int i = 0; i < 8; i++) {

					if (flag) {
						matchGames[count].setPlayer1Name(names.get(i));
						flag = false;
					} else {
						matchGames[count++].setPlayer2Name(names.get(i));
						flag = true;

					}

				}
				view.updateNames(names);

				stage.close();
				nextStage.show();
			}

		});

	}

	public GridPane getPane() {
		return quarterPane;
	}

	public MatchGame[] getMatches() {
		return quarterGames;
	}

	public ArrayList<String> getNames() {
		return names;
	}

}
