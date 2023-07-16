package group5.swp391.onlinelearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import group5.swp391.onlinelearning.entity.WithdrawalDetail;

@Repository
public interface WithDrawalDetailRepository extends JpaRepository<WithdrawalDetail, Integer> {
    @Query(value = "SELECT * FROM swp391_onvid.withdrawal_detail where teacher_id = ?1", nativeQuery = true)
    public List<WithdrawalDetail> findByTeacherId(int studentId);
}
