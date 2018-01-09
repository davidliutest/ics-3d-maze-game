package editor;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Clickable extends Rectangle {
	
	protected EditorApplication app;
	protected BufferedImage image, imageHover;

	protected boolean active = true;
	protected boolean hide = false;
	//clickable constructor
	public Clickable(EditorApplication app, BufferedImage i, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.app = app;
		this.image = i;
	}
	//Draws a string centered on the current rectangle
		protected void text(Graphics g, String s) {
			FontMetrics m = g.getFontMetrics(g.getFont());
		g.drawString(s, x+(width-m.stringWidth(s))/2, y+(height-m.getHeight())/2+m.getAscent());
	}
	
	protected abstract void click();
	//If the mouse is hovered
	protected boolean hover() {
		return contains(app.getMouse().mx(), app.getMouse().my());
	}
	//If the button is clickable
	protected  boolean clickable() {
		return hover() && active && app.getMouse().left();
	}
	//If the button is dasabled
	protected void disableClick() {
		active = false;
	}

	protected void enableClick() {
		active = true;
	}
	//Resets the click
	protected  void resetClick() {
		if(!app.getMouse().left())
			active = true;
	}

}
