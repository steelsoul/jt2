package ua.luxoft.odessa.apushkar.jt2.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import ua.luxoft.odessa.apushkar.jt2.api.IKeyObservable;
import ua.luxoft.odessa.apushkar.jt2.api.IKeyObserver;

public class InputHander implements KeyListener, IKeyObservable {
	private List<IKeyObserver> mObservers = new ArrayList<IKeyObserver>();

	@Override
	public void keyPressed(KeyEvent e) {
		for (IKeyObserver o: mObservers)
			o.notify(e);
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
		mObservers.add(o);
	}

	@Override
	public void remove(IKeyObserver o) {
		mObservers.remove(o);
	}

}
