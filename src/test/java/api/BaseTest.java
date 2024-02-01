package api;

import org.junit.jupiter.api.BeforeEach;
import service.RestService;

public class BaseTest {

    RestService RestSevice;

    @BeforeEach
    public void setUp(){
        RestSevice  = new RestService();
    }
}
