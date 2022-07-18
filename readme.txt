9. What does the count increment feature say about the Server object’s instantiation method?
Is it Singleton, Per client or Per call instantiation?
Briefly explain how can you make this a Per client or Per call instantiation?
(Hint: you can have multiple server objects and the Math Server object can have associations with other objects).

Answer:
The count value is returned as expected counting all the connected clients in, which is a behavior that characterises a
singleton. A single math server instance is created in the main method of the MathServer and bound to the registry,
Which is then accessed by all the clients.

To make this per client or per call object activation on java rmi could be used. The constructor newInstance
can be used to make a new instance.
