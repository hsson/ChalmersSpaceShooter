package lab1;

import javax.swing.*;
import java.awt.*;

public class MenuTile extends GameTile{
    private String text;
    private Image image;

    public MenuTile(String text, Image backgroundImage){
        this.text = text;
        this.image = backgroundImage;
    }

    public MenuTile(String text){
        this.text = text;
    }

    public MenuTile(Image backgroundImage){
        this.image = backgroundImage;
        this.text = "";
    }
    public MenuTile(){
        this.text = "";
    }
    public void setText(String text){
        this.text = text;
    }
    public void setBackgroundImage(Image image){
        this.image = image;
    }

    @Override
    public void draw(Graphics g, int x, int y, Dimension d){
        g.drawImage(image,x,y, null);
        g.drawString(text, x+2, y+26);
    }
}
