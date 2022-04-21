# MarbleMaze
Maze is a return to nostalgic childhood games with the use of modern technology. Marble Maze involves users controlling a rolling marble 
using the accelerometer and magnetometer in their smartphone to get the marble from the 
beginning of the maze to the end. Marble Maze will also change its appearance depending on 
its environment to create a fun and interesting color ambience, in which the light sensor will 
dictate whether the maze’s background is black (in dark environments) or white (in bright 
environments). Additionally, the color of the marble itself will change according to the 
temperature of the user’s environment using the device’s ambient temperature sensor.

# Sensing Pipeline
## Sensor Data Extraction
- Registering sensors using SENSOR_DELAY_GAME (20,000 microsecond delay), which is the recommended rate of getting sensor data for games.
- 


## Preprocessing
filter

## Feature Extraction
SensorManager.getRotationMatrix
- update() in GameplayScene: deltaPitch and deltaRoll depend on the change in the user's initial orientation of the phone. This is why it's important that the user has their phone parallel to the floor when they start playing so that the marble will roll accurately.

## Classification/Regression
SensorManager.getOrientation
- Azimuth (-z axis): values[0]
- Pitch (x axis): values[1]
- Roll (y axis): values[2]
