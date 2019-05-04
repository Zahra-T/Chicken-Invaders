package game.gamer;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;

import game.enemy.Enemy;
import game.enemy.RedChicken;
import game.engine.Game;
import game.engine.rocket.AttackCoolDown;
import game.engine.rocket.Rocket;
import game.engine.weapon.Bomb;
import game.engine.weapon.RedBullet;
import game.engine.weapon.Weapon;
import game.swing.MainPanel;
import game.swing.SoundPlayer;
import game.swing.UserLabel;

public class Gamer {
	

	private String userName;
	private UserLabel userLabel;
	private Rocket rocket;
	private ArrayList<Weapon> tirs;
	private ArrayList<Bomb> bombs;
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private boolean isChoosed;
	
	private int wave = 1;
	private int level = 1;
	private int heart = 5;
	private int bomb = 3;
	private int fireLight = 0;
	private int drumStick = 0;


//	private boolean shelik;
//	private boolean lockShelik = false;

	public Gamer(String userName) throws IOException
	{
		this.userName = userName;
		initialize();
	}

	private void initialize() throws IOException
	{
		rocket = new Rocket(960 - 50, 515 - 200, this);
		this.tirs = rocket.getTirs();
		this.bombs = rocket.getBombs();
		userLabel = new UserLabel(this);
		isChoosed = false;
		
		setEnemy();
	}
	

	
	public void setEnemy()
	{
		RedChicken redChicken = new RedChicken(-50, 300, 10, 0, 200);
		enemies.add(redChicken);
	}


	public Rocket getRocket() {
		return rocket;
	}

//	public ArrayList<Weapon> getTirs() {
//		return tirs;
//	}
//
//	public ArrayList<Bomb> getBombs() {
//		return bombs;
//	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public String getUserName() {
		return userName;
	}

	public UserLabel getUserLabel() {
		return userLabel;
	}

	public void isChoosed(boolean b)
	{
		this.isChoosed = b;
		if(b)
		{
			this.getUserLabel().setBorder(BorderFactory.createLineBorder(Color.white));
		}
		else
		{
			this.getUserLabel().setBorder(null);
		}
	}

	public boolean isChoosed()
	{
		return this.isChoosed;
	}
	public void setNew()
	{
		this.tirs = new ArrayList();
		this.bombs = new ArrayList();
		this.enemies = new ArrayList();
		
		//TODO setFirstEnemies
		//TODO setFirstLevel
	}

	public int getHeart() {
		return heart;
	}

	public void setHeart(int heart) {
		this.heart = heart;
	}

	public int getBomb() {
		return bomb;
	}

	public void setBomb(int bomb) {
		this.bomb = bomb;
	}

	public int getFireLight() {
		return fireLight;
	}

	public void setFireLight(int fireLight) {
		this.fireLight = fireLight;
	}

	public int getDrumStick() {
		return drumStick;
	}

	public void setDrumStick(int drumStick) {
		this.drumStick = drumStick;
	}

	public void increaseHeart(int n)
	{
		heart += n;
	}
	
	public void increaseBomb(int n)
	{
		bomb += n;
	}
	
	public void increaseFireLight(int n)
	{
		fireLight += n;
	}
	
	public void increaseDrumStick(int n)
	{
		drumStick += n;
	}
	
	public void decreaseHeart()
	{
		heart -= 1;
	}
	
	public void decreaseBomb()
	{
		bomb -= 1;
	}
	
	public void decreaseFireLight(int n)
	{
		fireLight -= n;
	}
	
	public void decreaseDrumStick(int n)
	{
		drumStick -= n;
	}

	public int getWave() {
		return wave;
	}

	public int getLevel() {
		return level;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserLabel(UserLabel userLabel) {
		this.userLabel = userLabel;
	}

	public void setRocket(Rocket rocket) {
		this.rocket = rocket;
	}

//	public void setTirs(ArrayList<Weapon> tirs) {
//		this.tirs = tirs;
//	}
//
//	public void setBombs(ArrayList<Bomb> bombs) {
//		this.bombs = bombs;
//	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}

	public void setChoosed(boolean isChoosed) {
		this.isChoosed = isChoosed;
	}

	public void setWave(int wave) {
		this.wave = wave;
	}

	public void setLevel(int level) {
		this.level = level;
	}


	
	

//	public boolean isShelik() {
//		return shelik;
//	}
//
//	public void setShelik(boolean shelik) {
//		this.shelik = shelik;
//	}
	
	
	
	
}
