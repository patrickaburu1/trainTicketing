package com.example.user.trainticketingsystem.Network;

/**
 * Created by User on 5/12/2017.
 */

public class URLs {
    //BASE URL
  // public static final String BASE_URL = "http://192.168.43.19:8080/booking/public/api/";
   public static final String BASE_URL = "http://d7d4a9a5.ngrok.io/booking/public/api/";

    //stock
    public static final String TICKETS=BASE_URL+"ticket";

    //confirm route CHECK
    public static final String  BOOKROUTE=BASE_URL+"bookcheck";
    
    //final booking
    public static final String  BOOK=BASE_URL+"bookticket";

    //FARE DETAILS
    public static final String  FAREDETAILS=BASE_URL+"fare";

    //LIST OF ROUTES
    public static final String  ROUTES=BASE_URL+"fare";

 //login
    public static final String  LOGIN_URL=BASE_URL+"login";

    public static final String  SIGNUP=BASE_URL+"register";

    //schedule
    public static final String  SCHEDULE=BASE_URL+"schedule";

//    @GET("active_patients/{facility_id}")
//    Call<ActivePatientsResponse> getActivePatients(@Path("facility_id") String facilityId);




}
