package com.matty.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.material.Diode;

public  class Helper {
	
	public static boolean ValidCannon(Location location) {
		BlockFace direction = findDirection(location);
		World world = location.getWorld();
		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();
		
		Location diode1, diode2, chest;
		if(direction == null) return  false;
		switch (direction){
			case SOUTH:
				diode1 = new Location(world,x+1,y,z);
				diode2 = new Location(world, x-1,y,z);
				chest = new Location(world, x, y, z-2);
				break;
			case NORTH:
				diode1 = new Location(world,x+1,y,z);
				diode2 = new Location(world, x-1,y,z);
				chest = new Location(world, x, y, z+2);
				break;
			case EAST:
				diode1 = new Location(world,x,y,z+1);
				diode2 = new Location(world, x,y,z-1);
				chest = new Location(world, x-2, y, z);
				break;
			case WEST:
				diode1 = new Location(world,x,y,z-1);
				diode2 = new Location(world, x,y,z+1);
				chest = new Location(world, x+2, y, z);
				break;
			default:
				diode1 = null;
				diode2 = null;
				chest = null;
				break;
		}
		return (diode1.getBlock().getType().equals(Material.DIODE_BLOCK_OFF) && diode2.getBlock().getType().equals(Material.DIODE_BLOCK_OFF)
				&& chest.getBlock().getType().equals(Material.CHEST));
	}
	
	public static BlockFace findDirection(Location location) {
		if(location.getBlock().getType().equals(Material.GLASS)){
			World world = location.getWorld();
			double x = location.getX();
			double y = location.getY();
			double z = location.getZ();
			Location dio = new Location(world, x+1,y,z);
			System.out.println(dio.getBlock().getType().toString());
			if(dio.getBlock().getType().equals(Material.DIODE_BLOCK_OFF)){
				Diode diode = (Diode) dio.getBlock().getState().getData();
				return  diode.getFacing();
			}
			dio = new Location(world, x,y,z+1);
			if(dio.getBlock().getType().equals(Material.DIODE_BLOCK_OFF)){
				Diode diode = (Diode) dio.getBlock().getState().getData();
				return  diode.getFacing();
			}
			return BlockFace.DOWN;
		}else return null; // If this happens cannon isnt valid
	}
	
}
