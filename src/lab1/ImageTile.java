package lab1;

import java.awt.*;

public class ImageTile extends GameTile {

    private final Image tileImage;

    public ImageTile(Image tileImage){
        this.tileImage = tileImage;
    }

    @Override
    public void draw(final Graphics g, final int x, final int y, Dimension d) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.tileImage, x, y, d.width, d.height, null);
    }
}
