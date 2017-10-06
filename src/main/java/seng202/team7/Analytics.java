package seng202.team7;

import java.util.ArrayList;

public class Analytics {


    public static ArrayList<PointM> checkRoutes(ArrayList<Trip> trips)
    {
        ArrayList<PointM> points = new ArrayList<PointM>();
        for (Trip t: trips)
        {
            points.addAll(checkRoute(t.getStartPoint(), t.getEndPoint()));
        }
        return points;
    }

    public static ArrayList<PointM> checkRoute(PointM startpt, PointM endpt) {
        ArrayList<PointM> squares = new ArrayList<PointM>();

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


