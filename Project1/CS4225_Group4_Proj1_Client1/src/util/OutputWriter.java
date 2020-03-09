package util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * Writes out the output after getting the results
 * @author Victoria Jenkins, Justin Smith, Aaron Merrell
 *
 */
public class OutputWriter {

	private void writeToFile(String output) {
		try {
			PrintWriter writer = new PrintWriter("./result.txt");
			writer.write(output);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}



	private String formatTime(long time) {
		return String.format("%02d min : %02d sec : %d millis", TimeUnit.MILLISECONDS.toMinutes(time),
				TimeUnit.MILLISECONDS.toSeconds(time)
						- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)),
				(time - TimeUnit.MINUTES.toMillis(TimeUnit.MILLISECONDS.toMinutes(time)) - TimeUnit.SECONDS
						.toMillis(TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time))) < 0)
								? 0
								: (time - TimeUnit.MINUTES.toMillis(TimeUnit.MILLISECONDS.toMinutes(time))
										- TimeUnit.SECONDS.toMillis(
												TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)))));
	}



}
