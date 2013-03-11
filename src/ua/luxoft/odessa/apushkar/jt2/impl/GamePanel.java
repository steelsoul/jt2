package ua.luxoft.odessa.apushkar.jt2.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

import ua.luxoft.odessa.apushkar.jt2.board.impl.Board;
import ua.luxoft.odessa.apushkar.jt2.figure.impl.TetrisFigure;
import ua.luxoft.odessa.apushkar.jt2.figureStrategies.impl.BoxStrategy;

public class GamePanel extends JPanel implements ActionListener, Observer{
	public static enum GameState {START, PAUSE, STOP};

	private static final long serialVersionUID = 1L;
	
	private GameState mGameState;
	private Board mBoard;
	private Timer mTimer;
	private TetrisFigure mFigure, mFigureNext;
	private SplashScreen mSplashScreen;

	public GamePanel() {
		// at the start of the program splash screen is always shown
		setBorder(BorderFactory.createLineBorder(Color.black));
		setDoubleBuffered(true);
		mGameState = GameState.STOP;
		mBoard = new Board();
		mSplashScreen = new SplashScreen();
		mSplashScreen.addObserver(this);
	}
	
	public void startGame()	{
		if (mGameState != GameState.START)
		{
			mSplashScreen.stopShow();
			mGameState = GameState.START;
			mTimer = new Timer(500, this);
			mTimer.start();
			mFigure = new TetrisFigure(new BoxStrategy(), mBoard);
			repaint();
		}
	}
	
	public void startSplash() {
		if (mGameState != GameState.STOP)
		{
			mTimer.stop();
			mSplashScreen.startShow(this);
			mGameState = GameState.STOP;			
		}
	}
	
	
	public Dimension getPrefferedSize() {
		return new Dimension(320, 200);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		int sizeW = getWidth()/Board.WIDTH;
		int sizeH = getHeight()/Board.HEIGHT;
		int size = sizeW < sizeH ? sizeW : sizeH;
		int offsetH = (getHeight() - size*Board.HEIGHT)/2;

		switch (mGameState)
		{
		case STOP:
			mSplashScreen.draw(g, getWidth()/size, getHeight()/size, size);
			break;
		case PAUSE:
			break;
		case START:
			mFigure.draw(g, offsetH, size);
			mBoard.draw(g, offsetH, size);
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		repaint();
	}
}
