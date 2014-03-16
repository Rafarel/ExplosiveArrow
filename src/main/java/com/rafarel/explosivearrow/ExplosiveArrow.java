package com.rafarel.explosivearrow;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExplosiveArrow extends JavaPlugin {

	public ArrowListener arrowListener = null;
	
	@Override
	public void onEnable(){
		
		getLogger().info("Explosive Arrow is enabled!");
		
		arrowListener = new ArrowListener(this);
	}
	
	@Override
	public void onDisable() {
		
		getLogger().info("Explosive Arrow is disabled!");
		
		
		
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equalsIgnoreCase("rambo"))
		{
			if (!(sender instanceof Player)) 
			{
				sender.sendMessage("This command can only be run by a player.");
				return false;
			} 
			else 
			{
				Player player = (Player) sender;
				
				if(!player.hasPermission("explosivearrow.rambo"))
				{
					sender.sendMessage(ChatColor.RED+"You can not use /rambo");
					return false;
				}
				
				PlayerInventory inventory = player.getInventory();
				
				if(!inventory.contains(Material.BOW))
				{
					ItemStack bow = new ItemStack(Material.BOW);
					inventory.addItem(bow);
				}
				
				ItemStack arrows = new ItemStack(Material.ARROW, 64);
				inventory.addItem(arrows);
				
				ItemStack tnt = new ItemStack(Material.TNT, 64);
				inventory.addItem(tnt);
				
				sender.sendMessage("Rambo gives you arrows & TNT!");
				
				return true;
			}

		}
		
		return false; 
	}
	
	
	
	
}