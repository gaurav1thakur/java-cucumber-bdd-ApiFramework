package StepDefinations;

import io.cucumber.java.Before;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeDeleteScenario() throws IOException {
        //write a code to get place id
        //execute this code only when place id is null
        if(StepDefinations.place_id == null){
            StepDefinations stepDefinations = new StepDefinations();
            stepDefinations.add_place_payload("GT","Chinese","Caddress");
            stepDefinations.user_calls_with_http_request("ADD_PLACE_API","POST");
            stepDefinations.verify_place_id_created_to_using("GT","GET_PLACE_API");
        }

    }

}
