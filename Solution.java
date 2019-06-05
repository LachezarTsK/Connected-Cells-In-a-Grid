import java.util.Scanner;
import java.util.Stack;

public class Solution {
	/**
	 * Input matrix. This implementation does not retain the original state of the
	 * input matrix.
	 */
	private static int[][] grid;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int rows = scanner.nextInt();
		int columns = scanner.nextInt();
		grid = new int[rows][columns];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				grid[r][c] = scanner.nextInt();
			}
		}
		scanner.close();
		System.out.println(getMaximumRegionOfConnectedCells(rows, columns));
	}

	/**
	 * The method searches for the largest region of connected cells (horizontally,
	 * vertically, or diagonally) with value of "1".
	 * 
	 * @return An integer, representing number of such cell in the maximum region.
	 */
	private static int getMaximumRegionOfConnectedCells(int rows, int columns) {
		int maximumRegion = 0;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				if (grid[r][c] == 1) {
					maximumRegion = Math.max(searchForConnectedCells(r, c), maximumRegion);
				}
			}
		}
		return maximumRegion;
	}

	/**
	 * The method searches (Depth First Search) for connected cells with value of
	 * "1". In order to prevent multiple visits of the same cell, a visited cell is
	 * ascribed a value of "0".
	 * 
	 * @return An integer, representing the number of connected cells in the current
	 *         region.
	 */
	private static int searchForConnectedCells(int row, int column) {
		Stack<Integer> queue_rows = new Stack<Integer>();
		Stack<Integer> queue_columns = new Stack<Integer>();
		queue_rows.add(row);
		queue_columns.add(column);
		int counter = grid[row][column]--;

		while (!queue_rows.isEmpty()) {
			row = queue_rows.pop();
			column = queue_columns.pop();
			/**
			 * Neighboring cell to the left.
			 */
			if (isCell(row, column - 1) && grid[row][column - 1] == 1) {
				queue_rows.add(row);
				queue_columns.add(column - 1);
				counter += grid[row][column - 1]--;
			}
			/**
			 * Neighboring cell to the right.
			 */
			if (isCell(row, column + 1) && grid[row][column + 1] == 1) {
				queue_rows.add(row);
				queue_columns.add(column + 1);
				counter += grid[row][column + 1]--;
			}
			/**
			 * Neighboring cell from below.
			 */
			if (isCell(row - 1, column) && grid[row - 1][column] == 1) {
				queue_rows.add(row - 1);
				queue_columns.add(column);
				counter += grid[row - 1][column]--;
			}
			/**
			 * Neighboring cell from above.
			 */
			if (isCell(row + 1, column) && grid[row + 1][column] == 1) {
				queue_rows.add(row + 1);
				queue_columns.add(column);
				counter += grid[row + 1][column]--;
			}
			/**
			 * Neighboring cell from above, to the right.
			 */
			if (isCell(row + 1, column + 1) && grid[row + 1][column + 1] == 1) {
				queue_rows.add(row + 1);
				queue_columns.add(column + 1);
				counter += grid[row + 1][column + 1]--;
			}
			/**
			 * Neighboring cell from above, to the left.
			 */
			if (isCell(row + 1, column - 1) && grid[row + 1][column - 1] == 1) {
				queue_rows.add(row + 1);
				queue_columns.add(column - 1);
				counter += grid[row + 1][column - 1]--;
			}
			/**
			 * Neighboring cell from below, to the right.
			 */
			if (isCell(row - 1, column + 1) && grid[row - 1][column + 1] == 1) {
				queue_rows.add(row - 1);
				queue_columns.add(column + 1);
				counter += grid[row - 1][column + 1]--;
			}
			/**
			 * Neighboring cell from below, to the left.
			 */
			if (isCell(row - 1, column - 1) && grid[row - 1][column - 1] == 1) {
				queue_rows.add(row - 1);
				queue_columns.add(column - 1);
				counter += grid[row - 1][column - 1]--;
			}
		}
		return counter;
	}

	/**
	 * The method checks whether the neighboring row and column are within the
	 * boundaries of the grid.
	 * 
	 * @return true, if they are within the boundaries. Otherwise, returns false.
	 */
	private static boolean isCell(int row, int column) {
		if (row < 0 || row >= grid.length || column < 0 || column >= grid[row].length) {
			return false;
		}
		return true;
	}
}
