package app.listview.pedor.com.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);
        Intent intent = getIntent();
        MenuItem item = (MenuItem) intent.getSerializableExtra("item");

        TextView nameView = findViewById(R.id.name);
        ImageView photoView = findViewById(R.id.photo);
        TextView detailsView = findViewById(R.id.details);
        TextView priceView = findViewById(R.id.price);

        nameView.setText(item.getName());
        Picasso.get().load(item.getImageUrl()).error(R.drawable.placeholder).into(photoView);
        detailsView.setText(item.getDescription());
        priceView.setText("\u20ac" + item.getPrice()+"0");
    }
}
