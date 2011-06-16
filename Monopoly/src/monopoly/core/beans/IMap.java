package monopoly.core.beans;

import java.util.List;

import com.iiiss.spring.common.IBean;

public interface IMap extends IBean
{
	public IStartField getStartField();

	public void setStartField(IStartField startField);

	public IGame getGame();

	public void setGame(IGame game);

	public List<IField> getFields();

	public void setFields(List<IField> fields);
}