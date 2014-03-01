package jframeexamen;

import JFrameExamen.SoundClip;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class JFrameExamen extends JFrame implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    private static final long serialVersionUID = 1L;
    // Se declaran las variables. 
    private Image dbImage;	// Imagen a proyectar	
    private Graphics dbg;	// Objeto grafico
    private SoundClip sonido;    // Objeto AudioClip
    private SoundClip bomb;    //Objeto AudioClip
    private SoundClip payaso;
    private Bueno link;    // Objeto de la clase Elefante
    private Malo mano;   //Objeto de la clase Raton
    private LinkedList lista;           //lista de ratones
    private int cant;               //cantidad de asteroides
    private boolean presionado;     //Boleano que controla si el bot´´oon está siendo presionado
    private boolean musicafondo;
    private int coordenada_x;
    private int coordenada_y;
    private int off_x;              //Coordenada para ajustar la imagen con click
    private int off_y;              //Coordenada para ajustar la imagen con click 
    private int vidas;
    private int contvidas;
    private Image game_over;        //Imagen de Game-over
    private int direccion;          //Variable para la dirección del personaje
    private int posrX;
    private int posrY;
    private int score;
    private int cont;
    private int x_mayor;
    private int x_menor;
    private int y_mayor;
    private int y_menor;
    private boolean flag;
    private boolean move;
    private boolean pausa;
    private long tiempoActual;
    private String nombreArchivo;
    private double z; //posición y
    private double z0; //posición y inicial
    private double vz0; //velocidad y inicial;
    private double x;// posición x;
    private double x0; //posicion x inicial;
    private double vx0; //velocidad x inicial ;
    private double velocidadInicial;
    private double tiempo;
    private boolean puedoDisparar;
    private int angulo;

    /**
     * Metodo <I>init</I> sobrescrito de la clase <code>Applet</code>.<P>
     * En este metodo se inizializan las variables o se crean los objetos a
     * usarse en el <code>Applet</code> y se definen funcionalidades.
     */
    public JFrameExamen() {
        init();
        start();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void init() {
        nombreArchivo = "datos.txt";
        setSize(800, 500);
        pausa = false;
        move = false;
        musicafondo = false;
        direccion = 0;
        score = 0;                    //puntaje inicial
        vidas = 5;                    //vidaas iniciales
        contvidas=0;                    //contador de vidas, cada 3 puntos se restará una vida
        cont = 0;                     //contadaor que indica cuantos asteroides han golpeado el fondo del applet
        x_mayor = (getWidth() - getWidth() / 10);           //posicion máxima en x que tendrán los asteroides
        x_menor = 0;           //posicion mínima en x que tendrán los asteroides
        y_mayor = -100;          //posicion máxima en y que tendrán los asteroides
        y_menor = -200;        //posicion mínima en y que tendrán los asteroides
        flag = false;
        int posY = getHeight() / 2;             // posicion inicial del planeta en y

        bomb = new SoundClip("sounds/Explosion.wav");
        sonido = new SoundClip("sounds/boom.wav");
        payaso = new SoundClip("sounds/pashaso.wav");

        URL eURL = this.getClass().getResource("bueno/link1.png");
        link = new Bueno(0, 290);
        
        URL aURL= this.getClass().getResource("malo/mano1.png");
        mano = new Malo(getWidth()/2,getHeight()-55);
        
        setBackground(Color.black);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        //Se cargan los sonidos.

        //URL beURL = this.getClass().getResource("sounds/boom.wav");
        //sonido = getAudioClip(beURL);
        //URL baURL = this.getClass().getResource("sounds/Explosion.wav");
        //bomb = getAudioClip(baURL);
        z = 290;
        z0 = 0;
        angulo = 45;
        velocidadInicial=(int)(Math.random()*(54)+54);
        //se aplica la fórmula v0=v0.senθ
        vz0 = velocidadInicial * Math.sin(Math.toRadians(angulo));
        //se aplica la fórmula v0=v0.cosθ
        vx0 = velocidadInicial * Math.cos(Math.toRadians(angulo));
        x = 10;
        x0 = 0;
        tiempo = 0;
        puedoDisparar = false;
        lista = new LinkedList();
        URL goURL = this.getClass().getResource("malo/gameover.jpg");
        game_over = Toolkit.getDefaultToolkit().getImage(goURL);

    }

    /**
     * Metodo <I>run</I> sobrescrito de la clase <code>Thread</code>.<P>
     * En este metodo se ejecuta el hilo, es un ciclo indefinido donde se
     * incrementa la posicion en x o y dependiendo de la direccion, finalmente
     * se repinta el <code>Applet</code> y luego manda a dormir el hilo.
     *
     */
    public void start() {
        // Declaras un hilo
        payaso.setLooping(true);
        payaso.play();
        Thread th = new Thread(this);
        // Empieza el hilo
        th.start();
    }

    public void run() {
        tiempoActual = System.currentTimeMillis();
        while (vidas > 0) {
            if (musicafondo) {
                payaso.stop();
                payaso.setLooping(false);
            } else {
                if (!payaso.getLooping()) {
                    payaso.setLooping(true);
                    payaso.play();
                }
            }
            if (!pausa) {
                actualiza();
                checaColision();
            }
            repaint();    // Se actualiza el <code>Applet</code> repintando el contenido.
            try {
                // El thread se duerme.
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                System.out.println("Error en " + ex.toString());
            }
        }
    }

    /**
     * Metodo <I>actualiza</I>.
     * <P>
     * En este metodo se actualizan las posiciones de link como de la armadura,
     * ya sea por presionar una tecla o por moverlos con el mouse.
     */
    public void actualiza() {

        long tiempoTranscurrido = System.currentTimeMillis() - tiempoActual;
        tiempoActual += tiempoTranscurrido;
        link.actualiza(tiempoTranscurrido);
        mano.actualiza(tiempoTranscurrido);
        double incrementoTiempo = 0.05;
        tiempo += incrementoTiempo;    //actualizar el tiempo y la nueva posicion.

        if (puedoDisparar) {
            link.setPosX((int) x);
            link.setPosY((int) z);
            //se aplica la fórmula x= v0.cosθ.t
            x = vx0 * Math.cos(Math.toRadians(angulo)) * tiempo;
            //posicionamos el proyectil respecto a sus coordenadas iniciales.
            x = x + 10;
            double a = -9.81;
            //se aplica la fórmula y(t)=v0 . sen θ . t - .5 g t2.
            z = vz0 * Math.sin(Math.toRadians(angulo)) * tiempo + 0.5 * a * tiempo * tiempo;
            //posicionamos el proyectil respecto a sus coordenadas iniciales.
            z = 300 - z;

        } else {
            z = 290;
            z0 = 0;
            angulo = 45;
            velocidadInicial=(int)(Math.random()*(54)+54);;
            //se aplica la fórmula v0=v0.senθ
            vz0 = velocidadInicial * Math.sin(Math.toRadians(angulo));
            //se aplica la fórmula v0=v0.cosθ
            vx0 = velocidadInicial * Math.cos(Math.toRadians(angulo));
            x = 10;
            x0 = 0;
            tiempo = 0;
        }
   if (move) {
            switch (direccion) {
                case 3: {
                    mano.setPosX(mano.getPosX() - 3);
                    break; //se mueve hacia la izquierda
                }
                case 4: {

                    mano.setPosX(mano.getPosX() + 3);
                    break; //se mueve hacia la derecha
                }
            }
        }

    }

    /**
     * Metodo usado para checar las colisiones del objeto link con el objeto
     * armadura y además con las orillas del <code>Applet</code>.
     */
    public void checaColision() {

        if (link.getPosY() < 0) {
            link.setPosY(0);
        }

        if (link.getPosY() + link.getAlto() > getHeight()) {
            link.setPosY(getHeight()/2);
            puedoDisparar = false;
            contvidas++;
            if(contvidas==3){
                vidas--;
                contvidas=0;
            }
            link.setPosX(0);
            link.setPosY(290);
        }

        if (link.getPosX() < 0) {
            link.setPosX(0);
        }

        if (link.getPosX() + link.getAncho() > getWidth()) {
            link.setPosX(getWidth() - link.getAncho());
        }
       if (mano.getPosX() < 0) {
            mano.setPosX(0);
        }
       if (mano.getPosX() + mano.getAncho() > getWidth()) {
            mano.setPosX(getWidth() - mano.getAncho());
        }
       
       
            if (mano.intersecta(link) && !(mano.intersec(link))) {
                flag = true;

            } else if (!(mano.intersecta(link)) && flag) {
                flag = false;

            } else if (!(flag) && mano.intersec(link)) {
                link.setPosY(getHeight()/2);
                puedoDisparar = false;
                link.setPosX(0);
                link.setPosY(290);
                score=score+100;

            }

    }

    /**
     * Metodo <I>update</I> sobrescrito de la clase <code>Applet</code>,
     * heredado de la clase Container.<P>
     * En este metodo lo que hace es actualizar el contenedor
     *
     * @param g es el <code>objeto grafico</code> usado para dibujar.
     */
    public void paint(Graphics g) {
        // Inicializan el DoubleBuffer
        if (dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }

        // Actualiza la imagen de fondo.
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

        // Actualiza el Foreground.
        dbg.setColor(getForeground());
        paint1(dbg);

        // Dibuja la imagen actualizada
        g.drawImage(dbImage, 0, 0, this);

    }

    /**
     * Metodo <I>keypPressed</I> sobrescrito de la clase
     * <code>KeyEvent</code>.<P>
     * En este método se actualiza la variable de dirección dependiendo de la
     * tecla que haya sido precionado El parámetro e se usará cpara obtener la
     * acción de la tecla que fue presionada.
     *
     */
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            direccion = 3;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

            direccion = 4;

        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            pausa = !pausa;

        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            if (!musicafondo) {
                musicafondo = true;
            } else {
                musicafondo = false;
            }

        }
        move = true;

    }

    public void keyTyped(KeyEvent e) {
    }

    /**
     * Metodo <I>keyReleased</I> sobrescrito de la clase
     * <code>KeyEvent</code>.<P>
     * En este método se verifica si alguna tecla que haya sido presionada es
     * liberada. Si es liberada la booleana que controla el movimiento se
     * convierte en falsa.
     */
    public void keyReleased(KeyEvent e) {
            move = false;
    }

    public void mouseClicked(MouseEvent e) {
        if (!puedoDisparar) {
            if (link.contiene(e.getX(), e.getY())) {
                puedoDisparar = true;
            }
        }

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    /**
     * Metodo <I>MousePressed</I> sobrescrito de la clase
     * <code>mouseEvent</code>.<P>
     * En este metodo se verifica si el mouse ha dado click sobre la imágen. Al
     * verificar que haya dado un click se actualizan las coordenadas de 'x' y
     * 'y' para ajustar el desfase que puede tener la imagen con el click
     */
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Metodo <I>MouseReleased</I> sobrescrito de la clase
     * <code>MouseEvent</code>.<P>
     * En este método se verifica si el click del mouse ha sido liberado, si sí
     * entonces la booleana que l ocontrola se hace falsa, para marcar que ya no
     * está siendo presionadao.
     */
    public void mouseReleased(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {

    }

    /**
     * Metodo <I>mouseDragged</I> sobrescrito de la clase
     * <code>MouseEvent</code>.<P>
     * En este método se actualiza las posiciones de 'x' y de 'y'hacia dónde ha
     * sido arrastrado el mouse
     */
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Metodo <I>paint</I> sobrescrito de la clase <code>Applet</code>, heredado
     * de la clase Container.<P>
     * En este metodo se dibuja la imagen con la posicion actualizada, ademas
     * que cuando la imagen es cargada te despliega una advertencia.
     *
     * @paramg es el <code>objeto grafico</code> usado para dibujar.
     */
    public void paint1(Graphics g) {
        if (vidas > 0) {
            if (link != null && mano!= null ) {
                g.drawImage(link.getImagenI(), link.getPosX(), link.getPosY(), this);
                g.drawImage(mano.getImagenI(), mano.getPosX(), mano.getPosY(), this);
                g.setColor(Color.white);
                g.drawString("Puntos = " + score, 20, 50);
                g.drawString("Vidas = " + vidas, 20, 70);
                if (pausa) {
                    g.setColor(Color.white);
                    g.drawString(link.getPausado(), link.getPosX() + link.getAncho() / 3, link.getPosY() + link.getAlto() / 2);
                }
            } else {
                //Da un mensaje mientras se carga el dibujo	
                g.drawString("No se cargo la imagen..", 20, 20);
            }

        } else {
            g.drawImage(game_over, getWidth() / 5, 0, this);
        }
    }
}

/**
 * Metodo que agrega la informacion del vector al archivo.
 *
 * @throws IOException
 */
//public void grabaArchivo() {
//                                                          
//         try {
//                PrintWriter fileOut = new PrintWriter(new FileWriter(nombreArchivo),false);
//                /*for (int i = 0; i < vec.size(); i++) {
//
//                    Puntaje x;
//                    x = (Puntaje) vec.get(i);*/
//                    fileOut.println(""+velocidadx +","+velocidady+","+angulo+ ","+tiempo+","+vidas);
//                
//                fileOut.close();
//         }
//         catch (IOException ioe) {
//             System.out.println(" Se obtuvo error al grabar archivo : " + ioe.toString());
//         }
//        }
//}
