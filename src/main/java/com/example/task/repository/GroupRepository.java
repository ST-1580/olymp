package com.example.task.repository;

import java.util.List;

import com.example.task.entity.Group;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Transactional
    @Modifying
    @Query(value = "update group_info set name = :name, description = :description where id = :id",
            nativeQuery = true)
    void updateNameAndDescriptionById(@Param("id") long id,
                                      @Nonnull @Param("name") String name,
                                      @Param("description") String description);

}
