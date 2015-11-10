package app.donation.model;

import com.google.common.base.Objects;
import static com.google.common.base.MoreObjects.toStringHelper;

public class Donor
{
  public long   id;
  public String firstName;
  public String lastName;
  public String email;
  public String password;
  
  public Donor(String firstName, String lastName, String email, String password)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }
  
  public String toString()
  {
    return toStringHelper(this).addValue(id)
    		                   .addValue(firstName)
                               .addValue(lastName)
                               .addValue(password)
                               .addValue(email)                               
                               .toString();
  }
  
  @Override
  public boolean equals(final Object obj)
  {
    if (obj instanceof Donor)
    {
      final Donor other = (Donor) obj;
      return Objects.equal(firstName,  other.firstName) 
          && Objects.equal(lastName,   other.lastName)
          && Objects.equal(email,      other.email)
          && Objects.equal(password,   other.password);                               
    }
    else
    {
      return false;
    }
  } 
}