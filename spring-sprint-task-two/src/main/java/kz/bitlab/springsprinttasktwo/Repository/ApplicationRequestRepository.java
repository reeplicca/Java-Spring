package kz.bitlab.springsprinttasktwo.Repository;

import kz.bitlab.springsprinttasktwo.entities.ApplicationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ApplicationRequestRepository extends JpaRepository<ApplicationRequest,Long> {
    ApplicationRequest findAllById(Long id);
    List<ApplicationRequest> findAllByHandled(boolean isHandled);
    List<ApplicationRequest> findAllByUserNameContains(String name);
}
