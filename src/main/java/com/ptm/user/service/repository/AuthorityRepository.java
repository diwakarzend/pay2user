package com.ptm.user.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptm.user.service.enitity.Authority;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
