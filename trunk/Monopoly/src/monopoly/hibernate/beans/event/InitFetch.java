package monopoly.hibernate.beans.event;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import monopoly.core.beans.event.IInitFetch;

@Entity
@DiscriminatorValue("InitFetch")
public class InitFetch extends Event implements IInitFetch
{
	{
		this.setType(TYPE);
	}

	public String toString()
	{
		return "'type':'" + TYPE + "'";
	}
}
