package app.donation.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import app.donation.main.DonationServiceAPI;
import app.donation.model.Donor;

public class DonorTest
{
  static Donor donorArray [] = 
  { 
    new Donor ("homer",  "simpson", "homer@simpson.com",  "secret"),
    new Donor ("lisa",   "simpson", "lisa@simpson.com",   "secret"),
    new Donor ("maggie", "simpson", "maggie@simpson.com", "secret"),
    new Donor ("bart",   "simpson", "bart@simpson.com",   "secret"),
    new Donor ("marge",  "simpson", "marge@simpson.com",  "secret"),
  };
  List <Donor> donorList = new ArrayList<>();	
	
  private DonationServiceAPI donationServiceAPI = new DonationServiceAPI();
	
  @Before
  public void setup() throws Exception
  { 
    for (Donor donor : donorArray)
    {
      Donor returned = donationServiceAPI.createDonor(donor);
      donorList.add(returned);
    }
  }
  
  @After
  public void teardown() throws Exception
  {
	donationServiceAPI.deleteAllDonors();  
  }  
  
  @Test
  public void testCreate () throws Exception
  {
    assertEquals (donorArray.length, donorList.size());
    for (int i=0; i<donorArray.length; i++)
    {
      assertEquals(donorList.get(i), donorArray[i]);
    }
  }

  @Test
  public void testList() throws Exception
  {
    List<Donor> list = donationServiceAPI.getAllDonors();
    assertTrue (list.containsAll(donorList));
  }
  
  @Test
  public void testDelete () throws Exception
  {
    List<Donor> list1 = donationServiceAPI.getAllDonors();
    
    Donor testdonor = new Donor("mark",  "simpson", "marge@simpson.com",  "secret");
    Donor returnedDonor = donationServiceAPI.createDonor(testdonor);
    
    List<Donor> list2 = donationServiceAPI.getAllDonors();
    assertEquals (list1.size()+1, list2.size());
    
    int code = donationServiceAPI.deleteDonor(returnedDonor.id);
    assertEquals (200, code);
    
    List<Donor> list3 = donationServiceAPI.getAllDonors();
    assertEquals (list1.size(), list3.size());
  }
  
}