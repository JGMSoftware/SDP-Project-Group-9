package pc.world;

public class WorldState {

	int ballX, ballY;
	int blueX, blueY;
	int greenX, greenY;
	int yellowX, yellowY;
	
	public int getGreenX() {
		return greenX;
	}
	public void setGreenX(int greenX) {
		this.greenX = greenX;
	}
	public int getGreenY() {
		return greenY;
	}
	public void setGreenY(int greenY) {
		this.greenY = greenY;
	}
	double ballXVelocity, ballYVelocity;
	double blueXVelocity, blueYVelocity, blueOrientation;
	double yellowXVelocity, yellowYVelocity, yellowOrientation;
	
	public int getBallX() {
		return ballX;
	}
	public void setBallX(int ballX) {
		this.ballX = ballX;
	}
	public int getBallY() {
		return ballY;
	}
	public void setBallY(int ballY) {
		this.ballY = ballY;
	}
	public double getBallXVelocity() {
		return ballXVelocity;
	}
	public void setBallXVelocity(double ballXVelocity) {
		this.ballXVelocity = ballXVelocity;
	}
	public double getBallYVelocity() {
		return ballYVelocity;
	}
	public void setBallYVelocity(double ballYVelocity) {
		this.ballYVelocity = ballYVelocity;
	}
	public int getBlueX() {
		return blueX;
	}
	public void setBlueX(int blueX) {
		this.blueX = blueX;
	}
	public int getBlueY() {
		return blueY;
	}
	public void setBlueY(int blueY) {
		this.blueY = blueY;
	}
	public double getBlueXVelocity() {
		return blueXVelocity;
	}
	public void setBlueXVelocity(double blueXVelocity) {
		this.blueXVelocity = blueXVelocity;
	}
	public double getBlueYVelocity() {
		return blueYVelocity;
	}
	public void setBlueYVelocity(double blueYVelocity) {
		this.blueYVelocity = blueYVelocity;
	}
	public double getBlueOrientation() {
		return blueOrientation;
	}
	public void setBlueOrientation(double blueOrientation) {
		this.blueOrientation = blueOrientation;
	}
	public int getYellowX() {
		return yellowX;
	}
	public void setYellowX(int yellowX) {
		this.yellowX = yellowX;
	}
	public int getYellowY() {
		return yellowY;
	}
	public void setYellowY(int yellowY) {
		this.yellowY = yellowY;
	}
	public double getYellowXVelocity() {
		return yellowXVelocity;
	}
	public void setYellowXVelocity(double yellowXVelocity) {
		this.yellowXVelocity = yellowXVelocity;
	}
	public double getYellowYVelocity() {
		return yellowYVelocity;
	}
	public void setYellowYVelocity(double yellowYVelocity) {
		this.yellowYVelocity = yellowYVelocity;
	}
	public double getYellowOrientation() {
		return yellowOrientation;
	}
	public void setYellowOrientation(double yellowOrientation) {
		this.yellowOrientation = yellowOrientation;
	}

}