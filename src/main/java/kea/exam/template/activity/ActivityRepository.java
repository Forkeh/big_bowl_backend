package kea.exam.template.activity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ActivityRepository extends JpaRepository<Activity, Long> {

    Page<Activity> findAllByTypeNameContainsIgnoreCase(Pageable pageable, String name);

    List<Activity> findAllByTypeNameContainsIgnoreCase(String name);

    List<Activity> findAllByTypeName(String typeName);
}
