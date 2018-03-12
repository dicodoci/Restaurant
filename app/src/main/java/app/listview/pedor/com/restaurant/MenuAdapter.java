package app.listview.pedor.com.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter<MenuItem> {
    private ArrayList<MenuItem> menuItems;

    public MenuAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        menuItems = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
        }

        ImageView photoView = convertView.findViewById(R.id.photo);
        TextView nameView = convertView.findViewById(R.id.name);
        TextView priceView = convertView.findViewById(R.id.price);

        nameView.setText(menuItems.get(position).getName());
        priceView.setText("\u20ac" + menuItems.get(position).getPrice()+"0");
        String imageUrl = menuItems.get(position).getImageUrl();
        Picasso.get().load(imageUrl).error(R.drawable.placeholder).into(photoView);

        return convertView;
    }
}
