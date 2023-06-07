import fr.irit.smac.amak.Amas;

public class TransporterRobotsAmas extends Amas<Room> {
	public TransporterRobotsAmas(Room environment) {
		super(environment, 1, ExecutionPolicy.ONE_PHASE);
		for (int i = 0; i < 5; i++) {
			new RobotAgent(this);
		}
	}
}
