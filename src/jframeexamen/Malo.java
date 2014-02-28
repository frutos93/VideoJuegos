package jframeexamen;


import java.awt.Image;
import java.awt.Toolkit;

      import java.awt.Image;
      import java.awt.Toolkit;

    public class Malo extends Base{
        
    public Malo(int posX,int posY){
	super(posX,posY);	
        Image malo1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("malo/mano1.1.png"));
        Image malo2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("malo/mano2.2.png"));
        Image malo3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("malo/mano3.3.png"));
        Image malo4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("malo/mano3.3.png"));
        Image malo5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("malo/mano2.2.png"));
        Image malo6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("malo/mano1.1.png"));
        animacion = new Animacion();
        animacion.sumaCuadro(malo1, 100);
        animacion.sumaCuadro(malo2, 100);
        animacion.sumaCuadro(malo3, 100);
        animacion.sumaCuadro(malo4, 100);
        animacion.sumaCuadro(malo5, 100);
        animacion.sumaCuadro(malo6, 100);
        
	}
    
    private static final String PAUSADO = "PAUSADO";
    private static final String DESAPARECE = "DESAPARECE";
    
    public static String getPausado(){
        return PAUSADO;
    }
    
    public static String getDesaparece(){
        return DESAPARECE;
    }
    
}