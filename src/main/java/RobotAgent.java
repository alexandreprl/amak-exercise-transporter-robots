import fr.irit.smac.amak.Agent;
import lombok.Getter;

import java.util.Optional;

public class RobotAgent extends Agent<TransporterRobotsAmas, Room> {
	@Getter
	private int x, y;
	private Box holdBox;

	protected RobotAgent(TransporterRobotsAmas amas) {
		super(amas);
		x = 1;
		y = 1;
	}

	@Override
	protected void onDecideAndAct() {
		if (!isHoldingABox()) {
			tryToPickUpABox();
		} else if (getAmas().getEnvironment().isDropZone(x, y)) {
			tryToDropABox();
		}
		var newX = x + getAmas().getEnvironment().getRandom().nextInt(3) - 1;
		var newY = y + getAmas().getEnvironment().getRandom().nextInt(3) - 1;
		if (move(newX, newY)) {
			x = newX;
			y = newY;
		}

	}

	private void tryToDropABox() {
		if (!isHoldingABox())
			return;
		getAmas().getEnvironment().dropBox(holdBox, x, y);
		holdBox = null;
	}

	private boolean move(int x, int y) {
		return amas.getEnvironment().move(this, x, y);
	}

	private void tryToPickUpABox() {
		var visionSnapshot = amas.getEnvironment().observe(x, y);
		var anyBoxSeen = visionSnapshot.getAnyBox();
		if (anyBoxSeen.isPresent()) {
			var box = anyBoxSeen.get();
			Optional<Box> boxPickResult = amas.getEnvironment().pickBox(box.getX(), box.getY());
			if (boxPickResult.isPresent())
				holdBox = boxPickResult.get();
		}
	}

	public boolean isHoldingABox() {
		return holdBox != null;
	}
}
