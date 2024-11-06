package com.test.knockknockback.api.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeRepository extends JpaRepository<SubscribeEntity, SubscribeId> {
    @Query("delete from SubscribeEntity s where s.id = :subId")
    boolean deleteBySubId(Long subId);
}
