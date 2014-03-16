package com.rafarel.explosivearrow;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
//import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
//import org.bukkit.entity.Player;
//import org.bukkit.entity.Projectile;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class ArrowListener implements Listener 
{
	
	public float power = 4.0f;

	public ArrowListener(ExplosiveArrow plugin) 
	{
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
	
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockHit(ProjectileHitEvent e) 
	{
		Entity arrow = e.getEntity();
		LivingEntity shooter = e.getEntity().getShooter();
		
		Bukkit.getLogger().info("Block hit by "+arrow.getType()+", shooter is "+shooter.getType());
		
		if(arrow.getType() != EntityType.ARROW || shooter.getType() != EntityType.PLAYER)
			return;
		
		Player player = (Player) shooter;
		
		if(!player.hasPermission("explosivearrow.shoot"))
			return;

		Inventory inventory = player.getInventory();
		
		if(inventory.contains(Material.TNT))
		{
			ItemStack tnt = inventory.getItem(inventory.first(Material.TNT));
			tnt.setAmount(tnt.getAmount() - 1);
			
			World world = player.getWorld();
			Location loc = e.getEntity().getLocation();											
			world.createExplosion(loc, power);
			
			arrow.remove();
		}
			

		
		
		
	
	}	
	
}

