package ru.project.monolith.task_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.project.monolith.task_manager.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
