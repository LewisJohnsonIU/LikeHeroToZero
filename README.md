# CO2_Emissions_Project
**CO2 Emissions Tracking System:**  This project leverages N-tier architecture, utilizing JSF, EJB, and Hibernate, to manage and analyze global CO2 emissions data. It integrates an external dataset from Kaggle, employs Apache POI for Excel file manipulation, and ensures secure user interactions. With Java 17 and Wildfly 26.

# Project Documentation

## I. Component and Interaction Overview

### 1. Application Architecture

This application follows the N-tier architecture, dividing it into three layers:

- **Web Container:** Includes Managed Beans and user interfaces developed using the JSF framework.
  
- **EJB Container:** Houses application logic with POJO (Java beans) and JPA (Java Persistence API) for data persistence. Utilizes Hibernate for mapping between Java Beans and the database.
  
- **Data Repository:** Utilizes XAMPP or WAMP server to store database components such as tables and fields. JPA module facilitates interaction with the database using EntityManager and HQL.

All containers are deployed on Wildfly 26, configured with appropriate datasources.

### 2. Component Interaction

To ensure seamless integration between containers:

- The Web container must be aware of the EJB container, achieved by adding the EJB module to the Web container's classpath.
  
- Utilization of @EJB annotation in Managed Beans to invoke EJB methods for various functionalities, such as managing CO2 emissions data.

## II. Data Source

### 1. About the Dataset

This application uses an external dataset from [Kaggle](https://www.kaggle.com/) to analyze global CO2 emissions. The dataset spans from 1960 to the present, providing insights into emissions by country. It aids researchers, policymakers, and the public in understanding and addressing the impact of greenhouse gas emissions on climate change.

### 2. Implementation of the Dataset on the Website

The dataset, provided as an Excel file, is read and stored in a local MySQL database using the Apache POI module. The POI library facilitates the programmatic manipulation of Excel files.

### 3. The POI Module

The Apache POI jar module allows reading and writing Microsoft Office file formats, including Excel spreadsheets.

### 4. Demo

A demonstration involves creating a class to read the Excel file using Apache POI components and initializing the local database with the dataset.

## III. Conception & Design

### 1. UML Diagram

A UML diagram illustrates the relationships between three classes: Co2Emissions, DataScientist, and Publishers.

- **Co2Emissions Class:** Stores CO2 emissions data with fields such as year, country, CO2 value, approval status, and country code.

- **DataScientist Class:** Represents individuals contributing CO2 emissions data, establishing a relationship with Co2Emissions.

- **Publishers Class:** Responsible for changing the approval status in Co2Emissions.

## IV. Database Structure Documentation

The database structure is automatically generated via Hibernate JPA provider. Entities are defined with JPA annotations (@Entity, @Table, @ID, @GeneratedValue), and relationships are established using ORM.

Tables:
- Co2 Emissions
- Publishers
- Scientists

## V. Website Design and Navigation

The website includes:

- Home page with descriptions and navigation bar.
  
- CO2 Emissions page for global data, with options to filter by year or country.
  
- User interfaces for data publishers and data scientists, allowing approval, disapproval, and addition of CO2 emissions.

## VI. Sessions Management

Sessions are managed using the `HttpSession` class to store user information. Users are assigned session IDs, allowing multiple simultaneous connections. Session management is implemented to control access to pages and link data additions with the respective user.

## VII. Project Setup and Deployment

### Requirements:

- Java 17
- Wildfly 26
- MySQL database

### Steps to Run the Project:

1. **Configure Wildfly:**

   a. **Standalone.xml:**
   
      ```xml
       <datasource jndi-name="java:/dsCo2Emission" pool-name="co2Emissions">
                    <connection-url>jdbc:mysql://localhost:3306/co2Emissions</connection-url>
                    <driver-class>com.mysql.cj.jdbc.Driver</driver-class>
                    <driver>mysql</driver>
                    <security>
                        <user-name>root</user-name>
                    </security>
                    <validation>
                        <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
                        <validate-on-match>true</validate-on-match>
                        <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
                    </validation>
      </datasource>
      ```

   b. **Graphic Interface:**
   
      Refer to [this video tutorial](https://www.youtube.com/watch?v=TRF--1YM-Ew) for datasource configuration.

3. **Add MySQL Driver:**
   
   Follow [this video tutorial](https://www.youtube.com/watch?v=I8t1TLSeEBw&t=757s&ab_channel=SkillfulTeacher) to add the MySQL driver.

4. **Create EAR Project:**
   
   Create an EAR project and add the EJB and WEB projects to it.

5. **Deploy the EAR:**
   
   Deploy the EAR on the configured Wildfly server. Check the console for a success message:

   ```bash
   14:32:30,746 INFO  [org.jboss.as.server] (ServerService Thread Pool -- 45) WFLYSRV0010: Deployed "Co2_emissions.ear" (runtime-name : "Co2_emissions.ear")
   ```

6. **Access the Website:**
   
   Search for the localhost URL with the configured port, open a web browser, and access the home page.

   ```bash
   http://localhost:8080/Co2EmissionsWeb
   ```

   You should see the home page. Congratulations! The project is successfully running.
