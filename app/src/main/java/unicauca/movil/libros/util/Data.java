package unicauca.movil.libros.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by darfe on 23/05/2017.
 */

public class Data {

    public static Retrofit retrofit =  new Retrofit.Builder()
            .baseUrl("http://192.168.128.82:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
