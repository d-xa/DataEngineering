# Maven Archetypes

### Create archetype from existing project
1. navigate to root of project & install
```
mvn clean install
```
2. navigate to target/generated-sources/archetype
```
mvn install archetype:update-local-catalog
```
3. check if available
```
less ~/.m2/repository/archetype-catalog.xml
```