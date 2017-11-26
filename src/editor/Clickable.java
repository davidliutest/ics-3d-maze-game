package editor;

// ADD HEADERS!!!!!!!!!!!!!!!!!

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Clickable extends Rectangle {
	
	protected EditorApplication app;
	// ADD HOVER IMAGE LIGHTER IMAGE!!!!!!!!!!!!
	protected BufferedImage image, imageHover;

	protected boolean active = true;
	protected boolean hide = false;
	
	public Clickable(EditorApplication app, BufferedImage i, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.app = app;
		this.image = i;
	}
	
	protected void text(Graphics g, String s) {
		FontMetrics m = g.getFontMetrics(g.getFont());
		g.drawString(s, x+(width-m.stringWidth(s))/2, y+(height-m.getHeight())/2+m.getAscent());
	}
	
	protected abstract void click();

	protected boolean hover() {
		return contains(app.getMouse().mx(), app.getMouse().my());
	}

	protected  boolean clickable() {
		return hover() && active && app.getMouse().left();
	}

	protected void disableClick() {
		active = false;
	}

	protected void enableClick() {
		active = true;
	}

	protected  void resetClick() {
		if(!app.getMouse().left())
			active = true;
	}

}
