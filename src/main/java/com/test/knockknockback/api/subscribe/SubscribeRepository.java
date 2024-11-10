package com.test.knockknockback.api.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscribeRepository extends JpaRepository<SubscribeEntity, SubscribeId> {
    @Modifying
    @Query("delete from SubscribeEntity s where s.id = :subId")
    void deleteBySubId(Long subId);

    @Query("select s from SubscribeEntity s where s.subscribeId.userName = :userName")
    List<SubscribeEntity> findAllByUserName(String userName);
}
