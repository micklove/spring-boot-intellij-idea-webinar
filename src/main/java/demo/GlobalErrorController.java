package demo;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import static com.google.common.collect.Lists.newArrayList;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by IntelliJ IDEA.
 * User: micklove
 * Date: 7/05/2015
 * Time: 11:38 PM
 */


/**
 * nb: dont forget to disable the whitelist property, in application.properties or application.yml
 * e.g. error.whitelabel.enabled=false
 *
 * See https://github.com/spring-projects/spring-boot/blob/v1.1.5.RELEASE/spring-boot-autoconfigure/src/main/java/org/springframework/boot/autoconfigure/web/ErrorMvcAutoConfiguration.java
 */
@Controller
public class GlobalErrorController implements ErrorController {

    public GlobalErrorController() {
    }

    Logger log = getLogger(this.getClass());

    @Value("${error.path:/error}")
    private String errorPath;


    @Override
    public String getErrorPath() {
        return this.errorPath;
    }


    @RequestMapping(value = "${error.path:/error}")
    @ResponseBody
    public ResponseEntity<List<RestError>> unmappedError(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        RestError restError = new RestError(null, status, "Unknown Error", "Some error", null);
        return new ResponseEntity<>(newArrayList(restError), status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            try {
                return HttpStatus.valueOf(statusCode);
            }
            catch (Exception ex) {
            }
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
