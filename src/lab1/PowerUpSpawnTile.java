package lab1;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PowerUpSpawnTile extends GameTile{

    private static final int[] LEVEL = {0, 5, 15, 40, 100, 250};
    private int level = 1;
    private static final Image DD_IMAGE = new ImageIcon("img/tile-greenufo.png").getImage();
    private static final Image HP_IMAGE = new ImageIcon("img/tile-redufo.png").getImage();
    private static final Image GHOST_IMAGE = new ImageIcon("img/tile-ghost.png").getImage();

    /** The tile representing the powerUps **/
    private static final ImageTile DD_TILE = new ImageTile(DD_IMAGE);
    private static final ImageTile RED_UFO_TILE = new ImageTile(HP_IMAGE);
    private static final ImageTile GHOST_TILE = new ImageTile(GHOST_IMAGE);

    private int score;
    private int x;
    private int y;

    private double spawnRate;

    private double ddSpawnRate;     //dd = double damage
    private double hpUpSpawnRate;
    private double ghostSpawnRate;  //TODO: More PowerUps, Everyone loves powerups
    private double tankSpawnRate;
    private double smartSpawnRate;

    Random gen;

    public PowerUpSpawnTile(int x, int y){
        this.score = SpaceShooterModel.getScore();
        this.x = x;
        this.y = y;
        gen = new Random();
    }

    public int getDeltaScore(int score){
        return score-LEVEL[level-1];
    }

    //TODO: Fix other names for the new powerups
    
    private void setSpawnRate(int score){
        if(level == 1){
            spawnRate = 0.01;
            ddSpawnRate = 1;
            hpUpSpawnRate = 0;
            ghostSpawnRate = 0;
            tankSpawnRate = 0;
            smartSpawnRate = 0;
        }else if(level == 2){
            spawnRate = 0.02;
            ddSpawnRate = 0.65;
            hpUpSpawnRate = 0.35;
            ghostSpawnRate = 0;
            tankSpawnRate = 0;
            smartSpawnRate = 0;
        }else if(level == 3){
            spawnRate = 0.03;
            ddSpawnRate = 0.3;
            hpUpSpawnRate = 0.5;
            ghostSpawnRate = 0.1;
            tankSpawnRate = 0;
            smartSpawnRate = 0;
        }else if(level == 4){
            spawnRate = 0.25+0.005*getDeltaScore(score);
            ddSpawnRate = 0;
            hpUpSpawnRate = 0.7;
            ghostSpawnRate = 0.25;
            tankSpawnRate = 0.05;
            smartSpawnRate = 0;
        }else if(level == 5){
            spawnRate = 0.4+0.0025*getDeltaScore(score);
            ddSpawnRate = 0;
            hpUpSpawnRate = 0.5;
            ghostSpawnRate = 0.3;
            tankSpawnRate = 0.1;
            smartSpawnRate = 0.1;
        }else{
            spawnRate = 0.6+0.001*getDeltaScore(score);
            ddSpawnRate = 0;
            hpUpSpawnRate = 0;
            ghostSpawnRate = 0.5-0.2*getDeltaScore(score);
            tankSpawnRate = 0.3+0.1*getDeltaScore(score);
            smartSpawnRate = 0.2+0.1*getDeltaScore(score);
        }
    }

    public void setLevel(int score){
        if(score > LEVEL[level] && level < LEVEL.length - 1){
            level++;
        }
    }

    public void spawnMonster(){
        this.score = SpaceShooterModel.getScore();
        setLevel(score);
        setSpawnRate(score);
        double chanceToSpawnMonster = gen.nextDouble();
        if(chanceToSpawnMonster < spawnRate){
            int xPosition = gen.nextInt(15);
            double chanceToSpawnType = gen.nextDouble();
            if(chanceToSpawnType < ddSpawnRate){
                new GreenUfo(DD_TILE, new Position(xPosition, this.y));     //TODO: DD class implementation missing
            }else if(chanceToSpawnType < hpUpSpawnRate+ddSpawnRate){
                new RedUfo(RED_UFO_TILE, new Position(xPosition, this.y));  //TODO: HPup class implementation missing
            }else if(chanceToSpawnType < ghostSpawnRate+hpUpSpawnRate+ddSpawnRate) {
                new Ghost(GHOST_TILE, new Position(xPosition, this.y));
            }else if(chanceToSpawnType < tankSpawnRate+ghostSpawnRate+hpUpSpawnRate+ddSpawnRate){
                //TODO: NEW TANK
            }else if(chanceToSpawnType < smartSpawnRate+tankSpawnRate+ghostSpawnRate+hpUpSpawnRate+ddSpawnRate){
                //TODO: NEW SMART
            }
        }

    }

}
