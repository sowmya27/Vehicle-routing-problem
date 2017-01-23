__author__ = 'annapurnaannadatha'
#############################################################################
##                               README                                    ##
##                                                                         ##
##     This program plots given customer locations with XY coordinates     ##
##     and annotates each point for a Vehicle routing problem.             ##
##     The resultant routes of the solution set are plotted with           ##
##     directions. Routes are differentiated with colors.                  ##
##     The dataset for this project was taken form a public site:          ##
##     http://www.coin-or.org/SYMPHONY/branchandcut/VRP/data               ##
##     The input is hardcoded.  										   ##
##                                                                         ##
#############################################################################


import numpy as np
import matplotlib.pyplot as plt
import pylab

# Customer location coordinates. First element is the depot [1,-1]
data = np.array([
 [1,-1],
 [34, 31],
 [45, 55],
 [70, 80],
 [81, 70],
 [85, 61],
 [59, 55],
 [45, 60],
 [50, 64],
 [80, 64],
 [75, 90],
 [25, 40],
 [9, 66],
 [1, 44],
 [50, 54],
 [35, 45],
 [71, 84],
 [1, 9],
 [25, 54],
 [45, 59],
 [45 ,71],
 [66, 84],
 [11 ,35],
 [81, 46],
 [85, 10],
 [75, 20],
 [15, 21],
 [90, 45],
 [15 ,0],
 [31, 26],
 [10 ,95],
 [6, 6],
 [51 ,5],
 [26, 36]
])

# routes from the output of the java module
Route1 = [0,23, 27, 5, 4, 9, 6, 1,0]
Route2 = [0,11, 20, 21, 10,16,3,8,7,0]
Route3 = [0,13, 22,0]
Route4 = [0,26, 15, 2, 19, 14, 0]
Route5 = [0,17, 12, 30, 18, 33,0]
Route6 = [0,28,32,24,25,29,31,0]

# assigning routes
Routes = [Route1,Route2,Route3,Route4,Route5,Route6]
color = ['r','g','b','orange','m','y','crimson']

# plots each route with a different color and arrows with the direction of route
for i in range(0,len(Routes)):
    x=[]
    y=[]
    for j in range(0,len(Routes[i])-1):
       x.append(data[Routes[i][j]][0])
       y.append(data[Routes[i][j]][1])
       plt.scatter(data[Routes[i][j]][0], data[Routes[i][j]][1],marker ='o',c = color[i],s=25)
       #if i==1: # incase to check just one route
       plt.arrow(data[Routes[i][j]][0], data[Routes[i][j]][1], data[Routes[i][j+1]][0] - data[Routes[i][j]][0], data[Routes[i][j+1]][1] - data[Routes[i][j]][1],
              head_width=1.5, length_includes_head=True, color = color[i])

# marking depot
plt.scatter(1,-1,c='b',marker='s',s=150)

# numbering the customer locations
for i in range(0,len(data)):
    plt.annotate(i,(data[i][0], data[i][1]))


pylab.show()