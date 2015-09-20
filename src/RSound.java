import java.io.*;
import javax.sound.sampled.*;

public class RSound extends Thread {
	private File sf;
	private boolean DONE;
	
	public RSound(String filename) {
		sf = new File(filename);
	}
	
	public void run() {
		try {
			AudioInputStream astr = AudioSystem.getAudioInputStream(sf);
			AudioFormat afmt = astr.getFormat();
			DataLine.Info inf = new DataLine.Info(SourceDataLine.class, afmt);
			SourceDataLine l = (SourceDataLine)AudioSystem.getLine(inf);
			
			l.open(afmt);
			l.start();
			
			byte[] buffer = new byte[65536];
			for (int n = 0; (n = astr.read(buffer, 0, buffer.length)) > 0 && !DONE;)
				l.write(buffer, 0, n);
			l.drain();
			l.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void done() {DONE = true;}
}