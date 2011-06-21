package monopoly.core.beans.event;

public interface IBankServiceSave extends IPlayerEvent
{
	public static final String TYPE = "bankservicesave";

	public Long getAmount();

	public void setAmount(Long amount);
}