package deletethis.townyforge.object;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.DimensionManager;

public class Plot
{
	private World world;
	private int x, z;
	private Town town;
	
	public Plot(World world, int x, int z, Town town)
	{
		this.world = world;
		this.x = x;
		this.z = z;
		this.town = town;
	}
	
	public boolean isInPlot(EntityPlayer player)
	{
		if(player.worldObj != world) return false;
		
		int playerChunkX = player.worldObj.getChunkFromBlockCoords(player.playerLocation).xPosition;
		int playerChunkZ = player.worldObj.getChunkFromBlockCoords(player.playerLocation).zPosition;
		if(this.x != playerChunkX || this.z != playerChunkZ) return false;
		
		return true;
	}
	
	public boolean isInPlot(World world, BlockPos pos)
	{
		if(this.world != world) return false;
		
		Chunk chunk = world.getChunkFromBlockCoords(pos);
		return chunk.xPosition == this.x && chunk.zPosition == this.z;
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		int dimension = world.provider.getDimensionId();
		nbt.setInteger("dimension", dimension);
		nbt.setInteger("x", x);
		nbt.setInteger("z", z);
		nbt.setString("town", town.getName());
	}
	
	public static Plot readFromNBT(NBTTagCompound nbt)
	{	
		int dimension = nbt.getInteger("dimension");
		int x = nbt.getInteger("x");
		int z = nbt.getInteger("z");
		World world = DimensionManager.getWorld(dimension);
		return new Plot(world, x, z, null);
	}
	
	public void setWorld(World world)
	{
		this.world = world;
	}
	
	public World getWorld()
	{
		return world;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getX()
	{
		return x;
	}
	
	public void setZ(int z)
	{
		this.z = z;
	}
	
	public int getZ()
	{
		return z;
	}
	
	public void setTown(Town town)
	{
		this.town = town;
	}
	
	public Town getTown()
	{
		return town;
	}
	
	public boolean isOwned()
	{
		return town == null ? false : true;
	}
	
	@Override
	public int hashCode()
	{
		int hash = 21;
		hash *= 56 + world.provider.getDimensionId();
		hash *= 56 + x;
		hash *= 56 + z;
		return hash;
	}
	
	@Override
	public boolean equals(Object object)
	{
		if(object == null)
			return false;
		if(!(object instanceof Plot))
			return false;
		
		Plot other = (Plot) object;
		
		return this.getX() == other.getX() && this.getZ() == other.getZ();
	}
}
