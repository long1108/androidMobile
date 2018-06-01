package edu.eiu.tourist_app;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

import edu.eiu.tourist_app.datamodel.WikipediaPage;
import retrofit2.Retrofit;

public class PlacesViewModel extends ViewModel {

    private LiveData<List<WikipediaPage>> touristSitesData;
    private PlacesRepository placesRepository;

    public LiveData<List<WikipediaPage>> getPlacesList() {
        return touristSitesData;
    }

    public void setTouristSitesData(LiveData<List<WikipediaPage>> touristSitesData) {
        this.touristSitesData = touristSitesData;
    }

    public PlacesRepository getPlacesRepository() {
        return placesRepository;
    }

    public void setPlacesRepository(PlacesRepository placesRepository) {
        this.placesRepository = placesRepository;
    }

    public PlacesViewModel(PlacesRepository repo) {

        placesRepository = repo;
        touristSitesData = placesRepository.getTouristSites();
    }

    public LiveData<List<WikipediaPage>> getTouristSites() {
        return touristSitesData;
    }

    public static class PlacesViewModelFactory implements ViewModelProvider.Factory{
        private PlacesRepository placesRepository;
        public  PlacesViewModelFactory(PlacesRepository placesRepository){
            this.placesRepository = placesRepository;
        }
        @NonNull
        @Override
        public PlacesViewModel create(@NonNull Class modelClass) {
            return new PlacesViewModel(placesRepository);
        }
    }

}
