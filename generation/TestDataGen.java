import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TestDataGen {

	public static void main(String[] args) {

		double MinX, MaxX, MinY, MaxY;
		int pCount;
		String cat;

		MinX = Double.parseDouble(args[0]);
		MaxX = Double.parseDouble(args[1]);
		MinY = Double.parseDouble(args[2]);
		MaxY = Double.parseDouble(args[3]);
		pCount = Integer.parseInt(args[4]);
		cat = args[5].toLowerCase();

		double[] xDm = ArrayRandomlyList(MinX, MaxX, pCount);
		double[] yDm = ArrayRandomlyList(MinY, MaxY, pCount);

		String optFile = "sampleData5000.txt";
		File outputFile = new File(optFile);
		FileWriter fWriter;
		String optPoint;
		BufferedWriter out;

		try {
			fWriter = new FileWriter(outputFile);
			out = new BufferedWriter(fWriter);
			for (int i = 0; i < pCount; i++) {
				optPoint = "id" + i + " " + cat + " " + xDm[i] + " " + yDm[i] + "\n";
				out.write(optPoint);
			}
			out.flush();
			out.close();

		} catch (IOException ex) {
			System.out.println("Error writing to file '" + optFile + "'");
		}
	}

	// create random data
	public static double[] ArrayRandomlyList(double MinRn, double MaxRn, int n) {

		if (MaxRn < MinRn) {
			return null;
		}

		double[] src = new double[n];
		double[] value = new double[n];
		Random random = new Random();

		for (int i = 0; i < n; i++) {
			src[i] = MinRn + (MaxRn - MinRn) * random.nextDouble();
		}
		boolean TempFlag = false;
		double DataRange = 0d;

		for (int i = 0; i < n; i++) {
			TempFlag = true;
			while (TempFlag) {
				DataRange = MinRn + (MaxRn - MinRn) * random.nextDouble();
				for (int j = 0; j < n; j++) {
					if (DataRange == src[j]) {
						TempFlag = true;
						break;
					} else {
						TempFlag = false;
					}
				}
			}

			value[i] = DataRange;
		}
		return value;
	}

}