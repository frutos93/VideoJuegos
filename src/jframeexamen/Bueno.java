package jframeexamen;

      import java.awt.Image;
      import java.awt.Toolkit;

    public class Bueno extends Base{
        
    public Bueno(int posX,int posY){
	super(posX,posY);	
        Image bueno1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("bueno/link1.png"));
        Image bueno2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("bueno/link2.png"));
        Image bueno3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("bueno/link3.png"));
        Image bueno4 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("bueno/link4.png"));
        Image bueno5 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("bueno/link5.png"));
        Image bueno6 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("bueno/link6.png"));
        Image bueno7 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("bueno/link7.png"));
        Image bueno8 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("bueno/link8.png"));
        Image bueno9 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("bueno/link9.png"));
        Image bueno10 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("bueno/link10.png"));
        Image bueno11 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("bueno/link11.png"));
        Image bueno12 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("bueno/link12.png"));
        Image bueno13 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("bueno/link13.png"));
        Image bueno14 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("bueno/link14.png"));
        Image bueno15 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("bueno/link15.png"));
        Image bueno16 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("bueno/link16.png"));
               
        animacion = new Animacion();
        animacion.sumaCuadro(bueno1, 100);
        animacion.sumaCuadro(bueno2, 100);
        animacion.sumaCuadro(bueno3, 100);
        animacion.sumaCuadro(bueno4, 100);
        animacion.sumaCuadro(bueno5, 100);
        animacion.sumaCuadro(bueno6, 100);
        animacion.sumaCuadro(bueno7, 100);
        animacion.sumaCuadro(bueno8, 100);
        animacion.sumaCuadro(bueno9, 100);
        animacion.sumaCuadro(bueno10, 100);
        animacion.sumaCuadro(bueno11, 100);
        animacion.sumaCuadro(bueno12, 100);
        animacion.sumaCuadro(bueno13, 100);
        animacion.sumaCuadro(bueno14, 100);
        animacion.sumaCuadro(bueno15, 100);
        animacion.sumaCuadro(bueno16, 100);
        
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
