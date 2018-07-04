package com.matty.main;

import com.matty.Listener.CannonListener;
import com.matty.util.CannonManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerCannonPlugin extends JavaPlugin{
	
	private  CannonListener cl;
	private CannonManager cm;
	
	@Override
	public void onEnable() {
		cm = new CannonManager(this);
		cl = new CannonListener(this, cm);
		getLogger().info("Cannon Enabled");
	}
	
}
