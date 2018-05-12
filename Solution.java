import java.util.LinkedList;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner reader = new Scanner(System.in);
		int numberOfRows = reader.nextInt();
		int numberOfColumns = reader.nextInt();
		int[][] matrix = new int[numberOfRows][numberOfColumns];
		for (int matrix_i = 0; matrix_i < numberOfRows; matrix_i++) {
			for (int matrix_j = 0; matrix_j < numberOfColumns; matrix_j++) {
				matrix[matrix_i][matrix_j] = reader.nextInt();
			}
		}
		int result = connectedCell(matrix);
		System.out.println(result);

	}

	public static int connectedCell(int[][] matrix) {
		LinkedList<Pair> x_y = new LinkedList<Pair>();
		int largestRegion = 0;
		int counter = 0;

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {

				if (matrix[i][j] == 1) {
					x_y.add(new Pair(i, j));
					matrix[i][j] = 0;
					counter++;
				}

				for (int i_coord = 0; i_coord < x_y.size(); i_coord++) {

					if (checkCell_PrevRow_NextColumn(x_y.get(i_coord).getX(), x_y.get(i_coord).getY(), matrix)) {
						int x = x_y.get(i_coord).getX() - 1;
						int y = x_y.get(i_coord).getY() + 1;
						x_y.add(new Pair(x, y));
						matrix[x][y] = 0;
						counter++;
					}

					if (checkCell_SameRow_NextColumn(x_y.get(i_coord).getX(), x_y.get(i_coord).getY(), matrix)) {
						int x = x_y.get(i_coord).getX();
						int y = x_y.get(i_coord).getY() + 1;
						x_y.add(new Pair(x, y));
						matrix[x][y] = 0;
						counter++;
					}

					if (checkCell_NextRow_NextColumn(x_y.get(i_coord).getX(), x_y.get(i_coord).getY(), matrix)) {
						int x = x_y.get(i_coord).getX() + 1;
						int y = x_y.get(i_coord).getY() + 1;
						x_y.add(new Pair(x, y));
						matrix[x][y] = 0;
						counter++;
					}

					if (checkCell_NextRow_SameColum(x_y.get(i_coord).getX(), x_y.get(i_coord).getY(), matrix)) {
						int x = x_y.get(i_coord).getX() + 1;
						int y = x_y.get(i_coord).getY();
						x_y.add(new Pair(x, y));
						matrix[x][y] = 0;
						counter++;
					}

					if (checkCell_NextRow_PrevColumn(x_y.get(i_coord).getX(), x_y.get(i_coord).getY(), matrix)) {
						int x = x_y.get(i_coord).getX() + 1;
						int y = x_y.get(i_coord).getY() - 1;
						x_y.add(new Pair(x, y));
						matrix[x][y] = 0;
						counter++;
					}

					if (checkCell_SameRow_PrevColumn(x_y.get(i_coord).getX(), x_y.get(i_coord).getY(), matrix)) {
						int x = x_y.get(i_coord).getX();
						int y = x_y.get(i_coord).getY() - 1;
						x_y.add(new Pair(x, y));
						matrix[x][y] = 0;
						counter++;
					}

					if (checkCell_PrevRow_PrevColumn(x_y.get(i_coord).getX(), x_y.get(i_coord).getY(), matrix)) {
						int x = x_y.get(i_coord).getX() - 1;
						int y = x_y.get(i_coord).getY() - 1;
						x_y.add(new Pair(x, y));
						matrix[x][y] = 0;
						counter++;
					}

					if (checkCell_PrevRow_SameColumn(x_y.get(i_coord).getX(), x_y.get(i_coord).getY(), matrix)) {
						int x = x_y.get(i_coord).getX() - 1;
						int y = x_y.get(i_coord).getY();
						x_y.add(new Pair(x, y));
						matrix[x][y] = 0;
						counter++;
					}
				}

			//	System.out.println(x_y);
				if (counter > largestRegion) {
					largestRegion = counter;
				}
				x_y = new LinkedList<Pair>();
				counter = 0;
			}
		}
		return largestRegion;
	}

	public static boolean checkCell_PrevRow_NextColumn(int row, int column, int[][] matrix) {
		if (row - 1 >= 0 && column + 1 < matrix[0].length) {
			if (matrix[row - 1][column + 1] == 1) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkCell_SameRow_NextColumn(int row, int column, int[][] matrix) {
		if (column + 1 < matrix[0].length) {
			if (matrix[row][column + 1] == 1) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkCell_NextRow_NextColumn(int row, int column, int[][] matrix) {
		if (row + 1 < matrix.length && column + 1 < matrix[0].length) {
			if (matrix[row + 1][column + 1] == 1) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkCell_NextRow_SameColum(int row, int column, int[][] matrix) {
		if (row + 1 < matrix.length) {
			if (matrix[row + 1][column] == 1) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkCell_NextRow_PrevColumn(int row, int column, int[][] matrix) {
		if (row + 1 < matrix.length && column - 1 >= 0) {
			if (matrix[row + 1][column - 1] == 1) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkCell_SameRow_PrevColumn(int row, int column, int[][] matrix) {
		if (column - 1 >= 0) {
			if (matrix[row][column - 1] == 1) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkCell_PrevRow_PrevColumn(int row, int column, int[][] matrix) {
		if (row - 1 >= 0 && column - 1 >= 0) {
			if (matrix[row - 1][column - 1] == 1) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkCell_PrevRow_SameColumn(int row, int column, int[][] matrix) {
		if (row - 1 >= 0) {
			if (matrix[row - 1][column] == 1) {
				return true;
			}
		}
		return false;
	}
}

class Pair {

	private int x;
	private int y;

	public Pair(int firstCoord, int secondCoord) {
		this.x = firstCoord;
		this.y = secondCoord;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public String toString() {
		return this.x + "&" + this.y;
	}
}
