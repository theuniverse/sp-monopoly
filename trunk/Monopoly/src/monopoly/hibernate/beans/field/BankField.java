package monopoly.hibernate.beans.field;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import monopoly.core.beans.field.IBankField;

@Entity
@DiscriminatorValue("BankField")
public class BankField extends Field implements IBankField
{
}
