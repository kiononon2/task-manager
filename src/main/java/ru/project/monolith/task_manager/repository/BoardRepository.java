package ru.project.monolith.task_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.monolith.task_manager.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
