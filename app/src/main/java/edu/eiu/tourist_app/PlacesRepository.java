package edu.eiu.tourist_app;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;
import android.view.ViewOutlineProvider;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.eiu.tourist_app.datamodel.QueryResponse;
import edu.eiu.tourist_app.datamodel.WikipediaPage;
import edu.eiu.tourist_app.datamodel.WikipediaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlacesRepository {
    private WikipediaService wikipediaService;

    public PlacesRepository(WikipediaService wikipediaService){
        this.wikipediaService = wikipediaService;
    }

    private Activity mainScreen;

    public LiveData<List<WikipediaPage>> getTouristSites() {
        final MutableLiveData<List<WikipediaPage>> liveData = new MutableLiveData<>();

        wikipediaService.getPlaces().enqueue(new Callback<WikipediaResponse>() {
            @Override
            public void onResponse(Call<WikipediaResponse> call, Response<WikipediaResponse> response) {
                WikipediaResponse wikipediaResponse = response.body();
                QueryResponse queryResponse = wikipediaResponse.getQuery();
                Map<Integer, WikipediaPage> pagesMap = queryResponse.getPages();
                List<WikipediaPage> pages = new ArrayList<WikipediaPage>(pagesMap.values()) ;
                liveData.postValue(pages);
                Collections.sort(pages, new Comparator<WikipediaPage>() {
                    @Override
                    public int compare(WikipediaPage lhs, WikipediaPage rhs) {
                        return lhs.getIndex() - rhs.getIndex();
                    }
                });
            }

            @Override
            public void onFailure(Call<WikipediaResponse> call, Throwable t) {
                Log.e("PlacesRepository", "Wikipedia request failed", t);
            }
        });

//        AsyncTask<Void, Void, List<WikipediaPage>> task = new AsyncTask<Void, Void, List<WikipediaPage>>() {
//            @Override
//            protected List<WikipediaPage> doInBackground(Void[] objects) {
//                List<WikipediaPage> places = null;
//                try {
//                    InputStream response = doRequest();
//                    places = handleResponse(response);
//                    liveData.postValue(places);
//                } catch (Exception ex) {
//                    Log.e("PlacesRepository", "FAILED!!! " + ex.getMessage());
//                    //FIXME handle errors
//                }
//                return places;
//            }
//        };
//        task.execute();

        return liveData;
    }

    private InputStream doRequest() throws IOException{

        String urlString ="/w/api.php?action=query&format=json&prop=pageimages&list=&generator=geosearch&piprop=thumbnail%7Cname%7Coriginal&pithumbsize=500&ggscoord=10.7712404%7C106.6978887&ggsradius=10000";
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream stream = urlConnection.getInputStream();

        return stream;
    }
    private List<WikipediaPage> handleResponse(InputStream response){
        Gson gson = new Gson();
        BufferedReader reader = new BufferedReader(new InputStreamReader(response));
        WikipediaResponse wikipediaResponse = gson.fromJson(reader, WikipediaResponse.class);
        QueryResponse queryResponse = wikipediaResponse.getQuery();
        Map<Integer, WikipediaPage> pagesMap = queryResponse.getPages();
        List<WikipediaPage> pages = new ArrayList<WikipediaPage>(pagesMap.values()) ;

        return pages;
    }
//    private List<String> handleResponse(String response) throws JSONException{
//        List<String> placeNames = new ArrayList<>();
//
//        JSONObject responseObject = new JSONObject(response);
//        JSONObject queryObject = responseObject.getJSONObject("query");
//        JSONObject pagesObject = queryObject.getJSONObject("pages");
//
//        Iterator<String> keys = pagesObject.keys();
//        while (keys.hasNext()) {
//            JSONObject page = pagesObject.getJSONObject(keys.next());
//            String title = page.getString("title");
//            placeNames.add(title);
//        }
//
//        return placeNames;
//    }
}
