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
- Max FPS is 30

## Preprocessing
- An attempt was made to incorporate and high pass filter to remove the influence of the force of gravity on the accelerometer. However, because this is a marble-based game that wants to mimic how a marble would roll in real life, gravity is necessary and the high-pass filter was removed.


## Feature Extraction
SensorManager.getRotationMatrix
- update() in GameplayScene: deltaPitch and deltaRoll depend on the change in the user's initial orientation of the phone. This is why it's important that the user has their phone parallel to the floor when they start playing so that the marble will roll accurately.

## Classification/Regression
SensorManager.getOrientation
- Azimuth (-z axis): values[0] -- Not used for marble movement because it is not necessary. The marble only moves in the x and y axes.
- Pitch (x axis): values[1] (held portrait: moves left and right)
- Roll (y axis): values[2] (held portrait: moves forward and backward)
