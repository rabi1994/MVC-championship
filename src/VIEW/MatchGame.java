package VIEW;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;

public class MatchGame {

	private Path leftPath;
	private Path rightPath;
	private GridPane pane;
	private Label name1;
	private Label name2;
	private Button startGame;
	private StageAble gameStage;

	public MatchGame(String player1, String player2, int index) {

		pane = new GridPane();
		pane.setPadding(new Insets(10));
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(5);
		pane.setVgap(5);
		Path leftPath = this.createLeftPath();
		pane.add(leftPath, 0, 0);
		Path RightPath = this.createRightPath();
		pane.add(RightPath, 2, 0);
		GridPane namePane = new GridPane();
		namePane.setPadding(new Insets(10));
		namePane.setAlignment(Pos.CENTER_LEFT);
		namePane.setHgap(5);
		namePane.setVgap(5);
		name1 = new Label(player1);
		name1.setMinWidth(50);
		name1.setFont(new Font("Ariel", 14));
		pane.add(name1, 0, 1);
		name2 = new Label(player2);
		name2.setMinWidth(50);
		name2.setFont(new Font("Ariel", 14));

		namePane.add(new Label("\t"), 0, 0);
		namePane.add(name2, 1, 0);

		pane.add(namePane, 2, 1);
		startGame = new Button("start");
		pane.add(startGame, 1, 0);

	}

	public void setPlayer1Name(String player1) {
		name1.setText(player1);
	}

	public void setPlayer2Name(String player2) {
		name2.setText(player2);
	}

	public void disableButton() {
		startGame.setDisable(true);
	}

	public void enableButton() {
		startGame.setDisable(false);
	}

	public String getPlayer1Name() {
		return this.name1.getText().toString();
	}

	public String getPlayer2Name() {
		return this.name2.getText().toString();
	}

	public void ButtonSetOnAction(String type, MainView view, int index) {
		if (type == "Tennis")
			gameStage = new TennisStage(index);
		if (type == "Football")
			gameStage = new FootballStage(index);
		if (type == "Basketball")
			gameStage = new BasketballStage(index);

		startGame.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				gameStage.buildStage(type, name1.getText().toString(), name2.getText().toString());
				gameStage.setButton(view);
				startGame.setDisable(true);
			}

		});
	}

	public StageAble getGameStage() {
		return gameStage;
	}

	public GridPane getPane() {
		return pane;
	}

	public void winLeftPath() {
		leftPath.setStroke(Color.LIME);
		rightPath.setStroke(Color.RED);
	}

	public void winRightPath() {
		rightPath.setStroke(Color.LIME);
		leftPath.setStroke(Color.RED);
	}

	public Path createLeftPath() {
		MoveTo moveTo = new MoveTo(0, 50);
		LineTo line = new LineTo(0, 0);
		LineTo line2 = new LineTo(50, 0);
		leftPath = new Path();
		leftPath.getElements().add(moveTo);
		leftPath.getElements().addAll(line, line2);
		leftPath.setStrokeWidth(5);
		leftPath.setStroke(Color.BLACK);
		return leftPath;
	}

	public Path createRightPath() {
		MoveTo moveTo = new MoveTo(50, 50);
		LineTo line = new LineTo(50, 0);
		LineTo line2 = new LineTo(0, 0);
		rightPath = new Path();
		rightPath.getElements().add(moveTo);
		rightPath.getElements().addAll(line, line2);
		rightPath.setStrokeWidth(5);
		rightPath.setStroke(Color.BLACK);
		return rightPath;
	}

}
