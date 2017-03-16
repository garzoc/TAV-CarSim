from Setup import *
    
#///// Setup /////

    
mainWindow = find("1488896415916.png")
textField = mainWindow.find("1488897568769.png")
forward = find("forward.png")
backward = find("backward.png")
park = find("park.png")
unpark = find("unpark.png")

#///// Scenario 2 /////
# Car parks in first available parking spot.
# Assumes at least one parking spot is available
click(park)

if(not textField.exists("1489656736180.png")):
    print("Failed Scenario 2")