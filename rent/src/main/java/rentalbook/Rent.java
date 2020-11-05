package rentalbook;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Rent_table")
public class Rent {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private String status;

    @PostPersist
    public void onPostPersist(){
        Rented rented = new Rented();
        BeanUtils.copyProperties(this, rented);
        rented.publishAfterCommit();

        try {
            Thread.currentThread().sleep((long) (800 + Math.random() * 300));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @PreUpdate
    public void onPreUpdate(){

        RentCanceled rentCanceled = new RentCanceled();
        BeanUtils.copyProperties(this, rentCanceled);
        rentCanceled.setStatus("Rent Canceled");
        rentCanceled.publishAfterCommit();

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
