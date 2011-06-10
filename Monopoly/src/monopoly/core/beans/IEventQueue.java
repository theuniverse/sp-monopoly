package monopoly.core.beans;

import java.util.List;

import monopoly.core.beans.event.IEvent;

public interface IEventQueue
{

	public IUser getUser();

	public void setUser(IUser user);

	public List<IEvent> getEvents();

	public void setEvents(List<IEvent> events);

}