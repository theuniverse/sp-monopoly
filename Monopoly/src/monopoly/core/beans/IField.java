package monopoly.core.beans;

import java.util.List;

import com.iiiss.spring.common.IBean;

public interface IField extends IBean
{
	public int getPosition();

	public void setPosition(int position);

	public IMap getMap();

	public void setMap(IMap map);

	public IField getNext();

	public void setNext(IField next);

	public IField getPrevious();

	public void setPrevious(IField previous);

	public List<IPlayer> getPlayers();

	public void setPlayers(List<IPlayer> players);

}