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

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private String url = "https://resto.mprog.nl/categories";
    private ArrayList<String> categoriesArray = new ArrayList<String>();
    private Callback callbackActivity;

    // If error occurs send error message
    @Override
    public void onErrorResponse(VolleyError error) {
        callbackActivity.gotCategoriesError(error.getMessage());
    }

    // If JSON was correctly retrieved, get all the items and add them as strings to the array
    @Override
    public void onResponse(JSONObject response) {
        JSONArray categoriesJSON ;
        try {
            categoriesJSON = response.getJSONArray("categories");
            for (int i = 0; i < categoriesJSON.length(); i++){
                categoriesArray.add(categoriesJSON.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        callbackActivity.gotCategories(categoriesArray);
    }

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    public CategoriesRequest(Context context) {
        this.context = context;
    }

    // Request the JSON object from the url
    public void getCategories(Callback activity) {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
        callbackActivity = activity;
    }
}
