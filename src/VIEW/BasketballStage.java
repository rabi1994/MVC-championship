package VIEW;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Exceptions.BasketballException;
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

public class BasketballStage extends GameStage implements StageAble {

	public BasketballStage(int index) {
		super(index);
		// TODO Auto-generated constructor stub
	}

	public void buildStage(String type, String player1, String player2) {
		this.type = type;
		stage.setTitle(type+" game number "+(index+1));
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				// TODO Auto-generated method stub
				Platform.exit();
				

			}

		});
		player1Results = new ArrayList<String>();
		player2Results = new ArrayList<String>();
		GridPane mainPane = this.createGridPane();

		player1TextResults = new TextField[4];
		player2TextResults = new TextField[4];
		for (int i = 0; i < player1TextResults.length; i++) {
			player1TextResults[i] = new TextField();
			player2TextResults[i] = new TextField();
		}
		GridPane playersPane = this.createGridPane();
		playersPane.add(new Label(" "), 0, 0);

		Label player1Label = new Label(player1);
		Label player2Label = new Label(player2);
		player1Label.setMinWidth(80);
		player2Label.setMinWidth(80);
		playersPane.add(player1Label, 0, 1);
		playersPane.add(player2Label, 0, 2);
		mainPane.add(playersPane, 0, 0);
		GridPane firstHalfPane = this.createGridPane();
		firstHalfPane.add(new Label("1st round"), 0, 0);
		firstHalfPane.add(player1TextResults[0], 0, 1);
		firstHalfPane.add(player2TextResults[0], 0, 2);
		mainPane.add(firstHalfPane, 1, 0);
		GridPane secondHalfPane = this.createGridPane();
		secondHalfPane.add(new Label("2nd round"), 0, 0);

		secondHalfPane.add(player1TextResults[1], 0, 1);
		secondHalfPane.add(player2TextResults[1], 0, 2);
		mainPane.add(secondHalfPane, 2, 0);
		GridPane extraTimePane = this.createGridPane();
		extraTimePane.add(new Label("3th round"), 0, 0);

		extraTimePane.add(player1TextResults[2], 0, 1);
		extraTimePane.add(player2TextResults[2], 0, 2);
		extraTimePane.setVisible(true);
		mainPane.add(extraTimePane, 3, 0);
		GridPane penaltyPane = this.createGridPane();
		penaltyPane.add(new Label("4th round"), 0, 0);
		penaltyPane.setVisible(true);
		penaltyPane.add(player1TextResults[3], 0, 1);
		penaltyPane.add(player2TextResults[3], 0, 2);
		mainPane.add(penaltyPane, 4, 0);
		GridPane buttonPane = this.createGridPane();
		nextButton = new Button("done");

		buttonPane.add(nextButton, 0, 0);
		mainPane.add(buttonPane, 2, 1);

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

				// nextButton.setDisable(true);
				

				try {
					if (player1Results.size() != 4)
						throw new BasketballException("not a full game!");
					view.startGame(player1Results, player2Results, index);
					closeStage();

				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "please enter only numbers");

				} catch (TieException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());

				} catch (TennisException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FootballException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BasketballException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());

				}
			}
		});
	}

}
