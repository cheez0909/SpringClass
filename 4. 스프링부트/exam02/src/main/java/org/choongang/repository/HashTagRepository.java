package org.choongang.repository;

import org.choongang.entites.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface HashTagRepository extends JpaRepository<HashTag, String> {

}
