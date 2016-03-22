package hu.frogs.whitepages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeSet;

public class ArrayHopper {

	class Route implements Comparable<Route> {
		int[] path;
		int max;

		@Override
		public int compareTo(Route o) {
			if (this.path.length == o.path.length)
				return Integer.compare(this.max, o.max);
			return Integer.compare(this.path.length, o.path.length);
		}

		public Route(int[] path, int max) {
			this.path = path;
			this.max = max;
		}

		public Route(int[] path, int index, int max) {
			if (path == null)
				path = new int[0];
			this.path = Arrays.copyOf(path, path.length + 1);

			this.path[this.path.length - 1] = index;
			this.max = max;
		}

		public Route(Route route, int index, int max) {
			this(route.path, index, max);
		}

	}

	int index = -1;

	// routes ordered by length, interval
	private TreeSet<Route> routes = new TreeSet<Route>() {
		private static final long serialVersionUID = 6248483871670544020L;

		// only add candidate which has more effective impact
		public boolean add(Route candidate) {
			for (Iterator<Route> routesi = routes.iterator(); routesi.hasNext();) {
				Route route = routesi.next();
				// candidate has same path length, but route has wider interval
				if (candidate.path.length == route.path.length
						&& candidate.max <= route.max)
					return false;
				// candidate has same path length, but candidate has wider interval
				if (candidate.path.length == route.path.length
						&& candidate.max > route.max)
					routesi.remove();

				// candidate has same interval, but route has shorter path
				if (candidate.max == route.max
						&& candidate.path.length >= route.path.length)
					return false;
				// candidate has same interval, but route longer path
				if (candidate.max == route.max
						&& candidate.path.length < route.path.length)
					routesi.remove();
			}
			return super.add(candidate);
		};
	};

	public static void main(String[] args) {

		try {
			ArrayHopper arrayHopper = new ArrayHopper();
			arrayHopper.processStream(new FileInputStream(args[0]));

			System.out.println(arrayHopper.getMinimumPath());
		} catch (IllegalStateException | IOException | NoSuchElementException e) {
			System.out.println("failure");
		}
	}

	public void processStream(InputStream is) throws IllegalStateException,
			IOException {

		// java 7 autoclosable
		Scanner scanner = new Scanner(is).useDelimiter("[\\r\\n]+");

		// process each array elements
		while (scanner.hasNextInt()) {
			int value = scanner.nextInt();
			processElement(++index, value);
		}

		// check whether input has remaining non integer invalid input
		if (scanner.hasNext())
			throw new IllegalStateException("Invalid input line");

		// empty input
		if (index == -1)
			throw new IllegalStateException("Empty input");

		// process jump out
		processElement(++index, 0);

	}

	public String getMinimumPath() throws NoSuchElementException {
		// exception if no route exists
		int[] path = routes.first().path;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < path.length; i++) {
			sb.append(path[i]);
			sb.append(", ");
		}

		sb.append("out");
		return sb.toString();

	}

	private void printOutRoutes(int index, int value) {
		System.out.println("_ [" + index + "]=" + value + " reaches="
				+ (index + value));
		for (Route r : this.routes) {
			System.out.println(Arrays.toString(r.path) + " | " + r.max);
		}
		System.out.println("-----");
	}

	private void processElement(int index, int value) {
		List<Route> candidateRoutes = new ArrayList<>();

		// initial route
		if (index == 0)
			routes.add(new Route(new int[] { 0 }, value));

		for (Iterator<Route> routesi = routes.iterator(); routesi.hasNext();) {
			Route route = routesi.next();

			// if route does not reach this node, then drop route
			if (index > route.max) {
				routesi.remove();
				continue;
			}

			// if node has no impact, then NOP
			if (value == 0)
				return;

			// if route path + node extends route interval, then candidate new
			// route
			if (index + value > route.max)
				candidateRoutes
						.add(new Route(route.path, index, index + value));
		}

		routes.addAll(candidateRoutes);
		//printOutRoutes(index, value);
	}

}