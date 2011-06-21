package monopoly.hibernate.beans.field;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import monopoly.core.beans.field.IParkField;

@Entity
@DiscriminatorValue("ParkField")
public class ParkField extends Field implements IParkField
{
}
