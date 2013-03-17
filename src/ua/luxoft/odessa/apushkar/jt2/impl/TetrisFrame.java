package ua.luxoft.odessa.apushkar.jt2.impl;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import ua.luxoft.odessa.apushkar.jt2.api.IGameObserver;

public class TetrisFrame extends JFrame implements IGameObserver, Runnable {
	
	private static final long serialVersionUID = 1L;
	private BaseScreen mCurrentScreen;
	private JFrame mFrame;
	private static TetrisFrame mWindow;
	
	
	public void createGUI() {
		
		mFrame = new JFrame("Tetris 2");
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Font font = new Font("Vernada", Font.PLAIN, 11);
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu gameMenu = new JMenu("Game");
		gameMenu.setFont(font);
		
		JMenuItem startItem = new JMenuItem("Start");
		startItem.setFont(font);
		gameMenu.add(startItem);
		
		gameMenu.addSeparator();
		
		JMenuItem quitItem = new JMenuItem("Quit");
		quitItem.setFont(font);
		gameMenu.add(quitItem);
		
		menuBar.add(gameMenu);
		
		mFrame.setJMenuBar(menuBar);
		mFrame.setPreferredSize(new Dimension(320, 400));
		mCurrentScreen = new SplashScreen();
		mFrame.getContentPane().add(mCurrentScreen);
		
		mFrame.pack();
		mFrame.setResizable(false);
		mFrame.setVisible(true);			
		
		quitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		startItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!mCurrentScreen.isActive()) {
					mFrame.getContentPane().remove(mCurrentScreen);
					mCurrentScreen = new GameScreen();
					mCurrentScreen.addObserver(mWindow);
					mFrame.getContentPane().add(mCurrentScreen);
					mCurrentScreen.requestFocusInWindow();
					mFrame.pack();	
					mCurrentScreen.setActive(true);
				}				
			}
		});
		
		startItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_F2, 0 ));
	}
	
	@Override
	public void notify(int scores) {
		mCurrentScreen.removeObserver(mWindow);
		mFrame.getContentPane().remove(mCurrentScreen);
		mCurrentScreen = new SplashScreen();
		mFrame.getContentPane().add(mCurrentScreen);
		mCurrentScreen.requestFocusInWindow();
		mFrame.pack();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		mWindow = new TetrisFrame();
		mWindow.start();
		
	}
	
	public void start() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				createGUI();
			}
		});
	}

}
