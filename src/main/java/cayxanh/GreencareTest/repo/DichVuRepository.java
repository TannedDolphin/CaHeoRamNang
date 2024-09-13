package cayxanh.GreencareTest.repo;

import cayxanh.GreencareTest.entity.DichVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DichVuRepository extends JpaRepository<DichVu,Long> {

    @Query(value = "Select * from DichVu order by id desc limit :limit",nativeQuery = true)
    List<DichVu> getListNewest(int limit);
}
