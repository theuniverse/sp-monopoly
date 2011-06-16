package monopoly.core.beans;

import java.util.List;

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

	public boolean isInitialized();

	public void setInitialized(boolean isInitialized);

	public List<IProperty> getProperties();

	public void setProperties(List<IProperty> properties);

	public IField getField();

	public void setField(IField field);

}