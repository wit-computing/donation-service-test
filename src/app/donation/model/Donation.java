package app.donation.model;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.google.common.base.Objects;

public class Donation
{
  public long   id;
  public int    amount;
  public String method;
  
  public Donation (int amount, String method)
  {
    this.amount = amount;
    this.method = method;
  }
  
  public String toString()
  {
    return toStringHelper(this).addValue(id)
    		                   .addValue(amount)
                               .addValue(method)                               
                               .toString();
  }
  
  @Override
  public boolean equals(final Object obj)
  {
    if (obj instanceof Donation)
    {
      final Donation other = (Donation) obj;
      return Objects.equal(amount ,  other.amount) 
          && Objects.equal(method,   other.method);                            
    }
    else
    {
      return false;
    }
  } 
}