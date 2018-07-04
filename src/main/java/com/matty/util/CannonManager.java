package com.matty.util;

import com.matty.main.PlayerCannonPlugin;
import org.bukkit.Location;

import java.util.HashMap;

public class CannonManager {
	private HashMap<Location, Cannon> cannons;
	private PlayerCannonPlugin plugin;
	
	public CannonManager(PlayerCannonPlugin plugin){
		cannons = new HashMap<Location, Cannon>();
		this.plugin = plugin;
	}
	public boolean addCannon(Location l) {
		if(cannons.containsKey(l)) return false;
		cannons.put(l, new Cannon(l, plugin, this));
		plugin.getLogger().info("cannon added");
		return  true;
	}
	
	public Cannon getCannon(Location l) {
		return cannons.get(l);
	}
	
	public boolean hasCannon(Location l) {
		return cannons.containsKey(l);
	}
	
	public void removeCannon(Location l) {
		if(cannons.containsKey(l)) cannons.remove(l);
	}
}
