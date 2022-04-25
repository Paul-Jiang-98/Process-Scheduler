import java.util.*;

public class ProcessStructure implements Comparable <ProcessStructure>{
	public Integer processID;
	public Integer priority;
	public Integer arrivalTime;
	public Integer duration;
	public Integer waitTime;
	public Integer execute;

	//Constructor;
	public ProcessStructure() {

	}

	public ProcessStructure(Integer processID, Integer priority, Integer arrivalTime, Integer duration) {
		this.processID = processID;
		this.priority = priority;
		this.arrivalTime = arrivalTime;
		this.duration = duration;
		this.waitTime = 0;
		this.execute = 0;
	}


	//implement get and set method for the each elements in constructor;
	//get method.
	public Integer getProcessID() {
		return processID;
	}

	public Integer getPriority() {
		return priority;

	}

	public Integer getArrivalTime() {
		return arrivalTime;
	}

	public Integer getDuration() {
		return duration;
	}

	//set method.
	public void setProcessID(int index) {
		processID = index;
	}

	public void setPriority(int index) {
		priority = index;
	}

	public void setArrivalTime(int index) {
		arrivalTime = index;
	}

	public void setDuration(int index) {
		duration = index;
	}

	public void setWaitTime(int index) {
		waitTime = index;
	}

	public Integer getWaitTime() {
		return waitTime;
	}

	public double getExecute() {
		return execute;
	}

	public void setExecute(Integer index) {
		this.execute = index;
	}

	public String toString() {
		String show = new String();
		show = "id = " + processID + "," + "priority = " + priority + "," + "duration = " + duration + "," + "arrival time = " + arrivalTime;
		return show;

	}


	@Override
	public int compareTo(ProcessStructure o) {
		return this.priority.compareTo(o.getPriority());
	}
}
