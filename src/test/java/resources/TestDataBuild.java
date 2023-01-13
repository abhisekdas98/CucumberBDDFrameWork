package resources;

import POJO.Location;
import POJO.Place;

import java.util.LinkedList;
import java.util.List;

public class TestDataBuild {

    public Place AddPlacePayLoad(String name,String language,String address)
    {
        Place p=new Place();
        Location location=new Location();
        List<String> ll=new LinkedList<>();
        ll.add("shoe park");
        ll.add("shop");
        location.setLat(-21.233);
        location.setLng(23.44);
        p.setAccuracy(54);
        p.setAddress(address);
        p.setLanguage(language);
        p.setLocation(location);
        p.setName(name);
        p.setPhone_number("+(91) 8328823423");
        p.setWebsite("www.abhisekdas98.com");
        p.setTypes(ll);

        return p;
    }
}
