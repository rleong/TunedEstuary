package framework;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import control.Game;
import object.Inventory;
import window.Handler;

public class MouseInput implements MouseListener {

	Handler handler;
	Game gm;

	public MouseInput(Handler handler, Game gm) {
		this.handler = handler;
		this.gm = gm;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		//System.out.print(x + " " + y); 
		if (!gm.isPause()) {
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject temp = handler.object.get(i);

				if (temp.getId() == ObjectId.inventory) {
					Inventory inv = (Inventory) temp;
					inv.build(x, y, gm);
				}

			}
		}

		// Test
		System.out.println(x + "," + y);
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

}
