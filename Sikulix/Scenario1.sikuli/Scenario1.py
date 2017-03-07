from Setup import *

#///// Scenario 1 /////
# Car moves to end of the street, then moves back to the start.

# Move once and check that position is indeed 1 and it is unparked
# Also moves the mouse over the forward button.
click(forward)

if(not textField.exists("1488899125680.png") or not textField.exists("1488899202270.png")):
    print("Failed initializing Scenario 1");

for x in range(0, 49):
   # A little faster than click() or doubleClick()
    mouseDown(Button.LEFT)
    mouseUp(Button.LEFT)

if(not textField.exists("1488906207849.png")):
    print("Failed Scenario 1");

click(backward)
for x in range(0, 49):
   # A little faster than click() or doubleClick()
    mouseDown(Button.LEFT)
    mouseUp(Button.LEFT)

if(not textField.exists("1488906316809.png")):
    print("Failed Scenario 1");

    