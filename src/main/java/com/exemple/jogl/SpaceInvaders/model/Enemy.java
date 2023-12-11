package com.exemple.jogl.SpaceInvaders.model;

import com.exemple.jogl.SpaceInvaders.Main;
import com.jogamp.opengl.GL2;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;

@Getter
public class Enemy extends GraphicalObject {
  private boolean isAlive;
  private int frameUntilShoot;
  
  
  public Enemy() {
    super(0, 0, 0, 0, 0, 0, 1);
    this.isAlive = true;
    this.frameUntilShoot = ThreadLocalRandom.current().nextInt(
        EnemyGroup.INVADER_MIN_SHOOTING_FRAME_DELAY * 20,
        EnemyGroup.INVADER_MAX_SHOOTING_FRAME_DELAY * 20
    );
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
  
  public void die() {
    this.isAlive = false;
  }
  
  public void reload(int reloadTime) {
    this.frameUntilShoot = reloadTime;
  }
}
