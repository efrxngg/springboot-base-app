package com.base.app.repository;

import com.base.app.entity.CustomerTasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTasksRepository extends JpaRepository<CustomerTasks, CustomerTasks.CustomerTasksId> {
}