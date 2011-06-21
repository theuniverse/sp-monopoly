package monopoly.hibernate.beans.field;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import monopoly.core.beans.field.IJailField;

@Entity
@DiscriminatorValue("JailField")
public class JailField extends Field implements IJailField
{
}
