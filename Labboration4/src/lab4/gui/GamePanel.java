package lab4.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lab4.data.GameGrid;

/**
 * A panel providing a graphical view of the game board
 */

public class GamePanel extends JPanel implements Observer{



	private final int UNIT_SIZE = 20;
	private GameGrid grid;

	/**
	 * The constructor
	 * 
	 * @param grid The grid that is to be displayed
	 */	
	public GamePanel(GameGrid grid){
		this.grid = grid;
		grid.addObserver(this);
		Dimension d = new Dimension(grid.getSize()*UNIT_SIZE+1, grid.getSize()*UNIT_SIZE+1);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		this.setBackground(Color.WHITE);

		addMouseListener(new mouseClicked());
	}

	/**
	 * Returns a grid position given pixel coordinates
	 * of the panel
	 * 
	 * @param x the x coordinates
	 * @param y the y coordinates
	 * @return an integer array containing the [x, y] grid position
	 */
	public int[] getGridPosition(int x, int y){
		int[] pos = null;
		pos[0] = x/UNIT_SIZE;
		pos[1] = y/UNIT_SIZE;
		return pos;

	}

	public void update(Observable arg0, Object arg1) {
		this.repaint();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);

		g.drawRect( 20, 20, 20, 20 );

		for(int x = 0;x<grid.getSize();x++) {
			for(int y = 0; y<grid.getSize();y++) {
				//System.out.println("y");
				if(grid.grid[x][y]==2) {
					g.fillRect(x*UNIT_SIZE, y*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
				}else if(grid.grid[x][y]==1) {
					g.setColor(Color.RED);
					g.fillRect(x*UNIT_SIZE, y*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
					g.setColor(Color.BLACK);
				}
				else {
					g.drawRect(x*UNIT_SIZE, y*UNIT_SIZE, UNIT_SIZE,UNIT_SIZE);
				}

			}
		}


	}	

	public class mouseClicked extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y= e.getY();
			System.out.println("X: " + x+" Y: " + y);
			//int [] grid = getGridPosition(x,y);
			try {
				grid.move(x/UNIT_SIZE,y/UNIT_SIZE,1);
			}catch(Exception r) {
				
			}

		}

	}


	public static void main(String[] args) {

	}

}