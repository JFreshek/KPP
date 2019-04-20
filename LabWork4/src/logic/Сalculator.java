package logic;

import java.util.concurrent.Callable;

public class Ñalculator implements Callable<Integer> {
	int line;
	int column;
	Matrix mtrx;

	public Ñalculator(Matrix mtrx, int line, int column) {
		this.line = line;
		this.column = column;
		this.mtrx = mtrx;
	}

	@Override
	public Integer call() throws Exception {
		return mtrx.getAlgebraiñAddition(line, column);
	}
}
