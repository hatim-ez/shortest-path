import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Plot extends JPanel {

	// ______________Attributes______________
	private Dot[] t;
	private double[][] scale;
	private final int width = 1000;
	private final int height = 500;
	private final int margin = 50;

	// ______________Constructor______________
	public Plot(String title, Dot[] t) {
		JFrame w = new JFrame();
		w.setSize(width + 2 * margin, height + 2 * margin);
		w.setLocationRelativeTo(null);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setTitle(title);
		w.setContentPane(this);
		w.setVisible(true);
		this.t = t;
		this.scale = Dot.getScale(t);
		
	}

	public Plot(String title, Dot[] t, double[][] scale) {
		// scale = [[xmin, xmax], [ymin, ymax]]
		JFrame w = new JFrame();
		w.setSize(width + 2 * margin, height + 2 * margin);
		w.setLocationRelativeTo(null);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setTitle(title);
		w.setContentPane(this);
		w.setVisible(true);
		this.t = t;
		this.scale = scale;
	}

	// ______________PaintComponent______________
	public void paintComponent(Graphics g) {
		this.drawAxis(g);
		int x, y, x0 = 0, y0 = 0;
		boolean first = true;
		for (Dot d : this.t) {
			x = margin + (int) (width * (d.getX() - this.scale[0][0]) / (this.scale[0][1] - this.scale[0][0]));
			y = margin + (int) (height * (this.scale[1][1] - d.getY()) / (this.scale[1][1] - this.scale[1][0]));
			if (first) {
				first = false;
			} else {
				g.setColor(Color.blue);
				g.drawLine(x, y, x0, y0);
			}
			g.setColor(Color.red);
			drawCross(g, x, y, 10, 2);
			x0 = x;
			y0 = y;
		}

	}

	// ______________Methods______________
	public void erase(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

	public void drawAxis(Graphics g) {
		int x, y;
		x = margin + (int) (width * (0 - this.scale[0][0]) / (this.scale[0][1] - this.scale[0][0]));
		y = margin + (int) (height * (this.scale[1][1] - 0) / (this.scale[1][1] - this.scale[1][0]));
		g.drawLine(0, y, width + 2 * margin, y);
		g.drawLine(x, 0, x, height + 2 * margin);
	}

	// ______________Functions______________

	public static void drawCross(Graphics g, int x, int y, int size, int width) {
		for (int i = -width / 2; i < width / 2; i++) {
			g.drawLine(x - size, y + i, x + size, y + i);
			g.drawLine(x + i, y - size, x + i, y + size);
		}
	}
}