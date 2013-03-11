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

public class TetrisFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static GamePanel mGamePanel;

	public static void createGUI() {
		
		JFrame frame = new JFrame("Tetris 2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
		
		frame.setJMenuBar(menuBar);
		frame.setPreferredSize(new Dimension(320, 400));
		mGamePanel = new GamePanel();		
		frame.add(mGamePanel);
		
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);			
		
		quitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		startItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mGamePanel.startGame();
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
