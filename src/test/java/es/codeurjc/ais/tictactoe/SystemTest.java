package es.codeurjc.ais.tictactoe;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class SystemTest {

    protected WebDriver navegador1;
    protected WebDriver navegador2;

    @BeforeClass
    public static void setupClass(){
        ChromeDriverManager.getInstance().setup();
    }

    @Before
    public void setupTest(){
        navegador1 = new ChromeDriver();
        navegador1.get("http://localhost:8080/");
        navegador1.findElement(By.id("nickname")).sendKeys("AlMiGad");
        navegador1.findElement(By.id("startBtn")).click();

        navegador2 = new ChromeDriver();
        navegador2.get("http://localhost:8080/");
        navegador2.findElement(By.id("nickname")).sendKeys("Laya");
        navegador2.findElement(By.id("startBtn")).click();
    }

    @After
    public void teardown(){
        if(navegador1 != null){
            navegador1.quit();
        }
        if(navegador2 != null){
            navegador2.quit();
        }
    }

    @Test
    public void ganaJugador1(){
        //Inicia Jugador 1
        String player1 = navegador1.findElement(By.id("p1Score")).getText();

        //Inicia Jugador 2
        String player2 = navegador2.findElement(By.id("p2Score")).getText();

        assertThat(player1).isEqualTo("X AlMiGad");
        assertThat(player2).isEqualTo("Laya O");


        //Player 1 mueve ficha 1,1
        navegador1.findElement(By.id("cell-0")).click();

        //Player 2 mueve ficha 1,3
        navegador2.findElement(By.id("cell-2")).click();

        //Player 1 mueve ficha 2,1
        navegador1.findElement(By.id("cell-3")).click();

        //Player 2 mueve ficha 2,3
        navegador2.findElement(By.id("cell-5")).click();

        //Player 1 mueve ficha 3,1
        navegador1.findElement(By.id("cell-6")).click();

        //Player 1 ha Ganado
        String alert1 = navegador1.switchTo().alert().getText();
        String alert2 = navegador2.switchTo().alert().getText();

        assertThat(alert1).isEqualTo("AlMiGad wins! Laya looses.");
        assertThat(alert2).isEqualTo("AlMiGad wins! Laya looses.");
    }

    @Test
    public void ganaJugador2(){
        //Inicia Jugador 1
        String player1 = navegador1.findElement(By.id("p1Score")).getText();
        //Inicia Jugador 2
        String player2 = navegador2.findElement(By.id("p2Score")).getText();

        assertThat(player1).isEqualTo("X AlMiGad");
        assertThat(player2).isEqualTo("Laya O");


        //Player 1 mueve ficha 1,1
        navegador1.findElement(By.id("cell-0")).click();

        //Player 2 mueve ficha 1,3
        navegador2.findElement(By.id("cell-2")).click();

        //Player 1 mueve ficha 2,1
        navegador1.findElement(By.id("cell-3")).click();

        //Player 2 mueve ficha 2,3
        navegador2.findElement(By.id("cell-5")).click();

        //Player 1 mueve ficha 2,1
        navegador1.findElement(By.id("cell-1")).click();

        //Player 2 mueve ficha 3,3
        navegador2.findElement(By.id("cell-8")).click();

        //Player 2 ha Ganado
        String alert1 = navegador1.switchTo().alert().getText();
        String alert2 = navegador2.switchTo().alert().getText();

        assertThat(alert1).isEqualTo("Laya wins! AlMiGad looses.");
        assertThat(alert2).isEqualTo("Laya wins! AlMiGad looses.");
    }

    @Test
    public void empate(){
        //Inicia Jugador 1
        String player1 = navegador1.findElement(By.id("p1Score")).getText();
        //Inicia Jugador 2
        String player2 = navegador2.findElement(By.id("p2Score")).getText();

        assertThat(player1).isEqualTo("X AlMiGad");
        assertThat(player2).isEqualTo("Laya O");


        //Player 1 mueve ficha
        navegador1.findElement(By.id("cell-0")).click();

        //Player 2 mueve ficha
        navegador2.findElement(By.id("cell-2")).click();

        //Player 1 mueve ficha
        navegador1.findElement(By.id("cell-4")).click();

        //Player 2 mueve ficha
        navegador2.findElement(By.id("cell-3")).click();

        //Player 1 mueve ficha
        navegador1.findElement(By.id("cell-5")).click();

        //Player 2 mueve ficha
        navegador2.findElement(By.id("cell-1")).click();

        //Player 1 mueve ficha
        navegador1.findElement(By.id("cell-6")).click();

        //Player 2 mueve ficha
        navegador2.findElement(By.id("cell-8")).click();

        //Player 1 mueve ficha
        navegador1.findElement(By.id("cell-7")).click();

        //Empate
        String alert1 = navegador1.switchTo().alert().getText();
        String alert2 = navegador2.switchTo().alert().getText();

        assertThat(alert1).isEqualTo("Draw!");
        assertThat(alert2).isEqualTo("Draw!");
    }
}
