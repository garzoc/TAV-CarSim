from Setup import *

#///// Scenario 4 /////
# Car parks in first available parking spot, unparks, and moves to end of street

click(park)
click(unpark)
for x in range(0, 49):
    click(forward)