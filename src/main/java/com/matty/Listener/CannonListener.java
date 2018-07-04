package com.matty.Listener;

import com.matty.main.PlayerCannonPlugin;
import com.matty.util.Cannon;
import com.matty.util.CannonManager;
import com.matty.util.Helper;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class CannonListener implements Listener{
	
	private PlayerCannonPlugin plugin;
	private CannonManager cannonManager;
	public CannonListener(PlayerCannonPlugin plugin, CannonManager cm) {
		this.plugin = plugin;
		cannonManager = cm;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent e) {
		if(e.getClickedBlock() == null) return;
		plugin.getLogger().info("click");
		if(e.getClickedBlock().getType().equals(Material.GLASS)){
			Location location = e.getClickedBlock().getLocation();
			plugin.getLogger().info(Helper.findDirection(location).toString());
			if(cannonManager.hasCannon(location)){
				Player player = e.getPlayer();
				Cannon cannon = cannonManager.getCannon(location);
				if(cannon.loaded()){
					player.sendMessage(cannon.fire());
				}else {
					player.sendMessage(cannon.loadPlayer(player));
				}
				
			}else if(Helper.ValidCannon(location)){
				cannonManager.addCannon(location);
			}else {
				plugin.getLogger().info("invalid cannon");
			}
		}
	}
	
}
