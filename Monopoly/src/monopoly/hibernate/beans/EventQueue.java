package monopoly.hibernate.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import monopoly.core.beans.IEventQueue;
import monopoly.core.beans.IUser;
import monopoly.core.beans.event.IEvent;
import monopoly.hibernate.beans.event.Event;

import com.iiiss.spring.common.BaseBean;

@Entity
public class EventQueue extends BaseBean implements IEventQueue
{
	@OneToOne(targetEntity = User.class)
	private IUser user;

	@ManyToMany(targetEntity = Event.class)
	private List<IEvent> events = new ArrayList<IEvent>();

	public IUser getUser()
	{
		return user;
	}

	public void setUser(IUser user)
	{
		this.user = user;
	}

	public List<IEvent> getEvents()
	{
		return events;
	}

	public void setEvents(List<IEvent> events)
	{
		this.events = events;
	}

}
