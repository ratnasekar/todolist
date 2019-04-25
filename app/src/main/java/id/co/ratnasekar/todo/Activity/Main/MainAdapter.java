package id.co.ratnasekar.todo.Activity.Main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import id.co.ratnasekar.todo.Model.Todo;
import id.co.ratnasekar.todo.R;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<Todo> todos;
    private ItemClickListener itemClickListener;

    public MainAdapter(Context context, List<Todo> todos, ItemClickListener itemClickListener) {
        this.context = context;
        this.todos = todos;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note,
                parent, false);
        return new RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        Todo todo = todos.get(position);
        holder.txtTitle.setText(todo.getTitle());
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtTitle;
        CardView cardItem;
        ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            cardItem = itemView.findViewById(R.id.cardItem);

            this.itemClickListener = itemClickListener;
            cardItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
