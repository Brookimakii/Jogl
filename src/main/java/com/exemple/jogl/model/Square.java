package com.exemple.jogl.model;

import com.exemple.jogl.GraphicalCube;
import com.jogamp.opengl.GL2;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper=true)
public class Square extends GraphicalObject {
  
  public Square(
      float posX, float posY, float posZ, float angX, float angY, float angZ, float scale
  ) {
    super(posX, posY, posZ, angX, angY, angZ, scale);
  }
  
  public Square(
      float red, float green, float blue, float posX, float posY, float posZ, float angX,
      float angY, float angZ, float scale
  ) {
    super(red, green, blue, posX, posY, posZ, angX, angY, angZ, scale);
  }
  
  @Override
  protected void display_normalised(GL2 gl) {
    gl.glBegin(GL2.GL_QUADS);
    gl.glVertex3f(1f, 1f, 0);
    gl.glVertex3f(-1f, 1f, 0);
    gl.glVertex3f(-1f, -1f, 0);
    gl.glVertex3f(1f, -1f, 0);
    gl.glEnd();
  }
  
}
