package com.bookflix.bookflix.library.service;

import com.bookflix.bookflix.library.entity.NearLibrary;
import com.bookflix.bookflix.library.repository.LibraryRepository;
import com.bookflix.bookflix.library.repository.NearLibraryRepository;
import com.bookflix.bookflix.library.entity.Library;
import com.bookflix.bookflix.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RequiredArgsConstructor
@Service
public class NearLibraryServiceImpl implements NearLibraryService{

    private final NearLibraryRepository nearLibraryRepository;

    private final LibraryRepository libraryRepository;

    private static double distance(double lat1, double lon1, double lat2, double lon2) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515 * 1.609344;
        return (dist);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }


    @Override
    public void createAndAddNearLibrary(double distance, User user, Long libraryId){
        Library library = libraryRepository.findById(libraryId).orElseThrow();
        NearLibrary nearLibrary = nearLibraryRepository.save(new NearLibrary(distance, user, library));
        user.addNearLibrary(nearLibrary);
    }

    @Override
    public void clearNearLibrary(User user){
        List<NearLibrary> nearLibraryList = user.getLibraryList();
        for (NearLibrary nearLibrary: nearLibraryList)
            nearLibraryRepository.delete(nearLibrary);
        user.clearNearLibrary();
    }

    @Override
    public void setNearLibraryList(User user){
        float longitude = user.getLongitude();
        float latitude = user.getLatitude();
        List<Library> allLibraryList = libraryRepository.findAll();
        TreeMap<Double, Long> distanceMap = new TreeMap<Double, Long>();

        clearNearLibrary(user);

        for(Library library : allLibraryList){
            double distance = distance(latitude, longitude, library.getLatitude(), library.getLongitude());
            distanceMap.put(distance, library.getId());
        }
        for (int i = 0; i< 3; i++) {
            Map.Entry<Double, Long> entry = distanceMap.firstEntry();
            createAndAddNearLibrary(entry.getKey(), user, entry.getValue());
            distanceMap.remove(entry.getKey());
        }
    }
}
