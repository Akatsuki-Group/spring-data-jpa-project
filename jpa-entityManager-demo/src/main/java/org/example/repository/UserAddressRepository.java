package org.example.repository;

import org.example.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author MSI-NB
 */
public interface UserAddressRepository extends JpaRepository<UserAddress,Long>, JpaSpecificationExecutor<UserAddress> {
}