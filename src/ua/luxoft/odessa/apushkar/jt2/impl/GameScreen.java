package ua.luxoft.odessa.apushkar.jt2.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import ua.luxoft.odessa.apushkar.jt2.api.IFigure;
import ua.luxoft.odessa.apushkar.jt2.api.IGameObserver;
import ua.luxoft.odessa.apushkar.jt2.api.IKeyObserver;
import ua.luxoft.odessa.apushkar.jt2.board.impl.Board;
import ua.luxoft.odessa.apushkar.jt2.board.impl.InfoTable;
import ua.luxoft.odessa.apushkar.jt2.figure.impl.FigureGenerator;
import ua.luxoft.odessa.apushkar.jt2.figure.impl.TetrisFigure;

/**
 *  This is the place where all game elements will be shown.
 * */
public class GameScreen extends BaseScreen implements ActionListener, IKeyObserver {
	private static final long serialVersionUID = 1L;
	
	private Boolean mPaused;
	private Board mBoard;
	private TetrisFigure mFigure;
	private TetrisFigure mNextFigure;
	private Timer mGameTimer, mAddDelayTimer;
	private KeyInputHandler mInput;
	private InfoTable mInfo;
	private Image mDbImage;
	private Graphics mDbG;
	private IGameObserver mObserver;

	public GameScreen() {
		super();
		mPaused = false;
		mBoard = new Board();
		mFigure = new TetrisFigure(FigureGenerator.generate(), mBoard);
		IFigure tempFigure = FigureGenerator.generate();
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
		if (mDbImage == null)
		{
			mDbImage = createImage(getSize().width, getSize().height);
			mDbG = mDbImage.getGraphics();
		}
		paint(mDbG);
		g.drawImage(mDbImage, 0, 0, this);
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
				// check end of the game
				if (!mFigure.checkDown())
				{
					setActive(false);
					mPaused = true;
					// we should change to new screen and show amount of scores
					if (mObserver != null)
						mObserver.notify(mInfo.getScores());					
				}
				mInput.add(mFigure);
				IFigure nextFigure = FigureGenerator.generate();
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

	@Override
	public void addObserver(IGameObserver o) {
		mObserver = o;
	}

	@Override
	public void removeObserver(IGameObserver o) {
		if (mObserver == o)
			mObserver = null;
	}
		
}
