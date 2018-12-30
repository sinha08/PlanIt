package planit.sinha.ankur.com.planit.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.reactivex.annotations.NonNull;
import planit.sinha.ankur.com.planit.R;
import planit.sinha.ankur.com.planit.data.model.db.Category;

/**
 * Created by ankur sinha on 29-12-2018.
 */

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> mData;
    private LayoutInflater mInflater;
    private Context mContext;
    private HomeActivity mActivity;

    // data is passed into the constructor
    CategoryAdapter(Context context, List<Category> data) {
        mContext = context;
        mActivity = (HomeActivity)context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    public void invalidateRecyclerView(List<Category> list) {
        mData = list;
        notifyDataSetChanged();
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String text = mData.get(position).getText();
        holder.myTextView.setText(text);
        holder.selectIcon.setVisibility(View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, text+ "clicked",Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                holder.selectIcon.setVisibility(View.VISIBLE);
                Category category = new Category(holder.myTextView.getText().toString());
                mActivity.setFabToDelete(true, category);
                return true;
            }
        });
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;
        ImageView selectIcon;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.category_name);
            selectIcon = itemView.findViewById(R.id.select_icon);
        }
    }
}