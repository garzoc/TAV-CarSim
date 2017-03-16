#///// Setup /////

    
mainWindow = find("1488896415916.png")
textField = mainWindow.find("1488897568769.png")
forward = find("forward.png")
backward = find("backward.png")
park = find("park.png")
unpark = find("unpark.png")

#///// Scenario 3 /////
# Car parks in first parking space, unparks, moves back, and parks in the same space
click(park)
val = capture(textField)
click(unpark)
for x in range(0, 6):
    click(backward)

click(park)

if(not textField.exists(val).getScore() > 0.98):
    print("Failed Scenario 2")
    
