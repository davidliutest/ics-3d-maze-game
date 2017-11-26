package editor;

import java.awt.event.*;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener{
	
	private boolean left;
	private int mx = -1, my = -1, wheel;

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			left = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			left = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		wheel += -1 * e.getWheelRotation();
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	public boolean left() {
		return left;
	}

	public void resetLeft() {
		left = false;
	}

	public int mx() {
		return mx;
	}

	public int my() {
		return my;
	}

	public void resetMXY() {
		mx = -1;
		my = -1;
	}

	public int wheel() {
		return wheel;
	}

	public void wheelReset() {
		wheel = 0;
	}

}
