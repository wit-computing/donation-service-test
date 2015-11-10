package app.donation.main;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.donation.model.Donation;
import app.donation.model.Donor;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class DonationServiceAPI
{
  private String service_url = "http://localhost:9000";
  private DonationServiceProxy service;

  public DonationServiceAPI()
  {
    Gson gson = new GsonBuilder().create();

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(service_url)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();
    service = retrofit.create(DonationServiceProxy.class);
  }

  public List<Donor> getAllDonors() throws Exception
  {
    Call<List<Donor>> call = (Call<List<Donor>>) service.getAllDonors();
    Response<List<Donor>> donors = call.execute();
    return donors.body();
  }
  
  public Donor getDonor(Long id) throws Exception
  {
    Call<Donor> call = (Call<Donor>) service.getDonor(id);
    Response<Donor> donors = call.execute();
    return donors.body();
  }

  public int deleteDonor(Long id) throws Exception
  {
	Call<Donor> call =  service.deleteDonor(id);
	Response<Donor> val  = call.execute();
    return val.code();
  }
  
  public int deleteAllDonors() throws Exception
  {
	Call<String> call =  service.deleteAllDonors();
	Response<String> val  = call.execute();
    return val.code();
  }
  
  public Donor createDonor(Donor newDonor) throws Exception
  {
    Call<Donor> call = (Call<Donor>) service.createDonor(newDonor);
    Response<Donor> returnedDonor = call.execute();
    return returnedDonor.body();
  }
  
  public List<Donation> getAllDonations() throws Exception
  {
    Call<List<Donation>> call = (Call<List<Donation>>) service.getDonations();
    Response<List<Donation>> donations = call.execute();
    return donations.body();
  }
  
  public Donation createDonation(Donation newDonation) throws Exception
  {
    Call<Donation> call = (Call<Donation>) service.createDonation(newDonation);
    Response<Donation> returnedDonation = call.execute();
    return returnedDonation.body();
  }
  
  public int deleteDonation(Long id) throws Exception
  {
	Call<Donation> call =  service.deleteDonation(id);
	Response<Donation> val  = call.execute();
    return val.code();
  }
  
  public int deleteAllDonations() throws Exception
  {
	Call<String> call =  service.deleteAllDonations();
	Response<String> val  = call.execute();
    return val.code();
  }

}
