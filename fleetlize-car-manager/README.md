To run this code in development mode, you should set this env:

```
DB_PASS=root;
DB_USER=root;
DB_URL=jdbc:mysql://localhost:3306/notification;
spring_profiles_active=local
CONSUL_ENABLED={true|false}
```

Consul can be optional.

Liquibase (alpha)

Change Database appointment in cb.changelog-master.yaml, then, try this commands:

````
mvn liquibase:status
mvn liquibase:update
````