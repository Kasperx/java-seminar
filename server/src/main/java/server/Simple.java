package server;

public class Simple {
	public double teile(double a, double b) {
		if (b==0)
			throw new IllegalArgumentException("b="+b+" darf nicht '0' sein");
		return a/b;
	}
}
