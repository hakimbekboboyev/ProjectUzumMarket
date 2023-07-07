package com.example.projectuzummarket.repository;

import com.example.projectuzummarket.entity.RegisterDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterDB,Integer> {

    @Query(value = "select * from registerdb where is_active=true",nativeQuery = true)
    List<RegisterDB> findAll();

    @Query(value = "select * from registerdb where is_active=true and id=?1", nativeQuery = true)
    Optional<RegisterDB> findById(Integer id);

    Optional<RegisterDB> findByUsername(String username);

    @Query(value = "select * from registerdb where is_active=false and id=?1",nativeQuery = true)
    Optional<RegisterDB> findByIdRestoreUser(Integer id);
}
