
import com.clove.clovewerable.ClovewearableRestURIConstants;
import com.clove.clovewerable.RegistrationResultBean;
import com.clove.clovewerable.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * @author unascribed
 */
public class CloverAPITest {

    // Change URL as applicable
    private final static String CLOVER_URL = "http://localhost:8081/Clovewerable";
    private static final String TEST_USER_ID = "user007";

    public static void main(String[] args) {

        //CloverAPITest.testRegisterUser();
        CloverAPITest.testGetRegisteredUser();
    }

    private static void testRegisterUser() {

        RestTemplate restTemplate = new RestTemplate();
        User user = new User();
        user.setName("user2");
        user.setEmail("email@gmail.com");
        user.setPincode("5555555");
        user.setLoginId("user007");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        RegistrationResultBean response = restTemplate.postForObject(CLOVER_URL + ClovewearableRestURIConstants.CREATE_REG, user, RegistrationResultBean.class);

        System.out.println("==================================================");
        System.out.println("Registration response >>>>>>>>>> )= " + response.getResult());
        System.out.println("==================================================");
    }

    private static void testGetRegisteredUser() {

        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject((CLOVER_URL + "/rest/clovewearable/getRegisteredUser/" + TEST_USER_ID), User.class);

        // JSON OBJECT
        ObjectMapper mapper = new ObjectMapper();
        try {
            String userAsJSONStr = mapper.writeValueAsString(user);
            System.out.println("==================================================");
            System.out.println("REGISTERD USER DETAILS >>>>>>>>>>>>> : " + userAsJSONStr);
            System.out.println("==================================================");
        } catch (JsonProcessingException ex) {
            Logger.getLogger(CloverAPITest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
