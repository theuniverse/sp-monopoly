package monopoly.core.beans.field;

public interface IRailwayStationField extends IField
{
	public IRailwayStationField getNextStop();

	public void setNextStop(IRailwayStationField nextStop);
}