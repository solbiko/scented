package com.dysb.scented.repository;

import com.dysb.scented.entity.Perfume;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Long>, JpaSpecificationExecutor<Perfume> {

    @NotNull
    @Override
    @EntityGraph(attributePaths = {"comments"})
    Page<Perfume> findAll(Pageable pageable);


}