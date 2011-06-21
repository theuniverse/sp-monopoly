package monopoly.hibernate.beans.field;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import monopoly.core.beans.field.IRailwayStationField;

@Entity
@DiscriminatorValue("RailwayStationField")
public class RailwayStationField extends Field implements IRailwayStationField
{
	@OneToOne(targetEntity = RailwayStationField.class)
	private IRailwayStationField nextStop;

	public IRailwayStationField getNextStop()
	{
		return nextStop;
	}

	public void setNextStop(IRailwayStationField nextStop)
	{
		this.nextStop = nextStop;
	}

}
