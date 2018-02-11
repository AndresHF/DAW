package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

import command.ConfirmButton;
import components.Agenda;
import view.MainCanvas;

public class Controller extends Thread implements MouseListener, MouseMotionListener{

	
	private MainCanvas canvas;
	private boolean loop = true;
	private ThreadEffect effect;
	
	public Controller(MainCanvas canvas){
		this.canvas = canvas;
	}
	
	public void run(){
		
		while(loop){
			canvas.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(!MainCanvas.agenda.agendaSelected()){
			Iterator<Integer> agendaIterator = Agenda.selectButtons.keySet().iterator();
			while(agendaIterator.hasNext()){
				int key = (int) agendaIterator.next();
				if(Agenda.selectButtons.get(key).isActive()){
					Agenda.selectButtons.get(key).setActivated(true);
					Agenda.selectButtons.get(key).setHitBoxOutOfBounds();
					Agenda.selectButtons.get(key).execute();
					effect = new ThreadEffect();
					effect.start();
					break;
				}
			}
		}else{
			Iterator<Integer> agendaIterator = Agenda.buttons.keySet().iterator();
			while(agendaIterator.hasNext()){
				int key = (int) agendaIterator.next();
				if(Agenda.buttons.get(key).isActive()){
					Agenda.buttons.get(key).setActivated(true);
					Agenda.buttons.get(key).setHitBoxOutOfBounds();
					/*System.out.println(Agenda.selectButtons.get(0).isActivated() + " Family");
					System.out.println(Agenda.selectButtons.get(1).isActivated() + " work");
					System.out.println(Agenda.selectButtons.get(2).isActivated() + " doctor");*/
					effect = new ThreadEffect();
					effect.start();
					break;
				}
			}
		}


		for(ConfirmButton c : Agenda.confirmButtons){
			if(c.isActive()){
				c.execute();
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		//System.out.println("X: " + e.getX() + " Y: " + e.getY());
		Point p = new Point(e.getX(), e.getY());
		//for(ButtonPattern b : Agenda.buttons) b.isHover(p);
		Iterator<Integer> agendaIterator = Agenda.buttons.keySet().iterator();

		while(agendaIterator.hasNext()){
			int key = (int) agendaIterator.next();
			Agenda.buttons.get(key).isHover(p);
		}

		agendaIterator = Agenda.selectButtons.keySet().iterator();
		while(agendaIterator.hasNext()){
			int key = (int) agendaIterator.next();
			Agenda.selectButtons.get(key).isHover(p);
			//System.out.println(Agenda.selectButtons.get(key).isActive() + " " +Agenda.selectButtons.get(key).toString());
		}
		for(ConfirmButton c : Agenda.confirmButtons) c.isHover(p);

		
	}

}
