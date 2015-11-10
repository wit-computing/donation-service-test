package app.donation.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import app.donation.main.DonationServiceAPI;
import app.donation.model.Donation;
import app.donation.model.Donor;

public class DonationTest
{
  private Donor marge =  new Donor ("marge",  "simpson", "homer@simpson.com",  "secret");
  
  private DonationServiceAPI donationServiceAPI = new DonationServiceAPI();
  
  @Before
  public void setup() throws Exception
  { 
    marge = donationServiceAPI.createDonor(marge);
  }
  
  @After
  public void teardown() throws Exception
  {
    donationServiceAPI.deleteDonor(marge.id);
  }
  
  @Test
  public void testCreateDonation () throws Exception
  {
    Donation donation = new Donation (123, "cash");
    Donation returnedDonation = donationServiceAPI.createDonation(marge.id, donation);
    assertEquals (donation, returnedDonation);
    
    donationServiceAPI.deleteDonation(marge.id, returnedDonation.id);
  }
  
  @Test
  public void testCreateDonations () throws Exception
  {
    Donation donation1 = new Donation (123, "cash");
    Donation donation2 = new Donation (450, "cash");
    Donation donation3 = new Donation (43,  "paypal");
    
    Donation returnedDonation1 = donationServiceAPI.createDonation(marge.id, donation1);
    Donation returnedDonation2 = donationServiceAPI.createDonation(marge.id, donation2);
    Donation returnedDonation3 = donationServiceAPI.createDonation(marge.id, donation3);
    
    assertEquals(donation1, returnedDonation1);
    assertEquals(donation2, returnedDonation2);
    assertEquals(donation3, returnedDonation3);

    donationServiceAPI.deleteDonation(marge.id, returnedDonation1.id);
    donationServiceAPI.deleteDonation(marge.id, returnedDonation2.id);    
    donationServiceAPI.deleteDonation(marge.id, returnedDonation3.id);
  }
  
  @Test
  public void testListDonations () throws Exception
  {
    Donation donation1 = new Donation (123, "cash");
    Donation donation2 = new Donation (450, "cash");
    Donation donation3 = new Donation (43,  "paypal");
    
    donationServiceAPI.createDonation(marge.id, donation1);
    donationServiceAPI.createDonation(marge.id, donation2);
    donationServiceAPI.createDonation(marge.id, donation3);
    
    List<Donation> donations = donationServiceAPI.getDonations(marge.id);
    assertEquals (3, donations.size());
    
    assertTrue(donations.contains(donation1));
    assertTrue(donations.contains(donation2));
    assertTrue(donations.contains(donation3));

    donationServiceAPI.deleteDonation(marge.id, donations.get(0).id);
    donationServiceAPI.deleteDonation(marge.id, donations.get(1).id);    
    donationServiceAPI.deleteDonation(marge.id, donations.get(2).id);
  }
}