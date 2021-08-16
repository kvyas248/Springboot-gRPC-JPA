/*
 * **********************************************************************************************
 *
 *  Copyright 2021  Kruti Vyas
 *  Use of this source code is governed by Apache 2.0 license that can be found in the LICENSE file or at
 *  https://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * *********************************************************************************************
 */

package com.kruti.vyas.springbootjpagrpc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.sql.Timestamp;

/**
 * @author Kruti Vyas
 */
@Entity
@Data
@Builder
@Table
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @SequenceGenerator(allocationSize=1, initialValue=1, sequenceName="user_id_seq", name="user_id_seq")
    @GeneratedValue(generator="account_id_seq", strategy=GenerationType.SEQUENCE)
    Long id;

    @Column(
            nullable = false,
            updatable = true
    )
    String name;

    @Column(
            nullable = false,
            updatable = true,
            unique = true
    )
    @Email
    String email;

    @Column
    @CreationTimestamp
    Timestamp createdAt;
}
