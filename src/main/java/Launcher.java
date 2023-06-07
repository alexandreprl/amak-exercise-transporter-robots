import fr.irit.smac.amak.scheduling.Scheduler;
import fr.irit.smac.amak.ui.SchedulerToolbar;

public class Launcher {
	public static void main(String[] args) {
		var room = new Room();
		var amas = new TransporterRobotsAmas(room);
		var mainWindow = new TransporterRobotsMainWindow(amas);
		var scheduler = new Scheduler(amas, room, mainWindow);
		mainWindow.addToolbar(new SchedulerToolbar("Scheduler", scheduler));
	}
}
