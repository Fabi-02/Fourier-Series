package fourierseries.frame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fourierseries.utils.Cycle;
import fourierseries.utils.Cycle.Wave;

/**
 * @author Fabian
 */

@SuppressWarnings("serial")
public class Frame extends JPanel implements ActionListener, ChangeListener {

	JFrame frame;

	public Frame() {
		frame = new JFrame("Fourier Series");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(1400, 600);
		frame.setMinimumSize(new Dimension(300, 242));
		frame.setVisible(true);
		frame.add(this);

		init();
	}

	Timer timer;

	public static Wave type = Wave.SAWTOOTH;
	public static final int beginGraphX = 450;

	public static double xVal = 0;
	public static ArrayList<Double> values;
	public static ArrayList<Cycle> cycles;

	public JSlider cyclesSlider;
	public JSlider typeSlider;

	int c = 1;

	private void init() {

		this.setLayout(null);

		cyclesSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
		cyclesSlider.addChangeListener(this);
		cyclesSlider.setLocation(20, 20);
		cyclesSlider.setSize(400, 20);
		cyclesSlider.setVisible(true);

		typeSlider = new JSlider(JSlider.HORIZONTAL, 1, 4, 1);
		typeSlider.addChangeListener(this);
		typeSlider.setLocation(600, 20);
		typeSlider.setSize(90, 20);
		typeSlider.setVisible(true);

		this.add(typeSlider);
		this.add(cyclesSlider);

		values = new ArrayList<>();
		cycles = new ArrayList<>();

		updateCycleValue();

		this.setBackground(Color.BLACK);

		timer = new Timer(1000 / 60, this);
		timer.start();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2D = (Graphics2D) g;

		g2D.setColor(Color.WHITE);
		g2D.setFont(new Font("Courier", Font.BOLD, 18));
		g2D.drawString("Cycles: " + c, 440, 35);
		g2D.drawString("Type: " + type, 700, 35);

		g2D.setStroke(new BasicStroke(2));

		cycles.get(0).drawCycle(g2D, xVal);

		g2D.setColor(new Color(255, 255, 0));

		for (int i = values.size() - 1; i > 0; i--) {
			double x = values.get(i);
			double lx = values.get(i - 1);
			g2D.drawLine(beginGraphX + values.size() - i, (int) x, beginGraphX + values.size() - i, (int) lx);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		xVal -= 0.02;
		repaint();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		cycles.clear();
		updateCycleValue();
	}

	public void updateCycleValue() {

		switch (typeSlider.getValue()) {
		case 1:
			type = Wave.SQUARE;
			break;
		case 2:
			type = Wave.SAWTOOTH;
			break;
		case 3:
			type = Wave.TRIANGLE;
			break;
		case 4:
			type = Wave.SEMICIRCLE;
			break;
		default:
			break;
		}

		c = 1;
		switch (cyclesSlider.getValue()) {
		case 1:
			c = 1;
			break;
		case 2:
			c = 2;
			break;
		case 3:
			c = 3;
			break;
		case 4:
			c = 5;
			break;
		case 5:
			c = 10;
			break;
		case 6:
			c = 20;
			break;
		case 7:
			c = 30;
			break;
		case 8:
			c = 40;
			break;
		case 9:
			c = 100;
			break;
		case 10:
			c = 1000;
			break;
		default:
			break;
		}

		for (int i = 1; i <= c; i++) {
			if (cycles.isEmpty()) {
				cycles.add(new Cycle(type, 225, 300, i));
			} else {
				cycles.add(new Cycle(type, i));
				cycles.get(i - 2).addCycle(cycles.get(i - 1));
			}
		}
	}
}
