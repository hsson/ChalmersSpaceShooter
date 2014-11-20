package lab1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Iterator;

public class SpaceShooterModel extends GameModel{

    public static int tickCount = 0;
    private static int score = 0;

    /** A list of images to be used in the game */

    private static final Image PLAYER_IMAGE = new ImageIcon("img/tile-spaceship.png").getImage();
    private static final Image MENU_BAR_IMAGE = new ImageIcon("img/tile-menu.png").getImage();
    private static final Image SINGLE_BULLET_IMAGE = new ImageIcon("img/tile-singlebullet.png").getImage();
    private static final Image DOUBLE_BULLET_IMAGE = new ImageIcon("img/tile-doublebullet.png").getImage();
    private static final Image TRIPLE_BULLET_IMAGE = new ImageIcon("img/tile-triplebullet.png").getImage();


    /** END OF IMAGE LIST */

    /** A blank tile to fill out the grid not containing anything */
    private static final GameTile BLANK_TILE = new GameTile();

    /** The tile representing the player */
    private static final ImageTile PLAYER_TILE = new ImageTile(PLAYER_IMAGE);

    /** The tile representing the bullets */
    private static final ImageTile SINGLE_BULLET_TILE = new ImageTile(SINGLE_BULLET_IMAGE);
    private static final ImageTile DOUBLE_BULLET_TILE = new ImageTile(DOUBLE_BULLET_IMAGE);
    private static final ImageTile TRIPLE_BULLET_TILE = new ImageTile(TRIPLE_BULLET_IMAGE);

    /** The tiles representing the menu bar*/
    private static final MenuTile MENU_BLANK = new MenuTile(MENU_BAR_IMAGE);
    private static final MenuTile MENU_SCORE_LABEL = new MenuTile("Score:", MENU_BAR_IMAGE);
    private static final MenuTile MENU_HEALTH_LABEL = new MenuTile("Health:", MENU_BAR_IMAGE);
    private MenuTile menuScoreTile = new MenuTile(MENU_BAR_IMAGE);
    private MenuTile menuHealthTile = new MenuTile(MENU_BAR_IMAGE);

    Player player;

    SpawnTile spawnTile;

    public SpaceShooterModel() {
        GameEntity.allGameEntities.clear();
        GreenUfo.instancesList.clear();
        RedUfo.instancesList.clear();
        Ghost.instancesList.clear();
        Bullet.instancesList.clear();
        score = 0;

        Dimension gridSize = getGameboardSize();


        player = new Player(PLAYER_TILE, new Position(gridSize.width / 2, gridSize.height / 4 * 3));
        spawnTile = new SpawnTile(gridSize.width, 0);

        // Fill the grid with blank tiles
        for (int i = 0; i < gridSize.width; i++) {
            for (int j = 0; j < gridSize.height; j++) {
                setGameboardState(i, j, BLANK_TILE);
            }
        }

        // Spawn the menu tiles
        for(int i = 0; i < gridSize.width; i++){
            setGameboardState(i, gridSize.height - 1, MENU_BLANK);
        }

        setGameboardState(0,(int)getGameboardSize().getHeight()-1, MENU_HEALTH_LABEL);
        setGameboardState(1,(int)getGameboardSize().getHeight()-1, menuHealthTile);
        setGameboardState(2,(int)getGameboardSize().getHeight()-1, MENU_SCORE_LABEL);
        setGameboardState(3,(int)getGameboardSize().getHeight()-1, menuScoreTile);
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
                Bullet bullet;
                switch (player.getPlayerBullet()){
                    case SINGLE:
                        bullet = new Bullet(SINGLE_BULLET_TILE, player.getPos(), 1);
                        break;
                    case DOUBLE:
                        bullet = new Bullet(DOUBLE_BULLET_TILE, player.getPos(), 2);
                        break;
                    case TRIPLE:
                        bullet = new Bullet(TRIPLE_BULLET_TILE, player.getPos(), 3);
                        break;
                    default:
                        bullet = new Bullet(SINGLE_BULLET_TILE, player.getPos(), 1); break;
                }
                setGameboardState(bullet.getPos().getX(), bullet.getPos().getY(), bullet.getTile());
                break;
            default:
                // Don't change direction if another key is pressed
                break;
        }
    }

    public static int getScore() {
        return score;
    }

    public static void increaseScore(int amount) {
        score += amount;
    }

    private boolean isOutOfBounds(Position pos) {
        return pos.getX() < 0 || pos.getX() >= getGameboardSize().width
                || pos.getY() < 0 || pos.getY() >= getGameboardSize().height-1;
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
                    if (e1 instanceof RedUfo){
                        ((RedUfo) e1).collisionAction(e2);
                        if(e1.getIsAlive())
                            setGameboardState(e1.getPos(), e1.getTile());
                        else
                            setGameboardState(e1.getPos(), BLANK_TILE);
                    } else if(e1 instanceof GreenUfo){
                        ((GreenUfo) e1).collisionAction(e2);
                        if(e1.getIsAlive())
                            setGameboardState(e1.getPos(), e1.getTile());
                        else
                            setGameboardState(e1.getPos(), BLANK_TILE);
                    } else if(e1 instanceof Ghost){
                        ((Ghost) e1).collisionAction(e2);
                        if(e1.getIsAlive())
                            setGameboardState(e1.getPos(), e1.getTile());
                        else
                            setGameboardState(e1.getPos(), BLANK_TILE);
                    } else if(e1 instanceof Bullet) {
                        ((Bullet) e1).collisionAction(e2);
                    } else if (e1 instanceof PowerUp) {
                        ((PowerUp) e1).collisionAction(e2);
                    }

                }
            }
        }
    }

    @Override
    public void gameUpdate(int lastKey) throws GameOverException {
        tickCount++;

        // Clear the game of dead objects
        for (Iterator<GameEntity> iterator = GameEntity.allGameEntities.iterator(); iterator.hasNext();) {
            GameEntity e = iterator.next();
            if (!e.getIsAlive()) {
                iterator.remove();
            }
        }

        // Spawn monsters and powerups
        spawnTile.spawn();

        // Update score and health
        menuScoreTile.setText(Integer.toString(score));
        menuHealthTile.setText(Integer.toString(player.getHealth()));

        if (player.getHealth() <= 0) {
            throw new GameOverException(score);
        }

        handleKeyPress(lastKey);

        // Everything within this if statement happens every third tick
        if (tickCount % 3 == 0) {

            // Updates player position
            if (!isOutOfBounds(player.getNextPos()) && isPositionEmpty(player.getNextPos())) {
                setGameboardState(player.getPos(), BLANK_TILE);
                player.setPos(player.getNextPos());
                setGameboardState(player.getPos(), PLAYER_TILE);
            } else {
                player.setDirection(Player.Directions.NONE);
                setGameboardState(player.getNextPos(), PLAYER_TILE);
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

            // Update logic for RedUfo
            for (RedUfo ufo : RedUfo.instancesList) {
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

            // Update logic for Ghost
            for (Ghost ghost : Ghost.instancesList) {
                if (ghost.getIsAlive()) {
                    setGameboardState(ghost.getPos(), BLANK_TILE);
                    ghost.setPos(ghost.getNextPos(player.getPos()));
                    if (!isOutOfBounds(ghost.getPos())) {
                        setGameboardState(ghost.getPos(), ghost.getTile());
                    } else {
                        ghost.setIsAlive(false);
                    }
                }
            }

            // Update logic for powerups
            for (PowerUp power : PowerUp.instancesList) {
                if (power.getIsAlive()) {
                    setGameboardState(power.getPos(), BLANK_TILE);
                    power.setPos(power.getNextPos());
                    if (!isOutOfBounds(power.getPos())) {
                        setGameboardState(power.getPos(), power.getTile());
                    } else {
                        power.setIsAlive(false);
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
