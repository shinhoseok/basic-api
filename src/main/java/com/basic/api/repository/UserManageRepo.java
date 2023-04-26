package com.basic.api.repository;

import com.basic.api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserManageRepo extends JpaRepository<UserEntity, Long>, UserManageRepoCustom {
}
