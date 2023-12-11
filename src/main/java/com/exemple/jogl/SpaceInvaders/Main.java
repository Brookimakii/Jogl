package com.exemple.jogl.SpaceInvaders;

import com.exemple.jogl.MainGl;
import com.exemple.jogl.SpaceInvaders.model.Enemy;
import com.exemple.jogl.SpaceInvaders.model.EnemyGroup;
import com.exemple.jogl.SpaceInvaders.model.Player;
import com.exemple.jogl.SpaceInvaders.model.Projectile;
import com.exemple.jogl.listerner.KeysListener;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import lombok.Getter;

@Getter
public class Main extends GLCanvas implements GLEventListener {
  static Animator animator;
  
  float theta;
  float aspectRatio;
  
  float posZ = -50f;
  
  
  EnemyGroup enemyGroup;
  Player player;
  ArrayList<Projectile> projectiles = new ArrayList<>();
  
  public Main() throws GLException {
    this.addGLEventListener(this);
    enemyGroup = new EnemyGroup(0, 0, posZ, 0, 0, 0, 1.5f, 3f);
    enemyGroup.setEnemies(new Enemy[][] {
        {new Enemy(), new Enemy(), new Enemy(), new Enemy(), new Enemy(),},
        {new Enemy(), new Enemy(), new Enemy(), new Enemy(), new Enemy(),},
        {new Enemy(), new Enemy(), new Enemy(), new Enemy(), new Enemy(),},
        {new Enemy(), new Enemy(), new Enemy(), new Enemy(), new Enemy(),}
    });
    this.player = new Player();
    player.setRed(0f);
    player.setGreen(1f);
    player.setBlue(1f);
    this.addKeyListener(new KeysListener(this));
    this.requestFocus();
    this.requestFocusInWindow();
  }
  
  public static void main(String[] args) {
    JFrame window = new JFrame("Hello World");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setLocationRelativeTo(null);
    GLCanvas canvas = new Main();
    canvas.setPreferredSize(new Dimension(480, 270));
    
    window.getContentPane().add(canvas);
    window.pack();
    window.setVisible(true);
    
    animator = new Animator(canvas);
    animator.start();
  }
  
  @Override
  public void init(GLAutoDrawable glAutoDrawable) {
    GL2 gl = glAutoDrawable.getGL().getGL2();
    gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    gl.glClearDepth(1.0f);
    gl.glEnable(GL2.GL_DEPTH_TEST);
    gl.glDepthFunc(GL2.GL_LEQUAL);
    gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
    
  }
  
  @Override
  public void dispose(GLAutoDrawable glAutoDrawable) {
  
  }
  
  @Override
  public void display(GLAutoDrawable glAutoDrawable) {
    final GL2 gl = glAutoDrawable.getGL().getGL2();
    if (enemyGroup.invaderLeftAlive() <= 0) {
      win();
    }
    
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
    
    enemyGroup.move();
    
    
    for (Enemy[] enemyRow : enemyGroup.getEnemies()) {
      for (Enemy enemy : enemyRow) {
//        System.out.println(enemy.getFrameUntilShoot());
        if (enemy.isAlive() & enemy.getFrameUntilShoot() <= 0) {
          enemyShoot(enemy);
          enemyGroup.enemyReload(enemy);
        }
      }
    }
    
    gl.glPushMatrix();
    
    //Enemies Spawn
    gl.glPushMatrix();
    enemyGroup.show(gl);
    gl.glPopMatrix();
    
    
    //Player Spawn
    gl.glPushMatrix();
    player.draw(gl);
    gl.glPopMatrix();
    
    
    //Projectile Spawn
    gl.glPushMatrix();
    for (int i = 0; i < projectiles.size(); i++) {
      Projectile projectile = projectiles.get(i);
      projectile.move();
      projectile.draw(gl);
      if (projectileEndOfLife(projectile)) {
        projectiles.remove(projectile);
      }
      projectileTouchEnemy(projectile);
      if (projectileTouchPlayer(projectile)) {
        System.out.println("Player touch by enemy Projectile");
        lose();
      }
    }
    gl.glPopMatrix();
    
    gl.glPopMatrix();
  }
  
  @Override
  public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
    System.out.println("HERE");
    GL2 gl = glAutoDrawable.getGL().getGL2();
    gl.glViewport(0, 0, i2, i3);
    gl.glMatrixMode(GL2.GL_PROJECTION);
    gl.glLoadIdentity();
    
    GLU glu = new GLU();
    
    float fov = 45.0f;
    theta = (float) (Math.tan(fov / 2) * 2);
    aspectRatio = (float) i2 / i3;
    
    glu.gluPerspective(fov, (float) i2 / i3, 0.1, 100.0);
    gl.glMatrixMode(GL2.GL_MODELVIEW);
    gl.glLoadIdentity();
    
    
    enemyGroup.finalizeGroup(minX(posZ), maxX(posZ));
    player.setUp(minX(posZ) / enemyGroup.getScale(), maxX(posZ) / enemyGroup.getScale(), minY(posZ),
        maxY(posZ), enemyGroup.getScale(), minY(posZ) / enemyGroup.getScale()
    );
    player.setScale(enemyGroup.getScale());
    player.setPosZ(posZ);
//    System.out.println(enemyGroup);
  }
  
  public float minX(float posZ) {
    return posZ * aspectRatio * theta / 2;
  }
  
  public float minY(float posZ) {
    return posZ * theta / 2;
  }
  
  public float maxX(float posZ) {
    return posZ * aspectRatio * -theta / 2;
  }
  
  public float maxY(float posZ) {
    return posZ * -theta / 2;
  }
  
  
  public void shoot(Player player) {
//    System.out.println("frameUntilShoot: " + player.getFrameUntilShoot());
    if (player.getFrameUntilShoot() <= 0) {
      player.reload();
      Projectile p =
          new Projectile(player.getPosX(), player.getPosY(), posZ, 0, 0, 0,
              (float) (enemyGroup.getScale() * 0.5),
              false, minY(posZ) / enemyGroup.getScale(), maxY(posZ) / enemyGroup.getScale()
          );
      
      p.setRed(1f);
      p.setGreen(1f);
      p.setBlue(1f);
      projectiles.add(p);
    }
//    gl.glPushMatrix();
//    for (int i = 0; i < projectiles.size(); i++) {
//      Projectile projectile = projectiles.get(i);
////        System.out.println(projectile);
//      projectile.move();
//      projectile.draw(gl);
//    }
//    gl.glPopMatrix();
//    System.out.println("PosY: " + posY + "; MaxY: " + maxY + " -> OOB: " + (posY>maxY));
//    if (posY<minY || posY>maxY){
//      if (origin instanceof Player){
//        ((Player) origin).deleteProjectile(this);
//      } else if (origin instanceof Enemy) {
//        //
//      }
//
//      return;
//    }
  }
  
  public void enemyShoot(Enemy enemy) {
    Projectile p =
        new Projectile(enemy.getPosX(), enemy.getPosY(), posZ, 0, 0, 0, (float) (enemyGroup.getScale() * 0.5), true,
            minY(posZ) / enemyGroup.getScale(), maxY(posZ) / enemyGroup.getScale()
        );
    p.setRed(1f);
    p.setGreen(0f);
    p.setBlue(0f);
    projectiles.add(p);
  }
  
  
  private boolean projectileEndOfLife(Projectile projectile) {
//    System.out.println(projectile.getPosY() < minY(posZ) || projectile.getPosY() > maxY(posZ));
    return (projectile.getPosY() < minY(posZ) || projectile.getPosY() > maxY(posZ));
  }
  
  private boolean projectileTouchPlayer(Projectile projectile) {
    if (!projectile.isHostile()) {return false;}
    return (
        Math.hypot(player.getPosX() - projectile.getPosX(), player.getPosY() - projectile.getPosY())
        < player.getScale() + projectile.getScale());
  }
  
  private void projectileTouchEnemy(Projectile projectile) {
    if (projectile.isHostile()) {return;}
    for (Enemy[] enemyRow : enemyGroup.getEnemies()) {
      for (Enemy enemy : enemyRow) {
        if (!enemy.isAlive()) {
          continue;
        }
        if (Math.hypot(enemy.getPosX() - projectile.getPosX(),
            enemy.getPosY() - projectile.getPosY()
        ) < enemy.getScale() + projectile.getScale()) {
//          System.out.println("Enemy     : " + enemy);
//          System.out.println("Projectile: " + projectile);
          projectiles.remove(projectile);
          enemy.die();
          return;
        }
      }
//      System.out.println("---------------------");
    }
//    System.out.println("\n\n\n");
  }
  
  private void win() {
    System.out.println("You Win !");
    animator.stop();
  }
  
  private void lose() {
    System.out.println("You Lose !");
    animator.stop();
  }
}
