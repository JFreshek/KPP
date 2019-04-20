package logic;

public class Matrix {
	private int linesNum;
	private int columnsNum;
	private int matrix[][];

	public Matrix() {
		linesNum = 3;
		columnsNum = 3;
		matrix = new int[linesNum][columnsNum];
	}

	public Matrix(int linesNum, int columnsNum) {
		this.linesNum = linesNum;
		this.columnsNum = columnsNum;
		this.matrix = new int[linesNum][columnsNum];
	}

	public void setElement(int line, int column, int number) {
		matrix[line][column] = number;
	}

	public int getMinor(int line, int column) {
		int minor;
		int k = 0, q = 0;
		int buffer[][] = new int[2][2];
		for (int i = 0; i < linesNum; ++i) {
			if (i != line - 1) {
				for (int j = 0; j < columnsNum; ++j) {
					if (j != column - 1) {
						buffer[k][q] = matrix[i][j];
						if (q < 1) {
							++q;
						} else {
							if (k < 1) {
								k++;
								q = 0;
							}
						}
					}
				}
			}
		}
		minor = buffer[0][0] * buffer[1][1] - buffer[0][1] * buffer[1][0];
		return minor;
	}

	public int getAlgebraiñAddition(int line, int column) {
		return (int) Math.pow(-1, line + column) * getMinor(line, column);
	}

	public int getDeterminant(int algAddition11, int algAddition12, int algAddition13) {
		return matrix[0][0] * algAddition11 + matrix[0][1] * algAddition12 + matrix[0][2] * algAddition13;
	}
}
