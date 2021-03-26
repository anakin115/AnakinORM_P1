import org.junit.Test;
import orm.ORM;
import orm.ORMService;

import static org.junit.Assert.*;

public class TestDriver {

    ORM orm = new ORM ();
    ORMService os = new ORMService (orm);

    @Test
    public void getAnimeTitle(){
        Anime anime = new Anime ("FSN","ufotable");
        assertEquals(anime.getTitle (),"FSN");
    }

    @Test
    public void testCreateTable(){
        Anime anime = new Anime ("Attack on Titan","MAPPA");
        assertEquals (orm.createTable (anime), 0);
        assertNotEquals(orm.createTable (anime),1);

        assertEquals (orm.createTableFromClass (Anime.class),0);
        assertNotEquals (orm.createTableFromClass (Anime.class),1);
    }

    @Test
    public void testInsertObject(){
        Anime anime = new Anime ("Attack on Titan","MAPPA");
        assertEquals (orm.insertObject (anime), 1);
        assertNotEquals (orm.insertObject (anime), 0);

    }

    @Test
    public void testUpdateObject(){
        Anime anime = new Anime ("Attack on Titan","MAPPA");
        assertEquals (orm.updateObject (anime,1,"studio","mappa..."), 1);
        assertNotEquals (orm.updateObject (anime,1,"studio","mappa..."), 0);

        StreamingService ss = new StreamingService ("budget",29);
        assertEquals (orm.updateObject (ss,1,"budget",10020), 1);
        assertNotEquals (orm.updateObject (ss,1,"budget",10020), 0);
    }

    @Test
    public void testUpdateObjectByClass(){
        assertEquals (orm.updateObjectByClass (Anime.class,1,"studio","not mappa"), 1);
        assertNotEquals (orm.updateObjectByClass (Anime.class,1,"studio","mappa..."), 0);

        assertEquals (orm.updateObjectByClass (StreamingService.class,1,"budget",10030), 1);
        assertNotEquals (orm.updateObjectByClass (StreamingService.class,1,"budget",10020), 0);
    }

    @Test
    public void testDeleteObject(){
        assertEquals (orm.deleteObjectByID (Anime.class, 11),1);
        assertNotEquals (orm.deleteObjectByID (Anime.class, 7),0);

        Anime anime = new Anime ("Attack on Titan","MAPPA");
        assertEquals (orm.deleteObjectByID (anime, 8),1);
        assertNotEquals (orm.deleteObjectByID (anime, 12),0);
    }

    @Test
    public void testDeleteObjectByField(){
        Anime anime = new Anime ("Attack on Titan","MAPPA");

        assertEquals (orm.deleteObjectByField (anime,"studio","White Fox"),2);
        assertNotEquals (orm.deleteObjectByField (anime,"studio","MAPPA"),0);
    }

    @Test
    public void testDeleteObjectByIntField(){

        CurrentlyAiring ca = new CurrentlyAiring ();
        assertEquals (orm.deleteObjectByField (ca,"ssid",1),6);
        assertNotEquals (orm.deleteObjectByField (ca,"ssid",2),1);
    }

    @Test
    public void testDropTableByObj(){
        Anime anime = new Anime();
        assertEquals (orm.dropTableByObject (anime),0);
        assertNotEquals (orm.dropTableByObject (anime),1);
    }

    @Test
    public void testDropTableByClass(){
        assertEquals (orm.dropTableByClass (StreamingService.class),0);
        assertNotEquals (orm.dropTableByClass (StreamingService.class),1);
    }

    @Test
    public void testTransaction(){
        assertEquals (orm.transferIntFromObj1ToObj2 (StreamingService.class,3,1,"budget",500),2);
        assertNotEquals (orm.transferIntFromObj1ToObj2 (StreamingService.class,3,1,"budget",500),0);
    }


}

