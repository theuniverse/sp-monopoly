package monopoly.hibernate.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import monopoly.core.beans.IField;
import monopoly.core.beans.IGame;
import monopoly.core.beans.IMap;
import monopoly.core.beans.IStartField;

import com.iiiss.spring.common.BaseBean;

@Entity
public class Map extends BaseBean implements IMap
{
	@OneToOne(targetEntity = StartField.class)
	private IStartField startField;

	@OneToOne(targetEntity = Game.class, cascade =
	{ CascadeType.ALL })
	private IGame game;

	@OneToMany(targetEntity = Field.class, mappedBy = "map")
	private List<IField> fields = new ArrayList<IField>();

	public IStartField getStartField()
	{
		return startField;
	}

	public void setStartField(IStartField startField)
	{
		this.startField = startField;
	}

	public IGame getGame()
	{
		return game;
	}

	public void setGame(IGame game)
	{
		this.game = game;
	}

	public List<IField> getFields()
	{
		return fields;
	}

	public void setFields(List<IField> fields)
	{
		this.fields = fields;
	}
}
