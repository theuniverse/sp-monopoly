package monopoly.impl.controllers.json;

import java.util.ArrayList;
import java.util.List;

import monopoly.core.beans.IPlayer;
import monopoly.core.beans.IUser;
import monopoly.core.beans.field.IBankField;
import monopoly.core.beans.field.IField;
import monopoly.core.beans.field.IJailField;
import monopoly.core.beans.field.INormalField;
import monopoly.core.beans.field.IParkField;
import monopoly.core.beans.field.IRailwayStationField;
import monopoly.core.beans.field.IStartField;

public class JsonInitInfo extends JsonObject {
	public static final int START_FIELD = 0;
	public static final int NORMAL_FIELD = 1;
	public static final int BANK_FIELD = 2;
	public static final int CHANCE_FIELD = 3;
	public static final int PARK_FIELD = 4;
	public static final int RAILWAY_FIELD = 5;
	public static final int JAIL_FIELD = 6;

	private long hostid;
	private List<String> players = new ArrayList<String>();
	private List<Integer> fields = new ArrayList<Integer>();

	public JsonInitInfo(long hostid, List<IPlayer> players, List<IField> fields) {
		this.hostid = hostid;
		for (IPlayer player : players)
			this.players.add(player.getColor());
		for (IField field : fields) {
			if (field instanceof IStartField)
				this.fields.add(START_FIELD);
			else if (field instanceof INormalField)
				this.fields.add(NORMAL_FIELD);
			else if (field instanceof IBankField)
				this.fields.add(BANK_FIELD);
			// else if (field instanceof IChanceField)
			// this.fields.add(CHANCE_FIELD);
			else if (field instanceof IParkField)
				this.fields.add(PARK_FIELD);
			else if (field instanceof IRailwayStationField)
				this.fields.add(RAILWAY_FIELD);
			else if (field instanceof IJailField)
				this.fields.add(JAIL_FIELD);
		}
	}

	public long getHostid() {
		return hostid;
	}

	public List<String> getPlayers() {
		return players;
	}

	public List<Integer> getFields() {
		return fields;
	}

}
