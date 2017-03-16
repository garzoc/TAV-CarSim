#///// Setup /////

    
mainWindow = find("1488896415916-1.png")
textField = mainWindow.find("1488897568769-1.png")
forward = find("forward-1.png")
backward = find("backward-1.png")
park = find("park-1.png")
unpark = find("unpark-1.png")


#///// Scenario 4 /////
# Car parks in all parking spots until it reaches the end of the street
while(not textField.exists(Pattern("1489658027872.png").exact())):
    click(park)
    click(unpark)

print("Finished 4")