package com.test.knockknockback.api.bizes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BizesRepsitory extends JpaRepository<BizesEntity, String> {
}
