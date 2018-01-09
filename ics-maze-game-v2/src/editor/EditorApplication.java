package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class EditorApplication implements Runnable{

    private boolean running;
    private Thread thread;

    private JFrame frame;
    private Container cont;
    private Canvas canvas;
    private BufferStrategy bs;
    private Graphics g;
    private final int initWidth = 1000, initHeight = 600;

    private Editor editor;
    private Keys keys;
    private Mouse mouse;
    //starts game
    public synchronized void start() {
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    //creates frame
    public void create() {
        frame = new JFrame("Maze and Pathfinding Test");
        frame.setSize(initWidth, initHeight);
        frame.setMinimumSize(new Dimension(600, 600));
        frame.setMaximumSize(new Dimension(1200, 800));
        frame.setResizable(true);

        canvas = new Canvas();
        canvas.setSize(new Dimension(initWidth, initHeight));
        canvas.setMinimumSize(new Dimension(600, 600));
        canvas.setMaximumSize(new Dimension(1200, 800));
        canvas.setFocusable(false);
        frame.add(canvas);
        frame.setVisible(true);
        frame.pack();

        keys = new Keys();
        mouse = new Mouse();
        frame.addMouseListener(mouse);
        frame.addMouseMotionListener(mouse);
        frame.addMouseWheelListener(mouse);
        canvas.addMouseListener(mouse);
        canvas.addMouseMotionListener(mouse);
        canvas.addMouseWheelListener(mouse);
        frame.addKeyListener(keys);

        editor = new Editor(this);
        editor.create();
    }
    // run gui
    public void run() {
        create();
        int FPS = 250;
        double timePerTick = 1000000000 / FPS;
        double delta = 0;
        double curTime;
        double lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running) {
            curTime = System.nanoTime();
            delta += (curTime - lastTime) / timePerTick;
            timer += curTime - lastTime;
            lastTime = curTime;
            if(delta >= 1) {
                // Handle input
                keys.tick();
                // Update GUI dimensions
                // Render and update game
                render();
                ticks++;
                delta--;
            }
            if(timer >= 1000000000) {
                System.out.println(ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }
    //renders game
    public void render() {
        bs = canvas.getBufferStrategy();
        if(bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        editor.render(g);
        bs.show();
        g.dispose();
    }
    //stops game
    public synchronized void stop() {
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //getters
    public int getCanvWidth() {
        return canvas.getWidth();
    }

    public int getCanvHeight() {
        return canvas.getHeight();
    }

    public Keys getKeys() {
        return keys;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public Editor getEditor() {
        return editor;
    }

    public int getInitWidth() {
        return initWidth;
    }

    public int getInitHeight() {
        return initHeight;
    }

    public static void main(String[] args) {
        new EditorApplication().start();
    }

}