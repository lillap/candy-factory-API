package com.experis.candy_manufactory.Repositories;

import com.experis.candy_manufactory.models.Candy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandyRepository extends JpaRepository<Candy, Long> {
}
