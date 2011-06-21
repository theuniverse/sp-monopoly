package monopoly.core.beans.event;

public interface IBankServiceWithdraw extends IPlayerEvent
{
	public static final String TYPE = "bankservicewithdraw";

	public Long getAmount();

	public void setAmount(Long amount);
}