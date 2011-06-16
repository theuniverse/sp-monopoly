package monopoly.hibernate.beans;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import monopoly.core.beans.IStartField;

@Entity
@DiscriminatorValue("StartField")
public class StartField extends Field implements IStartField
{
}
