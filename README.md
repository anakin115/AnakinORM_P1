# AnakinORM
## Project Description

AnakinORM is an application that allow users who has little to no experience with SQL to perform various tasks. That include creating a relational database
with tables connected to one another. Users can also manage the data in the database, perform functions such as insert, delete, read and update. 


## Technologies Used

   * Java SQL
   * JDBC
   * JUnit
   * PostgreSQL
   * Maven
   * AWS RDS
   * AWS EC2
   * AWS CodeBuild
   * AWS CodePipeline
   * Elastic Beanstalk
   * Mockito
   * DevOps
   * DBBeaver
   * Git
   * XML
   
   

## Features

    Users can configure their models with annotation to let the program know they want each field to persist in the database. 
    Users can perform persistence of entities which are the basic create, read, update and delete functions.
    Application utilized connection pooling to reduce the amount of connections user would have to make each time connecting to database.
    Multithreading support to minimize the time it takes for program to run.
    Used hikari cache to reduce the nubmer of connections to the database when possible.

## To-do list:

    Refactor some of the code so it is more readable and documentation each functions with JavaDocs

## Getting Started

git clone https://github.com/anakin115/AnakinORM_P1.git

    1. Configure the properties file in the resource folder with the right database connnection.
    2. Annotate models with the correct annotations.
    3. Perform the functions you want with the correct method calls.
    4. Enjoy!
