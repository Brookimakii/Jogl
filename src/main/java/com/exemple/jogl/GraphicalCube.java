package com.exemple.jogl;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import lombok.Getter;
import lombok.Setter;

public class GraphicalCube {
  
  private float rotation = 0f;
  @Getter @Setter private float scale, posX, posY, posZ = 0f;
  
  
  @Setter float thetaAngle = 0f;
  
  
  public void draw(GLAutoDrawable glAutoDrawable) {
    final GL2 gl = glAutoDrawable.getGL().getGL2();
    gl.glLoadIdentity();
    gl.glTranslatef(posX, posY, posZ - 5.0f);
    
    gl.glRotatef(rotation, 1.0f, 1.0f, 1.0f);
    
    gl.glBegin(GL2.GL_QUADS);
    
    gl.glColor3f(1f, 0f, 0f);
    gl.glVertex3f(1.0f, 1.0f, -1.0f);
    gl.glVertex3f(-1.0f, 1.0f, -1.0f);
    gl.glVertex3f(-1.0f, 1.0f, 1.0f);
    gl.glVertex3f(1.0f, 1.0f, 1.0f);
    
    gl.glColor3f(0f, 1f, 0f);
    gl.glVertex3f(1.0f, -1.0f, 1.0f);
    gl.glVertex3f(-1.0f, -1.0f, 1.0f);
    gl.glVertex3f(-1.0f, -1.0f, -1.0f);
    gl.glVertex3f(1.0f, -1.0f, -1.0f);
    
    gl.glColor3f(0f, 0f, 1f);
    gl.glVertex3f(1.0f, 1.0f, 1.0f);
    gl.glVertex3f(-1.0f, 1.0f, 1.0f);
    gl.glVertex3f(-1.0f, -1.0f, 1.0f);
    gl.glVertex3f(1.0f, -1.0f, 1.0f);
    
    gl.glColor3f(1f, 1f, 0f);
    gl.glVertex3f(1.0f, -1.0f, -1.0f);
    gl.glVertex3f(-1.0f, -1.0f, -1.0f);
    gl.glVertex3f(-1.0f, 1.0f, -1.0f);
    gl.glVertex3f(1.0f, 1.0f, -1.0f);
    
    gl.glColor3f(1f, 0f, 1f);
    gl.glVertex3f(-1.0f, 1.0f, 1.0f);
    gl.glVertex3f(-1.0f, 1.0f, -1.0f);
    gl.glVertex3f(-1.0f, -1.0f, -1.0f);
    gl.glVertex3f(-1.0f, -1.0f, 1.0f);
    
    gl.glColor3f(0f, 1f, 1f);
    gl.glVertex3f(1.0f, 1.0f, -1.0f);
    gl.glVertex3f(1.0f, 1.0f, 1.0f);
    gl.glVertex3f(1.0f, -1.0f, 1.0f);
    gl.glVertex3f(1.0f, -1.0f, -1.0f);
    
    gl.glEnd();
    gl.glFlush();
    
    rotation -= 0.5f;
  }
  
  public void calculateCoord() {
    this.posX = (float) (7 * Math.cos(thetaAngle));
    this.posY = (float) (7 * Math.sin(thetaAngle));
//    System.out.println("[" + posX + ";" + posY + "]");
  }
  
  public void addAngle(float angle) {
    this.thetaAngle += angle;
  }
}
