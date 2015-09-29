package deletethis.civilization;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class Plot
{
	private int dimension;
	private int x, z;
	private Town town;
	
	public Plot(int dimension, int x, int z)
	{
		this.dimension = dimension;
		this.x = x;
		this.z = z;
	}
	
	public boolean isInPlot(EntityPlayer player)
	{
		if(!(player.worldObj.provider.getDimensionId() == dimension)) return false;
		
		int playerChunkX = player.worldObj.getChunkFromBlockCoords(player.playerLocation).xPosition;
		int playerChunkZ = player.worldObj.getChunkFromBlockCoords(player.playerLocation).zPosition;
		if(this.x != playerChunkX || this.z != playerChunkZ) return false;
		
		return true;
	}
	
	public boolean isInPlot(World world, BlockPos pos)
	{
		if(!(world.provider.getDimensionId() == dimension)) return false;
		
		Chunk chunk = world.getChunkFromBlockCoords(pos);
		return chunk.xPosition == this.x && chunk.zPosition == this.z;
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("dimension", dimension);
		nbt.setInteger("x", x);
		nbt.setInteger("z", z);
	}
	
	public static Plot readFromNBT(NBTTagCompound nbt)
	{
		int dimension = nbt.getInteger("dimension");
		int x = nbt.getInteger("x");
		int z = nbt.getInteger("z");
		return new Plot(dimension, x, z);
	}
	
	public void setDimension(int dimension)
	{
		this.dimension = dimension;
	}
	
	public int getDimension()
	{
		return dimension;
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
	
	public boolean isOwned()
	{
		return town == null ? false : true;
	}
	
	public void setTown(Town town)
	{
		this.town = town;
	}
	
	public Town getTown()
	{
		return town;
	}
	
	@Override
	public int hashCode()
	{
		int hash = 21;
		hash *= 56 + dimension;
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
