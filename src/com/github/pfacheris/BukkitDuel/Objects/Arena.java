package com.github.pfacheris.BukkitDuel.Objects;

import org.bukkit.Location;
import org.bukkit.World;

import com.github.pfacheris.BukkitDuel.BukkitDuel;



public class Arena {
	
	private BukkitDuel plugin;
	private String name;
	private World world;
	private int minX, minY, minZ, maxX, maxY, maxZ;
	private Location spawn1, spawn2;
	private boolean inUse;
	
	
	public Arena(BukkitDuel plugin, String name, World world, int minX, int minY, int minZ, int maxX, int maxY, int maxZ)
	{		
		this.name = name;
		this.world = world;
		plugin.getConfig().set("Arenas." + name + ".world", world.getName());
		this.minX = minX;
		this.minY = minY;
		this.minZ = minZ;
		
		plugin.getConfig().set("Arenas." + name + ".corner1.x", minX);
		plugin.getConfig().set("Arenas." + name + ".corner1.y", minY);
		plugin.getConfig().set("Arenas." + name + ".corner1.z", minZ);
		
		this.maxX = maxX;
		this.maxY = maxY;
		this.maxZ = maxZ;
		
		plugin.getConfig().set("Arenas." + name + ".corner2.x", maxX);
		plugin.getConfig().set("Arenas." + name + ".corner2.y", maxY);
		plugin.getConfig().set("Arenas." + name + ".corner2.z", maxZ);
		
		this.inUse = false;
		this.plugin = plugin;
		
		plugin.saveConfig();
	}
	
	public String getName()
	{
		return name;
	}
	
	public World getWorld()
	{
		return world;
	}
	
	public int getMinX()
	{
		return minX;
	}
	
	public int getMinY()
	{
		return minY;
	}
	
	public int getMinZ()
	{
		return minZ;
	}
	
	public void setMin(int x, int y, int z)
	{
		this.minX = x;
		this.minY = y;
		this.minZ = z;
		
		plugin.getConfig().set("Arenas." + name + ".corner1.x", x);
		plugin.getConfig().set("Arenas." + name + ".corner1.y", y);
		plugin.getConfig().set("Arenas." + name + ".corner1.z", z);
		plugin.saveConfig();
	}
	
	public int getMaxX()
	{
		return maxX;
	}
	
	public int getMaxY()
	{
		return maxY;
	}
	
	public int getMaxZ()
	{
		return maxZ;
	}
	
	public void setMax(int x, int y, int z)
	{
		this.maxX = x;
		this.maxY = y;
		this.maxZ = z;
		
		plugin.getConfig().set("Arenas." + name + ".corner2.x", x);
		plugin.getConfig().set("Arenas." + name + ".corner2.y", y);
		plugin.getConfig().set("Arenas." + name + ".corner2.z", z);
		plugin.saveConfig();
	}
	
	public Location getSpawn1()
	{	
		if (spawn1 != null)
			return spawn1;
		else
		{
		    double midX = (maxX + minX)/2;
		    double midY = (maxY + minY)/2;
		    double midZ = (maxZ + minZ)/2;
			Location tempLocation = new Location(world, midX,midY,midZ).subtract(3, 0, 0);
			boolean locationValid = tempLocation.getBlock().isEmpty() &&
					(tempLocation.getX() < minX || tempLocation.getX() > maxX) || (tempLocation.getY() < minY || tempLocation.getY() > maxY) || (tempLocation.getZ() < minZ || tempLocation.getZ() > maxZ);
			while(!locationValid && tempLocation.getY() <= 256)
			{
				tempLocation.add(0,1,0);
				locationValid = tempLocation.getBlock().isEmpty();
			}
			return tempLocation;
		}
	}
	
	public void setSpawn1(Location newSpawn1)
	{	
		this.spawn1 = newSpawn1;
		plugin.getConfig().set("Arenas." + name + ".spawn1.x", newSpawn1.getX());
		plugin.getConfig().set("Arenas." + name + ".spawn1.y", newSpawn1.getY());
		plugin.getConfig().set("Arenas." + name + ".spawn1.z", newSpawn1.getZ());
		plugin.saveConfig();
	}
	
	public Location getSpawn2()
	{	
		if (spawn2 != null)
			return spawn2;
		else
		{
		    double midX = (maxX + minX)/2;
		    double midY = (maxY + minY)/2;
		    double midZ = (maxZ + minZ)/2;
			Location tempLocation = new Location(world, midX,midY,midZ).add(3, 0, 0);
			boolean locationValid = tempLocation.getBlock().isEmpty() &&
					(tempLocation.getX() < minX || tempLocation.getX() > maxX) || (tempLocation.getY() < minY || tempLocation.getY() > maxY) || (tempLocation.getZ() < minZ || tempLocation.getZ() > maxZ);
			while(!locationValid && tempLocation.getY() <= 256)
			{
				tempLocation.add(0,1,0);
				locationValid = tempLocation.getBlock().isEmpty();
			}
			return tempLocation;
		}
	}

	
	public void setSpawn2(Location newSpawn2)
	{	
		this.spawn2 = newSpawn2;
		plugin.getConfig().set("Arenas." + name + ".spawn2.x", newSpawn2.getX());
		plugin.getConfig().set("Arenas." + name + ".spawn2.y", newSpawn2.getY());
		plugin.getConfig().set("Arenas." + name + ".spawn2.z", newSpawn2.getZ());
		plugin.saveConfig();
	}
	
	public boolean getInUse()
	{
		return inUse;
	}
	
	public void setInUse(boolean isInUse)
	{
		this.inUse = isInUse;
	}
}
