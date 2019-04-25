package id.co.ratnasekar.todo.Activity.Main;

import android.support.annotation.NonNull;

import java.util.List;

import id.co.ratnasekar.todo.Model.Todo;
import id.co.ratnasekar.todo.Service.ApiClient;
import id.co.ratnasekar.todo.Service.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    private MainView view;

    public MainPresenter(MainView mainView) {
        this.view = mainView;
    }

    void getData() {
        view.showLoading();
        //request ke server
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        retrofit2.Call<List<Todo>> call = apiInterface.getTodo();
        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(@NonNull Call<List<Todo>> call, @NonNull Response<List<Todo>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Todo>> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading();
            }
        });
    }
}
