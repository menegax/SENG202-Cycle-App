package seng202.team7.Analysis;

import seng202.team7.PointM;
import seng202.team7.Trip;
import java.util.ArrayList;

/**
 * @author Morgan English
 * For map analytics of trips
 */
@SuppressWarnings({"ALL", "SpellCheckingInspection"})
public class Analytics {

    /**
     * Finds a list of all the points the passed in trips go through
     * @param trips ArrayList of all the trips to check
     * @return ArrayList of all the points that are passed through for each trip
     */
    public static ArrayList<PointM> checkRoutes(ArrayList<Trip> trips)
    {
        ArrayList<PointM> points = new ArrayList<>();
        for (Trip t: trips)
        {
            points.addAll(checkRoute(t.getStartPoint(), t.getEndPoint()));
        }
        return points;
    }

    /**
     * An implementation of Bresenham's line algorithm to fins intermediate points of a trip
     * @param startpt Start point of the trip
     * @param endpt EEnd point of the trip
     * @return ArrayList of point objects along the specific route passed in
     */
    @SuppressWarnings("SpellCheckingInspection")
    private static ArrayList<PointM> checkRoute(PointM startpt, PointM endpt) {
        ArrayList<PointM> squares = new ArrayList<>();

        int x1 = startpt.getX();
        int y1 = startpt.getY();
        int x2 = endpt.getX();
        int y2 = endpt.getY();
        // delta of exact value and rounded value of the dependant variable
        int d = 0;

        int dy = Math.abs(y2 - y1);
        int dx = Math.abs(x2 - x1);

        int dy2 = (dy << 1); // slope scaling factors to avoid floating
        int dx2 = (dx << 1); // point

        int ix = x1 < x2 ? 1 : -1; // increment direction
        int iy = y1 < y2 ? 1 : -1;

        if (dy <= dx) {
            for (; ; ) {
                squares.add(new PointM(x1, y1));
                //plot(g, x1, y1);
                if (x1 == x2)
                    break;
                x1 += ix;
                d += dy2;
                if (d > dx) {
                    y1 += iy;
                    d -= dx2;
                }
            }
        } else {
            for (; ; ) {
                squares.add(new PointM(x1, y1));
                //plot(g, x1, y1);
                if (y1 == y2)
                    break;
                y1 += iy;
                d += dx2;
                if (d > dy) {
                    x1 += ix;
                    d -= dy2;
                }
            }
        }
        return squares;
    }
}


