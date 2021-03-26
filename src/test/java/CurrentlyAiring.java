import annotation.Column;
import annotation.Id;
import annotation.FK;
import annotation.Table;

@Table(table = "currently_airing")
public class CurrentlyAiring {

    @Id(columnName = "airingid")
    private int airingid;

    @FK(dataType = "int", table = "streaming_service", columnName = "ssid")
    private int ssid;

   @FK(dataType = "int", table = "anime", columnName = "animeid")
   private int animeid;

   @Column(dataType = "int", columnName = "total_episodes")
   private int total_episodes;

    public CurrentlyAiring() {
    }

    public CurrentlyAiring(int airingid, int SSid, int animeid, int totalEpisodes) {
        this.airingid = airingid;
        this.ssid = SSid;
        this.animeid = animeid;
        this.total_episodes = totalEpisodes;
    }

    public CurrentlyAiring(int ssid, int animeid, int total_episodes) {
        this.ssid = ssid;
        this.animeid = animeid;
        this.total_episodes = total_episodes;
    }

    public CurrentlyAiring(int total_episodes) {
        this.total_episodes = total_episodes;
    }

    public void setAiringid(int airingid) {
        this.airingid = airingid;
    }

    public int getSsid() {
        return ssid;
    }

    public void setSsid(int ssid) {
        this.ssid = ssid;
    }

    public int getAnimeid() {
        return animeid;
    }

    public void setAnimeid(int animeid) {
        this.animeid = animeid;
    }

    public int getTotal_episodes() {
        return total_episodes;
    }

    public void setTotal_episodes(int total_episodes) {
        this.total_episodes = total_episodes;
    }

    @Override
    public String toString() {
        return "CurrentlyAiring{" +
                "airingid=" + airingid +
                ", SSid=" + ssid +
                ", animeid=" + animeid +
                ", totalEpisodes=" + total_episodes +
                '}';
    }
}

