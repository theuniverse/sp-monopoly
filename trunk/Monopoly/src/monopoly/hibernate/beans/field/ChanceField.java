package monopoly.hibernate.beans.field;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import monopoly.core.beans.field.IChanceField;

@Entity
@DiscriminatorValue("ChanceField")
public class ChanceField extends Field implements IChanceField {

}
