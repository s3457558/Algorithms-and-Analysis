package nearestNeigh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is required to be implemented. Naive approach implementation.
 *
 * @author Jeffrey, Youhan
 */
public class NaiveNN implements NearestNeigh {
	// public static List<Point> point = new ArrayList<Point>();
	public List<Point> Allpoint = new ArrayList<Point>();

	@Override
	public void buildIndex(List<Point> points) {
		// To be implemented.
		Allpoint = points;

	}

	@Override
	public List<Point> search(Point searchTerm, int k) {
		//start time
		
/*		long startTime = System.currentTimeMillis();
		System.out.println("The start timing: " + startTime + "ms");*/
		
		List<Point> newPoint = new ArrayList<Point>();
		List<Point> catPoint = new ArrayList<Point>();
		List<Point> nearestPoint = new ArrayList<Point>();

		newPoint = Allpoint;

		for (int i = 0; i < newPoint.size(); i++) {
			if (newPoint.get(i).cat.equals(searchTerm.cat)) {
				catPoint.add(newPoint.get(i));
			}
		}
		for (int i = 0; i < catPoint.size(); i++) {
			for (int j = 0; j < catPoint.size() - 1; j++) {
				if (catPoint.get(j).distTo(searchTerm) > catPoint.get(j + 1).distTo(searchTerm)) {
					Collections.swap(catPoint, j, j + 1);
				}
			}
		}
		for (int i = 0; i < k; i++) {
			nearestPoint.add(catPoint.get(i));
		}

		//end time
/*		long endTime = System.currentTimeMillis();

		System.out.println("The end timing: " + endTime + "ms");
		System.out.println("The timing: " + (endTime - startTime) + "ms");*/
		
		return nearestPoint;
	}

	// To be implemented.

	@Override
	public boolean addPoint(Point point) {
		
/*		long startTime = System.currentTimeMillis();
		System.out.println("The start timing: " + startTime + "ms");*/
		
		
		for (int i = 0; i < Allpoint.size(); ++i) {
			if (Allpoint.get(i).equals(point)) {
				return false;
			}
		}

		Allpoint.add(point);
		
/*		long endTime = System.currentTimeMillis();

		System.out.println("The end timing: " + endTime + "ms");
		System.out.println("The timing: " + (endTime - startTime) + "ms");
		*/
		return true;

	}

	@Override
	public boolean deletePoint(Point point) {
		boolean result = false;
		
		long startTime = System.currentTimeMillis();
		
		for (int i = 0; i < Allpoint.size(); ++i) {
			if (Allpoint.get(i).equals(point)) {
				Allpoint.remove(i);
				result = true;
				break;
			}
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("The timing: " + (endTime - startTime) + "ms");
		
		return result;
	}

	@Override
	public boolean isPointIn(Point point) {
		// To be implemented.

		for (int i = 0; i < Allpoint.size(); ++i) {
			if (point.equals(Allpoint.get(i))) {
				return true;
			}
		}

		return false;
	}

}
