import annotation.Column;
import annotation.Id;
import annotation.Table;

@Table(table = "streaming_service")
public class StreamingService {

    @Id(columnName = "ssid")
    private int ssid;

    @Column(dataType = "text", columnName = "website")
    private String website;

    @Column(dataType = "int", columnName = "budget")
    private int budget;

    public StreamingService() {}


    public StreamingService(int SSid, String website,int budget) {
        this.ssid = SSid;
        this.website = website;
        this.budget = budget;
    }

    public StreamingService(String website,int budget) {
        this.website = website;
        this.budget = budget;
    }

    public void setSsid(int ssid) {
        this.ssid = ssid;
    }

    public String getWebsite() {
        return website;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "StreamingService{" +
                "SSid=" + ssid +
                ", website='" + website + '\'' +
                '}';
    }
}

