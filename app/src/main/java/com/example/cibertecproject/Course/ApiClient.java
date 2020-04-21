package com.example.cibertecproject.Course;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.TimeZone;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String URL_BASE = "http://192.168.1.62/WcfCibertec/WCFCurso.svc/";
    //private static final String URL_BASE = "http://10.0.2.2:2174/WCFCurso.svc/";

    private static Retrofit retrofit;

    public static Retrofit getApiClient(){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggingInterceptor);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateTimeConverter());
        gsonBuilder.registerTypeAdapter(Time.class, new TimeConverter());
        //gsonBuilder.setDateFormat();
        Gson gson = gsonBuilder.create();

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }
}

class DateTimeConverter implements JsonSerializer<Date>,
                         JsonDeserializer<Date> {

    private final String dateTimeFormatter;

    public DateTimeConverter() {
        this.dateTimeFormatter = ""; //DateTimeFormat.forPattern("YYYY-MM-dd HH:mm");
    }


    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        //return super();
        // \/Date(1588309200000-0500)\/
        long strMil = src.getTime();
        String strVal = "/Date(" + strMil + ")/";
        //strVal = (char)(92) + "/Date(1325134800000-0500)" + (char)92 + "/";
        //String cad2 = strVal.replace("\\",'\\');

        JsonElement jsonElement = new JsonPrimitive( strVal );
        return  jsonElement;
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        if (json.getAsString() == null || json.getAsString().isEmpty()) {
            return null;
        }
        String date = json.getAsString();
        String cad = date.substring(6,19); //Obtiene la parte principal de la fecha
        // \/Date(-62135578800000-0500)\/
        long longDate = Long.parseLong(cad);


        try {
            return new Date(longDate);
            //return formatter.parse(date);
        } catch (Exception e) {
            return null;
        }
        //return dateTimeFormatter.parseDate(json.getAsString());
    }

}



class TimeConverter implements  JsonDeserializer<Time>,JsonSerializer<Time> {

    private final String TimeFormatter;

    public TimeConverter() {
        this.TimeFormatter = ""; //DateTimeFormat.forPattern("YYYY-MM-dd HH:mm");
    }


    @Override
    public JsonElement serialize(Time src, Type typeOfSrc, JsonSerializationContext context) {
        //return super();
        String strVal = src.toString();
        StringTokenizer st = new StringTokenizer(strVal,":");
        String strHor="0",strMin="0",strSeg="0", cadHora = "";
        int ind = 1;
        while(st.hasMoreTokens()){
            if( ind == 1){
                strHor = "PT" + st.nextToken() + "H";
            }else if( ind  == 2){
                strMin = st.nextToken() + "M";
            }else {
                strSeg = st.nextToken() + "S";
            }
            ind += 1;
        }
        cadHora = strHor + strMin + strSeg ;


        //String src = "";
        //Time t = new Time(src.getTime());
        JsonElement strJson = new JsonPrimitive( cadHora );

        return strJson;
    }

    @Override
    public Time deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        if (json.getAsString() == null || json.getAsString().isEmpty()) {
            return null;
        }
        int vHora = 0, vMin=0, vSeg = 0;
        String strTime = json.getAsString();

        String num ="";
        for(int i = 0; i < strTime.length(); i++ ){
            String letra = strTime.substring(i,i+1);

            if( "0123456789".contains(letra) ){
                num = num + letra;
            }else{
                if( letra.equals("H") ){ vHora = Integer.parseInt(num); }
                else if( letra.equals("M") ){ vMin = Integer.parseInt(num); }
                else if( letra.equals("S") ){ vSeg = Integer.parseInt(num); };
                num = "";
            }
        }
        //Obtiene Hora //PT14H30M15S

        try {
            return new Time(vHora, vMin, vSeg);
            //return formatter.parse(date);
        } catch (Exception e) {
            return null;
        }
        //return dateTimeFormatter.parseDate(json.getAsString());
    }

}
