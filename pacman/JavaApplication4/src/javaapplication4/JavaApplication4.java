/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;

/**
 *
 * @author small
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
public class JavaApplication4 extends JFrame {

    public JavaApplication4() {
        
        initUI();
    }
    
    private void initUI() {
        Board board=new Board();
        add(board);
        setTitle("Pacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 420);
        setLocationRelativeTo(null);
        setVisible(true);        
    }
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            JavaApplication4 ex = new JavaApplication4();
            ex.setVisible(true);
        });
    }
}
