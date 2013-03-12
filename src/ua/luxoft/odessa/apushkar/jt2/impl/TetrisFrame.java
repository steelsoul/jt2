package ua.luxoft.odessa.apushkar.jt2.impl;

import java.awt.Canvas;
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

public class TetrisFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static Canvas mCurrentScreen;
	private static JFrame mFrame;

	public static void createGUI() {
		
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
				mFrame.getContentPane().remove(mCurrentScreen);
				mCurrentScreen = new GameScreen();
				mFrame.getContentPane().add(mCurrentScreen);
				mCurrentScreen.requestFocusInWindow();
				mFrame.pack();				
			}
		});
		
		startItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_F2, 0 ));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				createGUI();
			}
		});
	}

}
