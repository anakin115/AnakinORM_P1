package orm;

import util.ConnectionUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ORMService {

    ORM orm;
    public ORMService(ORM orm){
        this.orm = orm;
    }
    ConnectionUtil util = ConnectionUtil.INSTANCE;


    public Future<Integer> createTable(Object o){
        return util.getThread ().submit(() -> orm.createTable (o));

    }

    public Future<Integer> createTableFromClass(Class o){
        return util.getThread ().submit(() -> orm.createTableFromClass (o));
    }

    public Future<Integer> insertObject(Object o){
        return util.getThread ().submit(() -> orm.insertObject (o));
    }

    public void viewAllDataByClass(Class o){
        orm.viewAllDataByClass (o);
    }

    public void viewDataByID(Class o, int id){
        orm.viewDataByID (o,id);
    }

    public void viewDataByField(Class o,String field, int id){
        orm.viewDataByField (o,field,id);
    }

    public void viewDataByField(Class o,String field, String str){
        orm.viewDataByField (o,field,str);
    }

    public Future<Integer> updateObject(Object o, int id, String field, String str){
        return util.getThread ().submit(() -> orm.updateObject (o,id,field,str));
    }

    public Future<Integer> updateObject(Object o, int id, String field, int num){
        return util.getThread ().submit(() -> orm.updateObject (o,id,field,num));
    }

    public Future<Integer> updateObjectByClass(Class o, int id, String field, int num){
        return util.getThread ().submit(() -> orm.updateObjectByClass (o,id,field,num));
    }

    public Future<Integer> updateObjectByClass(Class o, int id, String field, String str){
        return util.getThread ().submit(() -> orm.updateObjectByClass (o,id,field,str));
    }

    public Future<Integer> deleteObjectByID(Class o, int id){
        return util.getThread ().submit(() -> orm.deleteObjectByID (o,id));
    }

    public Future<Integer> deleteObjectByID(Object o, int id){
        return util.getThread ().submit(() -> orm.deleteObjectByID (o,id));
    }

    public Future<Integer> deleteObjectByField(Object o, String field, String str){
        return util.getThread ().submit(() -> orm.deleteObjectByField (o,field,str));
    }

    public Future<Integer> deleteObjectByField(Object o, String field, int num){
        return util.getThread ().submit(() -> orm.deleteObjectByField (o,field,num));
    }

    public Future<Integer> dropTableByObject(Object o){
        return util.getThread ().submit(() -> orm.dropTableByObject (o));
    }

    public Future<Integer> dropTableByClass(Class o){
        return util.getThread ().submit(() -> orm.dropTableByClass (o));
    }

    public Future<Integer> transferIntFromObj1ToObj2(Class o, int objID1,int objID2,String field, int amount){
        return util.getThread ().submit(() -> orm.transferIntFromObj1ToObj2(o,objID1,objID2,field,amount));
    }

}
