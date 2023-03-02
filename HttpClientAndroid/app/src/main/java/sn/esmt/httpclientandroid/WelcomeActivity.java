package sn.esmt.httpclientandroid;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sn.esmt.httpclientandroid.httpconfig.Api;
import sn.esmt.httpclientandroid.httpconfig.UsersResponse;
import sn.esmt.httpclientandroid.tools.MyAdapterUser;

public class WelcomeActivity extends AppCompatActivity {

    private ListView list;
    private ArrayList<UsersResponse> users = new ArrayList<UsersResponse>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        list = (ListView) findViewById(R.id.listUser);
        chargerListe();
    }
    public void chargerListe(){

        //Création de l'objet Retrofit pour accéder à l'API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.17:8081") //URL de base de l'API
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Crée une instance de l'interface Api,
        //qui définit les méthodes pour les appels HTTP.
        Api api = retrofit.create(Api.class);

        //creation d'un objet Call pour l'appel à la méthode login() de l'interface Api.
        Call<ArrayList<UsersResponse>> call = api.all();

        call.enqueue(new Callback<ArrayList<UsersResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<UsersResponse>> call, Response<ArrayList<UsersResponse>> response) {
                if (response.isSuccessful()) {
                    Log.d("Response :", response.body().get(0).getEmail());
                    response.body().stream().forEach(usersResponse -> users.add(usersResponse));
                    MyAdapterUser adpt = new MyAdapterUser(WelcomeActivity.this,users);
                    Log.d("Debbugage : " , users.get(0).getFirstName());
                    list.setAdapter(adpt);
                } else {
                    Log.d("error message exception", response.toString());

                }
            }

            @Override
            public void onFailure(Call<ArrayList<UsersResponse>> call, Throwable t) {
                Log.d("Error : ", t.getMessage());
                //D/Error :: CLEARTEXT communication to 192.168.1.17 not permitted by network security policy
            }

        });


    }

}