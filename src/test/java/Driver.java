import annotation.Column;
import annotation.Id;
import annotation.Table;
import orm.ORM;
import orm.ORMService;

import javax.sound.midi.Soundbank;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.sql.Connection;
import java.util.stream.Stream;

public class Driver {
    public static void main(String[] args) {
        Anime anime = new Anime ("ATO","MAPPA");
        Anime anime2 = new Anime ("Demon Slayer","Ufotable");
        Anime anime3 = new Anime ("Horimiya","Cloverworks");
        Anime anime4 = new Anime ("Re:Zero", "White Fox");
        Anime anime5 = new Anime ("Jujutsu Kaisen","MAPPA");
        Anime anime6 = new Anime ("Haikyuu","Production I.G");

        StreamingService ss = new StreamingService ("Crunchyroll",10000);
        StreamingService ss2 = new StreamingService ("4anime",3000);
        StreamingService ss3 = new StreamingService ("netflix",20000);
        StreamingService ss4 = new StreamingService ("otakustream",0);
        StreamingService ss5 = new StreamingService ("kissanime",0);

        CurrentlyAiring ca = new CurrentlyAiring (1,1,16);
        CurrentlyAiring ca2 = new CurrentlyAiring (2,3,13);
        CurrentlyAiring ca3 = new CurrentlyAiring (3,2,24);
        CurrentlyAiring ca4 = new CurrentlyAiring (2,4,12);
        CurrentlyAiring ca5 = new CurrentlyAiring (1,5,24);
        CurrentlyAiring ca6 = new CurrentlyAiring (1,6,12);

        ORM orm = new ORM ();

        ORMService os = new ORMService (orm);

        orm.createTableFromClass (Anime.class);
        orm.createTableFromClass (StreamingService.class);
        orm.createTableFromClass (CurrentlyAiring.class);

        os.insertObject (anime);
        os.insertObject (anime2);
        os.insertObject (anime3);
        os.insertObject (anime4);
        os.insertObject (anime5);
        os.insertObject (anime6);
//
        os.insertObject (ss);
        os.insertObject (ss2);
        os.insertObject (ss3);
        os.insertObject (ss4);
        os.insertObject (ss5);

        os.insertObject (ca);
        os.insertObject (ca2);
        os.insertObject (ca3);
        os.insertObject (ca4);
        os.insertObject (ca5);
        os.insertObject (ca6);

//        orm.createTableFromClass (Anime.class);
//        orm.createTableFromClass (StreamingService.class);
//        orm.createTableFromClass (CurrentlyAiring.class);
//
//        orm.insertObject (anime);
//        orm.insertObject (anime2);
//        orm.insertObject (anime3);
//        orm.insertObject (anime4);
//        orm.insertObject (anime5);
//        orm.insertObject (anime6);
//
//        orm.insertObject (ss);
//        orm.insertObject (ss2);
//        orm.insertObject (ss3);
//        orm.insertObject (ss4);
//        orm.insertObject (ss5);
//
//        orm.insertObject (ca);
//        orm.insertObject (ca2);
//        orm.insertObject (ca3);
//        orm.insertObject (ca4);
//        orm.insertObject (ca5);
//        orm.insertObject (ca6);

    }
}