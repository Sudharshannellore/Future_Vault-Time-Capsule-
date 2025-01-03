package com.futurevault.repositories;

import com.futurevault.entites.Capsules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapsulesRepository extends JpaRepository<Capsules, Long> {
}
