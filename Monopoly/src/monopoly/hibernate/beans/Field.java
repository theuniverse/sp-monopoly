package monopoly.hibernate.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import monopoly.core.beans.IField;
import monopoly.core.beans.IMap;
import monopoly.core.beans.IPlayer;

import com.iiiss.spring.common.BaseBean;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "FieldType")
public class Field extends BaseBean implements IField
{
	@Basic
	private int position;

	@ManyToOne(targetEntity = Map.class, cascade =
	{ CascadeType.ALL })
	private IMap map;

	@OneToOne(targetEntity = Field.class)
	private IField next;

	@OneToOne(targetEntity = Field.class)
	private IField previous;

	@OneToMany(targetEntity = Player.class, mappedBy = "field")
	private List<IPlayer> players = new ArrayList<IPlayer>();

	public int getPosition()
	{
		return position;
	}

	public void setPosition(int position)
	{
		this.position = position;
	}

	public IMap getMap()
	{
		return map;
	}

	public void setMap(IMap map)
	{
		this.map = map;
	}

	public IField getNext()
	{
		return next;
	}

	public void setNext(IField next)
	{
		this.next = next;
	}

	public IField getPrevious()
	{
		return previous;
	}

	public void setPrevious(IField previous)
	{
		this.previous = previous;
	}

	public List<IPlayer> getPlayers()
	{
		return players;
	}

	public void setPlayers(List<IPlayer> players)
	{
		this.players = players;
	}

}
