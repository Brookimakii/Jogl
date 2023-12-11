package com.exemple.jogl.SpaceInvaders.model;

import com.jogamp.opengl.GL2;
import java.util.ArrayList;
import lombok.Getter;

public class Player extends GraphicalObject {
  float limitPosX, limitNegX, limitPosY, limitNegY;
  private float movement;
  
  @Getter private int frameUntilShoot;
  
  
  private static final int SHOOTING_FRAME_DELAY = 30;
  
  
  public Player() {
    super(0, 0, 0, 0, 0, 0, 1);
  }
  
  public void setUp(
      float limitNegX, float limitPosX, float limitNegY, float limitPosY, float movement, float posY
  ) {
    this.posY = posY + movement;
    this.limitPosX = limitPosX;
    this.limitNegX = limitNegX;
    this.limitPosY = limitPosY;
    this.limitNegY = limitNegY;
    this.movement = movement;
    this.frameUntilShoot = SHOOTING_FRAME_DELAY;
  }
  
  @Override
  protected void display_normalised(GL2 gl) {
    frameUntilShoot--;
    
    gl.glBegin(GL2.GL_QUADS);
    gl.glVertex3f(1f, 1f, 0);
    gl.glVertex3f(-1f, 1f, 0);
    gl.glVertex3f(-1f, -1f, 0);
    gl.glVertex3f(1f, -1f, 0);
    gl.glEnd();
  }
  
  public void move(boolean left) {
//    System.out.println(limitNegX + "<= x <=" + limitPosX);
    float futureX = posX;
    futureX += left ? -movement : movement;
    if (futureX < limitNegX || futureX > limitPosX) {
      return;
    }
//    System.out.println("futureX: " + futureX);
//    System.out.println("posX: " + posX);
    posX = futureX;
  }
  
  public void reload(){
    this.frameUntilShoot = SHOOTING_FRAME_DELAY;
  }
}
