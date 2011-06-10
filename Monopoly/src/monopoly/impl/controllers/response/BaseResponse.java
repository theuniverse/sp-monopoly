package monopoly.impl.controllers.response;

import java.util.ArrayList;
import java.util.List;

public class BaseResponse
{
	private List<ResponsePair> attributes = new ArrayList<ResponsePair>();

	public List<ResponsePair> getAttributes()
	{
		return attributes;
	}

	public void put(String name, Object value)
	{
		this.attributes.add(new ResponsePair(name, value));
	}

	public String toString()
	{
		String ret = "";
		for (ResponsePair pair : attributes)
			ret += pair.getName() + ":" + pair.getValue() + "&&";
		if (ret.length() > 0)
			ret = ret.substring(0, ret.length() - 2);
		return ret;
	}

}
