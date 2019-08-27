# UFO Sightings Service
AAAA
We have a huge TSV file (included in the project resources folder) and we need to consume the information inside it.
We have provided a simple interface (UfoSightingService) and would like you provide an implementation for it.
  


### First Objective
- Write an implementation of the UfoSightingService interface that fulfill the requirements in the javadocs present on it.
- Searching the sighting happened on October 1995 should return 107 sightings
- Searching all the sightings should return 61391 results


### Second objective
Optimize it to be as fast as it can be

### Constraints
- UfoSighting DTO and UfoSightingService interface cannot be changed
- TSV File cannot be altered
- Usage of multithreading is not allowed

### What are we looking for

- Clean code
- Unit testing
- Your comments on what you would change on the interface and DTO


### Suggestions
Keep it simple
You can use any Java version

You can use any library available on internet but none should be necessary




### How to submit the code

Git Pull on our repo 

**OR** via mail but make sure to

Clean the project (don't submit the target folder)

Remove the tsv file (Limited inbox, sorry)


### Developer Comments

#### Updated on 09-Jan-2018

1. Introduced a Cache at the DAO so that only the first invocation of the service loads the data from the file and the subsequent calls reads the data from the cache.
    * Consequently the DAO is now implemented as a Singleton.
    * Further improvements can be done by introducing a Repository Class which holds the Cache instead of the DAO. This would change the dependency as follows
    Service -> Repository(holding Cache) -> DAO
2. The Data is loaded into a Hashmap with the concatenated Year and Month as Key. This improves the search performance as now the retrieve complexity is O(1)
   instead of O(n) as it was when it was loaded in a ArrayList.
    * Further improvements can be done by loading the data in a multi-level map; the year being the first level key and the month being the second level key.

#### Updated on 03-Jan-2018

- I have overridden toString(), hashCode() and equals() methods on the DTO class for easy testing and object comparison and debugging.
- Would have liked to throw an Service/Application exception, but this means changing the service interface.
    Hence throwing an Runtime exception from the service implementation methods.
- Unit testing of DAO with Mockito was proving tricky. I have applied Single responsibility principle and extracted business logic to the
    Mapper class to enable unit testing. The rest of the DAO code is covered in integation tests
- Integration tests specified in Objective 1 above is written in ufo.service.impl.UfoSightingServiceImplTest with test methods having
    "IntegrationTest" suffix.
- To speed up data load and search, I have used parallel stream provided by Java8. To further improve speed of search, we can consider loading
    the data into a map of maps (keyed on year followed by key on month).


