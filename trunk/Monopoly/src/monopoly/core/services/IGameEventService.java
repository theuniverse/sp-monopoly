package monopoly.core.services;

public interface IGameEventService
{
	public boolean checkCastDie(String username);

	public void checkNextPlayer(String username);
}
