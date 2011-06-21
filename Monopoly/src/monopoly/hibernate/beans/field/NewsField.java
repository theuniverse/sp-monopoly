package monopoly.hibernate.beans.field;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import monopoly.core.beans.field.INewsField;

@Entity
@DiscriminatorValue("NewsField")
public class NewsField extends Field implements INewsField
{
}
