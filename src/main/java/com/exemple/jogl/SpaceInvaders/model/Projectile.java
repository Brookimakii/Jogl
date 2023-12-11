package com.exemple.jogl.SpaceInvaders.model;

import com.jogamp.opengl.GL2;
import lombok.Getter;

public class Projectile extends GraphicalObject {
  private final float maxY;
  @Getter boolean isHostile;
  GraphicalObject origin;
  private final float minY;
  
  private static final float PROJECTILE_SPEED = 1f;
  
  public Projectile(
      float posX, float posY, float posZ, float angX, float angY, float angZ, float scale,
      boolean isHostile, float minY, float maxY
  ) {
    super(posX, posY, posZ, angX, angY, angZ, scale);
    this.isHostile = isHostile;
    this.maxY = maxY;
    this.minY = minY;
  }
  
  @Override
  protected void display_normalised(GL2 gl) {
//    System.out.println(this);
    gl.glBegin(GL2.GL_QUADS);
    gl.glVertex3f(1f, 1f, 0);
    gl.glVertex3f(-1f, 1f, 0);
    gl.glVertex3f(-1f, -1f, 0);
    gl.glVertex3f(1f, -1f, 0);
    gl.glEnd();
  }
  
  public void move() {
//    System.out.println("Old Y: " + posY);
    posY += isHostile ? (float) (-PROJECTILE_SPEED * 0.5) : PROJECTILE_SPEED;
//    System.out.println("New Y: " + posY);
//    System.out.println("_________________");
  }
  
}
