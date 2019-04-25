package id.co.ratnasekar.todo.Activity.Main;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import id.co.ratnasekar.todo.Activity.Editor.EditorActivity;
import id.co.ratnasekar.todo.Model.Todo;
import id.co.ratnasekar.todo.R;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final int INTENT_EDIT = 200;
    private static final int INTENT_ADD = 100;
    FloatingActionButton mFloating;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;
    MainPresenter presenter;
    MainAdapter adapter;
    MainAdapter.ItemClickListener itemClickListener;
    List<Todo> todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefresh = findViewById(R.id.swipeRefresh);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFloating = findViewById(R.id.addNew);
        mFloating.setOnClickListener(view ->
            startActivityForResult(new Intent(this, EditorActivity.class), INTENT_ADD)
        );

        presenter = new MainPresenter(this);
        presenter.getData();

        swipeRefresh.setOnRefreshListener(
                () -> presenter.getData()
        );

        itemClickListener = ((view, position) -> {
            int id = todo.get(position).getId();
            String title = todo.get(position).getTitle();

            Intent intent = new Intent(this, EditorActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("title", title);
            startActivityForResult(intent, INTENT_EDIT);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INTENT_ADD && resultCode == RESULT_OK) {
            presenter.getData();
        }
        else if (requestCode == INTENT_EDIT && resultCode == RESULT_OK) {
            presenter.getData();
        }
    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onGetResult(List<Todo> todos) {
        adapter = new MainAdapter(this, todos, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        todo = todos;
    }

    @Override
    public void onErrorLoading() {
        Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
    }
}
