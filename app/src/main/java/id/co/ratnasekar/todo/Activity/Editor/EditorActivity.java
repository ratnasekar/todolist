package id.co.ratnasekar.todo.Activity.Editor;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import id.co.ratnasekar.todo.R;

public class EditorActivity extends AppCompatActivity implements EditorView {

    EditText edtTitle;
    ProgressDialog mProgress;
    EditorPresenter presenter;
    int id;
    String title;
    Menu actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        edtTitle = findViewById(R.id.edtTitle);

        mProgress = new ProgressDialog(this);
        mProgress.setMessage("Loading ...");

        presenter = new EditorPresenter(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        title = intent.getStringExtra("title");

        setDataFromIntentExtra();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.editor_menu, menu);
        actionMenu = menu;

        if (id != 0) {
            actionMenu.findItem(R.id.ubah).setVisible(true);
            actionMenu.findItem(R.id.hapus).setVisible(true);
            actionMenu.findItem(R.id.simpan).setVisible(false);
            actionMenu.findItem(R.id.update).setVisible(false);
        } else {
            actionMenu.findItem(R.id.ubah).setVisible(false);
            actionMenu.findItem(R.id.hapus).setVisible(false);
            actionMenu.findItem(R.id.simpan).setVisible(true);
            actionMenu.findItem(R.id.update).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = edtTitle.getText().toString().trim();
        switch (item.getItemId()) {
            case R.id.simpan:
                if (title.isEmpty()) {
                    edtTitle.setError("Harap masukan todo list");
                } else {
                    presenter.simpanTodo(title);
                }
                return true;
            case R.id.ubah:
                editMode();
                actionMenu.findItem(R.id.ubah).setVisible(false);
                actionMenu.findItem(R.id.hapus).setVisible(false);
                actionMenu.findItem(R.id.simpan).setVisible(false);
                actionMenu.findItem(R.id.update).setVisible(true);
                return true;
            case R.id.update:
                if (title.isEmpty()) {
                    edtTitle.setError("Harap masukan todo list");
                } else {
                    presenter.updateTodo(title);
                }
                return true;
            case R.id.hapus:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Hapus!");
                alertDialog.setMessage("Apakah kamu yakin?");
                alertDialog.setNegativeButton("Iya", (dialog, which) -> {
                    dialog.dismiss();
                    presenter.hapusTodo(id);
                });
                alertDialog.setPositiveButton("Tidak",
                        (dialog, which) -> dialog.dismiss());
                alertDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void showProgress() {
//        mProgress.show();
    }

    @Override
    public void hideProgress() {
        mProgress.hide();
    }

    @Override
    public void onRequestCompleted() {
        Toast.makeText(EditorActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onRequestError() {
        Toast.makeText(EditorActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
    }

    private void setDataFromIntentExtra() {
        if (id != 0) {
            edtTitle.setText(title);
            getSupportActionBar().setTitle("Update Todo List");
            readMode();
        } else {
            editMode();
        }
    }

    private void editMode() {
        edtTitle.setFocusableInTouchMode(true);
    }

    private void readMode() {
        edtTitle.setFocusableInTouchMode(false);
        edtTitle.setFocusable(false);
    }
}
