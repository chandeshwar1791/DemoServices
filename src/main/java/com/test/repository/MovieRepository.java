package com.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.bean.Movie;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> 
{
    List<Movie> findIdById(@Param("id") Long id);
}
