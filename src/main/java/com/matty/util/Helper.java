package com.matty.util;

import org.bukkit.Location;
import org.bukkit.Material;

public  class Helper {
	
	public static boolean ValidCannon(Location location) {
		if(!location.getBlock().getType().equals(Material.GLASS)) return false;
		
		return true;
	}
}
