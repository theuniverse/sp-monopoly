package monopoly.core.beans;

import com.iiiss.spring.common.IBean;

public interface IPlayer extends IBean
{

	public String getColor();

	public void setColor(String color);

	public Long getCash();

	public void setCash(Long cash);

	public IGame getGame();

	public void setGame(IGame game);

	public IUser getUser();

	public void setUser(IUser user);

}