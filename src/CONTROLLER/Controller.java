package CONTROLLER;

import java.util.ArrayList;

import Exceptions.FootballException;
import Exceptions.NotFiveGamesException;
import Exceptions.TennisException;
import Exceptions.TieException;
import LISTENERS.ModelListener;
import LISTENERS.ViewListener;
import MODEL.Tournament;
import VIEW.MainView;

public class Controller implements ModelListener,ViewListener {
	private MainView view;
	private Tournament model;
	
	public Controller(MainView view,Tournament model) {
		this.view=view;
		this.model=model;
		this.view.registerListener(this);
		this.model.registerListener(this);
	}

	
	

	@Override
	public void setWinners(ArrayList<String> winnerName) {
		// TODO Auto-generated method stub
		this.view.setWinners(winnerName);
	}

	@Override
	public void updateType(String type) {
		// TODO Auto-generated method stub
		this.model.updateType(type);
	}

	@Override
	public void updateNames(ArrayList<String> names) {
		// TODO Auto-generated method stub
		this.model.updateNames(names);
	}

	@Override
	public void playGame(ArrayList<String> player1Results, ArrayList<String> player2Results,int index) throws TennisException, FootballException,TieException,NumberFormatException {
		// TODO Auto-generated method stub
		
		
		this.model.playGame(player1Results, player2Results, index);
	}

	@Override
	public void startGame(ArrayList<String> player1Results, ArrayList<String> player2Results, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateWinners(ArrayList<String> names) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateWinnersList(String winner) {
		// TODO Auto-generated method stub
		view.updateWinnersList(winner);
	}

	
	

	
	

}
