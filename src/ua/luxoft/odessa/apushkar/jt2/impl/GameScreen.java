package ua.luxoft.odessa.apushkar.jt2.impl;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import ua.luxoft.odessa.apushkar.jt2.api.IFigure;
import ua.luxoft.odessa.apushkar.jt2.api.IKeyObserver;
import ua.luxoft.odessa.apushkar.jt2.board.impl.Board;
import ua.luxoft.odessa.apushkar.jt2.board.impl.InfoTable;
import ua.luxoft.odessa.apushkar.jt2.figure.impl.Box;
import ua.luxoft.odessa.apushkar.jt2.figure.impl.Line;
import ua.luxoft.odessa.apushkar.jt2.figure.impl.TetrisFigure;

/**
 *  This is the place where all game elements will be shown.
 * */
public class GameScreen extends Canvas implements ActionListener, IKeyObserver {
	private static final long serialVersionUID = 1L;
	
	private Boolean mPaused;
	private Board mBoard;
	private TetrisFigure mFigure;
	private TetrisFigure mNextFigure;
	private Timer mGameTimer, mAddDelayTimer;
	private KeyInputHandler mInput;
	private InfoTable mInfo;

	public GameScreen() {
		mPaused = false;
		mBoard = new Board();
		mFigure = new TetrisFigure(new Box(), mBoard);
		IFigure tempFigure = new Box();
		mNextFigure = new TetrisFigure(tempFigure, mBoard);
		mInfo = new InfoTable(tempFigure);
		mGameTimer = new Timer(500, this);
		mAddDelayTimer = new Timer(500, this);
		mInput = new KeyInputHandler();
		addKeyListener(mInput);
		mInput.add(this);
		mInput.add(mFigure);
		mGameTimer.start();
	}
	
	public void pause() {
		mPaused = !mPaused;
		if (mPaused) {
			mGameTimer.stop();
			mInput.remove(mFigure);
		}
		else
		{
			mInput.add(mFigure);
			mGameTimer.start();
		}
	}
	
	public void paint(Graphics g) {
		int sizeW = getWidth()/Board.WIDTH; 
		int sizeH = getHeight()/Board.HEIGHT; 
		int size = sizeW < sizeH ? sizeW : sizeH;
		int offsetH = (getHeight() - size*Board.HEIGHT)/2;
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.drawRect(offsetH - 1, 
				offsetH - 1, 
				size*Board.WIDTH + 1, 
				size*Board.HEIGHT + 1);
		mBoard.draw(g, offsetH, size);
		mFigure.draw(g, offsetH, size);
		mInfo.draw(g, size*Board.WIDTH + 1, offsetH, size);
	}
	
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		
		if (e.getSource() == mGameTimer)
		{
			if (mFigure.checkDown()) {
				mFigure.stepDown();
			} else
			{
				mAddDelayTimer.start();
			}			
		}
		else 
		if (e.getSource() == mAddDelayTimer)
		{
			if (mFigure.checkDown()) {
				mAddDelayTimer.stop();
			}
			else
			{
				mFigure.stayOnBoard();
				mInput.remove(mFigure);
				int amount = mBoard.checkBoard();
				mInfo.addScores(20*amount);
				mFigure = mNextFigure;
				mInput.add(mFigure);
				IFigure nextFigure = new Line();
				mNextFigure = new TetrisFigure(nextFigure, mBoard);
				mInfo.changeFigure(nextFigure);
				mAddDelayTimer.stop();
			}
				
		}		

	}

	@Override
	public void notify(int event) {
		if (event == KeyEvent.VK_P)
		{
			pause();
		}
		repaint();
	}
		
}
