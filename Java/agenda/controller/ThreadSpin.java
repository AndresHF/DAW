package controller;

import components.ContactFinder;

public class ThreadSpin extends Thread {

	private boolean loop = true;

	private ContactFinder finder;
	public ThreadSpin(ContactFinder finder) {
		this.finder = finder;

	}

	public void run() {

		while (loop) {
			try {
				
				if (finder.getHoverImg()[0])
					finder.spinContacts(-2);
				else if (finder.getHoverImg()[1])
					finder.spinContacts(2);
				else {
					loop = false;
				}

				Thread.sleep(7);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}
	

}
