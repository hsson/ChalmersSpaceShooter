package lab1;

import java.util.ArrayList;

public interface GameEntity {

    public static ArrayList<GameEntity> allGameEntities = new ArrayList<GameEntity>();

    public boolean getIsAlive();
    public void setIsAlive(boolean isAlive);
    public Position getPos();
    public void setPos(Position pos);
    public ImageTile getTile();
}
