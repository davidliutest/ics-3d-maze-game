package editor;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button extends Clickable {

    private float posx, posy;
    private int posw, posh;
    private String str;
    private Click press;
    private boolean square;
    // Constructor for button
    public Button(EditorApplication app, BufferedImage i, float posx, float posy, int w, int h, String s, Click press) {
        super(
                app, i,
                (int)(posx*app.getCanvWidth()-app.getCanvWidth()*w/2),
                (int)(posy*app.getCanvHeight()-app.getCanvHeight()*h/2),
                w, h
        );
        this.posx = posx;
        this.posy = posy;
        this.posw = w;
        this.posh = h;
        this.str = s;
        this.press = press;
    }

    public void setSquare() {
        square = true;
    }
    //render button set clickable and changable
    public void render(Graphics g) {
        if(!hide) {
            int nw = (int)((float)app.getCanvWidth()/app.getInitWidth()*posw);
            int nh = (int)((float)app.getCanvHeight()/app.getInitHeight()*posh);
            if(!square) {
                setSize(nw, nh);
            } else {
                int len = Math.min(nw, nh);
                setSize(len, len);
            }
            setLocation(
                    (int)(posx*app.getCanvWidth()-width/2),
                    (int)(posy*app.getCanvHeight()-height/2)
            );
            g.drawImage(image, x, y, width, height, null);
            text(g, str);
            if(clickable()) {
                click();
                disableClick();
            }
            resetClick();
        }
    }

    protected void click() {
        press.click();
    }

}
