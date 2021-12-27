package com.mim.entryapp.retrofit;

import com.mim.entryapp.models.BookingStatusResponse;
import com.mim.entryapp.models.FamilyModel;
import com.mim.entryapp.models.LoginEmailResult;
import com.mim.entryapp.models.LoginResult;
import com.mim.entryapp.models.OtpResult;
import com.mim.entryapp.models.OtpVerifyModel;
import com.mim.entryapp.models.RegistrationResult;
import com.mim.entryapp.models.ResidentResponse;
import com.mim.entryapp.models.SaveAddressModel;
import com.mim.entryapp.models.AddressResult;
import com.mim.entryapp.models.DocumentResult;
import com.mim.entryapp.models.SaveRegistrationModel;
import com.mim.entryapp.models.SaveVehicleModel;
import com.mim.entryapp.models.SaveVisitorModel;


import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {

    @Headers("Accept: application/json")
    @POST("api/societyApp/SaveRegistrationDetail")
    Call<RegistrationResult> createUser(@Body SaveRegistrationModel saveRegistrationModel, @Header("Content-Type") String content_type);

    @Headers("Accept: application/json")
    @POST("api/societyApp/VerifyOTP")
    Call<OtpResult> verifyOtp(@Body OtpVerifyModel otpVerifyModel, @Header("Content-Type") String content_type);

    @Headers("Accept: application/json")
    @POST("api/societyApp/SaveAddressDetail")
    Call<AddressResult> saveAddress(@Body SaveAddressModel saveAddressModel, @Header("Content-Type") String content_type);

    @Multipart
    @Headers("Accept: application/json")
    @POST("api/societyApp/SaveDocuments")
    Call<DocumentResult> postImage(@Part MultipartBody.Part image);

    @GET("api/societyApp/Login")
    Call<LoginResult> getLoginResult(@Query("apikey") String apikey, @Query("mobile") String mobile);

    @GET("api/societyApp/Loginemail")
    Call<LoginEmailResult> getEmailLoginResult(@Query("apikey") String apikey, @Query("email") String email);

    @GET("api/societyApp/GetBookingStatus")
    Call<BookingStatusResponse> getBookingStatus(@Query("apikey") String apikey, @Query("mobile") String mobile);

    @GET("api/societyApp/ResidentList")
    Call<ResidentResponse> getResidentList(@Query("apikey") String apikey, @Query("mobile") String mobile);

    @Headers("Accept: application/json")
    @POST("api/societyApp/SaveVisitor")
    Call<AddressResult> saveVisitor(@Body SaveVisitorModel saveVisitorModel, @Header("Content-Type") String content_type);

    @Headers("Accept: application/json")
    @POST("api/societyApp/AddVehicle")
    Call<AddressResult> saveVehicle(@Body SaveVehicleModel saveVehicleModel, @Header("Content-Type") String content_type);

    @Headers("Accept: application/json")
    @POST("api/societyApp/SaveFamily")
    Call<AddressResult> saveFamily(@Body FamilyModel familyModel, @Header("Content-Type") String content_type);


}
