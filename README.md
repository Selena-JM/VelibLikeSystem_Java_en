## Description

**This project was realized as part of a course on project oriented programming in second year of engineering school.**

This project consists on developping a Java framework, called myVelib for managing bike sharing. 
It is a bike sharing system (like, for example, Velib in Paris) allows inhabitants to rent bicycles
and cycle around a metropolitan area. Such a system consists of several interacting parts
including: the docking stations (displaced in key points of a metropolitan area), different
kind of bicycles (mechanical and electrically assisted), the users (which may posses a reg-
istration card), the maintenance crew (responsible for collecting/replacing broken down
bicycles), etc.

**The project consists of two parts:**

Part 1: myVelib core: design and development of the core Java infrastructure for the myVelib
system (based on requirements given in Section 2).

Part 2: myVelib user-interface: design and development of a user-interface for the myVelib
system (see Section 3).

_**To test this implementation, please see the 7 and 8 parts of the report (named "Tests" and "How to test" respectively)**_

## Requirements and bonuses

### **Components of the myVelib system**

• Docking station: a docking station is where bicycles can be rented and dropped. It
consists of a number of parking slots where bikes are stored and of a terminal which
users can interact with in order for renting bicycles. Parking slots can be be either
occupied by a bicycle, free or out-of-order. A station is located in a specific place
specified by GPS coordinates. A station can be on service or offline: if it is offline
all of its parking bays as well as the terminal cannot be used. There exist two types
of stations, a “standard” type, and a “plus” type. When a user who hold a Velib
card drops a bicycle to a “plus” station it earns 5 minutes credits in its time balance.
Each station has a unique numerical ID and so each parking slot (within a station)
has a unique numerical ID.

• Bicycle: there exists two kind of bicycles, mechanical and electrical. Each bicycle
has a unique numerical ID. Bikes can be rented/returned from/to a docking station
or from/to their current GPS position (if they have been parked not in a docking
station). Therefore each bike has a physical location that may corresponds to that of
the station in which the bike is parked or of a GPS coordinate if the bike is parked
elsewhere, not in a station (e.g. on the pavement of a road).

• User: has a name, a unique numerical ID, a geographical position (GPS coordinates)
a credit card and might have a registration card. In case a user holds a card she has
also a time-credit balance (expressed in minutes) representing the credit gained by
returning bicycles to “plus” stations. The time credit is used to compute the actual
cost of a bike ride. The user has also a balance of total charges representing the total
amount of money she has been charged for for using bicycle of the myVelib system.

• Cards: there are two kinds of registration type: Vlibre and Vmax (these cards can
be virtualised on the User’s mobile phone)


### **Costs for using bicycles**

When a ride is ended the system will (automatically) compute the corresponding cost of
the ride based on the ride duration (in minutes), the kind of bike, the type of card a user
has and also on whether the bike has been parked in a docking station or not (e.g. on a
street’s pavement). Specifically:

• In case the bike is rented-from and returned-to a docking station the following pricing
hold:

  – if a user has no card the cost is 1 Euro per hour (for mechanical bikes) and 2
Euro per hour (for electrical bikes)
if a user has a Vlibre card the cost is: 0 Euro for the first hour then 1E per
each successive hour (for mechanical) and 1 Euro for the first hour then 2E per
each successive hour (for electrical bikes). If a ride lasts longer than 60min the
actual time balance exceeding 60min is computed by deducing from the user’s
time-credit (if any). For example a user’s time-credit is 20min and the ride
lasts 75min, then the user won’t be charged any extra min (beyond 1h) but it’s
time-credit will be updated to 5min (deducing the 15min in excess to the free
hour). On the other hand if the ride lasts 135min, the user will be charged for
135-20 = 115min, hence for the 55min in excess of an hour, and so on.

  – if a user has Vmax card the cost is: 0 Euro for the first hour then 1E per each
successive hour (independently of the kind of bike)

• In case the bike is rented-from a free position (the bike was parked not at a docking
station) and returned-to a docking station the price is computed by applying a 10%
discount to the pricing computed when the bike is taken-from and returned-to a
docking station.

• In case the bike is not returned to a docking station (i.e. it is left parked in the road)
the price is computed by applying a 10% malus to the pricing computed when the
bike is taken-from and returned-to a docking station.

### **Rides planning**

The myVelib system must be equipped with a functionality that helps users to plan a ride
from a starting location to a destination location. Given the starting and destination GPS
coordinates the ride planning functionality will identify the “optimal” start and end stations
from/to where the bike should be taken/dropped according to the following criteria:

• the start, respectively the end, station, for a ride should be as close as possible to
the starting, respectively to the destination, location of the ride.

• the start station should have one bike of the desired kind (electrical or mechanical)
available for renting

• the end station should have at least one free parking slot.

Requirement: it should be possible to extend the myVelib by adding more riding policies
functionalities.

Bonus points: if you apply an open-close design for realising the ride planning functional-
ity you will gain some bonus points. Furthermore if you equip your design with any of the
optional ride-planning policies described below you will also gain some additional points.

Optional ride-planning policies: the following ride-planning policies are to be consid-
ered as optional (you can, if you want, let your myVelib design support them however they
are not mandatory).

• avoid “plus” stations: like minimal walking distance but return station cannot be
a “plus” station

• prefer “plus” stations: with this policy the return station should be a “plus”
station (given a “plus” station no further away than 10% of the distance of the
closest station to the destination location exists). If no such a “plus” station exists
then this policy behaves normally (as a minimal walking distance).

• prefer a bike parked in the street: like minimal walking distance but the bike
to rent is chosen amongst those that are not parked into a docking station.

• preservation of uniformity of bicycles distribution amongst stations: with
this policy the choice of the source and destination station is affected by the number
of available bikes (at source station) and free slots (at destination). Specifically, let
s0 be the closest station to the starting location with at least one available bike of the
wanted kind, and sd be the station closest to the destination location with at least
one free parking slot. Then if a station s′0 whose distance from the starting location
is no more than 105% the distance of s0 from the start location has a larger number
of bikes (of the wanted kind) than those available at s0 it should be selected in place
of s0. Similarly if a station s′
d (whose distance from the destination location is at
most 105% of the distance of sd from the destination location) has a larger number
of free parking slots than sd it should be selected as the destination station in place
of sd.

### **Rental and returning of a bicycle**

To rent a bicycle a user must get to one station, identify themselves (either through a velib-card
or through a credit-card) and pick up one of the available bikes. A user can only rent at
most one bicycle (i.e. if she has a bicycle and has not yet returned it, she cannot rent a
second one). To return a bicycle a user must park it to a free (and on-duty) parking bay
of some station. When the bike is returned the cost for the ride is computed and user is
automatically charged (if a charge applies).

### **Computing statistics and sorting of stations**

The myVelib system should support the following functionalities for computing relevant
statistics:

• User balance: should allow to retrieve, for any user of the myVelib system, the
number of rides, the total time spent on a bike, the total amount of charges for all
rides performed by a user, as well as the time-credit earned by a user

• Station balance: should allow to retrieve the total number of rents operation, as
well as of return operations performed on the station.

Furthermore myVelib should support different policies for sorting stations including
those based on the following criteria:

• most used station: stations are sorted w.r.t. the total number of renting + drop-
ping operations

• least occupied station: stations are sorted w.r.t. the difference between the num-
ber of dropping and renting operations. Such a difference is an indicator of the level
of occupation of a station. For example a station for which such difference is large
will have (on average) many free parking slots, whereas a station for which such dif-
ference is small (or even negative) will have (on average) few free parking slots. Such
a statistics allows for figure out policy to increase the use of less occupied stations (for
example by electing the least occupied stations to the “plus” category so to attract
users to drop bikes).

Remark (an OPEN-CLOSE solution). Your design should match as much as possible
the open-close principle. Using of design patterns should be properly documented in the
project report explicitly describing to to fulfil which requirement of the myVelib system a
design pattern has been applied.

**All complementary information can be found in the report. 
This project was graded 18.5/20**_
