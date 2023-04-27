# Otso's Map Generator
Tiralabra 2023 / Data structures and algorithms lab work, spring 2023


## Installation and usage

### Backend
You need [Java 17](https://www.java.com/en/download/) or newer and [Maven 3.9](https://maven.apache.org/download.cgi) to run the backend.

On a Linux machine, it might be necessary to manually install Maven 3.9, as only earlier versions seem to be available with apt. If necessary, please refer to this [guide on how to manually install Maven](https://medium.com/ci-cd-devops/error-error-executing-maven-error-java-lang-illegalstateexception-unable-to-load-cache-item-39e886a67216), changing the version to 3.9.1 or newer. 

Once you have Java nad Maven, navigate to /program/backspring and start backend by using command

```mvn exec:java -Dexec.mainClass=org.helsinki.back.App```

If prompted, accept incoming connections. Backend should start in port 8080.

### Frontend

You need npm to run the frontend.

To install the necessary dependencies, navigate to /program/front and enter command

```npm i```

Once that has run successfully, start frontend by using command

```npm start```

Frontend should start in port 3000, accessible at http://localhost:3000.

Refreshing the page gives the user a new diagram.


## Documentation
[Software Requirements Specification](https://github.com/otsohelos/mapgenerator/blob/main/srs.md)

[Test documentation](https://github.com/otsohelos/mapgenerator/blob/main/docs/testing.md)

### Weekly reports (in Finnish)
[Week 1](https://github.com/otsohelos/mapgenerator/blob/main/viikkoraportit/viikko1.md)

[Week 2](https://github.com/otsohelos/mapgenerator/blob/main/viikkoraportit/viikko2.md)

[Week 3](https://github.com/otsohelos/mapgenerator/blob/main/viikkoraportit/viikko3.md)

[Week 4](https://github.com/otsohelos/mapgenerator/blob/main/viikkoraportit/viikko4.md)

[Week 5](https://github.com/otsohelos/mapgenerator/blob/main/viikkoraportit/viikko5.md)

<!--[Week 6](https://github.com/otsohelos/mapgenerator/blob/main/viikkoraportit/viikko6.md)-->