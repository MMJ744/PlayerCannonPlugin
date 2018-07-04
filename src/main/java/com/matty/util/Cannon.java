package com.matty.util;

import com.matty.main.PlayerCannonPlugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;

import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

public class Cannon {
	private PlayerCannonPlugin plugin;
	private Location location;
	private Player ammo;
	private int powerLevel;
	private int angle;
	private Direction direction;
	private Block lever;
	private Chest chest;
	
	public Cannon(Location l, PlayerCannonPlugin plugin){
		location = l;
		ammo = null;
		this.plugin = plugin;
		powerLevel = 1;
		angle = 45;
		direction = findDirection();
	}
	
	public int getPowerLevel() {
		return powerLevel;
	}
	
	public void setPowerLevel(int powerLevel) {
		if(powerLevel > 4){
			this.powerLevel = 4;
		}else if(powerLevel < 1){
			this.powerLevel = 1;
		}else this.powerLevel=powerLevel;
		
	}
	
	public int getAngle() {
		return angle;
	}
	
	public Block getLever() {
		return lever;
	}
	public void setAngle(int angle) {
		this.angle=angle;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public String loadPlayer(Player player){
		if(ammo	!= null){
			return  "Failed to load cannon is loaded";
		}
		ammo=player;
		Location l = location.clone();
		l.add(0.5,0,0.5);
		player.teleport(l);
		return "Player loaded";
	}
	public boolean loaded(){
		return ammo != null;
	}
	public void unLoad() {
		ammo = null;
		ammo.sendMessage("You have been unloaded from the cannon");
	}
	
	public Direction findDirection(){
		Location l = location.clone();
		l.subtract(0,0,1);
		lever = l.getBlock();
		l.subtract(0,0,1);
		chest = (Chest) l.getBlock().getState();
		return Direction.SOUTH;
	}
	
	public String fire(){
		if(ammo == null) return "failed no ammo";
		Inventory inv = chest.getBlockInventory();
		if(inv.contains(Material.TNT, powerLevel)){
			Location l = location.clone();
			l.add(0.5,0,0.5);
			ammo.teleport(l);
			switch (direction){
				case SOUTH: {
					ammo.setVelocity(new Vector(0,2 * powerLevel,2 * powerLevel));
					break;
				}
			}
			ammo = null;
			return "cannon fired";
		} else return "failed not enough TNT";
	}
	
	public void delete() {
		plugin.removeCannon(location);
	}
}
