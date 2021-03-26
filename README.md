Project 1 - Custom Object Relational Mapping Framework
Description

Your first project will be to create a custom object relational mapping (ORM) framework. This framework will allow for a simplified and SQL-free interaction with the relational data source. The requires of the project are purposefully vague, the intention is to allow for you to be creative in your implementation of this framework. There are many ways that this task can be approached, and you are encouraged to explore existing Java ORM implementations in order to get some inspiration. Some suggested features that your ORM can provide are:

    provide developers the option of file-based and/or programmatic configuration of entities

    Programmatic persistence of entities (basic CRUD support)

    Basic transaction management (begin, commit, savepoint, rollback)

    Connection pooling

    Multithreading support for executing queries

Tech Stack

    Java 8
    JUnit
    Apache Maven
    PostGreSQL deployed on AWS RDS
    Git SCM (on GitHub)

Init Instructions

    Create a new repository within this organization (naming convention: orm_name_p1; with orm_name being replaced by the name of your custom library)

Presentation

    finalized version of library must be pushed to personal repository within this organization by the presentation date (March 26th, 2021)
    10-15 minute live demonstration of the implemented features using a demo application to showcase the ORM's functionality

**Method within this ORM :**

public int createTable(Object o)
public int createTableFromClass(Class c)
public int insertObject(Object o)

public void viewAllDataByClass(Class c)
public void viewDataByID(Class c,int id)
public void viewDataByField(Class c,String field,int id)
public void viewDataByField(Class c,String field,String str)

public int updateObject(Object o, int id, String field, String str)
public int updateObject(Object o, int id, String field, int num)
public int updateObjectByClass(Class c, int id, String field, int num)
public int updateObjectByClass(Class c, int id, String field, String str)

public int deleteObjectByID(Class c, int num)
public int deleteObjectByID(Object o, int num)
public int deleteObjectByField(Object o, String field, String str)
public int deleteObjectByField(Object o, String field, int num)

public int dropTableByObject(Object o)
public int dropTableByClass(Class c)

public int transferIntFromObj1ToObj2(Class c,int obj1ID,int obj2ID,String field,int amount)
    - if problem occur during transaction, it will rollback to before the transaction happen