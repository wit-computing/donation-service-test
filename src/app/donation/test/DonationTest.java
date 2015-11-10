package app.donation.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import app.donation.main.DonationServiceAPI;
import app.donation.model.Donation;

public class DonationTest
{
  static Donation donationArray [] = 
  { 
    new Donation (23,   "visa"),
    new Donation (3,    "paypal"),
    new Donation (423,  "cashl"),
    new Donation (11,   "paypal")
  };
  List <Donation> donationList = new ArrayList<>();	
	
  private DonationServiceAPI donationServiceAPI = new DonationServiceAPI();
	
  @Before
  public void setup() throws Exception
  { 
    for (Donation donation : donationArray)
    {
      Donation returned = donationServiceAPI.createDonation(donation);
      donationList.add(returned);
    }
  }
  
  @After
  public void teardown() throws Exception
  {
	donationServiceAPI.deleteAllDonations();  
  }  
  
  @Test
  public void testCreate () throws Exception
  {
    assertEquals (donationArray.length, donationList.size());
    for (int i=0; i<donationArray.length; i++)
    {
      assertEquals(donationList.get(i), donationArray[i]);
    }
  }

  @Test
  public void testList() throws Exception
  {
    List<Donation> list = donationServiceAPI.getAllDonations();
    assertTrue (list.containsAll(donationList));
  }
  
  @Test
  public void testDelete () throws Exception
  {
    List<Donation> list1 = donationServiceAPI.getAllDonations();
    
    Donation testdonation = new Donation(1234, "debit");
    Donation returnedDonation = donationServiceAPI.createDonation(testdonation);
    
    List<Donation> list2 = donationServiceAPI.getAllDonations();
    assertEquals (list1.size()+1, list2.size());
    
    int code = donationServiceAPI.deleteDonation(returnedDonation.id);
    assertEquals (200, code);
    
    List<Donation> list3 = donationServiceAPI.getAllDonations();
    assertEquals (list1.size(), list3.size());
  }
  
}