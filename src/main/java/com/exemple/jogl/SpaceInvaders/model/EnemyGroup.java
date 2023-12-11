package com.exemple.jogl.SpaceInvaders.model;

import com.jogamp.opengl.GL2;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class EnemyGroup extends GraphicalObject {
  @Setter private Enemy[][] enemies;
  private float posX, posY;
  private final float spacingX, spacingY;
  
  private int invaderAlive = 0;
  
  private int frameLeftUntilMove;
  
  private static final int INVADER_MOUVEMENT_FRAME_DELAY = 2;
  protected static final int INVADER_MIN_SHOOTING_FRAME_DELAY = 10;
  protected static final int INVADER_MAX_SHOOTING_FRAME_DELAY = 30;
  
  private boolean isGoingLeft = false;
  float maxX, minX, maxY;
  
  public EnemyGroup(
      float posX, float posY, float posZ, float angX, float angY, float angZ, float scale,
      float spacing
  ) {
    super(posX, posY, posZ, angX, angY, angZ, scale);
    this.spacingX = (float) (spacing * 1.25);
    this.spacingY = (float) (spacing * 0.75);
  }
  
  public void finalizeGroup(float minX, float maxX) {
    int maxWidth = Arrays.stream(enemies).map(row -> row.length).max(Integer::compareTo).get();
    float groupWidth = maxWidth * scale + spacingX * (maxWidth - 1);
    float groupHeight = enemies.length * scale + spacingY * (enemies.length - 1);
    
    this.minX = (float) (minX / scale - scale);
    this.maxX = (float) (maxX / scale - groupWidth - scale * 1.5);
    posX = (this.maxX + this.minX) / 2;
    
    
    invaderAlive = invaderLeftAlive();
    frameLeftUntilMove = invaderAlive * 8;


//    posX = (maxX+minX)/2;
    posY = 0 + groupHeight / 4 + scale;
    
    for (Enemy[] enemyRow : enemies) {
      for (Enemy enemy : enemyRow) {
        enemy.setRed(1f);
        enemy.setGreen(0f);
        enemy.setBlue(1f);
        enemy.setScale(scale);
        enemy.setPosZ(posZ);
//        System.out.println(enemy);
      }
    }
  }
  
  
  public void move() {
    frameLeftUntilMove--;
    if (frameLeftUntilMove == 0) {
      movement();
      frameLeftUntilMove = invaderLeftAlive() * INVADER_MOUVEMENT_FRAME_DELAY;
    }
    
//    System.out.println(posX + ";" + posY);
    
    for (int i = 0; i < enemies.length; i++) {
      for (int j = 0; j < enemies[i].length; j++) {
        
        Enemy enemy = enemies[i][j];
        enemy.setPosX(posX + j + spacingX * scale * j);
        enemy.setPosY(posY + i + spacingY * scale * i);
//        System.out.println("[" + enemy.posX + ";" + enemy.posY + ";" + enemy.posZ + "]");
      }
    }
//    System.out.println("---------------------------------------");
  }
  
  public int invaderLeftAlive() {
    int number = 0;
    
    
    for (Enemy[] enemyRow : enemies) {
      for (Enemy enemy : enemyRow) {
        number += enemy.isAlive() ? 1 : 0;
      }
    }
    return number;
  }
  
  private void movement() {
//    System.out.println(posX + ";" + posY);
    
    float futureX = posX;
    futureX += isGoingLeft ? -scale : scale;
    if (futureX < minX || futureX > maxX) {
      posY -= scale;
      isGoingLeft = !isGoingLeft;
    } else {
      posX += isGoingLeft ? -scale : scale;
    }
  }
  
  @Override
  protected void display_normalised(GL2 gl) {
  }
  
  public void show(GL2 gl){
    for (Enemy[] enemyRow : enemies) {
      for (Enemy enemy : enemyRow) {
        if (enemy.isAlive()){
          enemy.draw(gl);
        }
      }
    }
  }
  
  public void enemyReload(Enemy enemy){
    enemy.reload(
        ThreadLocalRandom.current().nextInt(INVADER_MIN_SHOOTING_FRAME_DELAY * invaderLeftAlive(), INVADER_MAX_SHOOTING_FRAME_DELAY * invaderLeftAlive())
    );
  }
}
