import java.util.ArrayList;

interface Solver
{
	byte INDEX = 73; // Nième valeur recherchée

	static void main(final String... args) {
		buffer.add(6d);     // 0
		buffer.add(-16d);   // 1
		buffer.add(396d);   // 2
		buffer.add(-2152d); // 3
		buffer.add(30804d); // 4
		System.out.println(recSolver(INDEX));
		System.out.println(solver(INDEX));
	}

	ArrayList<Double> buffer = new ArrayList<>(INDEX);

	// a[n] = -16a[n-1] - 18a[n-2] + 736a[n-3] + 3259a[n-4] + 2520a[n-5]
	static double recSolver(final int n) {
		if (n < buffer.size())
			return buffer.get(n);
		else {
			final double rec
					= recSolver(n - 1) * -16
					+ recSolver(n - 2) * -18
					+ recSolver(n - 3) * 736
					+ recSolver(n - 4) * 3259
					+ recSolver(n - 5) * 2520;
			buffer.add(rec);
			return rec;
		}
	}

	// a[n] = 3⋅(-9)^n + 2⋅(-8)^n + -3⋅(-5)^n + 2⋅(-1)^n + 2⋅7^n
	static double solver(final int n) {
		return 3 * pow(-9, n) + 2 * pow(-8, n) + -3 * pow(-5, n) + 2 * pow(-1, n) + 2 * pow(7, n);
	}

	static double pow(final int b, final int k) {
		return b * (k == 2 ? b : pow(b, k - 1));
	}
}
