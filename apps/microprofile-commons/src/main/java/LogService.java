import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogService {

	private File file;

	public LogService(String filePath) throws IOException {
		this.file = new File(filePath);
		file.createNewFile();
	}

	public boolean log(Object object) {

		try{
			FileWriter writer = new FileWriter(file,true);
			Jsonb builder = JsonbBuilder.create();

			writer.write(builder.toJson(object));
			writer.write("\n");

			writer.flush();
			writer.close();
		}
		catch (IOException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

}


