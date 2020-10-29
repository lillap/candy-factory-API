package com.experis.candy_manufactory.Repositories;

import com.experis.candy_manufactory.models.Candy;
import com.experis.candy_manufactory.models.CandyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandyRepository extends JpaRepository<Candy, Long> {
    int countByCandyType(CandyType candyType);

}
