package jframeexamen;

import JFrameExamen.SoundClip;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.LinkedList;

public class JFrameExamen extends JFrame implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    private static final long serialVersionUID = 1L;
    // Se declaran las variables. 
    private Image dbImage;	// Imagen a proyectar	
    private Graphics dbg;	// Objeto grafico
    private SoundClip sonido;    // Objeto AudioClip
    private SoundClip bomb;    //Objeto AudioClip 
    private Bueno link;    // Objeto de la clase Elefante
    private Malo armadura;   //Objeto de la clase Raton
    private LinkedList lista;           //lista de ratones
    private int cant;               //cantidad de asteroides
    private boolean presionado;     //Boleano que controla si el bot´´oon está siendo presionado
    private int coordenada_x;
    private int coordenada_y;
    private int off_x;              //Coordenada para ajustar la imagen con click
    private int off_y;              //Coordenada para ajustar la imagen con click 
    private int vidas;
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

        setSize(800, 500);
        pausa = false;
        move = false;
        direccion = 0;
        score = 0;                    //puntaje inicial
        vidas = 5;                    //vidaas iniciales
        cont = 0;                     //contadaor que indica cuantos asteroides han golpeado el fondo del applet
        x_mayor = (getWidth() - getWidth() / 10);           //posicion máxima en x que tendrán los asteroides
        x_menor = 0;           //posicion mínima en x que tendrán los asteroides
        y_mayor = -100;          //posicion máxima en y que tendrán los asteroides
        y_menor = -200;        //posicion mínima en y que tendrán los asteroides
        flag = false;
        int posX = getWidth() / 2;              // posicion inicial del planeta en x
        int posY = getHeight();             // posicion inicial del planeta en y

        bomb = new SoundClip("sounds/Explosion.wav");
        sonido = new SoundClip("sounds/boom.wav");

        URL eURL = this.getClass().getResource("bueno/link1.png");
        link = new Bueno(posX, posY);

        setBackground(Color.black);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        //Se cargan los sonidos.

        //URL beURL = this.getClass().getResource("sounds/boom.wav");
        //sonido = getAudioClip(beURL);
        //URL baURL = this.getClass().getResource("sounds/Explosion.wav");
        //bomb = getAudioClip(baURL);
        lista = new LinkedList();

        cant = (int) (Math.random() * 3 + 1);           //se crea la cantidad de asteroides al azar
        switch (cant) {
            case 1:
                for (int i = 1; i <= 6; i++) {
                    if (i <= 3) {
                        posrX = -400;    //se generarán los asteroides en posiciones aleatorias fuera del applet
                        posrY = ((int) (Math.random() * (getHeight() / 10 * 9)) + getHeight() / 10);
                        URL rURL = this.getClass().getResource("malo/armor1.png");
                        armadura = new Malo(posrX, posrY);
                        armadura.setPosX(posrX);
                        armadura.setPosY(posrY);
                        lista.add(armadura);
                    } else {
                        posrX = ((int) (Math.random() * (getWidth() / 4)) + getWidth());
                        posrY = ((int) (Math.random() * (getHeight() / 10 * 9)) + getHeight() / 10);
                        URL rURL = this.getClass().getResource("malo/armor1.png");
                        armadura = new Malo(posrX, posrY);
                        armadura.setPosX(posrX);
                        armadura.setPosY(posrY);
                        lista.add(armadura);
                    }
                }
                break;
            case 2:
                for (int i = 1; i <= 10; i++) {
                    if (i <= 5) {
                        posrX = -400;
                        posrY = ((int) (Math.random() * (getHeight() / 10 * 9)) + getHeight() / 10);
                        URL rURL = this.getClass().getResource("malo/armor1.png");
                        armadura = new Malo(posrX, posrY);
                        armadura.setPosX(posrX);
                        armadura.setPosY(posrY);
                        lista.add(armadura);
                    } else {
                        posrX = ((int) (Math.random() * (getWidth() / 4)) + getWidth());
                        posrY = ((int) (Math.random() * (getHeight() / 10 * 9)) + getHeight() / 10);
                        URL rURL = this.getClass().getResource("malo/armor1.png");
                        armadura = new Malo(posrX, posrY);
                        armadura.setPosX(posrX);
                        armadura.setPosY(posrY);
                        lista.add(armadura);
                    }
                }
                break;
            case 3:
                for (int i = 1; i <= 12; i++) {
                    if (i <= 6) {
                        posrX = -400;
                        posrY = ((int) (Math.random() * (getHeight() / 10 * 9)) + getHeight() / 10);
                        URL rURL = this.getClass().getResource("malo/armor1.png");
                        armadura = new Malo(posrX, posrY);
                        armadura.setPosX(posrX);
                        armadura.setPosY(posrY);
                        lista.add(armadura);
                    } else {
                        posrX = ((int) (Math.random() * (getWidth() / 4)) + getWidth());
                        posrY = ((int) (Math.random() * (getHeight() / 10 * 9)) + getHeight() / 10);
                        URL rURL = this.getClass().getResource("malo/armor1.png");
                        armadura = new Malo(posrX, posrY);
                        armadura.setPosX(posrX);
                        armadura.setPosY(posrY);
                        lista.add(armadura);
                    }
                }
                break;
        }

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
        Thread th = new Thread(this);
        // Empieza el hilo
        th.start();
    }

    public void run() {
        tiempoActual = System.currentTimeMillis();
        while (vidas > 0) {
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
        armadura.actualiza(tiempoTranscurrido);

        if (move) {
            switch (direccion) {
                case 3: {

                    link.setPosX(link.getPosX() - 2);
                    break; //se mueve hacia la izquierda
                }
                case 4: {

                    link.setPosX(link.getPosX() + 2);
                    break; //se mueve hacia la derecha
                }
            }
        }

        if (presionado) {                              //si la imagen sigue presionda, se actualizan las posiciones de x y de y
            link.setPosY(coordenada_y - off_y);
            link.setPosX(coordenada_x - off_x);

        }
        for (int i = 0; i < lista.size(); i++) {
            Malo asteroide = (Malo) lista.get(i);
            if (i <= (lista.size() / 2)) {
                asteroide.setPosX(asteroide.getPosX() + asteroide.getSpeed());
            } else {
                asteroide.setPosX(asteroide.getPosX() - asteroide.getSpeed());
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
            link.setPosY(getHeight() - link.getAlto());
        }

        if (link.getPosX() < 0) {
            link.setPosX(0);
        }

        if (link.getPosX() + link.getAncho() > getWidth()) {
            link.setPosX(getWidth() - link.getAncho());
        }

        for (int i = 0; i < lista.size(); i++) {
            Malo asteroide = (Malo) lista.get(i);
            if ((i + 1) <= (lista.size() + 1) / 2) {
                if (asteroide.getPosX() > getWidth() + asteroide.getAncho()) {
                    sonido.play();
                    asteroide.setPosX(-300);
                    asteroide.setPosY((int) (Math.random() * (getHeight() / 10 * 9)) + getHeight() / 10);

                }

            } else{
                if(asteroide.getPosX() + asteroide.getAncho() <0) {
                sonido.play();
                asteroide.setPosX((int) (Math.random() * (getWidth() / 4)) + getWidth());
                asteroide.setPosY((int) (Math.random() * (getHeight() / 10 * 9)) + getHeight() / 10);
            }
            }
        }

        for (int i = 0; i < lista.size(); i++) {
            Malo asteroide = (Malo) lista.get(i);

            if (link.intersecta(asteroide) && !(link.intersec(asteroide))) {
                flag = true;

            } else if (!(link.intersecta(asteroide)) && flag) {
                flag = false;

            } else if (!(flag) && link.intersec(asteroide)) {
                bomb.play();
                score += 100;
                asteroide.setPosX(((int) (Math.random() * (x_mayor - x_menor))) + x_menor);
                asteroide.setPosY(((int) (Math.random() * (y_mayor - y_menor))) + y_menor);

            }

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

        if (link.contiene(e.getX(), e.getY()) & !presionado) {
            coordenada_x = e.getX();
            coordenada_y = e.getY();
            off_x = e.getX() - link.getPosX();
            off_y = e.getY() - link.getPosY();
            presionado = true;
        }
    }

    /**
     * Metodo <I>MouseReleased</I> sobrescrito de la clase
     * <code>MouseEvent</code>.<P>
     * En este método se verifica si el click del mouse ha sido liberado, si sí
     * entonces la booleana que l ocontrola se hace falsa, para marcar que ya no
     * está siendo presionadao.
     */
    public void mouseReleased(MouseEvent e) {
        presionado = false;
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

        if (presionado) {
            coordenada_x = e.getX();
            coordenada_y = e.getY();
        }
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
            if (link != null && lista != null) {
                g.drawImage(link.getImagenI(), link.getPosX(), link.getPosY(), this);
                g.setColor(Color.white);
                g.drawString("Puntos = " + score, 20, 20);
                g.drawString("Vidas = " + vidas, 20, 50);
                if (pausa) {
                    g.setColor(Color.white);
                    g.drawString(link.getPausado(), link.getPosX() + link.getAncho() / 3, link.getPosY() + link.getAlto() / 2);
                }

                for (int i = 0; i < lista.size(); i++) {
                    Malo asteroide = (Malo) lista.get(i);
                    g.drawImage(asteroide.getImagenI(), asteroide.getPosX(), asteroide.getPosY(), this);
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
