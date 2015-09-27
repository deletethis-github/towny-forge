package deletethis.civilization;

import net.minecraft.nbt.NBTTagCompound;

public class Resident
{
	private String uuid;
	
	public Resident(String uuid)
	{
		this.uuid = uuid;
	}
	
	public String getUUID()
	{
		return uuid;
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		nbt.setString("uuid", uuid);
	}
	
	public static Resident readFromNBT(NBTTagCompound nbt)
	{
		String uuid = nbt.getString("uuid");
		return new Resident(uuid);		
	}
}
