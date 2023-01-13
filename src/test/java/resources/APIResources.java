package resources;

public enum APIResources {
    ADDPLACEAPI("/maps/api/place/add/json"),
    GETPLACEAPI("/maps/api/place/get/json");
    private String resource;
    APIResources(String s) {
        resource=s;
    }

    public String getResource()
    {
        return resource;
    }
}
