import java.math.BigInteger;
import java.util.ArrayList;

interface Solver
{
	byte INDEX = 73; // Nième valeur recherchée

	static void main(final String... args) {
        improved_buffer[0] = new BigInteger("6");     // 0
        improved_buffer[1] = new BigInteger("-16");   // 1
        improved_buffer[2] = new BigInteger("396");   // 2
        improved_buffer[3] = new BigInteger("-2152"); // 3
        improved_buffer[4] = new BigInteger("30804"); // 4
        for (int k = 0; k < 5; k++)
            buffer.add(improved_buffer[k].doubleValue());
        System.out.println("Solution pour N qui équivaut à " + INDEX);
        System.out.println("Version non-récursive: " + solver(INDEX));
		System.out.println("Version récursive:     " + recSolver(INDEX));
		System.out.println("Version améliorée:     " + imnprovedRecSolver(INDEX));
	}

	ArrayList<Double> buffer = new ArrayList<>(INDEX + 1);

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

	BigInteger[] improved_buffer = new BigInteger[INDEX + 1];

    // a[n] = -16a[n-1] - 18a[n-2] + 736a[n-3] + 3259a[n-4] + 2520a[n-5]
    static BigInteger imnprovedRecSolver(final int n) {
    	final BigInteger local_buffer = improved_buffer[n];
        if (local_buffer != null)
            return local_buffer;
        else {
            final BigInteger rec = imnprovedRecSolver(n - 1).multiply(new BigInteger("-16"))
                    .add(imnprovedRecSolver(n - 2).multiply(new BigInteger("-18")))
                    .add(imnprovedRecSolver(n - 3).multiply(new BigInteger("736")))
                    .add(imnprovedRecSolver(n - 4).multiply(new BigInteger("3259")))
                    .add(imnprovedRecSolver(n - 5).multiply(new BigInteger("2520")));
            improved_buffer[n] = rec;
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
