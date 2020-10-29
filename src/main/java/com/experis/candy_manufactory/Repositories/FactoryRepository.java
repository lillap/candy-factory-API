package com.experis.candy_manufactory.Repositories;

import com.experis.candy_manufactory.models.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactoryRepository extends JpaRepository<Factory, Long> {
}
