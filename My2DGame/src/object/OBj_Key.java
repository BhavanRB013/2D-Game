package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBj_Key extends SuperObject {
	
	public OBj_Key() {
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
