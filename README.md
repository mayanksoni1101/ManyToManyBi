# Batch & Student Management System (JPA / Hibernate Many-to-Many Bi-Directional)

A Java application demonstrating a **Many-to-Many Bi-directional Mapping** between `Batch` and `Student` entities using **JPA (Java Persistence API)** and **Hibernate**. The application includes database initialization, bidirectional relationship mapping, and execution classes for executing queries via JPQL (Java Persistence Query Language).

---

## 📌 Key Features

* **Bi-Directional Relationship Mapping:**
  * A `Batch` contains a list of `Student` entities.
  * A `Student` can belong to multiple `Batch` instances.
  * Configured using `@ManyToMany` annotations and `@JoinTable` cascade options.
* **Batch Ingestion:**
  * Sample batch insertion capability using `InsertData.java` to populate sample batches, students, and their bidirectional mappings.
* **Batch-Centric Queries:**
  * Lookup batches by Primary Key ID, Batch Code, Subject, or Trainer Name.
  * Find batches associated with a student using Student ID, Student Phone, or combinations with Student Name.
* **Student-Centric Queries:**
  * Lookup students by Primary Key ID, Name, Phone Number, or Marks/Percentage threshold filter queries (>35%, >60%, >85%, and dynamic percentage ranges).
  * Filter students based on Batch Code, Subject, or Trainer parameters.

---

## 🛠️ Tech Stack & Prerequisites

* **Language:** Java (JDK 8 or higher)
* **API Framework:** JPA (Java Persistence API) / Hibernate ORM
* **Query Language:** JPQL (Java Persistence Query Language)
* **Database:** MySQL / PostgreSQL / Oracle (or any JDBC-compliant SQL database)
* **Persistence Provider:** Hibernate (`javax.persistence`)

---

## 📂 Project Structure

```
src/
└── org/jsp/manytomanybi/
    ├── Batch.java                            # Entity representing Batch details
    ├── Student.java                          # Entity representing Student details
    ├── InsertData.java                       # Inserts sample Batch & Student mappings
    │
    ├── Batch Operations:
    ├── FindBatchByBatchId.java               # Find Batch by ID (em.find)
    ├── FindBatchByBatchCode.java             # Find Batch by unique Batch Code
    ├── FindBatchBySubject.java               # Find Batches by Subject
    ├── FindBatchByTrainerName.java           # Find Batches by Trainer Name
    ├── FindBatchByStudentId.java             # Find Batches associated with Student ID
    ├── FindBatchByStudentIdAndName.java      # Find Batches by Student ID and Name
    ├── FindBatchByStudentPhone.java          # Find Batches by Student Phone
    └── FindBatchByStudentPhoneAndName.java   # Find Batches by Student Phone and Name
    │
    └── Student Operations:
        ├── FindStudentById.java              # Find Student by ID (em.find)
        ├── FindStudentByName.java            # Find Student by Name
        ├── FindStudentByPhone.java           # Find Student by Phone Number
        ├── FindStudentsByBatchId.java        # Find Students by Batch Code
        ├── FindStudentBySubjectAndBatchCode.java # Find Students by Batch Code and Subject
        ├── FindStudentByTrainerAndBatchCode.java # Find Students by Batch Code and Trainer
        ├── FindStudentWhosePerGreater35.java # Fetch Students with Marks > 35
        ├── FindStudentPerMoreThan60.java     # Fetch Students with Marks > 60
        ├── FindStudentPerMoreThan85.java     # Fetch Students with Marks > 85
        └── FindStudentBetweenPerRange.java   # Fetch Students within a custom Marks range
```

---

## ⚙️ Configuration Setup

Ensure your `persistence.xml` is configured under `src/META-INF/persistence.xml` with the persistence unit name matching `"M15"`.

Example `src/META-INF/persistence.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
                                 http://xmlns.jcp.org/xml/ns/persistence/persistence_2_0.xsd"
             version="2.0">

    <persistence-unit name="M15">
        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
        <class>org.jsp.manytomanybi.Batch</class>
        <class>org.jsp.manytomanybi.Student</class>
        
        <properties>
            <!-- Database connection setup -->
            <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver" />
            <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/your_database_name" />
            <property name="javax.persistence.jdbc.user" value="your_username" />
            <property name="javax.persistence.jdbc.password" value="your_password" />
            
            <!-- Hibernate Properties -->
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect" />
            <property name="hibernate.show_sql" value="true" />
            <property name="hibernate.hbm2ddl.auto" value="update" />
        </properties>
    </persistence-unit>
</persistence>
```

---

## 📋 Schema Overview

### `Batch` Entity
| Column | Type | Constraints / Description |
| :--- | :--- | :--- |
| `id` | `int` | Primary Key, Auto-generated (`IDENTITY`) |
| `batchCode` | `String` | Unique Batch Identifier Code |
| `subject` | `String` | Subject taught in the batch |
| `trainer` | `String` | Trainer assigned to the batch |
| `std` | `List<Student>` | `@ManyToMany` mapped collection |

### `Student` Entity
| Column | Type | Constraints / Description |
| :--- | :--- | :--- |
| `id` | `int` | Primary Key, Auto-generated (`IDENTITY`) |
| `name` | `String` | Student Name |
| `phone` | `long` | Contact Phone Number |
| `marks` | `double` | Student Marks / Percentage |
| `batch` | `List<Batch>` | `@ManyToMany(mappedBy = "std")` mapped collection |

---

## 🚀 How to Run

1. **Database Setup:** Start your SQL database server and create the required target schema.
2. **Update Configuration:** Configure your database credentials inside `src/META-INF/persistence.xml`.
3. **Seed Database:** Execute `InsertData.java` to populate sample batches and students with their respective relationships.
4. **Execute Query Actions:** Run any lookup class (e.g., `FindBatchByBatchCode.java`, `FindStudentPerMoreThan60.java`, or `FindStudentByTrainerAndBatchCode.java`) to interactively query records from the console.
