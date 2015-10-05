package deletethis.civilization.object;

import net.minecraft.nbt.NBTTagCompound;

public class Resident
{
	private String uuid;
	private Town town;
	
	public Resident(String uuid, Town town)
	{
		this.uuid = uuid;
		this.town = town;
	}
	
	public String getUUID()
	{
		return uuid;
	}
	
	public void setUUID(String uuid)
	{
		this.uuid = uuid;
	}
	
	public Town getTown()
	{
		return town;
	}
	
	public void setTown(Town town)
	{
		this.town = town;
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		nbt.setString("uuid", uuid);
	}
	
	public static Resident readFromNBT(NBTTagCompound nbt)
	{
		String uuid = nbt.getString("uuid");
		return new Resident(uuid, null);		
	}
	
	@Override
	public int hashCode()
	{
		int hash = 21;
		hash *= 56 + uuid.hashCode();
		return hash;
	}
	
	@Override
	public boolean equals(Object object)
	{
		if(object == null)
			return false;
		if(!(object instanceof Resident))
			return false;
		
		Resident other = (Resident) object;
		
		return this.getUUID().equals(other.getUUID());
	}
}
