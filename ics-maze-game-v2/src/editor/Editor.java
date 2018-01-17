package editor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Editor {

    private EditorApplication app;
    private final float mainWidth = 0.82f, sideWidth = 0.18f;

    private static BufferedImage[] images;

    private Map1 map1;
    private float scale;
    private float co, ro;
    private int td;

    private List<Button> bttns;
    private int select = -1;
    private JFileChooser fileChooser;

    public Editor(EditorApplication app) {
        this.app = app;
        BufferedImage sheet;
        images = new BufferedImage[7];
        try {
            sheet = ImageIO.read(Editor.class.getResource("/textures/t.png"));
            for(int i = 0; i < images.length; i++)
                images[i] = sheet.getSubimage(i * 16, 0, 16, 16);
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        map1 = new Map1(app, 20, 20);
        setScale(1.0f);

        bttns = new ArrayList<Button>();
        fileChooser = new JFileChooser();
    }

    public void create() {
        map1.create();
        // Side bar
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 3; j++) {
                float len = sideWidth/3;
                final int id = i*3+j;
                if (id == 5)
                    break;
                Button b = new Button(
                        app, images[i*3+j], mainWidth+len/2+len*j, 0.1f+(len+0.01f)*i, 40, 40, "",
                        new Click(){
                            public void click(){
                                select = id;
                            }
                        }
                );
                b.setSquare();
                bttns.add(b);
            }
        }
        Button load = new Button(
                app, images[6], mainWidth+sideWidth/2, 0.8f,
                (int)(app.getInitWidth()*sideWidth), 40, "Load",
                new Click(){
                    public void click() {
                        app.getMouse().resetLeft();
                        int returnVal = fileChooser.showOpenDialog(null);
                        // int returnValue = jfc.showSaveDialog(null);
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            File selected = fileChooser.getSelectedFile();
                            //System.out.println(selected.getAbsolutePath());
                            try {
                                FileInputStream fileIn = new FileInputStream(selected);
                                ObjectInputStream in = new ObjectInputStream(fileIn);
                                MapData1 mapData1 = (MapData1) in.readObject();
                                for(int i = 0; i < mapData1.data.length; i++){
                                    for(int j =0; j<mapData1.data[1].length;j++) {
                                        System.out.print(mapData1.data[i][j]);
                                    }
                                    System.out.println();
                                }
                                map1.load(mapData1);
                                in.close();
                                fileIn.close();
                            } catch (IOException i) {
                                i.printStackTrace();
                            } catch (ClassNotFoundException c) {
                                c.printStackTrace();
                            }
                        }
                    }
                }
        );
        bttns.add(load);
        Button save = new Button(
                app, images[6], mainWidth+sideWidth/2, 0.87f,
                (int)(app.getInitWidth()*sideWidth), 40, "Save",
                new Click(){
                    public void click(){
                        app.getMouse().resetLeft();
                        int returnVal = fileChooser.showSaveDialog(null);
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            File selected = fileChooser.getSelectedFile();
                            selected = new File(selected.toString() + ".mapdata");
                            //System.out.println(selected.getAbsolutePath());
                            try {
                                FileOutputStream fileOut = new FileOutputStream(selected);
                                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                                out.writeObject(map1.save());
                                out.close();
                                fileOut.close();
                            } catch (IOException i) {
                                i.printStackTrace();
                            }
                        }
                    }
                }
        );
        bttns.add(save);
    }

    public void render(Graphics g) {
        int cs = (int)Math.max(0,co/td);
        int ce = (int)Math.min(map1.width(),(co+app.getCanvWidth()*mainWidth)/td+1);
        int rs = (int)Math.max(0,ro/td);
        int re = (int)Math.min(map1.height(),(ro+app.getCanvHeight())/td+1);
        for(int r = rs; r < re; r++) {
            for(int c = cs; c < ce; c++) {
                Tile cur = map1.tile(r, c);
                cur.render(g, (int)(c*td-co), (int)(r*td-ro), td);
            }
        }
        g.setColor(Color.lightGray);
        g.fillRect((int)(app.getCanvWidth()*mainWidth), 0, (int)(app.getCanvWidth()*sideWidth), app.getCanvHeight());

        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 18));

        Iterator<Button> it = bttns.iterator();
        while(it.hasNext()) {
            it.next().render(g);
        }
        cam();
    }

    public void cam() {
        if(!app.getMouse().left()) {
            if(app.getKeys().l)
                co-=5;
            if(app.getKeys().r)
                co+=5;
            if(app.getKeys().u)
                ro-=5;
            if(app.getKeys().d)
                ro+=5;
            int wheel = app.getMouse().wheel();
            if(wheel != 0) {
                float ratio = 0.1f * wheel;
                float scalen = scale + ratio;
                if(scalen > 0.5f && scalen <= 15.0f) {
                    float tdo = td;
                    setScale(scalen);
                    float mx = app.getMouse().mx();
                    float my = app.getMouse().my();
                    co = (co + mx) / tdo * td - mx;
                    ro = (ro + my) / tdo * td - my;
                }
                app.getMouse().wheelReset();
            }
        }else{
            app.getMouse().wheelReset();
        }
    }

    public void setScale(float n) {
        scale = n;
        td = (int)(25 * scale);
    }

    public BufferedImage getImg(int id) {
        return images[id];
    }

    public float getMainWidth() {
        return mainWidth;
    }

    public float getSideWidth() {
        return sideWidth;
    }

    public int getSelect() {
        return  select;
    }

}
