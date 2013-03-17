package ua.luxoft.odessa.apushkar.jt2.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

import ua.luxoft.odessa.apushkar.jt2.api.IGameObserver;
import ua.luxoft.odessa.apushkar.jt2.board.impl.Board;
import ua.luxoft.odessa.apushkar.jt2.board.impl.Cell;

/**
 *  Show splash screen 
 * */
public class SplashScreen extends BaseScreen implements ActionListener{
	private static final long serialVersionUID = 1L;

	private static final int AMOUNT = 40;
	private Point[] mPoints = new Point[AMOUNT];
	private Color[] mColors = new Color[AMOUNT];
	private Timer mTimer;
	private int mSizeW;
	private int mSizeH;
	private Boolean mShowHelp;
	private Image mDbImage;
	private Graphics mDbG;
	
	public SplashScreen() {
		mSizeW = mSizeH = 0;
		mTimer = new Timer(200, this);
		mTimer.start();
		mShowHelp = false;
		Point tempPoint = new Point(0,0);
		for (int i = 0; i < AMOUNT; i++) {
			mColors[i] = Color.BLACK;
			mPoints[i] = tempPoint;
		}
	}
	
	public void stopShow() {
		mTimer.stop();		
	}
	
	private void regenerate() {
		if (mSizeW != 0 && mSizeH != 0) {
			Random rndGen = new Random();
			for (int i = 0; i < AMOUNT; i++) {
				int r = rndGen.nextInt(200) + 50;
				int g = rndGen.nextInt(200) + 50;
				int b = rndGen.nextInt(200) + 50;
				mColors[i] = new Color(r, g, b);
				int x = rndGen.nextInt(mSizeW);
				int y = rndGen.nextInt(mSizeH - 4) + 2;
				mPoints[i] = new Point(x, y);
			}
		}
	}
	
	private void draw(Graphics g, int size) {
		Cell tempCell = new Cell();
		for (int i = 0; i < AMOUNT; i++) {
			tempCell.setColor(mColors[i]).setChecked(true).setVisible(true);
			tempCell.draw(g, mPoints[i].x*size, mPoints[i].y*size, size);
		}
		if (mShowHelp)
		{
			g.setColor(Color.RED);
			String text = "Press F2 to start";
			g.drawString(text, 
					(int) 
					(mSizeW*size/2 - 
							g.getFontMetrics().
							getStringBounds(text, g).
							getWidth()/2), 
					20);
		}
	}
	
	public void paint(Graphics g) {
		int sizeW = getWidth()/Board.WIDTH; 
		int sizeH = getHeight()/Board.HEIGHT; 
		int size = sizeW < sizeH ? sizeW : sizeH;
		mSizeW = getWidth()/size;
		mSizeH = getHeight()/size;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		draw(g, size);
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
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		regenerate();
		mShowHelp = !mShowHelp;
	}

	@Override
	public void addObserver(IGameObserver o) {
		// TODO Auto-generated method stub
		// should do nothing
	}

	@Override
	public void removeObserver(IGameObserver o) {
		// TODO Auto-generated method stub
		// should do nothing
	}

}
