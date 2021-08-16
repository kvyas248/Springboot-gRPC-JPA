/*
 * **********************************************************************************************
 *
 *  Copyright 2021  Kruti Vyas
 *  Use of this source code is governed by Apache 2.0 license that can be found in the LICENSE file or at
 *  https://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * *********************************************************************************************
 */

package com.kruti.vyas.springbootjpagrpc.repository;

import com.kruti.vyas.springbootjpagrpc.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Kruti Vyas
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    List<UserEntity> findAll();
    Optional<UserEntity> findById(Long id);
}
