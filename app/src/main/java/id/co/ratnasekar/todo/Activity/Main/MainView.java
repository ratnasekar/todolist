package id.co.ratnasekar.todo.Activity.Main;

import java.util.List;

import id.co.ratnasekar.todo.Model.Todo;

public interface MainView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Todo> todos);
    void onErrorLoading();
}
