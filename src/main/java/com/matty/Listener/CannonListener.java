package com.matty.Listener;

import com.matty.main.PlayerCannonPlugin;
import com.matty.util.Cannon;
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
	
	public CannonListener(PlayerCannonPlugin plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent e) {
		plugin.getLogger().info("click");
		if(e.getClickedBlock().getType().equals(Material.GLASS)){
			Location location = e.getClickedBlock().getLocation();
			if(plugin.hasCannon(location)){
				Player player = e.getPlayer();
				Cannon cannon = plugin.getCannon(location);
				if(cannon.loaded()){
					player.sendMessage(cannon.fire());
				}else {
					player.sendMessage(cannon.loadPlayer(player));
				}
				
			}else if(Helper.ValidCannon(location)){
				plugin.addCannon(location);
			}
		}
	}
}
