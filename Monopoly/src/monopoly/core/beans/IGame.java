package monopoly.core.beans;

import java.util.List;

import com.iiiss.spring.common.IBean;

public interface IGame extends IBean
{

	public IHost getHost();

	public void setHost(IHost host);

	public List<IPlayer> getPlayers();

	public void setPlayers(List<IPlayer> players);

}