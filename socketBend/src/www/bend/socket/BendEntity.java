package www.bend.socket;

/**
 * 档杆实体类
 * @author lin
 *
 */
public class BendEntity {

	/**
	 * raise 抬杆
	 */
	private String raise;
	/**
	 * fall 落杆	 */
	private String fall;
	/**
	 * raiseTime  抬杆时间
	 */
	private String raiseTime;
	/**
	 * fallTime  落杆时间 */
	private String fallTime;
	public String getRaise() {
		return raise;
	}
	public void setRaise(String raise) {
		this.raise = raise;
	}
	public String getFall() {
		return fall;
	}
	public void setFall(String fall) {
		this.fall = fall;
	}
	public String getRaiseTime() {
		return raiseTime;
	}
	public void setRaiseTime(String raiseTime) {
		this.raiseTime = raiseTime;
	}
	public String getFallTime() {
		return fallTime;
	}
	public void setFallTime(String fallTime) {
		this.fallTime = fallTime;
	}
	
	
	
}
