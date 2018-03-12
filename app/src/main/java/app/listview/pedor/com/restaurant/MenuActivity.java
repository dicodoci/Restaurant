package app.listview.pedor.com.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback{

    ListView menuListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menuListView = findViewById(R.id.menuList);
        Intent intent = getIntent();
        String category = (String) intent.getSerializableExtra("category");

        new MenuRequest(getApplicationContext(), category).getMenu(this);
        menuListView.setOnItemClickListener(new ItemClickListener());
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menu) {
        MenuAdapter adapter = new MenuAdapter(this, R.layout.menu_item, menu);
        menuListView.setAdapter(adapter);
    }

    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public class ItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            MenuItem item = (MenuItem) adapterView.getItemAtPosition(i);
            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("item", item);
            startActivity(intent);
        }
    }
}
