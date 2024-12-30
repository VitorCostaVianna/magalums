package vitor.projects.magalums.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import vitor.projects.magalums.entity.Channel;
import vitor.projects.magalums.entity.Status;
import vitor.projects.magalums.repository.ChannelRepository;
import vitor.projects.magalums.repository.StatusRepository;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final StatusRepository statusRepository;

    private final ChannelRepository channelRepository;

    public DataLoader(StatusRepository statusRepository, 
                      ChannelRepository channelRepository) {
        this.statusRepository = statusRepository;
        this.channelRepository = channelRepository;
    }


    // run method when application starts
    // tables are created and data is inserted
    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(Channel.Values.values()) // acess enum values
                .map(Channel.Values::toChannel) // convert to Channel
               .forEach(channelRepository::save); // save in database 
    
    
        Arrays.stream(Status.Values.values())
            .map(Status.Values::toStatus)
            .forEach(statusRepository::save);
    }
    
}
