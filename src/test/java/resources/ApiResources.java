package resources;

public enum ApiResources {
 //enum is special class in java which has collection of constants or methods
    ADD_PLACE_API("/maps/api/place/add/json"),
    DELETE_PLACE_API("/maps/api/place/delete/json"),
    GET_PLACE_API("/maps/api/place/get/json"),
    UPDATE_PLACE_API("/maps/api/place/update/json");

    private String resource;
    ApiResources(String resource) {
        this.resource = resource;
        System.out.println(("this.resource after :: ")+this.resource);
    }
    public String getResource(){
        return resource;
    }
}
