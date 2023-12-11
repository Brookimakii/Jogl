package com.exemple.jogl;

import com.exemple.jogl.model.Cube;
import com.exemple.jogl.model.Square;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.Animator;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;

public class MainGl extends GLCanvas implements GLEventListener {
  private float posX;
  private float posY;
  private float posZ;
  private float rotation, rotation2 =0;
  private boolean positifX, positifY, positifZ = true;
  
  private ArrayList<GraphicalCube> cubes = new ArrayList<>();
  
  private Square square, square2;
  private Cube cube, cube2, cube3;
  
  public MainGl() {
    this.addGLEventListener(this);
//    posX = 0;
//    posY = 0;
//    posZ = -25f;
    rotation = 0;
//    GraphicalCube cube1 = new GraphicalCube();
//    cube1.setPosZ(-10);
//    cubes.add(cube1);
//    GraphicalCube cube2 = new GraphicalCube();
//    cube2.setPosX(5);
//    cube2.setPosZ(-20);
//    cubes.add(cube2);
    square = new Square(0, 0, -20, 0, 0, 0, 1);
    square.color(1, 0, 0);
    square2 = new Square(0, 0, -20, 0, 0, 0, 1);
    square2.color(0, 1, 1);
    
    cube = new Cube(0, 0, -20, 0, 0, 0, 1.5f);
    cube2 = new Cube(0, 0, -20, 0, 0, 0, 1);
    cube3 = new Cube(0, 0, -20, 0, 0, 0, 0.5f);
    
  }
  
  public static void main(String[] args) {
    JFrame window = new JFrame("Hello World");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setLocationRelativeTo(null);
    GLCanvas canvas = new MainGl();
    canvas.setPreferredSize(new Dimension(480, 270));
    
    window.getContentPane().add(canvas);
    window.pack();
    window.setVisible(true);
    
    Animator animator = new Animator(canvas);
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
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

//    rotatingSquare(gl);
    
    gl.glPushMatrix();
    
      cube.rotate(-100f, -100f, -100f);
      cube.draw(gl);
      
      gl.glPushMatrix();
        gl.glRotatef(rotation,0,0,1);
        gl.glTranslatef(6, 0, 0);
        cube2.rotate(0.4f, 0.23f, 1);
        cube2.draw(gl);
        
        gl.glPushMatrix();
          gl.glRotatef(rotation2,0,0,-1);
          gl.glTranslatef(3, 0, 0);
          cube3.rotate(-4, -2.3f, -10);
          cube3.draw(gl);
        
        gl.glPopMatrix();
      
      gl.glPopMatrix();
    
    gl.glPopMatrix();

    rotation +=1;
    rotation2 +=10;
//    new Cube().draw(glAutoDrawable);


//    System.out.println("Position: [" + posX + ";" + posY + ";" + posZ + "]");
//    System.out.println((positifZ) + " - " + this.posZ + " > " +  -25 + ": " + (this.posZ > -25));
//    System.out.println(rotX);
//
//    if (positifX && this.posX > 2 * (posZ + 10) + 1) {this.positifX = false;}
//    if (!positifX && this.posX < -2 * (posZ + 10) - 1) {this.positifX = true;}
//    if (positifY && this.posY > 1 * (posZ + 10) + 1) {this.positifY = false;}
//    if (!positifY && this.posY < -1 * (posZ + 10) -1) {this.positifY = true;}
//    if (positifZ && this.posZ > -5) {this.positifZ = false;}
//    if (!positifZ && this.posZ < -50) {this.positifZ = true;}
//
//    this.posX += positifX ? 0.05f : -0.05f;
//    this.posY += positifY ? 0.025f : -0.025f;
//    this.posZ += positifZ ? 0.05f : -0.05f;
//
//    this.rotation += 1f;
//
//    gl.glTranslatef(posX, posY, posZ);
//    gl.glRotatef(rotation, 1.0f, .58f, .42f);
//
//    gl.glBegin(GL2.GL_TRIANGLES);
//
//    gl.glColor3f(.3f, .1f, .7f);
//    gl.glVertex3f(0.0f, 1.0f, 0);
//    gl.glColor3f(.2f, .7f, .3f);
//    gl.glVertex3f(-1.0f, -1.0f, 0);
//    gl.glColor3f(.7f, .1f, .3f);
//    gl.glVertex3f(1.0f, -1.0f, 0);
//
//    gl.glEnd();
//
//  cubes.get(0).draw(glAutoDrawable);
//  GraphicalCube cube2 =  cubes.get(1);
//  cube2.addAngle(0.01f);
//  cube2.calculateCoord();
//  cube2.draw(glAutoDrawable);
  }
  
  private void rotatingSquare(GL2 gl) {
    gl.glPushMatrix();
    
      square.rotate(0, 0, -1);
      square.draw(gl);
      
      gl.glPushMatrix();
        gl.glRotatef(square2.getAngZ(), 0, 0, 1);
        gl.glTranslatef(4, 0, 0);
        square2.rotate(0, 0, 1);
        square2.draw(gl);
      
      gl.glPopMatrix();
    
    gl.glPopMatrix();
  }
  
  @Override
  public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {
    GL2 gl = glAutoDrawable.getGL().getGL2();
    gl.glViewport(0, 0, i2, i3);
    gl.glMatrixMode(GL2.GL_PROJECTION);
    gl.glLoadIdentity();
    
    GLU glu = new GLU();
    glu.gluPerspective(45.0, (float) i2 / i3, 0.1, 100.0);
    gl.glMatrixMode(GL2.GL_MODELVIEW);
    gl.glLoadIdentity();
  }
  
}
