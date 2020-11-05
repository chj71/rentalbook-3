package rentalbook;

import rentalbook.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import rentalbook.external.Delivery;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    RentRepository rentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCancelled_RentCancel(@Payload OrderCancelled orderCancelled){

        if(orderCancelled.isMe()){
            System.out.println("##### listener RentCancel : " + orderCancelled.toJson());
            List<Rent> rentList = rentRepository.findByOrderId(orderCancelled.getId());
            for(Rent rent : rentList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                rent.setStatus("Rent Canceled");
                // view 레파지 토리에 save
                rentRepository.save(rent);
            }
        }
    }

}
