package pc.vision;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import pc.vision.interfaces.VideoReceiver;

/**
 * Class to remove barrel distortion
 * 
 * @author Rado
 * @author James Hulme
 */
public class DistortionFix implements VideoReceiver {
	private static int width = 640;
	private static int height = 480;
	public static double barrelCorrectionX = -0.03;
	public static double barrelCorrectionY = -0.08;

	private ArrayList<VideoReceiver> videoReceivers = new ArrayList<VideoReceiver>();
	private boolean active = true;

	private final PitchConstants pitchConstants;

	public DistortionFix(final PitchConstants pitchConstants) {
		this.pitchConstants = pitchConstants;
	}

	/**
	 * Determines whether the barrel distortion correction is currently active,
	 * i.e. whether the distortion effect is applied to the video stream
	 * 
	 * @return true if the correction is active, false otherwise
	 */
	public boolean isActive() {
		return this.active;
	}

	/**
	 * Enables or disables the barrel distortion correction being applied to the
	 * video stream
	 * 
	 * @param active
	 *            true to enable, false to disable
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Remove barrel distortion on whole image
	 * 
	 * Buffers used so we only correct the pitch area not the useless background
	 * area
	 * TODO: Actually use these buffers
	 * 
	 * TODO: find an efficient way to merge pixels based on rounding up/down to
	 * avoid "duplicate" pixels - mostly aesthetic
	 * 
	 * @param image
	 *            Frame to correct
	 * @param left
	 *            Left buffer
	 * @param right
	 *            Right buffer
	 * @param top
	 *            Top buffer
	 * @param bottom
	 *            Bottom buffer
	 * @return A new image with no barrel distortion
	 */
	public static BufferedImage removeBarrelDistortion(BufferedImage image, 
			int left, int right, int top, int bottom) {

		BufferedImage newImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		int xy[];
		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				xy = invBarrelCorrect(i,j);
				
				//I'm not 100% sure we don't need this
				//if (0 <= p.x && p.x < width && 0 <= p.y && p.y < height) {
					newImage.setRGB(i, j, image.getRGB(xy[0], xy[1]));
				//}
			}
		}

		return newImage;
	}

	/**
	 * Inverse barrel correction for single points
	 * 
	 * Used to correct the distortion in an image without producing an odd
	 * grid-like visual artifact
	 * Done with an array instead of a Position for a small improvement in speed.
	 * 
	 * @param x
	 *            x of Point to "unfix"
	 * @param y
	 *            y of Point to "unfix"
	 */
	public static int[] invBarrelCorrect(int x, int y) {
		// first normalise pixel
		double px = (2 * x - width) / (double) width;
		double py = (2 * y - height) / (double) height;

		// then compute the radius of the pixel you are working with
		double rad = px * px + py * py;

		// then compute new pixel
		double px1 = px * (1 + barrelCorrectionX * rad);
		double py1 = py * (1 + barrelCorrectionY * rad);

		// then convert back
		int pixi = (int) ((px1 + 1) * width / 2);
		int pixj = (int) ((py1 + 1) * height / 2);
		
		int[] xy = new int[2];
		xy[0] = pixi;
		xy[1] = pixj;
		return xy;
	}

	/**
	 * Registers an object to receive frames from the distortion fix
	 * 
	 * @param receiver
	 *            The object being registered
	 */
	public void addReceiver(VideoReceiver receiver) {
		this.videoReceivers.add(receiver);
	}

	/**
	 * Used to send a frame to the distortion fix
	 * 
	 * @param frame
	 *            The frame being sent
	 * @param delta
	 *            The time between frames
	 * @param frameCounter
	 *            The current frame index
	 */
	@Override
	public void sendFrame(BufferedImage frame, float delta, int frameCounter) {
		BufferedImage processedFrame;

		// If the distortion overlay is active, apply it
		if (isActive()) {
			int topBuffer = this.pitchConstants.getPitchTop();
			int bottomBuffer = topBuffer + this.pitchConstants.getPitchHeight();
			int leftBuffer = this.pitchConstants.getPitchLeft();
			int rightBuffer = leftBuffer + this.pitchConstants.getPitchWidth();

			processedFrame = removeBarrelDistortion(frame, 
					topBuffer, bottomBuffer, leftBuffer, rightBuffer);
		}
		// Otherwise just forward the frame as-is
		else
			processedFrame = frame;

		for (VideoReceiver receiver : this.videoReceivers)
			receiver.sendFrame(processedFrame, delta, frameCounter);
	}
}
