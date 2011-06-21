package monopoly.hibernate.beans.event;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.iiiss.spring.common.BaseBean;

import monopoly.core.beans.event.IEvent;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "EventType")
public abstract class Event extends BaseBean implements IEvent
{
	@Basic
	private String type;

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public abstract String toString();
}
