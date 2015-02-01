/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package uwi.dcit.agriexpensesvr;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

import uwi.dcit.agriexpensesvr.MyBean;

/**
 * An endpoint class we are exposing
 */
@Api(name = "myTestApi", version = "v1", namespace = @ApiNamespace(ownerDomain = "agriexpensett.dcit.uwi",
        ownerName = "agriexpensett.dcit.uwi", packagePath = ""))
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }

}