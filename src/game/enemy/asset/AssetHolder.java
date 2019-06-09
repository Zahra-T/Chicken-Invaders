package game.enemy.asset;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Logger.Logger;

public class AssetHolder {
	
	private ArrayList<Egg> eggs;
	private ArrayList<Empowerer> empowerers;
	private ArrayList<TypeEmpowerer> typeEmpowerers;
	private ArrayList<Coin> coins;
	private transient Logger logger = Logger.getLogger();
	public AssetHolder() {
		eggs = new ArrayList<Egg>();
		empowerers = new ArrayList<Empowerer>();
		typeEmpowerers = new ArrayList<TypeEmpowerer>();
		coins = new ArrayList<Coin>();
	}
	
	public void paint(Graphics2D g2) {
		synchronized(eggs) {
			for(Egg egg : eggs) {
				egg.paint(g2);
			}
		}
		synchronized(empowerers) {
			for(Empowerer empowerer : empowerers) {
				empowerer.paint(g2);
			}
		}
		synchronized(typeEmpowerers) {
			for(TypeEmpowerer typeEmpowerer : typeEmpowerers) {
				typeEmpowerer.paint(g2);
			}
		}
		synchronized(coins) {
			for(Coin coin : coins) {
				coin.paint(g2);
			}
		}
	}
	
	public void move() {
		logger.debug("in move");
		synchronized(eggs) {
			for(Egg egg : eggs) {
				egg.move();
			}
		}
		synchronized(empowerers) {
			for(Empowerer empowerer : empowerers) {
				empowerer.move();
			}
		}
		synchronized(typeEmpowerers) {
			for(TypeEmpowerer typeEmpowerer : typeEmpowerers) {
				typeEmpowerer.move();
			}
		}
		synchronized(coins) {
			for(Coin coin : coins) {
				coin.move();
			}
		}
	}
	
	public void add(Asset asset){
		if(asset.getClass().getSimpleName().equals("Egg")) {
			eggs.add((Egg)asset);
		}
		else if(asset.getClass().getSimpleName().equals("Empowerer")) {
			empowerers.add((Empowerer)asset);
		}
		else if(asset.getClass().getSimpleName().equals("TypeEmpowerer")) {
			typeEmpowerers.add((TypeEmpowerer)asset);
		}
		else if(asset.getClass().getSimpleName().equals("Coin")) {
			coins.add((Coin)asset);
		}
	}
	
	public void remove(Asset asset) {
		if(asset.getClass().getName().equals("Egg")) {
			eggs.remove(asset);
		}
		else if(asset.getClass().getName().equals("Empowerer")) {
			empowerers.remove(asset);
		}
		else if(asset.getClass().getName().equals("TypeEmpowerer")) {
			typeEmpowerers.remove(asset);
		}
		else if(asset.getClass().getName().equals("Coin")) {
			coins.remove(asset);
		}
	}
	
	
	public ArrayList<Egg> getEggs() {
		return eggs;
	}
	public void setEggs(ArrayList<Egg> eggs) {
		this.eggs = eggs;
	}
	public ArrayList<Empowerer> getEmpowerers() {
		return empowerers;
	}
	public void setEmpowerers(ArrayList<Empowerer> empowerers) {
		this.empowerers = empowerers;
	}
	public ArrayList<TypeEmpowerer> getTypeEmpowerers() {
		return typeEmpowerers;
	}
	public void setTypeEmpowerers(ArrayList<TypeEmpowerer> typeEmpowerers) {
		this.typeEmpowerers = typeEmpowerers;
	}
	
	
	
	

}
