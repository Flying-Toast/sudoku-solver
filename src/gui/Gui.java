
package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import board.Board;
import solver.Solver;

public class Gui extends JFrame {

	private static final long serialVersionUID = 1L;

	public Gui() {
		setTitle("Sudoku Solver");
		setSize(300, 325);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(9, 9));
		setMinimumSize(new Dimension(200, 225));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setPreferredSize(new Dimension(WIDTH, 25));
		this.setJMenuBar(menuBar);

		JMenu solveMenu = new JMenu("Solve");
		menuBar.add(solveMenu);
		JMenuItem solveButton = new JMenuItem("Solve Board");
		solveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				int[] startState = new int[81];

				Component[] tileInputs = getRootPane().getContentPane().getComponents();

				for (int i = 0; i < 81; i++) {

					String tileValue = ((JTextField) tileInputs[i]).getText();
					if (tileValue.isEmpty()) {
						tileValue = "0";
					}

					startState[i] = Integer.parseInt(tileValue);
				}

				Board board = new Board(9, startState);
				Solver solver = new Solver(board);
				solver.solve();
			}
		});
		solveMenu.add(solveButton);

		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		JMenuItem helpButton = new JMenuItem("Usage");
		helpMenu.add(helpButton);
		JMenuItem aboutButton = new JMenuItem("About");
		helpMenu.add(aboutButton);

		for (int i = 0; i < 81; i++) {
			JTextField textField = new JTextField();
			textField.setHorizontalAlignment(JTextField.CENTER);
			add(textField);
		}
	}

	public static void main(String[] args) {
		JFrame frame = new Gui();
		frame.setVisible(true);
	}
}
