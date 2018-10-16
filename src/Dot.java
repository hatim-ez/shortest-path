
public class Dot {

	// ______________Attributes______________
	private double x;
	private double y;

	// ______________Constructor______________
	public Dot(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// ______________Methods______________
	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	// ______________Functions______________
	public static double[][] getScale(Dot[] t) {
		double[][] scale = new double[2][];
		scale[0] = new double[] { 0, 0 };
		scale[1] = new double[] { 0, 0 };
		for (Dot d : t) {
			if (scale[0][0] > d.x) {
				scale[0][0] = d.x;
			}
			if (scale[0][1] < d.x) {
				scale[0][1] = d.x;
			}
			if (scale[1][0] > d.y) {
				scale[1][0] = d.y;
			}
			if (scale[1][1] < d.y) {
				scale[1][1] = d.y;
			}
		}
		return scale;
	}
}