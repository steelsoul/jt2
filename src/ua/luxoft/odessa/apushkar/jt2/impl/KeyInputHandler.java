package ua.luxoft.odessa.apushkar.jt2.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import ua.luxoft.odessa.apushkar.jt2.api.IKeyObservable;
import ua.luxoft.odessa.apushkar.jt2.api.IKeyObserver;

/*
 *  Lazy observable object realization
 *  
 * */

public class KeyInputHandler implements KeyListener, IKeyObservable {
	private List<IKeyObserver> mObservers = new ArrayList<IKeyObserver>();
	private List<IKeyObserver> mBuffer = new ArrayList<IKeyObserver>();
	private List<IKeyObserver> mRemoveBuffer = new ArrayList<IKeyObserver>();
	private Boolean mBusy;
	
	public KeyInputHandler() {
		mBusy = false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		mBusy = true;
		for (IKeyObserver o: mObservers)
			o.notify(e.getKeyCode());
		mBusy = false;
		for (IKeyObserver o: mBuffer)
			mObservers.add(o);
		mBuffer.clear();
		for (IKeyObserver o: mRemoveBuffer)
			mObservers.remove(o);
		mRemoveBuffer.clear();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void add(IKeyObserver o) {
		if (!mBusy) {
			mObservers.add(o);
		} else
		{
			mBuffer.add(o);
		}
	}

	@Override
	public void remove(IKeyObserver o) {
		if (!mBusy) {
			mObservers.remove(o);
		} else
		{
			mRemoveBuffer.add(o);
		}
	}

}
