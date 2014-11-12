package lab1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class SpaceShooterModel extends GameModel{

    static private int tickCount = 0;

    /** A list of images to be used in the game */

    private static final Image PLAYER_IMAGE = new ImageIcon("img/tile-spaceship.png").getImage();
    private static final Image GREEN_UFO_IMAGE = new ImageIcon("img/tile-greenufo.png").getImage();
    private static final Image BULLET_IMAGE = new ImageIcon("img/tile-bullet.png").getImage();

    /** END OF IMAGE LIST */

    /** A blank tile to fill out the grid not containing anything */
    private static final GameTile BLANK_TILE = new GameTile();

    /** The tile representing the player */
    private static final ImageTile PLAYER_TILE = new ImageTile(PLAYER_IMAGE);

    /** The tile representing the bullets */
    private static final ImageTile BULLET_TILE = new ImageTile(BULLET_IMAGE);

    /** The tile representing the green ufos */
    private static final ImageTile GREEN_UFO_TILE = new ImageTile(GREEN_UFO_IMAGE);

    Player player;

    public SpaceShooterModel() {
        Dimension gridSize = getGameboardSize();
        player = new Player(PLAYER_TILE, new Position(gridSize.width / 2, gridSize.height / 4 * 3));
        // Fill the grid with blank tiles
        for (int i = 0; i < gridSize.width; i++) {
            for (int j = 0; j < gridSize.height; j++) {
                setGameboardState(i, j, BLANK_TILE);
            }
        }
        GreenUfo testUfo = new GreenUfo(GREEN_UFO_TILE, new Position(gridSize.width/2, 0));
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
                player.setDirection(Player.Directions.WEST);
                break;
            case KeyEvent.VK_UP:
                player.setDirection(Player.Directions.NORTH);
                break;
            case KeyEvent.VK_RIGHT:
                player.setDirection(Player.Directions.EAST);
                break;
            case KeyEvent.VK_DOWN:
                player.setDirection(Player.Directions.SOUTH);
                break;
            case KeyEvent.VK_SPACE:
                Bullet bullet = new Bullet(BULLET_TILE, new Position(player.getPos().getX(), player.getPos().getY()-1));
                setGameboardState(bullet.getPos().getX(), bullet.getPos().getY(), BULLET_TILE);
                break;
            default:
                // Don't change direction if another key is pressed
                break;
        }
    }

    private boolean isOutOfBounds(Position pos) {
        return pos.getX() < 0 || pos.getX() >= getGameboardSize().width
                || pos.getY() < 0 || pos.getY() >= getGameboardSize().height;
    }

    /**
     * This method checks if two GameEntity objects are at
     * the same position in the game.
     *
     * @param e1 The first GameEntity object.
     * @param e2 The second GameEntity object.
     *
     * @return Returns true if the two specified GameEntity
     * objects share the same position.
     *
     * @see lab1.GameEntity
     * @see lab1.Position
     * */
    private boolean isCollisionBetween(GameEntity e1, GameEntity e2) {
        if (e1 == null || e2 == null) {
            return false;
        }

        Position p1 = e1.getPos();
        Position p2 = e2.getPos();

        if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
            // There is a collision
            return true;
        } else {
            // There is no collision
            return false;
        }
    }

    private void performCollisionCheck() {
        for (int i = 0; i < GameEntity.allGameEntities.size(); i++) {
            GameEntity e1 = GameEntity.allGameEntities.get(i);
            for (int j = 0; j < GameEntity.allGameEntities.size(); j++) {
                GameEntity e2 = GameEntity.allGameEntities.get(j);

                if (e1 != e2 && e1.getIsAlive() && e2.getIsAlive() && isCollisionBetween(e1, e2)) {
                    e1.setIsAlive(false);
                    e2.setIsAlive(false);
                    setGameboardState(e1.getPos(), BLANK_TILE);
                }
            }
        }
    }

    @Override
    public void gameUpdate(int lastKey) throws GameOverException {
        tickCount++;

        handleKeyPress(lastKey);

        // Everything within this if statement happens every third tick
        if (tickCount % 3 == 0) {
            // Updates player position
            if (!isOutOfBounds(player.getNextPos()) && isPositionEmpty(player.getNextPos())) {
                setGameboardState(player.getPos(), BLANK_TILE);
                player.setPos(player.getNextPos());
                setGameboardState(player.getPos(), PLAYER_TILE);
            }

            // Update logic for GreenUfo
            for (GreenUfo ufo : GreenUfo.instancesList) {
                if (ufo.getIsAlive()) {
                    setGameboardState(ufo.getPos(), BLANK_TILE);
                    ufo.setPos(ufo.getNextPos());
                    if (!isOutOfBounds(ufo.getPos())) {
                        setGameboardState(ufo.getPos(), ufo.getTile());
                    } else {
                        ufo.setIsAlive(false);
                    }
                }
            }
        }

        performCollisionCheck();

        // Update logic for Bullet
        for (Bullet bullet : Bullet.instancesList){
            if (bullet.getIsAlive()) {
                setGameboardState(bullet.getPos(), BLANK_TILE);
                bullet.setPos(bullet.getNextPos());
                if (!isOutOfBounds(bullet.getPos())) {
                    setGameboardState(bullet.getPos(), bullet.getTile());
                } else {
                    bullet.setIsAlive(false);
                }
            }
        }

        performCollisionCheck();
    }
}
