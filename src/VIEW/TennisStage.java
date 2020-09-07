package VIEW;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Exceptions.FieldIsEmptyException;
import Exceptions.FootballException;
import Exceptions.NotNumberException;
import Exceptions.TennisException;
import Exceptions.TieException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class TennisStage extends GameStage implements StageAble {

	public TennisStage(int index) {
		super(index);
	}

	public void buildStage(String type, String player1, String player2) {
		stage.setTitle(type+" game number "+(index+1));
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				// TODO Auto-generated method stub
				Platform.exit();
				

			}

		});

		this.type = type;
		player1Results = new ArrayList<String>();
		player2Results = new ArrayList<String>();
		GridPane mainPane = this.createGridPane();

		GridPane playersPane = this.createGridPane();
		Label player1Label = new Label(player1);
		Label player2Label = new Label(player2);
		player1Label.setMinWidth(80);
		player2Label.setMinWidth(80);

		playersPane.add(player1Label, 0, 1);
		playersPane.add(player2Label, 0, 2);
		player1TextResults = new TextField[5];
		player2TextResults = new TextField[5];
		for (int i = 0; i < player1TextResults.length; i++) {
			playersPane.add(new Label("round " + (i + 1)), (i + 1), 0);
			player1TextResults[i] = new TextField();
			player2TextResults[i] = new TextField();
			playersPane.add(player1TextResults[i], (i + 1), 1);
			playersPane.add(player2TextResults[i], (i + 1), 2);

		}
		mainPane.add(playersPane, 0, 0);
		GridPane buttonPane = this.createGridPane();
		nextButton = new Button("done");

		buttonPane.add(nextButton, 0, 0);
		mainPane.add(buttonPane, 0, 1);

		Scene scene = new Scene(mainPane, 600, 200);
		this.stage.setScene(scene);
		this.showStage();

	}

	public void setButton(MainView view) {

		nextButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				player1Results = new ArrayList<String>();
				player2Results = new ArrayList<String>();
				int textFieldsNumber = 0;
				for (int i = 0; i < player1TextResults.length; i++) {
					player1TextResults[i].setText(player1TextResults[i].getText().toString().trim());
					player2TextResults[i].setText(player2TextResults[i].getText().toString().trim());

					if (player1TextResults[i].getText().isEmpty())
						break;
					else
						textFieldsNumber++;
				}
				for (int i = 0; i < textFieldsNumber; i++) {

					try {
						if (player1TextResults[i].getText().isEmpty() || player2TextResults[i].getText().isEmpty())
							throw new FieldIsEmptyException("one of the fields is empty");
						if (!((isNumeric(player1TextResults[i].getText().toString()))
								|| isNumeric(player2TextResults[i].getText().toString())))
							throw new NotNumberException("one of the fields dont have a number");
					} catch (FieldIsEmptyException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						return;
					} catch (NotNumberException e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						return;
					}
					player1Results.add(player1TextResults[i].getText().toString());
					player2Results.add(player2TextResults[i].getText().toString());

				}


				try {
					view.startGame(player1Results, player2Results, index);
					closeStage();
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "please enter only numbers");

				} catch (TennisException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());

				} catch (TieException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());

				} catch (FootballException e) {

				}

			}
		});
	}
}
