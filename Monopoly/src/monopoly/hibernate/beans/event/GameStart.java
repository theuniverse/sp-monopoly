package monopoly.hibernate.beans.event;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import monopoly.core.beans.event.IGameStart;

@Entity
@DiscriminatorValue("GameStart")
public class GameStart extends Event implements IGameStart
{
	{
		this.setType(TYPE);
	}

	public String toString()
	{
		return "'type':'" + TYPE + "'";
	}
}
