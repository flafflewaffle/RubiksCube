/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rubik;

/**
 *
 * @author sophiasingh
 */
public class Rubik {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        RubiksCube rubik = new RubiksCube();
        
        //rubik.front();
        //rubik.rightInverse();
        //rubik.downInverse();
        //rubik.right();
        //rubik.down();
        //rubik.XInverseTurn();
        rubik.shuffle();
                
        System.out.println(rubik);
       
    }
    
}
