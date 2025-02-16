package vitor.projects.magalums.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_channel")
public class Channel {
    
    @Id
    private Long channelId;

    private String description;

    public Channel() {
    }

    public Channel(Long channelId, String description) {
        this.channelId = channelId;
        this.description = description;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Channel channel = (Channel) obj;
        return Objects.equals(channelId, channel.channelId) &&
               Objects.equals(description, channel.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channelId, description);
    }

    // possible values for channel
    public enum Values{
        EMAIL(1L, "Email"),
        SMS(2L, "SMS"),
        PUSH(3L, "Push"),
        WHATSAPP(4L, "WhatsApp"),;

        private Long id;
        private String description;
        private Values(Long id, String description) {
            this.id = id;
            this.description = description;
        }

        public Channel toChannel(){
            return new Channel(id , description);
        }
    }
    
}
