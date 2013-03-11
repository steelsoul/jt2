package ua.luxoft.odessa.apushkar.jt2.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Vector;

import javax.swing.Timer;

import ua.luxoft.odessa.apushkar.jt2.board.impl.Cell;


public class SplashScreen extends Observable implements ActionListener {
	private static final int AMOUNT = 40;
	private Vector<Point> mPoints = new Vector<Point>(AMOUNT);
	private Vector<Color> mColors = new Vector<Color>(AMOUNT);
	private Timer mTimer;
	private List<Observer> mObservers = new ArrayList<Observer>();
	private int mSizeW;
	private int mSizeH;
	private Boolean mShowHelp;
	
	public SplashScreen() {
		mSizeW = mSizeH = 0;
		mTimer = new Timer(200, this);
		mTimer.start();
		mShowHelp = false;
	}
	
	public void addObserver(Observer o) {
		mObservers.add(o);
	}
	
	public void removeObserver(Observer o) {
		mObservers.remove(o);
	}
	
	public void stopShow() {
		mTimer.stop();
		mObservers.clear();
		mPoints.clear();
		mColors.clear();
	}
	
	public void startShow(Observer o) {
		mTimer.start();
		mObservers.add(o);
	}
	
	private void regenerate() {
		if (mSizeW != 0 && mSizeH != 0) {
			mColors.clear();
			mPoints.clear();
			Random rndGen = new Random();
			for (int i = 0; i < AMOUNT; i++) {
				int r = rndGen.nextInt(200) + 50;
				int g = rndGen.nextInt(200) + 50;
				int b = rndGen.nextInt(200) + 50;
				Color tempColor = new Color(r, g, b);
				mColors.add(tempColor);
				int x = rndGen.nextInt(mSizeW);
				int y = rndGen.nextInt(mSizeH);
				Point tempPoint = new Point(x, y);
				mPoints.add(tempPoint);
			}
		}
	}
	
	public void draw(Graphics g, int sizeW, int sizeH, int size) {
		if (mColors.size() != 0 && mPoints.size() != 0)
			for (int i = 0; i < AMOUNT; i++) {
				Cell tempCell = new Cell();
				tempCell.setColor(mColors.get(i)).setChecked(true).setVisible(true);
				tempCell.draw(g, mPoints.get(i).x*size, mPoints.get(i).y*size, size);
			}
		if (mShowHelp)
		{
			g.setColor(Color.RED);
			String text = "Press F2 to start";
			g.drawString(text, 
					(int) (sizeW*size/2 - g.getFontMetrics().getStringBounds(text, g).getWidth()/2), 
					20);
		}
		mSizeW = sizeW;
		mSizeH = sizeH;			
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		regenerate();
		for (Observer o: mObservers)
			o.update(this, o);
		mShowHelp = !mShowHelp;
	}

}
