package com.yeonnnnjs.click.data.repository;

import com.yeonnnnjs.click.data.Entity.Battles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattlesRepository extends JpaRepository<Battles, String> {
}
