package com.exemple.jogl.SpaceInvaders.model;

import com.jogamp.opengl.GL2;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class GraphicalObject {
  //Color
  protected float red, green, blue = 1;
  
  //Position
  protected float posX, posY, posZ;
  
  //Rotation
  protected float angX, angY, angZ;
  
  //Scale
  protected float scale;
  
  public GraphicalObject(
      float posX, float posY, float posZ, float angX, float angY, float angZ, float scale
  ) {
    this.posX = posX;
    this.posY = posY;
    this.posZ = posZ;
    this.angX = angX;
    this.angY = angY;
    this.angZ = angZ;
    this.scale = scale;
  }
  
  public GraphicalObject(
      float red, float green, float blue, float posX, float posY, float posZ, float angX,
      float angY, float angZ, float scale
  ) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.posX = posX;
    this.posY = posY;
    this.posZ = posZ;
    this.angX = angX;
    this.angY = angY;
    this.angZ = angZ;
    this.scale = scale;
  }
  
  protected abstract void display_normalised(GL2 gl);
  
  public void draw(GL2 gl) {
    gl.glPushMatrix();
      gl.glTranslatef(this.posX, this.posY, this.posZ);
      gl.glScaled(this.scale, this.scale, this.scale);
      
      gl.glRotatef(angX, 1f, 0, 0);
      gl.glRotatef(angY, 0, 1f, 0);
      gl.glRotatef(angZ, 0, 0, 1f);
      
      gl.glColor3f(red, green, blue);
      display_normalised(gl);
      
    gl.glPopMatrix();
  }
  
  public void rotate(float angX, float angY, float angZ) {
    this.angX += angX;
    this.angY += angY;
    this.angZ += angZ;
  }
  
  public void translate(float posX, float posY, float posZ) {
    this.posX += posX;
    this.posY += posY;
    this.posZ += posZ;
  }
  
  public void color(float r, float g, float b) {
    this.red = r;
    this.green = g;
    this.blue = b;
  }
}
