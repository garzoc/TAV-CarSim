#///// Setup /////
    
mainWindow = find("1488896415916-1.png")
textField = mainWindow.find("1488897568769-1.png")
forward = find("forward-1.png")
backward = find("backward-1.png")
park = find("park-1.png")
unpark = find("unpark-1.png")


#///// Scenario 5 /////
# Car tries to move out of bounds but stops at 0

click(backward)

if(not textField.exists("1489658271345.png")):
    print("Failed scenario 5")