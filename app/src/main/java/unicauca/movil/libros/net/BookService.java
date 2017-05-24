package unicauca.movil.libros.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import unicauca.movil.libros.models.Book;
import unicauca.movil.libros.models.SimpleResponse;

public interface BookService {

    @GET("books")
    Call<List<Book>> all();

    @POST("books")
    Call<SimpleResponse> insert(@Body Book book);
}
