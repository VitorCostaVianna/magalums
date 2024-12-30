package vitor.projects.magalums.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vitor.projects.magalums.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {
    
}
