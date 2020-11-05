
package rentalbook.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="rent", url="${api.rent.url}")
public interface RentService {

    @RequestMapping(method= RequestMethod.POST, path="/rents")
    public void rent(@RequestBody Rent rent);

}