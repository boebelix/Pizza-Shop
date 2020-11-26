package ateam.util;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogService {

	private final File file;
	private final FileWriter writer;
	private static final Jsonb builder = JsonbBuilder.create();

	public LogService(final String filePath) throws IOException {
		this.file = new File(filePath);
		file.createNewFile();
		writer = new FileWriter(file, true);
	}

	public boolean log(final Object object) {

		try {

			writer.write(builder.toJson(object));
			writer.write("\n");

			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean close() {
		try {
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}


