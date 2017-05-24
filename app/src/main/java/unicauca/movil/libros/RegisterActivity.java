package unicauca.movil.libros;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import unicauca.movil.libros.databinding.ActivityRegisterBinding;
import unicauca.movil.libros.models.SimpleResponse;
import unicauca.movil.libros.models.User;
import unicauca.movil.libros.net.UserService;
import unicauca.movil.libros.util.Data;

public class RegisterActivity extends AppCompatActivity implements Callback<SimpleResponse> {

    ActivityRegisterBinding binding;
    UserService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setHandler(this);
        service = Data.retrofit.create(UserService.class);
    }

    public void register(){
        String email =  binding.email.getText().toString();
        String pass =  binding.pass.getText().toString();
        String name =  binding.nombre.getText().toString();
        User user = new User(name, email, pass);
        Call<SimpleResponse> request = service.registro(user);
        request.enqueue(this);
    }

    @Override
    public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
        if(response.isSuccessful()){
            SimpleResponse res = response.body();
            if(res.isSuccess()){
                Toast.makeText(this, "Registro exitoso !", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Toast.makeText(RegisterActivity.this, res.getMsg(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onFailure(Call<SimpleResponse> call, Throwable t) {

    }
}
