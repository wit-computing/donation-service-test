package app.donation.main;

import java.util.List;
import app.donation.model.Donor;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface DonationServiceProxy
{
  @GET("/api/donors")
  Call<List<Donor>> getAllDonors();
  
  @GET("/api/donors/{id}")
  Call<Donor> getDonor(@Path("id") Long id);
  
  @POST("/api/donors")
  Call<Donor> createDonor(@Body Donor Donor);
  
  @DELETE("/api/donors/{id}")
  Call<Donor> deleteDonor(@Path("id") Long id);

  @DELETE("/api/donors")
  Call<String> deleteAllDonors();
}