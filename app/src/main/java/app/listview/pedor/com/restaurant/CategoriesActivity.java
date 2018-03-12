package app.listview.pedor.com.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback{

    ListView categoriesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        categoriesListView = findViewById(R.id.categories);

        // Call to CategoriesRequest class the get categories
        new CategoriesRequest(getApplicationContext()).getCategories(this);
        categoriesListView.setOnItemClickListener(new CategoryClickListener());
    }

    // If the categories are retrieved, make an adapter fill the list with items
    @Override
    public void gotCategories(ArrayList<String> categories) {
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this,
                                                android.R.layout.simple_list_item_1 , categories);
        categoriesListView.setAdapter(categoryAdapter);
    }

    // If the categories could not be retrieved, create an error toast for the user
    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // ClickListener that starts the menu activity with the clicked category.
    public class CategoryClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String category = (String) adapterView.getItemAtPosition(i);
            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);
        }
    }
}
