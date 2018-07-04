package com.matty.main;

import com.matty.Listener.CannonListener;
import com.matty.util.Cannon;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class PlayerCannonPlugin extends JavaPlugin{
	
	private HashMap<Location, Cannon> cannons;
	
	@Override
	public void onEnable() {
		cannons = new HashMap<Location, Cannon>();
		new CannonListener(this);
		getLogger().info("Cannon Enabled");
	}
	
	public boolean addCannon(Location l) {
		if(cannons.containsKey(l)) return false;
		cannons.put(l, new Cannon(l, this));
		getLogger().info("cannon added");
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
