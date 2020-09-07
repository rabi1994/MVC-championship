package VIEW;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import Exceptions.FootballException;
import Exceptions.TennisException;
import Exceptions.TieException;
import LISTENERS.ModelListener;
import LISTENERS.ViewListener;

import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainView extends AbstractView implements ViewListener {

	private String tournamentTypeName;
	private ArrayList<String> names;
	private MatchGame[] quarterGames;
	private MatchGame[] semiFinalGames;
	private MatchGame finalGame;
	private Stage gameStage;
	private TournamentNames tournamentNames;
	private TournamentType tournamentType;
	private ModelListener listener;
	private boolean flagFromQuarterToSemi;
	private boolean flagFromSemiToFinal;
	private Label winnerLabel;
	private Label tournamentTypeLabel;
	private Label[] winnersName;

	GridPane mainPane;

	public MainView(Stage stage) {
		this.stage = stage;
		stage.setTitle("Main Tournament event");
		quarterGames = new MatchGame[4];
		flagFromQuarterToSemi = true;
		flagFromSemiToFinal = true;
		tournamentTypeName = "";
		GridPane quarterPane = this.createGridPane();

		for (int i = 0; i < quarterGames.length; i++) {
			quarterGames[i] = new MatchGame("\t\t", "\t\t", i);

			quarterPane.add(quarterGames[i].getPane(), i, 0);
		}
		GridPane semiPane = this.createGridPane();
		semiFinalGames = new MatchGame[2];
		semiFinalGames[0] = new MatchGame("\t\t", "\t\t\t\t ", 0);
		semiFinalGames[0].disableButton();
		semiFinalGames[1] = new MatchGame("\t\t", "\t\t", 1);
		semiFinalGames[1].disableButton();

		semiPane.add(semiFinalGames[0].getPane(), 0, 0);
		semiPane.add(new Label("\t\t\t\t"), 1, 0);
		semiPane.add(semiFinalGames[1].getPane(), 2, 0);
		GridPane finalPane = this.createGridPane();
		finalGame = new MatchGame("\t\t", "\t\t", 0);
		finalGame.disableButton();
		finalPane.add(finalGame.getPane(), 0, 0);

		tournamentNames = new TournamentNames();
		tournamentNames.setOnActionButton(stage, quarterGames, this);

		Stage namesStage = tournamentNames.getStage();
		tournamentType = new TournamentType();
		tournamentType.setButtonAction(namesStage, quarterGames, this);

		mainPane = this.createGridPane();
		this.tournamentTypeName = tournamentType.getTournamentType();
		names = new ArrayList<String>();

		this.names = tournamentNames.getNames();
		GridPane headPane = this.createGridPane();
		winnerLabel = new Label();
		tournamentTypeLabel = new Label();
		this.setDropShadow(tournamentTypeLabel);
		headPane.add(tournamentTypeLabel, 1, 0);
		Label headTitle = new Label(Calendar.getInstance().get(Calendar.YEAR) + " tournament");
		headTitle.setFont(new Font("Ariel", 44));
		this.setDropShadow(headTitle);
		headPane.add(headTitle, 0, 0);

		headPane.add(winnerLabel, 2, 0);
		this.setDropShadow(winnerLabel);
		winnerLabel.setFont(new Font("Ariel", 24));
		winnerLabel.setTextFill(Color.LIME);
		winnersName = new Label[3];
		for (int i = 0; i < winnersName.length; i++) {
			if (i == 2)
				winnersName[i] = new Label("Tournament winner");
			else
				winnersName[i] = new Label("\twinners");

			this.setDropShadow(winnersName[i]);

		}
		winnersName[0].setTextFill(Color.ROYALBLUE);
		winnersName[1].setTextFill(Color.BLUE);
		winnersName[2].setTextFill(Color.DEEPSKYBLUE);

		mainPane.add(headPane, 0, 0);
		mainPane.add(finalPane, 0, 1);
		mainPane.add(semiPane, 0, 2);
		mainPane.add(quarterPane, 0, 3);
		for (int i = 0; i < winnersName.length; i++) {
			mainPane.add(winnersName[i], 1, winnersName.length - i);

		}
		Scene scene = new Scene(mainPane, 1200, 550);
		stage.setScene(scene);

		tournamentType.showStage();
	}

	

	public void from4To2() {
		for (int i = 0; i < 2; i++) {
			semiFinalGames[i].enableButton();
		}
	}

	public void from2To1() {
		finalGame.enableButton();
	}

	@Override
	public void setWinners(ArrayList<String> winnerName) {
		this.names = winnerName;

		if (flagFromQuarterToSemi) {

			for (int i = 0; i < winnerName.size(); i++) {
				if (winnerName.get(i) == quarterGames[i].getPlayer1Name())
					quarterGames[i].winLeftPath();
				else
					quarterGames[i].winRightPath();
			}
			flagFromQuarterToSemi = false;
			this.from4To2();
			for (int i = 0; i < semiFinalGames.length; i++) {
				semiFinalGames[i].setPlayer1Name(winnerName.get((i * 2)));
				semiFinalGames[i].setPlayer2Name(winnerName.get((i * 2 + 1)));
				semiFinalGames[i].ButtonSetOnAction(tournamentTypeName, this, i);
			}
		} else if (flagFromSemiToFinal) {
			flagFromSemiToFinal = false;
			this.from2To1();
			for (int i = 0; i < winnerName.size(); i++)
				if (winnerName.get(i) == semiFinalGames[i].getPlayer1Name())
					semiFinalGames[i].winLeftPath();
				else
					semiFinalGames[i].winRightPath();
			finalGame.setPlayer1Name(winnerName.get(0));
			finalGame.setPlayer2Name(winnerName.get(1));
			finalGame.ButtonSetOnAction(tournamentTypeName, this, 0);
		} else {
			if (winnerName.get(0) == finalGame.getPlayer1Name())
				finalGame.winLeftPath();
			else
				finalGame.winRightPath();
			winnerLabel.setText("winner : " + winnerName.get(0));
			JOptionPane.showMessageDialog(null, "our biggest winner is !!! " + winnerName.get(0) + "\nthe"
					+ this.tournamentTypeName + " tournament in " + Calendar.getInstance().get(Calendar.YEAR));
		}

	}

	public void registerListener(ModelListener listener) {
		// TODO Auto-generated method stub
		this.listener = listener;

	}

	@Override
	public void updateType(String type) {
		// TODO Auto-generated method stub
		this.tournamentTypeName = type;
		this.tournamentTypeLabel.setText(" " + type + " " + Calendar.getInstance().get(Calendar.YEAR));
		this.listener.updateType(type);
	}

	@Override
	public void updateNames(ArrayList<String> names) {
		// TODO Auto-generated method stub
		this.listener.updateNames(names);
	}

	@Override
	public void startGame(ArrayList<String> player1Results, ArrayList<String> player2Results, int index)
			throws TennisException, FootballException,TieException,NumberFormatException {
		// TODO Auto-generated method stub
		this.listener.playGame(player1Results, player2Results, index);
	}

	@Override
	public void updateWinnersList(String winner) {
		// TODO Auto-generated method stub
		String winners;
		if (flagFromQuarterToSemi) {
			winners = winnersName[0].getText().toString();
			winnersName[0].setText(winners + "\n\t" + winner);

		} else if (flagFromSemiToFinal) {
			winners = winnersName[1].getText().toString();
			winnersName[1].setText(winners + "\n\t" + winner);

		} else {
			winners = winnersName[2].getText().toString();
			winnersName[2].setText(winners + "\n\t" + winner);

		}
	}

}
