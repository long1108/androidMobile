package edu.eiu.tourist_app;

import android.provider.CallLog;

import java.util.HashMap;

import edu.eiu.tourist_app.datamodel.QueryResponse;
import edu.eiu.tourist_app.datamodel.WikipediaPage;
import edu.eiu.tourist_app.datamodel.WikipediaResponse;
import retrofit2.Call;
import retrofit2.mock.Calls;

public class MockWikipediaService implements WikipediaService {
    @Override
    public Call<WikipediaResponse> getPlaces() {
        WikipediaResponse wikipediaResponse = new WikipediaResponse();
        wikipediaResponse.setQuery(new QueryResponse());

        wikipediaResponse.getQuery().setPages(new HashMap<Integer, WikipediaPage>());
        wikipediaResponse.getQuery().getPages().put(1234, new WikipediaPage());
        wikipediaResponse.getQuery().getPages().get(1234).setTitle("Statue");
        wikipediaResponse.getQuery().getPages().get(1234).setIndex(0);
        wikipediaResponse.getQuery().getPages().put(4321, new WikipediaPage());
        wikipediaResponse.getQuery().getPages().get(4321).setTitle("Big Bridge");
        wikipediaResponse.getQuery().getPages().get(4321).setIndex(1);
        wikipediaResponse.getQuery().getPages().put(7890, new WikipediaPage());
        wikipediaResponse.getQuery().getPages().get(7890).setTitle("City Zoo");
        wikipediaResponse.getQuery().getPages().get(7890).setIndex(2);

        return Calls.response(wikipediaResponse);
    }
}
