# Spring MVC with MySQL Prototype

This prototype will work with following technical specifications:
1.) Spring 4.1.4.
2.) Spring Security 3.2.5.
3.) Java 1.7
4.) Mysql 5.0 or above
5.) Apach Tomcat 7

#### Dependencies

* Java and MySql should be installed on your computer.

Below are the steps that needs to be done to start this application running in your local environment:

#### Installing and Running:

1. Configure **database.properties** and **testcase.database.properties** file with your database credentials. (Find it in \ccm-dao-mysql\src\main\resources\database-config\database.properties and  \ccm-dao-mysql\src\test\resources\database-config\testcase.database.properties)
2. Configure **mail.properties** file with your email credentials. (Find it in \ccm-dao-mysql\src\main\resources\mail-config\mail.properties)
3. Configure **aws-credential.properties** file with your Amazon S3 credentials. (Find it in \ccmservices\src\main\resources\amazon-config\aws-credential.properties)
4. Insert admin user in **ccm_user** table (Find it in /public/sql_script.sql)
5. Import geographics master data
	* Find CSV file in public folder of the application
	* Run script to load data from CSV file (Find it in /public/sql_script.sql)
6. ```Maven Install ccm-dao-mysql```
7. ```Maven Install ccmservices```
8. ```Maven Install ccmspring```
9. Copy ccmspring.war file from \ccm-spring\ccmspring\target to $TOMCAT_HOME\webapp\ OR add maven plugin to deploy war file into tomcat webapp.
10. Start Tomcat server and hit http://localhost:8080/ccmspring/
11. Login with admin user

### Bulk upload customers and transactions
1. Find **customers.csv/customers.xlsx** and **transactions.csv/transactions.xlsx** files in public folder.
2. Once the customers are uploaded then you can view details of uploaded customers in customers tab.
3. To upload transactions, customer must exist with the credit card having a credit card number mentioned in the transaction entry in **transactions.csv/transactions.xlsx** file, so upload the transactions after uploading the customers.
