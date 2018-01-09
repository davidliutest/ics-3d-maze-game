package editor;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile extends Clickable {

    private int id;
    //Tile constructor
    public Tile(EditorApplication app, BufferedImage i, int x, int y, int w, int h, int id) {
        super(app, i, x, y, w, h);
        this.id = id;
    }
    //sets button picture
    public void render(Graphics g, int x, int y, int td) {
        if(!hide) {
            setBounds(x, y, td, td);
            g.drawImage(image, x, y, td, td, null);
            if (x+width < app.getCanvWidth() * app.getEditor().getMainWidth() && clickable()) {
                click();
                disableClick();
            }
            resetClick();
        }
    }
    //clicks the button, set image
    protected void click() {
        int select = app.getEditor().getSelect();
        if(select != -1) {
            id = select;
            image = app.getEditor().getImg(select);
        }
    }

    public int id() {
        return id;
    }

    // DEBUG
    public String toString() {
        return Integer.toString(id);
    }

}
