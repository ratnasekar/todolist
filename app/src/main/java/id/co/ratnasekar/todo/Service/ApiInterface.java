package id.co.ratnasekar.todo.Service;

import java.util.List;

import id.co.ratnasekar.todo.Model.Todo;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("/todos")
    Call<List<Todo>> getTodo();

    @POST("/todos")
    @FormUrlEncoded
    Call<Todo> simpanTodo(
            @Field("userId") int userId,
            @Field("title") String title
    );

    @PUT("/todos/{id}")
    @FormUrlEncoded
    Call<Todo> updateTodo(
            @Field("userId") int userId,
            @Path("id") int id,
            @Field("title") String title
    );

    @DELETE("/todos/{id}")
    Call<Todo> hapusTodo(@Path("id") int id);
}
