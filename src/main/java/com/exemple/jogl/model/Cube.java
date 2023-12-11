package com.exemple.jogl.model;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import java.util.ArrayList;
import lombok.Getter;
import lombok.ToString;

@ToString(callSuper=true)
public class Cube extends GraphicalObject {
  
  //Cube Faces
  private ArrayList<Square> faces = new ArrayList<>();
  
  public Cube(float posX, float posY, float posZ, float angX, float angY, float angZ, float scale) {
    super(posX, posY, posZ, angX, angY, angZ, scale);
    
    faces.add(new Square(1,0,0, 0, 0, 1, 0, 0, 0, 1));
    faces.add(new Square(0,1,0, 0, 0, -1, 0, 0, 0, 1));
    faces.add(new Square(0,0,1, 1, 0, 0, 0, 90, 0, 1));
    faces.add(new Square(1,1,0, -1, 0, 0, 0, -90, 0, 1));
    faces.add(new Square(0,1,1, 0, 1, 0, 90, 0, 0, 1));
    faces.add(new Square(1,0,1, 0, -1, 0, -90, 0, 0, 1));
  }
  
  
  @Override
  protected void display_normalised(GL2 gl) {
    faces.forEach(face -> face.draw(gl));
//    side =
//    side.color(1,0,0);
//    side.draw(gl);
//    side = new Square(0, 0, -1, 0, 0, 0, 1);
//    side.color(0,1,0);
//    side.draw(gl);
//    side = new Square(0, 1, 0, 0, 90, 0, 1);
//    side.color(0,0,1);
//    side.draw(gl);
//    side = new Square(0, -1, 0, 0, -90, 0, 1);
//    side.color(1,1,0);
//    side.draw(gl);
//    side = new Square(1, 0, 0, 90, 0, 0, 1);
//    side.color(0,1,1);
//    side.draw(gl);
//    side = new Square(-1, 0, 0, -90, 0, 0, 1);
//    side.color(1,0,1);
//    side.draw(gl);
  }


//    gl.glScaled(this.scale, this.scale, this.scale);


//    this.up.draw(gl);
//    this.down.draw(gl);
//    this.left.draw(gl);
//    this.right.draw(gl);
//    this.front.draw(gl);
//    this.back.draw(gl);


}
