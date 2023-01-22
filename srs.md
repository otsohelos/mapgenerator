# Software requirements specification
**MapGenerator** by **Otso Helos**
## Introduction
### Purpose
MapGenerator is a tool for creating randomized maps with realistic features such as elevation, terrain type, and rivers and other bodies of water.

MapGenerator is my demo work for the course "Aineopintojen harjoitustyö: Tietorakenteet ja algoritmit" as part of my Bachelor's in Computer Science, University of Helsinki, spring 2023.

### Intended audience
MapGenerator is intended for role-players, speculative fiction authors, and people who just like looking at nice maps.

### Intended use
MapGenerator is intended for fun and inspiration. It is meant to produce believable maps of fictional areas the size of continents or large groups of islands.

<!---Product Scope-->
    

## Overall description

### User needs
The user will use MapGenerator to define some variables for the map, generate a map, and then save the map wither as a picture or possibly a file that can be edited in MapGenerator in the future.

#### Choosing variables
The user will be able to just generate a map without choosing any variables, as there will be a preset that produces a nice map. If the user wants to, they can choose several variables, including
* Size of the map
* Variability in elevation
* Amount of precipitation

#### Generating a map
After choosing the variables, the user will be able to generate a map. If the result isn't satisfactory, they can choose to generate another one with the same variables. As randomness is used, each repetition will produce a different map.

#### Saving a map
The interface will allow for saving the map as an image file on the user's computer. If maps are made editable in later iterations of the software, there will also be a possibility to save a file that can be uploaded back to MapGenerator and edited.

### Technical specification
#### Programming languages and frameworks
MapGenerator will use a Java backend and a React.js frontend.

For course purposes, I also know C# and Python well enough to review the work of others.

#### Algorithms and data structures

I will divide the map area to polygons by putting in random dots and then generating a Voronoi diagram with Fortune's algorithm. The resulting polygonal map might need to be processed with one or several iterations of Lloyd's relaxation algorithm. After that, I will assign elevations to the polygon corners, probably as a function of a small number of "seed" elevation points. The user-given variability in elevation will affect both the possible elevations of the seed point and the variability between elevations of neighboring corners. The corners function as nodes in a network, and the boundaries between polygons are edges.

After that, I will find areas that are lower than a certain set elevation, which will be oceans, and contiguous areas that are lower than their surrounding land, which will be lakes. Terrain type will be assigned according to deterministic rules, depending on elevation and precipitation. A more complex water-retention algorithm is also possible for the bodies of water. Time permitting, a linear optimization model could be used to create bodies of water based on elevation differences between adjoining polygons.

The possibility to add noise to the polygon borders will be investigated.

#### Time requirements

Fortune's algorithm uses O(n log n) time, where n is the number of points. Lloyd's algorithm uses O(n) time, where n is also the number of points. An overall complexity of O(n^2^) could be a goal.

#### Language

I will be writing the code, comments, and documentation in English. Weekly reports will be written in Finnish.

## Sources
[Course homepage](https://tiralabra.github.io/2023_p3)

[Red Blob Games: Polygon map generation, part 1](https://simblob.blogspot.com/2010/09/polygon-map-generation-part-1.html)

[Wikipedia: Voronoi diagram](https://en.wikipedia.org/wiki/Voronoi_diagram)

[Rosetta Code: Voronoi diagram](https://rosettacode.org/wiki/Voronoi_diagram)

[Wikipedia: Lloyd's algorithm](https://en.wikipedia.org/wiki/Lloyd%27s_algorithm)


<!---## System Features and Requirements

### Functional Requirements

### External Interface Requirements

### System Features

### Nonfunctional Requirements-->