package org.choongang.repository;

import org.choongang.entites.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * 필요하다면 쿼리dsl 추가
 */
public interface AddressRepository extends JpaRepository<Address, Long>, QuerydslPredicateExecutor<Address> {
}
