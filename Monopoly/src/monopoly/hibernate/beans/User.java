package monopoly.hibernate.beans;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import monopoly.core.beans.IHost;
import monopoly.core.beans.IUser;

import com.iiiss.spring.common.BaseBean;

@Entity
public class User extends BaseBean implements IUser
{
	@Basic
	private String username;

	@Basic
	private String password;

	@ManyToOne(targetEntity = Host.class)
	private IHost host;

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public IHost getHost()
	{
		return host;
	}

	public void setHost(IHost host)
	{
		this.host = host;
	}
}