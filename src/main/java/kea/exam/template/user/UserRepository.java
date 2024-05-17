package kea.exam.template.user;

import kea.exam.template.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {


}
