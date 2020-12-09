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

	public LogService(final String filePath) throws LogServiceException{
		this.file = new File(filePath);
		try{
			file.createNewFile();
			writer = new FileWriter(file, true);
		}
		catch (IOException e)
		{
			throw new LogServiceException("IOException occured when creating LogService Object",e);
		}
	}

	public boolean log(final Object object) throws LogServiceException{

		try {

			writer.write(builder.toJson(object));
			writer.write("\n");

			writer.flush();
		}
		catch (IOException e)
		{
			throw new LogServiceException("IOException occured when trying to write Log",e);
		}
		return true;
	}

	public boolean close() throws LogServiceException {
		try {
			writer.close();
			return true;
		}
		catch (IOException e)
		{
			throw new LogServiceException("IOException occured when closing LogService Object Writer",e);
		}
	}

}


