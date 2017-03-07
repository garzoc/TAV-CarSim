#
# Assumes a street with at least 50 spots with at least two valid parking spaces within those spots.
#

from sikuli import *
#///// Setup /////

def setup(self):
    
    mainWindow = find("1488896415916.png")
    textField = mainWindow.find("1488897568769.png")
    forward = find("forward.png")
    backward = find("backward.png")
    park = find("park.png")
    unpark = find("unpark.png")
