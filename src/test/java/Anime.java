import annotation.Column;
import annotation.Id;
import annotation.Table;

//@Entity
//@Table(name="anime")
@Table(table = "anime")
public class Anime {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id(columnName = "animeid")
    public int animeid;

    @Column(dataType = "text", columnName = "title")
    private String title;
    @Column(dataType = "text", columnName = "studio")
    private String studio;

    public Anime() {}

    public Anime(int animeid, String title, String studio){
        this.animeid = animeid;
        this.title = title;
        this.studio = studio;
    }

    public Anime(String title, String studio) {
        this.title = title;
        this.studio = studio;
    }

    public void setAnimeid(int animeid) {
        this.animeid = animeid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "animeid=" + animeid +
                ", title='" + title + '\'' +
                ", studio='" + studio + '\'' +
                '}';
    }
}
