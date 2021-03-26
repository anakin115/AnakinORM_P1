package orm;

import annotation.Column;
import annotation.FK;
import annotation.Id;
import annotation.Table;
import util.ConnectionUtil;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class ORM {

    public String getTableNameFromObj(Object o) {
        Annotation an = o.getClass ().getAnnotation (Table.class);
        if (an instanceof Table) {
            Table t = (Table) an;
            return t.table ();
        }
        return "";
    }

    public String getTableNameFromClass(Class c) {
        Annotation an = c.getAnnotation (Table.class);
        if (an instanceof Table) {
            Table t = (Table) an;
            return (t.table ());
        }
        return "";
    }


    public String getIDfromObj(Object o) {

        for (Field fields : o.getClass ().getDeclaredFields ()) {
            Annotation an = fields.getAnnotation (Id.class);
            if (an instanceof Id) {
                Id t = (Id) an;
                return ((t.columnName ()) + " ") + (t.Type ());
            }
        }
        return "nothing";
    }

    public String getIDfromClass(Class c) {
        for(Field fields : c.getDeclaredFields ()){
            Annotation an = fields.getAnnotation (Id.class);
            if (an instanceof Id) {
                Id t = (Id) an;
                return ((t.columnName ()) + " ") + (t.Type ());
            }
        }
        return "";
    }

    public String getIDFieldNameFromObject(Object o) {

        for (Field fields : o.getClass ().getDeclaredFields ()) {
            Annotation an = fields.getAnnotation (Id.class);
            if (an instanceof Id) {
                Id t = (Id) an;
                return t.columnName ();
            }
        }
        return "nothing";
    }

    public String getIDFieldNameFromClass(Class c){

        for(Field fields : c.getDeclaredFields ()){
            Annotation an = fields.getAnnotation (Id.class);
            if (an instanceof Id) {
                Id t = (Id) an;
                return t.columnName ();
            }
        }
        return "";
    }

    public String getColumn(Object o) {

        StringBuilder sb = new StringBuilder ();
        for (Field fields : o.getClass ().getDeclaredFields ()) {
            Annotation an = fields.getAnnotation (Column.class);
            if (an instanceof Column) {
                Column t = (Column) an;
                sb.append ((t.columnName ()) + " ");
                sb.append (t.dataType ());
                sb.append (", ");
            }
        }
        return sb.toString ();
    }

    public String getColumnFromClass(Class o) {

        StringBuilder sb = new StringBuilder ();
        for (Field fields : o.getDeclaredFields ()) {
            Annotation an = fields.getAnnotation (Column.class);
            if (an instanceof Column) {
                Column t = (Column) an;
                sb.append ((t.columnName ()) + " ");
                sb.append (t.dataType ());
                sb.append (", ");
            }
        }
        return sb.toString ();
    }

    public String getFK(Object o) {

        StringBuilder sb = new StringBuilder ();
        for (Field fields : o.getClass ().getDeclaredFields ()) {
            Annotation an = fields.getAnnotation (FK.class);
            if (an instanceof FK) {
                FK t = (FK) an;
                sb.append ((t.columnName ()) + " ");
                sb.append ((t.dataType ()) + " ");
                sb.append ((t.keyword ()) + " ");
                sb.append ((t.table ()) + " ");
                sb.append ("ON DELETE CASCADE");
                sb.append (", ");
            }
        }
        return sb.toString ();
    }

    public String getFKFromClass(Class o) {

        StringBuilder sb = new StringBuilder ();
        for (Field fields : o.getDeclaredFields ()) {
            Annotation an = fields.getAnnotation (FK.class);
            if (an instanceof FK) {
                FK t = (FK) an;
                sb.append ((t.columnName ()) + " ");
                sb.append ((t.dataType ()) + " ");
                sb.append ((t.keyword ()) + " ");
                sb.append ((t.table ()) + " ");
                sb.append ("ON DELETE CASCADE");
                sb.append (", ");
            }
        }
        return sb.toString ();
    }

    public String getAllColumnName(Object o) {
        StringBuilder sb = new StringBuilder ();
        for (Field fields : o.getClass ().getDeclaredFields ()) {
            String name = fields.getName ();
            Annotation an1 = fields.getAnnotation (Id.class);
//            Annotation an2 = fields.getAnnotation (FK.class);
            if (!(an1 instanceof Id)) {
                sb.append ((name) + ", ");
            }
//            sb.append ((name) + ", ");
        }
        sb.delete (sb.length () - 2, sb.length ());
        return sb.toString ();
    }

    public String getAllValues(Object o) {
        StringBuilder sb = new StringBuilder ();
        for (Field fields : o.getClass ().getDeclaredFields ()) {
            String name = fields.getName ();
            Annotation an1 = fields.getAnnotation (Id.class);
//            Annotation an2 = fields.getAnnotation (FK.class);
            if (!(an1 instanceof Id)) {
                String value = getGetter (o, name);
                sb.append ("'" + (value) + "', ");
            }
        }
        sb.delete (sb.length () - 2, sb.length ());
        return sb.toString ();
    }

    //recreating from netjstech invoking getters ands setters using reflection
    public String getGetter(Object o, String field) {
        PropertyDescriptor pd;
        try {
            pd = new PropertyDescriptor (field, o.getClass ());
            return "" + pd.getReadMethod ().invoke (o);
        } catch (IntrospectionException e) {
            e.printStackTrace ();
        } catch (IllegalAccessException e) {
            e.printStackTrace ();
        } catch (InvocationTargetException e) {
            e.printStackTrace ();
        }
        return "";
    }

    public int createTable(Object o) {
        Statement stmt = null;
        int i = -1;
        try (Connection con = ConnectionUtil.getConnection ()) {
            System.out.println ("got access to connection, ready to create table");
            stmt = con.createStatement ();

            String sql = "CREATE TABLE IF NOT EXISTS  " + getTableNameFromObj (o) + " " +
                    "(" + getIDfromObj (o) + ", " +
                    getColumn (o) +
                    getFK (o) +
                    " PRIMARY KEY ( " + getIDFieldNameFromObject (o) + " ))";

            i = stmt.executeUpdate (sql);
            if (i == 0) {
                System.out.println ("created table " + getTableNameFromObj (o));
            } else
                System.out.println ("failed to create table");
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return i;
    }

    public int createTableFromClass(Class c) {
        Statement stmt = null;
        int i = -1;
        try (Connection con = ConnectionUtil.getConnection ()) {
            System.out.println ("got access to connection, ready to create table");
            stmt = con.createStatement ();

            String sql = "CREATE TABLE IF NOT EXISTS  " + getTableNameFromClass (c) + " " +
                    "(" + getIDfromClass (c) + ", " +
                    getColumnFromClass (c) +
                    getFKFromClass (c) +
                    " PRIMARY KEY ( " + getIDFieldNameFromClass (c) + " ))";
//            System.out.println (sql);
            i = stmt.executeUpdate (sql);
            if (i == 0) {
                System.out.println ("created table " + getTableNameFromClass (c));
            } else
                System.out.println ("failed to create table");
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return i;
    }

    public int insertObject(Object o) {
        Statement stmt = null;
        int i = -1;
        try (Connection con = ConnectionUtil.getConnection ()) {
            System.out.println ("got access to connection, ready to insert object");
            stmt = con.createStatement ();

            String sql = "INSERT INTO " + getTableNameFromObj (o) +
                    "(" + getAllColumnName (o) + ")" +
                    "VALUES ( " + getAllValues (o) + ");";
            i = stmt.executeUpdate (sql);
//            System.out.println (sql);
            if (i > 0) {
                System.out.println ("inserted object to the " + getTableNameFromObj (o) + " table");
            } else
                System.out.println ("failed to insert object");

        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return i;

    }

    public int updateObject(Object o, int id, String field, String str) {
        Statement stmt = null;
        int i = -1;
        try (Connection con = ConnectionUtil.getConnection ()) {
            System.out.println ("got access to connection, ready to update object");
            stmt = con.createStatement ();

            String sql = "UPDATE " + getTableNameFromObj (o) +
                    " SET " + field + " = '" + str + "'" +
                    "WHERE " + getIDFieldNameFromObject (o) + " = " + id + ";";
//            System.out.println (sql);
            i = stmt.executeUpdate (sql);
            if (i > 0) {
                System.out.println ("updated object " + field + " to " + str);
            } else
                System.out.println ("failed to update object");
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return 1;
    }

    public int updateObject(Object o, int id, String field, int num) {
        Statement stmt = null;
        int i = -1;
        try (Connection con = ConnectionUtil.getConnection ()) {
            System.out.println ("got access to connection, ready to update object");
            stmt = con.createStatement ();

            String sql = "UPDATE " + getTableNameFromObj (o) +
                    " SET " + field + " = " + num + "" +
                    "WHERE " + getIDFieldNameFromObject (o) + " = " + id + ";";
//            System.out.println (sql);
            i = stmt.executeUpdate (sql);
            if (i > 0) {
                System.out.println ("updated object " + field + " to " + num);
            } else
                System.out.println ("failed to update object");
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return i;
    }

    public int updateObjectByClass(Class c, int id, String field, int num) {
        Statement stmt = null;
        int i = -1;
        try (Connection con = ConnectionUtil.getConnection ()) {
            System.out.println ("got access to connection, ready to update object");
            stmt = con.createStatement ();

            String sql = "UPDATE " + getTableNameFromClass (c) +
                    " SET " + field + " = " + num + "" +
                    "WHERE " + getIDFieldNameFromClass (c) + " = " + id + ";";
//            System.out.println (sql);
            i = stmt.executeUpdate (sql);
            if (i > 0) {
                System.out.println ("updated object " + field + " to " + num);
            } else
                System.out.println ("failed to update object");
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return i;
    }

    public int updateObjectByClass(Class c, int id, String field, String str) {
        Statement stmt = null;
        int i = -1;
        try (Connection con = ConnectionUtil.getConnection ()) {
            System.out.println ("got access to connection, ready to update object");
            stmt = con.createStatement ();

            String sql = "UPDATE " + getTableNameFromClass (c) +
                    " SET " + field + " = '" + str + "'" +
                    "WHERE " + getIDFieldNameFromClass (c) + " = " + id + ";";
//            System.out.println (sql);
            i = stmt.executeUpdate (sql);
            if (i > 0) {
                System.out.println ("updated object " + field + " to " + str);
            } else
                System.out.println ("failed to update object");
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return i;
    }

    public int deleteObjectByID(Class c, int num) {
        Statement stmt = null;
        int i = -1;
        try (Connection con = ConnectionUtil.getConnection ()) {
            System.out.println ("got access to connection, ready to delete object");
            stmt = con.createStatement ();

            String sql = "DELETE FROM " + getTableNameFromClass (c) +
                    " WHERE " + getIDFieldNameFromClass (c) + " = " + num + ";";
//            System.out.println (sql);
            i = stmt.executeUpdate (sql);
            if (i >= 0) {
                System.out.println ("deleted object with " + getIDFieldNameFromClass(c) + " equal " + num);
            } else
                System.out.println ("failed to delete object");
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return i;
    }

    public int deleteObjectByID(Object o, int num) {
        Statement stmt = null;
        int i = -1;
        try (Connection con = ConnectionUtil.getConnection ()) {
            System.out.println ("got access to connection, ready to delete object");
            stmt = con.createStatement ();

            String sql = "DELETE FROM " + getTableNameFromObj (o) +
                    " WHERE " + getIDFieldNameFromObject (o) + " = " + num + ";";
//           System.out.println (sql);
            i = stmt.executeUpdate (sql);
            if (i >= 0) {
                System.out.println ("deleted object with " + getIDFieldNameFromObject(o) + " equal " + num);
            } else
                System.out.println ("failed to delete object");
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return i;
    }

    public int deleteObjectByField(Object o, String field, String str) {
        Statement stmt = null;
        int i = -1;
        try (Connection con = ConnectionUtil.getConnection ()) {
            System.out.println ("got access to connection, ready to delete object");
            stmt = con.createStatement ();

            String sql = "DELETE FROM " + getTableNameFromObj (o) +
                    " WHERE " + field + " = '" + str + "';";
            System.out.println (sql);
            i = stmt.executeUpdate (sql);
            if (i >= 0) {
                System.out.println ("deleted object with " + field + " equal " + str);
            } else
                System.out.println ("failed to delete object");
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return i;
    }

    public int deleteObjectByField(Object o, String field, int num) {
        Statement stmt = null;
        int i = -1;
        try (Connection con = ConnectionUtil.getConnection ()) {
            System.out.println ("got access to connection, ready to delete object");
            stmt = con.createStatement ();

            String sql = "DELETE FROM " + getTableNameFromObj (o) +
                    " WHERE " + field + " = " + num + ";";
//            System.out.println (sql);
            i = stmt.executeUpdate (sql);
            if (i >= 0) {
                System.out.println ("deleted object with " + field + " equal " + num);
            } else
                System.out.println ("failed to delete object");
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return i;
    }

    public int dropTableByObject(Object o) {

        Statement stmt = null;
        int i = -1;
        try (Connection con = ConnectionUtil.getConnection ()) {
            System.out.println ("got access to connection, ready to drop table");
            stmt = con.createStatement ();

            String sql = "DROP TABLE IF EXISTS " + getTableNameFromObj (o) +
                    " CASCADE;";

//            System.out.println (sql);
            i = stmt.executeUpdate (sql);
            if (i == 0) {
                System.out.println ("deleted " + getTableNameFromObj (o) + " table");
            } else
                System.out.println ("failed to delete");
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return i;
    }

    public int dropTableByClass(Class c) {

        Statement stmt = null;
        int i = -1;
        try (Connection con = ConnectionUtil.getConnection ()) {
            System.out.println ("got access to connection, ready to drop table");
            stmt = con.createStatement ();

            String sql = "DROP TABLE IF EXISTS " + getTableNameFromClass (c) +
                    " CASCADE;";

//            System.out.println (sql);
            i = stmt.executeUpdate (sql);
            if (i == 0) {
                System.out.println ("deleted " + getTableNameFromClass (c) + " table");
            } else
                System.out.println ("failed to delete");
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return 0;
    }

    public void viewAllDataByClass(Class c) {
        Statement stmt = null;
        StringBuilder sb = new StringBuilder ();
        try (Connection con = ConnectionUtil.getConnection ()) {
            System.out.println ("got access to connection, view all data in table");
            stmt = con.createStatement ();

            String sql = "select * from " + getTableNameFromClass (c) + ";";
            ResultSet rs = stmt.executeQuery (sql);
            ResultSetMetaData rsmd = rs.getMetaData ();
            int colCount = rsmd.getColumnCount ();
            System.out.println ("All data from " +getTableNameFromClass (c) + " table");
            while(rs.next ()) {
                String res = "";
                for (int i = 1; i <= colCount; i++) {
                    String name = rsmd.getColumnName (i);
                    res = rs.getString (name);
                    sb.append ((name)+ " : ");
                    sb.append ((res)+"          ");
                }
                sb.append ("\n");
            }
            System.out.println (sb.toString ());

        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
    }

    public void viewDataByID(Class c,int id) {
        Statement stmt = null;
        StringBuilder sb = new StringBuilder ();
        try (Connection con = ConnectionUtil.getConnection ()) {
            System.out.println ("got access to connection, ready to view data by id");
            stmt = con.createStatement ();

            String sql = "select * from " + getTableNameFromClass (c) +
                    " where " + getIDFieldNameFromClass (c)+ " = " + id + ";";
            System.out.println (sql);
            ResultSet rs = stmt.executeQuery (sql);
            ResultSetMetaData rsmd = rs.getMetaData ();
            int colCount = rsmd.getColumnCount ();
            System.out.println ("Data from " +getTableNameFromClass (c) + " table where "+
                    getIDFieldNameFromClass (c) + " = " + id);
            while(rs.next ()) {
                String res = "";
                for (int i = 1; i <= colCount; i++) {
                    String name = rsmd.getColumnName (i);
                    res = rs.getString (name);
                    sb.append ((name)+ " : ");
                    sb.append ((res)+"          ");
                }
                sb.append ("\n");
            }
            System.out.println (sb.toString ());

        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
    }

    public void viewDataByField(Class c,String field,int id) {
        Statement stmt = null;
        StringBuilder sb = new StringBuilder ();
        try (Connection con = ConnectionUtil.getConnection ()) {
            System.out.println ("got access to connection, ready to view table by field");
            stmt = con.createStatement ();

            String sql = "select * from " + getTableNameFromClass (c) +
                    " where " +field+ " = " + id + ";";
            System.out.println (sql);
            ResultSet rs = stmt.executeQuery (sql);
            ResultSetMetaData rsmd = rs.getMetaData ();
            int colCount = rsmd.getColumnCount ();
            System.out.println ("Data from " +getTableNameFromClass (c) + " table where "+
                    field + " = " + id);
            while(rs.next ()) {
                String res = "";
                for (int i = 1; i <= colCount; i++) {
                    String name = rsmd.getColumnName (i);
                    res = rs.getString (name);
                    sb.append ((name)+ " : ");
                    sb.append ((res)+"          ");
                }
                sb.append ("\n");
            }
            System.out.println (sb.toString ());

        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
    }

    public void viewDataByField(Class c,String field,String str) {
        Statement stmt = null;
        StringBuilder sb = new StringBuilder ();
        try (Connection con = ConnectionUtil.getConnection ()) {
            System.out.println ("got access to connection, ready to view table");
            stmt = con.createStatement ();

            String sql = "select * from " + getTableNameFromClass (c) +
                    " where " +field+ " = '" + str + "';";

            ResultSet rs = stmt.executeQuery (sql);
            ResultSetMetaData rsmd = rs.getMetaData ();
            int colCount = rsmd.getColumnCount ();
            System.out.println ("Data from " +getTableNameFromClass (c) + " table where "+
                    field + " = " + str);
            while(rs.next ()) {
                String res = "";
                for (int i = 1; i <= colCount; i++) {
                    String name = rsmd.getColumnName (i);
                    res = rs.getString (name);
                    sb.append ((name)+ " : ");
                    sb.append ((res)+"          ");
                }
                sb.append ("\n");
            }
            System.out.println (sb.toString ());

        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
    }

    public int transferIntFromObj1ToObj2(Class c,int obj1ID,int obj2ID,String field,int amount) {
        Statement stmt = null;
        int i = 0;
        Connection con = null;
        try {
            con = ConnectionUtil.getConnection ();
            System.out.println ("got access to connection, ready to transfer int");
            con.setAutoCommit (false);
            stmt = con.createStatement ();
            Savepoint sp = con.setSavepoint ();
            String sql = "UPDATE " + getTableNameFromClass (c) +
                    " SET " + field + " = " + field + " - " + amount +
                    " WHERE " + getIDFieldNameFromClass (c) + " = " + obj1ID + ";";
            i += stmt.executeUpdate(sql);
            sql = "UPDATE " + getTableNameFromClass (c) +
                    " SET " + field + " = " + field + " + " + amount +
                    " WHERE " + getIDFieldNameFromClass (c) + " = " + obj2ID + ";";
            i += stmt.executeUpdate (sql);

//            System.out.println ("i = " + i);
            if( i != 2){
                con.rollback (sp);
                System.out.println ("Transaction failed, rolling back....");
            } else{
                System.out.println ("Transaction successful");
            }
            con.commit ();
        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
        return i;
    }

}