package id.co.ratnasekar.todo.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Todo {

    @Expose
    @SerializedName("userId") private int userId;
    @Expose
    @SerializedName("id") private int id;
    @Expose
    @SerializedName("title") private String title;
    @Expose
    @SerializedName("completed") private boolean completed;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
