package app.listview.pedor.com.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by dico_ on 11-3-2018.
 */

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener{
    private Context context;
    private String url = "https://resto.mprog.nl/menu";
    private ArrayList<MenuItem> menuArray = new ArrayList<>();
    private Callback callbackActivity;
    private String intendedCategory;

    @Override
    public void onErrorResponse(VolleyError error) {
        callbackActivity.gotMenuError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray menuJSON;
        JSONObject menuItemJson;
        String name;
        String description;
        String imageUrl;
        String price;
        String category;
        try {
            menuJSON = response.getJSONArray("items");
            for (int i = 0; i < menuJSON.length(); i++){
                menuItemJson = menuJSON.getJSONObject(i);
                name = menuItemJson.getString("name");
                description = menuItemJson.getString("description");
                imageUrl = menuItemJson.getString("image_url");
                price = menuItemJson.getString("price");
                category = menuItemJson.getString("category");
                if (Objects.equals(category, intendedCategory)) {
                    menuArray.add(new MenuItem(name, description, imageUrl, price, category));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        callbackActivity.gotMenu(menuArray);
    }

    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menu);
        void gotMenuError(String message);
    }

    public MenuRequest(Context context, String intendedCategory) {
        this.context = context;
        this.intendedCategory = intendedCategory;
    }

    public void getMenu(Callback activity) {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
        callbackActivity = activity;
    }
}
