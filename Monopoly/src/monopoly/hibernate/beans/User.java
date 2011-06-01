package monopoly.hibernate.beans;

import javax.persistence.Basic;
import javax.persistence.Entity;

import monopoly.core.beans.IUser;

import com.iiiss.spring.common.BaseBean;

@Entity
public class User extends BaseBean implements IUser {
	@Basic
	private String username;

	@Basic
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
