package cayxanh.GreencareTest.repo;

import cayxanh.GreencareTest.entity.DichVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DichVuRepo extends JpaRepository<DichVu, Integer> {
}
