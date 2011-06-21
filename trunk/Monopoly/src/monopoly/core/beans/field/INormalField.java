package monopoly.core.beans.field;

import monopoly.core.beans.IProperty;

public interface INormalField extends IField
{

	public IProperty getProperty();

	public void setProperty(IProperty property);

}