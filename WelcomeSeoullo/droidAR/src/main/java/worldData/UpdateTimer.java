package worldData;

import commands.Command;

public class UpdateTimer implements Updateable {

	private static final float DEF_RETRY_TIME = 0.2f;
	private float myTriggerValue;
	private Command myCommand;
	private float time = 0;
	private float myRetryTime = DEF_RETRY_TIME;

	public UpdateTimer(float timeTrigger, Command commandToExecute) {
		myTriggerValue = timeTrigger;
		myCommand = commandToExecute;
	}

	public UpdateTimer(float timeTrigger, Command commandToExecute,
			float retryTime) {
		this(timeTrigger, commandToExecute);
		this.myRetryTime = retryTime;
	}

	public void setTriggerTime(float myTriggerValue) {
		this.myTriggerValue = myTriggerValue;
	}

	@Override
	public boolean update(float timeDelta, Updateable parent) {
		time += timeDelta;
		if (time > myTriggerValue) {

			if (myCommand == null || myCommand.execute()) {
				time = 0;
				return true;
			} else {
				time -= myRetryTime;
				if (time < 0)
					time = 0;
			}
		}
		return false;
	}
}
