import java.util.HashMap;
import java.util.Map;

/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
	HashMap<Location, Waypoint> openList = new HashMap<>();
	HashMap<Location, Waypoint> closedList = new HashMap<>();
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;


    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
 	
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
    	if (numOpenWaypoints() == 0)
    		return null;
    	 float minDistance = Float.MAX_VALUE;
         Waypoint minWaypoint = null;
         for (Map.Entry<Location, Waypoint> entry : openList.entrySet()) {
             Waypoint currWaypoint = entry.getValue();
             float currDistance = currWaypoint.getTotalCost();
             if (currDistance < minDistance) {
                 minDistance = currDistance;
                 minWaypoint = currWaypoint;
             }
         }
         return minWaypoint;
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
    	  for (Map.Entry<Location, Waypoint> entry : openList.entrySet()) {
              if (entry.getKey().equals(newWP.getLocation())) {
                  if (entry.getValue().getPreviousCost() > newWP.getPreviousCost()){
                	  openList.put(newWP.getLocation(), newWP);
                      return true;
                  } else {
                      return false;
                  }
              }
          }
    	  openList.put(newWP.getLocation(), newWP);
          return true;
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
    	return openList.size();
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
    	 Waypoint temp = openList.remove(loc);
    	closedList.put(loc, temp);	
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {
    	return closedList.containsKey(loc);
    }
}

