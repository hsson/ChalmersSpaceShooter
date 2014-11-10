package lab1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class SpaceShooterModel extends GameModel{

    static private int tickCount = 0;

    public enum Directions {
        EAST(1, 0),
        WEST(-1, 0),
        NORTH(0, -1),
        SOUTH(0, 1),
        NONE(0, 0);

        private final int xDelta;
        private final int yDelta;

        Directions(final int xDelta, final int yDelta) {
            this.xDelta = xDelta;
            this.yDelta = yDelta;
        }

        public int getXDelta() {
            return this.xDelta;
        }

        public int getYDelta() {
            return this.yDelta;
        }
    }

    /** A blank tile to fill out the grid not containing anything */
    private static final GameTile BLANK_TILE = new GameTile();

    /** The tile representing the player */
    private static final PlayerTile PLAYER_TILE = new PlayerTile();

    /** The image representing the instancesList */
    private static final ImageTile BULLET_TILE = new ImageTile(new ImageIcon("img/tile-bullet.png").getImage());

    /** The position of the player. */
    private Position playerPosition;

    /** The direction of the player. */
    private Directions playerDirection = Directions.NORTH;

    public SpaceShooterModel() {
        Dimension gridSize = getGameboardSize();

        // Fill the grid with blank tiles
        for (int i = 0; i < gridSize.width; i++) {
            for (int j = 0; j < gridSize.height; j++) {
                setGameboardState(i, j, BLANK_TILE);
            }
        }

        // Player starter position
        this.playerPosition = new Position(gridSize.width / 2, gridSize.height / 4 * 3);
        setGameboardState(this.playerPosition, PLAYER_TILE);
    }

    private boolean isPositionEmpty(final Position pos) {
        return (getGameboardState(pos) == BLANK_TILE);
    }

    /**
     * Handle the keypresses made by the users
     */
    private void handleKeyPress(final int key) {
        switch (key) {
            case KeyEvent.VK_LEFT:
                this.playerDirection = Directions.WEST;
                break;
            case KeyEvent.VK_UP:
                this.playerDirection = Directions.NORTH;
                break;
            case KeyEvent.VK_RIGHT:
                this.playerDirection = Directions.EAST;
                break;
            case KeyEvent.VK_DOWN:
                this.playerDirection = Directions.SOUTH;
                break;
            case KeyEvent.VK_SPACE:
                // TODO: Shoot here
                Bullet bullet = new Bullet(new Position(playerPosition.getX(), playerPosition.getY()-1), BULLET_TILE);
                setGameboardState(bullet.getPos().getX(), bullet.getPos().getY(), BULLET_TILE);
                break;
            default:
                // Don't change direction if another key is pressed
                break;
        }
    }

    /**
     * Get next position of the player.
     */
    private Position getNextPlayerPosition() {
        return new Position(
                this.playerPosition.getX() + this.playerDirection.getXDelta(),
                this.playerPosition.getY() + this.playerDirection.getYDelta());
    }

    private boolean isOutOfBounds(Position pos) {
        return pos.getX() < 0 || pos.getX() >= getGameboardSize().width
                || pos.getY() < 0 || pos.getY() >= getGameboardSize().height;
    }

    @Override
    public void gameUpdate(int lastKey) throws GameOverException {
        tickCount++;

        handleKeyPress(lastKey);
        if (tickCount % 3 == 0 && !isOutOfBounds(getNextPlayerPosition()) && isPositionEmpty(getNextPlayerPosition())) {
            setGameboardState(playerPosition, BLANK_TILE);
            playerPosition = getNextPlayerPosition();
            setGameboardState(playerPosition, PLAYER_TILE);
        }
        for(int i = 0; i < Bullet.instancesList.size(); i++){
            Bullet bullet = Bullet.instancesList.get(i);


            setGameboardState(bullet.getPos(), BLANK_TILE);
            bullet.setPos(bullet.getNextPos());
            if(!isOutOfBounds(bullet.getPos())) {
                setGameboardState(bullet.getPos(), BULLET_TILE);
            }
            if(isOutOfBounds(bullet.getPos())){
                Bullet.instancesList.remove(i);
            }

        }
    }
}
