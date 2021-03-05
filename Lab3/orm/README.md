# Lab 3: ORM, Hibernate, JPA and Spring

## Challenges

1. [Set Employee cache to 20 seconds](#cache)
2. [User table has Employee FK](#user-table-has-employee-fk)
3. [Difference between cascade.REMOVE and orphanRemoval=true](#difference-between-cascade.REMOVE-and-orphanRemoval=true)
4. [Remove lazy load from benefits](#remove-lazy-load-from-benefits)
5. [Remove cascades from benefits and addresses](#remove-cascades-from-benefits-and-addresses)
6. [Remove @Transactional](#Remove-@Transactional)
7. [Unit tests](#unit-tests)
8. [Online leave form (TODO)](#Online-leave-form-(TODO))

***

## Cache

For some reason, the cache, even though when setting the timeToLive to 20 seconds, Hibernate still queries the database after only 10 seconds. The employee object should still be in the database. See below. 

### 10 seconds

![deployment](docs/lab3-cache-10.png)

### 20 seconds (same result)

![deployment](docs/lab3-cache-10-not-working.png)

## User table has Employee FK

![deployment](docs/lab3-b.png)

## Difference between cascade.REMOVE and orphanRemoval=true

`Cascade.REMOVE` removes the target/child entities whenever the parent entity is removed. In other words, the entities with the foreign key pointing to the parent are deleted. It will not delete when the FK is made null. On the other hand, `orphanRemoval=true` removes the child entities whenever their relationships (i.e. the FKs) are set to null.

## Remove lazy load from benefits

During lazyloading, when the benefits are loaded, an SQL Query is made separately. During eager loading, no separate SQL queries are made. The children are called in the first SQL Query. Please see below.

**Note**: Some error happened - MultipleBagException because Employee has two collections and it is not recommended by Hibernate due to the Cartesian Product being generated. This can be “swept under the carpet” by using Set instead of List but it is not recommended to use Eager loading for multiple collections. [Reference](https://stackoverflow.com/questions/24675340/org-hibernate-loader-multiplebagfetchexception-cannot-simultaneously-fetch-mult).

### Lazy

***
![deployment](docs/lab3-lazy-c.png)

### Eager

***
![deployment](docs/lab3-eager-c.png)

## Remove cascades from benefits and addresses

The addresses and benefits aren’t persisted in the database after the test is run even though the employee itself is persisted.

![deployment](docs/lab3-address-not-persist.png)

However, for Benefits, an error is thrown, reporting that since Benefit is not explicitly persisted, the association table cannot be created.

![deployment](docs/lab3-persist-error.png)

This fails because the children will be left with empty parents and this violates the FK constraint of SQL.

![deployment](docs/lab3-cascade-remove.png)

## Remove @Transactional

Fails because entityManager is a transactional class and all such classes can only have transactional methods. The CRUD methods utilizes these transactions.

![deployment](docs/lab3-transaction-error.png)

## Unit tests

![deployment](docs/lab3-unit-tests.png)

**Note**: `testCache` test case not properly implemented (no assertions).

## Online leave form (TODO)
