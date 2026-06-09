# SQL queries for all below endpoints are handled by built in JPA (You will not find SQL queries for these)
    @GetMapping
    @GetMapping("/{id}")
    @PostMapping
    @PutMapping("/{id}")
    @DeleteMapping("/{id}")

# url for above mappings
http://192.168.0.121:8080/api/students
http://192.168.0.121:8080/api/students/id

# User defined SQL queries in JPA Querying Approaches

### 1) Method Name Query
GET http://192.168.0.121:8080/api/students/search?name=Test&age=17
### 2) JPL Query
GET http://192.168.0.121:8080/api/students/search-JPLquery?name=Test&age=17
### 3) Native Query
GET http://192.168.0.121:8080/api/students/search-native?name=Test&age=17

./gradlew build
./gradlew :application:bootRun   
./gradlew :application:bootRun --stacktrace
./gradlew :application:bootRun --stacktrace > output.txt