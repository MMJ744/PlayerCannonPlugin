package com.matty.util;

import com.matty.main.PlayerCannonPlugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;

import org.bukkit.inventory.Inventory;
import org.bukkit.material.Diode;
import org.bukkit.util.Vector;

public class Cannon {
	private PlayerCannonPlugin plugin;
	private Location location;
	private CannonManager cannonManager;
	private Player ammo;
	private int powerLevel;
	private int angle;
	private BlockFace direction;
	private Chest chest;
	private Diode lDiode,rDiode;
	
	public Cannon(Location l, PlayerCannonPlugin plugin, CannonManager cm){
		location = l;
		ammo = null;
		this.plugin = plugin;
		powerLevel = 1;
		angle = 45;
		direction = Helper.findDirection(l);
		cannonManager = cm;
		findBlocks();
	}
	
	public void setPowerLevel(int powerLevel) {
		this.powerLevel=powerLevel;
	}
	
	public int getAngle() {
		return angle;
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
	
	private void findBlocks(){
		World world = location.getWorld();
		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();
		Location diode1, diode2, chest;
		switch (direction) {
			case SOUTH:
				diode1=new Location(world, x+1, y, z);
				diode2=new Location(world, x-1, y, z);
				chest=new Location(world, x, y, z-2);
				break;
			case NORTH:
				diode1=new Location(world, x-1, y, z);
				diode2=new Location(world, x+1, y, z);
				chest=new Location(world, x, y, z+2);
				break;
			case EAST:
				diode1=new Location(world, x, y, z-1);
				diode2=new Location(world, x, y, z+1);
				chest=new Location(world, x-2, y, z);
				break;
			case WEST:
				diode1=new Location(world, x, y, z+1);
				diode2=new Location(world, x, y, z-1);
				chest=new Location(world, x+2, y, z);
				break;
			default:
				diode1 = null;
				diode2 = null;
				chest = null;
				break;
		}
		this.lDiode = (Diode) diode1.getBlock().getState().getData();
		this.rDiode = (Diode) diode2.getBlock().getState().getData();
		this.chest = (Chest) chest.getBlock().getState();
	}
	
	public String fire(){
		if(ammo == null) return "failed no ammo";
		findBlocks();
		powerLevel = lDiode.getDelay();
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
			return "cannon fired with power: " + powerLevel;
		} else return "failed not enough TNT";
	}
	
	public void delete() {
		cannonManager.removeCannon(location);
	}
}
