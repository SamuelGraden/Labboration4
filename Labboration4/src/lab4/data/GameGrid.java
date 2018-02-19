package lab4.data;

import java.util.Arrays;
import java.util.Observable;

/**
 * Represents the 2-d game grid
 */

public class GameGrid extends Observable {

	public static final int EMPTY = 0;
	public static final int ME = 1;
	public static final int OTHER = 2;
	public int[][] grid;
	private int size;
	private int INROW;

	/**
	 * Constructor
	 * 
	 * @param size
	 *            The width/height of the game grid
	 */
	public GameGrid(int size) {
		this.size = size;
		grid = new int[this.size][this.size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				grid[i][j] = EMPTY;
			}
		}
	}

	/**
	 * Reads a location of the grid
	 * 
	 * @param x
	 *            The x coordinate
	 * @param y
	 *            The y coordinate
	 * @return the value of the specified location
	 */
	public int getLocation(int x, int y) {
		return grid[x][y];
	}

	/**
	 * Returns the size of the grid
	 * 
	 * @return the grid size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Enters a move in the game grid
	 * 
	 * @param x
	 *            the x position
	 * @param y
	 *            the y position
	 * @param player
	 * @return true if the insertion worked, false otherwise
	 */
	public boolean move(int x, int y, int player) {
		if (grid[x][y] == EMPTY) {
			grid[x][y] = player;
			setChanged();
			notifyObservers();
			System.out.println(isWinner(player));
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Clears the grid of pieces
	 */
	public void clearGrid() {
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; i < getSize(); j++) {
				grid[i][j] = EMPTY;
			}
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Check if a player has 5 in row
	 * 
	 * @param player
	 *            the player to check for
	 * @return true if player has 5 in row, false otherwise
	 */
	public boolean isWinner(int player) {
		INROW = 0;
		// lodrätt
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (INROW >= 5) {
					return true;
				} else if (grid[i][j] == player) {
					INROW += 1;
				} else {
					INROW = 0;
				}
			}
		}
		// vågrätt
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if (INROW >= 5) {
					return true;
				} else if (grid[j][i] == player) {
					INROW += 1;
				} else {
					INROW = 0;
				}
			}
		}

		for (int x = 2 * getSize(); x > 0; x--) {

			for (int y = 0; y < getSize(); y++) {

				if (INROW >= 5) {
					return true;
				}
				try {
				//	System.out.println("(x,y): " + (x - y) + " " + (getSize() - y));
					if (grid[x - y][getSize() - y] == player) {
						INROW += 1;

					} else {
						INROW = 0;
					}
				} catch (Exception e) {
					INROW = 0;
					continue;

				}
			}
		}
		for (int x = -getSize(); x < getSize() - 1; x++) {

			for (int y = getSize() - 1; y > 0; y--) {

				if (INROW >= 5) {
					return true;
				}
				try {
					 System.out.println("(x,y): " + (x+getSize()-1-y) + " "+y);
					if (grid[x + getSize() - 1 - y][y] == player) {
						INROW += 1;

					} else {
						INROW = 0;
					}
				} catch (Exception e) {
					INROW = 0;
					continue;

				}

			}

		}
		return false;
	}

	public static void main(String[] args) {

	}
}