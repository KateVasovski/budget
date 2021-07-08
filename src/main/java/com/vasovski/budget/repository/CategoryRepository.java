package com.vasovski.budget.repository;

import com.vasovski.budget.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryCustomRepository {

    @Query(nativeQuery = true, value = "Select * from category where tree ~ lquery(CONCAT('*.', :treeEnd))")
    Category getByTreeEnd(@Param("treeEnd") String treeEnd);
}
