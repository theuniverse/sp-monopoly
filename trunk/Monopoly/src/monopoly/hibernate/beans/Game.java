package monopoly.hibernate.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import monopoly.core.beans.IGame;
import monopoly.core.beans.IHost;

import com.iiiss.spring.common.BaseBean;

@Entity
public class Game extends BaseBean implements IGame
{
	@OneToOne(targetEntity = Host.class, cascade =
	{ CascadeType.ALL })
	private IHost host;

	public IHost getHost()
	{
		return host;
	}

	public void setHost(IHost host)
	{
		this.host = host;
	}

}
