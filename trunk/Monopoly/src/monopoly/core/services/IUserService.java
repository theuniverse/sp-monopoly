package monopoly.core.services;

public interface IUserService {

	public boolean register(String username, String password);

	public boolean login(String username, String password);

}