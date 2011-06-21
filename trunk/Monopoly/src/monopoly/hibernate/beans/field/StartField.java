package monopoly.hibernate.beans.field;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import monopoly.core.beans.field.IStartField;

@Entity
@DiscriminatorValue("StartField")
public class StartField extends Field implements IStartField
{
}
