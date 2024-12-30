package vitor.projects.magalums.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vitor.projects.magalums.entity.Channel;

public interface ChannelRepository  extends JpaRepository<Channel, Long> {
    
}
