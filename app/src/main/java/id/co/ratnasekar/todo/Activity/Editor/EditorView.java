package id.co.ratnasekar.todo.Activity.Editor;

public interface EditorView {

    void showProgress();
    void hideProgress();
    void onRequestCompleted();
    void onRequestError();
}
