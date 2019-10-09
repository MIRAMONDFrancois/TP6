# Exemple de test avec la base HSQLDB

## Description

Je jeu de test utilise une base [HSQLDB](hsqldb.org). La base est créée et initialisée
en mémoire avant chaque test (`@Before`) en utilisant la classe [SqlFile](http://hsqldb.org/doc/2.0/apidocs/org/hsqldb/cmdline/SqlFile.html) fournie par HSQLDB. Le code SQL d'initialisation est dans les
ressources de test (`src/test/resources`) sous la forme de fichiers SQL.

La source HSQLDB de données est configurée dans le jeu de test par la méthode suivante :

```java
	public static DataSource getDataSource() {
		org.hsqldb.jdbc.JDBCDataSource ds = new org.hsqldb.jdbc.JDBCDataSource();
		ds.setDatabase("jdbc:hsqldb:mem:testcase;shutdown=true");
		ds.setUser("sa");
		ds.setPassword("sa");
		return ds;
	}	
```

## Dépendances

Le fichier pom.xml contient les dépendances vers le driver JDBC de HSQLDB, et vers
l'outil sqltool qui permet d'exécuter les instructions SQL d'initialisation.


```xml
        <!-- Le driver JDBC utilisé seulement pour le test -->        
        <dependency>
            <groupId>org.hsqldb</groupId>
            <artifactId>hsqldb</artifactId>
            <version>2.0.0</version>
            <scope>test</scope>
        </dependency>
        <!-- sqltool utilisé seulement pour le test pour initialiser la base -->                
        <dependency>
            <groupId>org.hsqldb</groupId>
            <artifactId>sqltool</artifactId>
            <version>2.0.0</version>
            <scope>test</scope>
        </dependency>
```

