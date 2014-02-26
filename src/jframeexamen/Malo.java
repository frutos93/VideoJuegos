package jframeexamen;


import java.awt.Image;
import java.awt.Toolkit;

public class Malo extends Base {

    private static int conteo;
    private int speed;

    public Malo(int posX, int posY) {
        super(posX, posY);
        Image malo1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("malo/armor1.png"));
        Image malo2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("malo/armor2.png"));
        animacion = new Animacion();
        animacion.sumaCuadro(malo1, 100);
        animacion.sumaCuadro(malo2, 100);
        speed = (int) ((Math.random() * (6))+ 3);
    }
    

    public static int getConteo() {
        return conteo;
    }

    public static void setConteo(int cont) {
        conteo = cont;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int vel) {
        speed = vel;
    }
}

