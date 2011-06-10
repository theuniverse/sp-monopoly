package monopoly.core.beans;

import com.iiiss.spring.common.IBean;

public interface IGame extends IBean
{

	public IHost getHost();

	public void setHost(IHost host);

}