package fourierseries.utils;

import java.awt.Color;
import java.awt.Graphics2D;

import fourierseries.frame.Frame;

/**
 * @author Fabian
 */

public class Cycle {

	public double offsetX;
	public double offsetY;
	public Wave type;
	public int n;

	public Cycle child;

	public Cycle(Wave type, double offsetX, double offsetY, int n) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.type = type;
		if (type == Wave.SQUARE || type == Wave.TRIANGLE) {
			this.n = 2 * n - 1;
		}
		if (type == Wave.SAWTOOTH || type == Wave.SEMICIRCLE) {
			this.n = n;
		}
	}

	public Cycle(Wave type, int n) {
		this.type = type;
		if (type == Wave.SQUARE || type == Wave.TRIANGLE) {
			this.n = 2 * n - 1;
		}
		if (type == Wave.SAWTOOTH || type == Wave.SEMICIRCLE) {
			this.n = n;
		}
	}

	public void addCycle(Cycle cycle) {
		this.child = cycle;
	}

	public void drawCycle(Graphics2D g2D, double xVal) {
		if (type == Wave.SQUARE) {
			g2D.setColor(Color.WHITE);
			double radius = (4 * 100) / (n * Math.PI); // 100 = height
			g2D.setColor(new Color(255, 255, 255, 100));
			g2D.drawOval((int) offsetX - (int) radius, (int) offsetY - (int) radius, (int) radius * 2,
					(int) radius * 2);
			double angle = n * xVal;
			double x = radius * Math.cos(angle);
			double y = radius * Math.sin(angle);
			g2D.setColor(Color.WHITE);
			g2D.drawLine((int) offsetX, (int) offsetY, (int) (x + offsetX), (int) (y + offsetY));

			if (this.child == null) {
				g2D.setColor(new Color(255, 255, 0, 126));
				g2D.drawLine((int) (x + offsetX), (int) (y + offsetY), Frame.beginGraphX, (int) (y + offsetY));
				Frame.values.add(y + offsetY);
				if (Frame.values.size() > 1000) {
					Frame.values.remove(0);
				}
			} else {
				child.offsetX = x + offsetX;
				child.offsetY = y + offsetY;
				child.drawCycle(g2D, xVal);
			}
		}
		if (type == Wave.SAWTOOTH) {
			g2D.setColor(Color.WHITE);
			double radius = (2 * 100) / (n * Math.PI); // 100 = height
			g2D.setColor(new Color(255, 255, 255, 100));
			g2D.drawOval((int) offsetX - (int) radius, (int) offsetY - (int) radius, (int) radius * 2,
					(int) radius * 2);
			double angle = n * xVal;
			double x = radius * Math.cos(angle);
			double y = radius * Math.sin(angle);
			g2D.setColor(Color.WHITE);
			g2D.drawLine((int) offsetX, (int) offsetY, (int) (x + offsetX), (int) (y + offsetY));

			if (this.child == null) {
				g2D.setColor(new Color(255, 255, 0, 126));
				g2D.drawLine((int) (x + offsetX), (int) (y + offsetY), Frame.beginGraphX, (int) (y + offsetY));
				Frame.values.add(y + offsetY);
				if (Frame.values.size() > 1000) {
					Frame.values.remove(0);
				}
			} else {
				child.offsetX = x + offsetX;
				child.offsetY = y + offsetY;
				child.drawCycle(g2D, xVal);
			}
		}
		if (type == Wave.TRIANGLE) {
			g2D.setColor(Color.WHITE);
			double radius = (2 * 100) / (Math.pow(n, 2) * Math.PI); // 100 = height
			g2D.setColor(new Color(255, 255, 255, 100));
			g2D.drawOval((int) offsetX - (int) radius, (int) offsetY - (int) radius, (int) radius * 2,
					(int) radius * 2);
			double angle = n * xVal;
			double x = radius * Math.sin(angle);
			double y = radius * Math.cos(angle);
			g2D.setColor(Color.WHITE);
			g2D.drawLine((int) offsetX, (int) offsetY, (int) (x + offsetX), (int) (y + offsetY));

			if (this.child == null) {
				g2D.setColor(new Color(255, 255, 0, 126));
				g2D.drawLine((int) (x + offsetX), (int) (y + offsetY), Frame.beginGraphX, (int) (y + offsetY));
				Frame.values.add(y + offsetY);
				if (Frame.values.size() > 1000) {
					Frame.values.remove(0);
				}
			} else {
				child.offsetX = x + offsetX;
				child.offsetY = y + offsetY;
				child.drawCycle(g2D, xVal);
			}
		}
		if (type == Wave.SEMICIRCLE) {
			g2D.setColor(Color.WHITE);
			double radius = (2 * 100) / (Math.pow(n, 2) * Math.PI); // 100 = height
			g2D.setColor(new Color(255, 255, 255, 100));
			g2D.drawOval((int) offsetX - (int) radius, (int) offsetY - (int) radius, (int) radius * 2,
					(int) radius * 2);
			double angle = n * xVal;
			double x = radius * Math.sin(angle);
			double y = radius * Math.cos(angle);
			g2D.setColor(Color.WHITE);
			g2D.drawLine((int) offsetX, (int) offsetY, (int) (x + offsetX), (int) (y + offsetY));

			if (this.child == null) {
				g2D.setColor(new Color(255, 255, 0, 126));
				g2D.drawLine((int) (x + offsetX), (int) (y + offsetY), Frame.beginGraphX, (int) (y + offsetY));
				Frame.values.add(y + offsetY);
				if (Frame.values.size() > 1000) {
					Frame.values.remove(0);
				}
			} else {
				child.offsetX = x + offsetX;
				child.offsetY = y + offsetY;
				child.drawCycle(g2D, xVal);
			}
		}
	}

	public enum Wave {
		SQUARE, SAWTOOTH, TRIANGLE, SEMICIRCLE;
	}
}
