import lombok.Getter;
import lombok.Setter;

public final class Box {
	@Getter
	@Setter
	private int x;
	@Getter
	@Setter
	private int y;

	public Box(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
