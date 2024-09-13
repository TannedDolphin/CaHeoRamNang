package cayxanh.GreencareTest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cayxanh.GreencareTest.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
}
