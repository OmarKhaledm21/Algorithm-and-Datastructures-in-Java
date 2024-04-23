package IntegerMultiplication;

import java.util.Arrays;

public class GradeSchoolAlgorithm {

	public static void main(String[] args) {
		int n = 5;
		long n1 = 12345;
		long n2 = 56789;
		long[] a = new long[n];
		long[] b = new long[n];

		for (int i = 0; i < n; i++) {
			long c = n1 % 10;
			n1 /= 10;
			a[n - i - 1] = c;
		}
		for (int i = 0; i < n; i++) {
			long c = n2 % 10;
			n2 /= 10;
			b[n - i - 1] = c;
		}
		long[] res = new GradeSchoolAlgorithm().multiply(a, b, 2 * n);
		System.out.println(Arrays.toString(res));
	}

	public long[] multiply(long[] a, long[] b, int n) {
		long[] res = new long[n];
		int x = a.length;
		int y = b.length;
		long[][] middle = new long[x][n];

		int midind = 0;

		for (int i = x - 1; i >= 0; i--) {
			long rem = 0;
			long temp = 0;
			int qq = 1;
			for (int j = y - 1; j >= 0; j--) {

				long c = a[i] * b[j];
				if (rem != 0) {
					c += rem;
					rem = 0;
					temp = 0;
				}
				if (c > 9) {
					temp = c % 10;
					rem = c / 10;
					c = temp;
				}
				middle[midind][n - qq - midind] = c;
				qq++;
			}
			middle[midind][n - qq - midind] = rem;
			midind++;
		}

		long rem = 0;
		long temp = 0;
		for (int j = n - 1; j >= 0; j--) {
			long c = 0;
			for (int i = 0; i < x; i++) {
				c += middle[i][j];
			}
			if (rem != 0) {
				c += rem;
				rem = 0;
				temp = 0;
			}
			if (c > 9) {
				temp = c % 10;
				rem = c / 10;
				c = temp;
			}
			res[j] = c;
		}

		return res;
	}
}
