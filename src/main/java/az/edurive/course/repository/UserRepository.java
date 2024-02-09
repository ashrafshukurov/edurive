package az.edurive.course.repository;

import az.edurive.course.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author ashraf on 31.01.24
 * @project course
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByGmail(String gmail);
    Optional<User> findByGmail(String gmail);
}
