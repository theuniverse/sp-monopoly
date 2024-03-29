package monopoly.hibernate.beans.field;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import monopoly.core.beans.IProperty;
import monopoly.core.beans.field.INormalField;
import monopoly.hibernate.beans.Property;

@Entity
@DiscriminatorValue("NormalField")
public class NormalField extends Field implements INormalField
{
	@OneToOne(targetEntity = Property.class)
	private IProperty property;

	public IProperty getProperty()
	{
		return property;
	}

	public void setProperty(IProperty property)
	{
		this.property = property;
	}

}
