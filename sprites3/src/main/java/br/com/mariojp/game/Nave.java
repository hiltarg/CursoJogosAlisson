package br.com.mariojp.game;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Nave extends Sprite {
	Timer timer = new Timer();
	private int dx;
	private int dy;
	private boolean right;
	private boolean left;
	private boolean up;
	private boolean down;
	private final int SPEED_NAVE = 2;
	private int ammo = 10;

	private int alcance;
	
    private ArrayList<Missil> missiles;

	public Nave(int x, int y, int alcance) {
		super(x, y);
		this.alcance = alcance;
		initNave();
	}

	private void initNave() {
		missiles = new ArrayList<Missil>(); 
		carregarImagem("/imagens/nave.png"); 
		getImageDimensions();
	}

	public void move() {
		x += dx;
		y += dy;
	}


	public Image getImage() {
		return image;
	}

	public void keyPressed(KeyEvent e) { 
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SPACE) {
			atira(); 
		}
		if (key == KeyEvent.VK_LEFT){
			left = true;
			dx= - SPEED_NAVE;
		}if(key==KeyEvent.VK_RIGHT){
			right = true;
			dx = SPEED_NAVE;
		}if(key==KeyEvent.VK_UP){
			up = true;
			dy= - SPEED_NAVE;
		}if(key==KeyEvent.VK_DOWN){
			down = true;
			dy = SPEED_NAVE;
		}
		//Cancelar mov
		if (left == true && right == true) {
			dx = 0;
		}
		if (up == true && down == true) {
			dy = 0;
		}
	}
	
	private void atira(){
		missiles.add(new Missil(x + width, y + height / 2, alcance ));
		ammo--;
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				ammo++;
				
			}
		}, 1500);
		
		/* If para recarga de metralhadora
		if (ammo > 0) {
			missiles.add(new Missil(x + width, y + height / 2, alcance ));
			ammo--;
		}else {
			// Fazer uma contagem de 5 segundoe e então seta ammo em 30 novamente;
			timer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					ammo = 30;
					
				}
			}, 3000);
		}
		*/

	}

	public void keyReleased(KeyEvent e) { 
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT){
			left = false;
			dx =0;
		}if(key==KeyEvent.VK_RIGHT){
			right = false;
			dx =0;
		}if(key==KeyEvent.VK_UP){
			up = false;
			dy=0;
		}if(key==KeyEvent.VK_DOWN){
			down = false;
			dy=0;
		}
		//Retornar mov
		// Esqueda e Direita
		if (left == false && right == true) {
			dx = SPEED_NAVE;
		}
		if (left == true && right == false) {
			dx = - SPEED_NAVE;
		}
		// Cima e baixo
		if (up == true && down == false) {
			dy = - SPEED_NAVE;
		}
		if (up == false && down == true) {
			dy = SPEED_NAVE;
		}
	}
	
	public ArrayList<Missil> getMissiles() {
		return missiles;
	}

	public int getAmmo() {
		return ammo;
	}

	
}
