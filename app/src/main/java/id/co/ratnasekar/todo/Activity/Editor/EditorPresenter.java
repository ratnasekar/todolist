package id.co.ratnasekar.todo.Activity.Editor;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.util.Log;

import id.co.ratnasekar.todo.Model.Todo;
import id.co.ratnasekar.todo.Service.ApiClient;
import id.co.ratnasekar.todo.Service.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class EditorPresenter {

    private EditorView view;

    public EditorPresenter(EditorView view) {
        this.view = view;
    }

    void simpanTodo(String title) {
//        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Todo> call = apiInterface.simpanTodo(1, title);
        call.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(@NonNull Call<Todo> call, @NonNull Response<Todo> response) {
                view.hideProgress();
                if (response.isSuccessful()) {
                    response.body().toString();
                    Log.i(TAG, "Post submitted to API" + response.body().toString());
                    view.onRequestCompleted();

                    /* Boolean completed = response.body().getCompleted();
                    if (completed) {
                        view.onRequestError();
                    } else {
                        view.onRequestCompleted();
                        Log.i(TAG, "Post submitted to API" + response.body().toString());
                    } */
                }
            }
            @Override
            public void onFailure(@NonNull Call<Todo> call, @NonNull Throwable t) {
                Log.e(TAG, "Unable to submit Post to API");
                view.hideProgress();
                view.onRequestError();
            }
        });
    }

    void updateTodo(String title) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Todo> call = apiInterface.updateTodo(1, 1, title);
        call.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(@NonNull Call<Todo> call, @NonNull Response<Todo> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    response.body().toString();
                    Log.i(TAG, "Post submitted to API" + response.body().toString());
                    view.onRequestCompleted();

                    /* boolean completed = response.body().getCompleted();
                    if (completed) {
                        view.onRequestError();
                        Log.i(TAG, "Put submitted to API" + response.body().toString());
                    } else {
                        view.onRequestCompleted();
                    } */
                }
            }
            @Override
            public void onFailure(@NonNull Call<Todo> call, @NonNull Throwable t) {
                Log.e(TAG, "Unable to submit Put to API");
                view.hideProgress();
                view.onRequestError();
            }
        });
    }

    void hapusTodo(int id) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Todo> call = apiInterface.hapusTodo(id);
        call.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(@NonNull Call<Todo> call, @NonNull Response<Todo> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    response.body().toString();
                    Log.i(TAG, "Delete submitted to API" + response.body().toString());
                    view.onRequestCompleted();

                    /* boolean completed = response.body().getCompleted();
                    if (completed) {
                        view.onRequestError();
                    } else {
                        view.onRequestCompleted();
                    } */
                }
            }
            @Override
            public void onFailure(@NonNull Call<Todo> call, @NonNull Throwable t) {
                Log.e(TAG, "Unable to submit Delete to API");
                view.hideProgress();
                view.onRequestError();
            }
        });
    }
}
