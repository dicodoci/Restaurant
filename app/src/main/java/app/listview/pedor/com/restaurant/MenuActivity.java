package app.listview.pedor.com.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

        // Retrieve clicked class
        Intent intent = getIntent();
        String category = (String) intent.getSerializableExtra("category");

        // Call to MenuRequest class the get the menu items of given class
        new MenuRequest(getApplicationContext(), category).getMenu(this);

        menuListView.setOnItemClickListener(new ItemClickListener());
    }

    // If the menu was retrieved, make the custom adapter fill in the list with menu items
    @Override
    public void gotMenu(ArrayList<MenuItem> menu) {
        MenuAdapter adapter = new MenuAdapter(this, R.layout.menu_item, menu);
        menuListView.setAdapter(adapter);
    }

    // If an error occurred retrieving the menu items, show that error to the user
    @Override
    public void gotMenuError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // ClickListener that starts the activity with a single menu item, with as info the item
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
