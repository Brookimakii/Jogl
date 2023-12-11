package com.exemple.jogl.listerner;

import com.exemple.jogl.SpaceInvaders.Main;
import com.exemple.jogl.SpaceInvaders.model.Player;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeysListener implements KeyListener {
  private final Main main;
  
  public KeysListener(Main main) {
    this.main = main;
  }
  
  @Override
  public void keyPressed(KeyEvent e) {
    Player player = main.getPlayer();
    // Handle key press events
    int keyCode = e.getKeyCode();
//    System.out.println("Key pressed: " + KeyEvent.getKeyText(keyCode));
    switch (keyCode) {
      case KeyEvent.VK_LEFT, KeyEvent.VK_Q -> {
        player.move(true);
//        System.out.println("Left: " + player.getPosX() + ";" + player.getPosY());
      }
      case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> {
//        System.out.println("Right: " + player.getPosX() + ";" + player.getPosY());
        player.move(false);
      }
      case KeyEvent.VK_UP, KeyEvent.VK_Z -> {
        main.shoot(player);
      }
    }
  }
  
  @Override
  public void keyReleased(KeyEvent e) {
    // Handle key release events
    int keyCode = e.getKeyCode();
//    System.out.println("Key released: " + KeyEvent.getKeyText(keyCode));
  }
  
  @Override
  public void keyTyped(KeyEvent e) {
    // Handle key typed events
    char keyChar = e.getKeyChar();
//    System.out.println("Key Typed: " + KeyEvent.getKeyText(keyChar));
  }
}
