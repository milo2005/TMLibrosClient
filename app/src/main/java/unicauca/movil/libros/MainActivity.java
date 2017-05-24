package unicauca.movil.libros;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import unicauca.movil.libros.adapters.BookAdapter;
import unicauca.movil.libros.databinding.ActivityMainBinding;
import unicauca.movil.libros.models.Book;
import unicauca.movil.libros.net.BookService;
import unicauca.movil.libros.util.Data;

public class MainActivity extends AppCompatActivity implements Callback<List<Book>> {

    ActivityMainBinding binding;
    BookAdapter adapter;
    BookService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHandler(this);

        adapter =  new BookAdapter(getLayoutInflater(), new ArrayList<Book>());
        binding.list.setAdapter(adapter);
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        service = Data.retrofit.create(BookService.class);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadBooks();
    }

    private void loadBooks() {
        Call<List<Book>> req = service.all();
        req.enqueue(this);
    }

    public void goToAdd(){
        Intent intent = new Intent(this, AddBookActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
        if(response.isSuccessful()){
            adapter.setData(response.body());
        }
    }

    @Override
    public void onFailure(Call<List<Book>> call, Throwable t) {

    }
}
