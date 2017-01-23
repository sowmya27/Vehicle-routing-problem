package vrp.program;

import java.io.IOException;

/**
 * Created by harikanukala on 4/21/16.
 */
public class Start {

	public static void main(String[] args) throws IOException{
        String fileName="inputTest1.in";
		CVRPAlgorithm.loadData(fileName);
		CVRPAlgorithm.clarkAndWright();
	}
}
