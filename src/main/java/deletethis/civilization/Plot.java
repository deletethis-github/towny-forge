package deletethis.civilization;

import net.minecraft.nbt.NBTTagCompound;

public class Plot
{
	private int x, z;
	private Town town;
	
	public Plot(int x, int z)
	{
		this.x = x;
		this.z = z;
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("x", x);
		nbt.setInteger("z", z);
	}
	
	public static Plot readFromNBT(NBTTagCompound nbt)
	{
		int x = nbt.getInteger("x");
		int z = nbt.getInteger("z");
		return new Plot(x, z);		
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
	
	@Override
	public int hashCode()
	{
		int hash = 21;
		hash *= 56 * x;
		hash *= 56 * z;
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
