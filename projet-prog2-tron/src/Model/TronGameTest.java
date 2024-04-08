package Model;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TronGameTest {

    private TronGame testTronGame;

    @Before
    public void setUp(){

        testTronGame = new TronGame(800,800);
        testTronGame.restartGame();
        testTronGame.setGameLives(1);
    }

    @Test
    public void testGetPlayer1VelocityY(){
        assertEquals(1,testTronGame.getPlayer1().getVelocityY());
    }


    @Test
    public void testMove(){
        //position initiale du joueur 1 est à 5
        assertEquals(5,testTronGame.getPlayer1().getY());

        //on fait 5 itération de mouvement, le joueur 1 se déplace par défaut dans l'angle des y
        for (int i = 0; i < 5; i++) {
            testTronGame.move();
        }

        assertEquals(10,testTronGame.getPlayer1().getY());

    }

    @Test
    public void testIsGameOver(){

        //on fait 26 itération de mouvement, les joueurs se déplacent par défaut dans l'angle des y et aura une colision avec un mur
        for (int i = 0; i < 26; i++) {
            testTronGame.move();
        }

        assertTrue(testTronGame.isGameOver());

    }

    @Test
    public void testGetWinnerDraw(){

        //on fait 26 itération de mouvement, les joueurs se déplacent par défaut dans l'angle des y et aura une colision avec un mur
        for (int i = 0; i < 26; i++) {
            testTronGame.move();
        }

        //la partie devrait être terminée
        assertTrue(testTronGame.isGameOver());

        assertEquals("The match is a draw!",testTronGame.getWinner());
    }

    @Test
    public void testGetWinnerPlayer1(){

        //on fait 26 itération de mouvement, les joueurs se déplacent par défaut dans l'angle des y et aura une colision avec un mur
        for (int i = 0; i < 26; i++) {

            if(i==20)
            {
                //fait tourner le joueur 1 vers la droite avant qu'il ne crash dans le mur
                testTronGame.getPlayer1().setVelocityY(0);
                testTronGame.getPlayer1().setVelocityX(1);
            }

            testTronGame.move();

        }

        //la partie devrait être terminée
        assertTrue(testTronGame.isGameOver());

        assertEquals("Player 1 wins!",testTronGame.getWinner());
    }

    @Test
    public void testGetWinnerPlayer2(){

        //on fait 26 itération de mouvement, les joueurs se déplacent par défaut dans l'angle des y et aura une colision avec un mur
        for (int i = 0; i < 26; i++) {

            if(i==20)
            {
                //fait tourner le joueur 2 vers la gauche avant qu'il ne crash dans le mur
                testTronGame.getPlayer2().setVelocityY(0);
                testTronGame.getPlayer2().setVelocityX(-1);
            }

            testTronGame.move();

        }

        //la partie devrait être terminée
        assertTrue(testTronGame.isGameOver());

        assertEquals("Player 2 wins!",testTronGame.getWinner());
    }


}
