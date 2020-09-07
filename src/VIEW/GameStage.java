package VIEW;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.junit.platform.commons.util.StringUtils;

import Exceptions.BasketballException;
import Exceptions.FieldIsEmptyException;
import Exceptions.FootballException;
import Exceptions.NotFiveGamesException;
import Exceptions.NotNumberException;
import Exceptions.TennisException;
import Exceptions.TieException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameStage extends AbstractView implements StageAble {
	protected ArrayList<String> player1Results;
	protected ArrayList<String> player2Results;
	protected Button nextButton;
	protected TextField[] player1TextResults;
	protected TextField[] player2TextResults;
	protected int index;
	protected String type;

	public GameStage(int index) {
		stage = new Stage();
		this.index = index;
	}

	public void buildStage(String type, String player1, String player2) {

		
	}

	public void setButton(MainView view) {

	
	}

	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
