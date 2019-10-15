import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PaintPanel extends JPanel {
	private final ArrayList<PaintPoint> points = new ArrayList<>();
	private Point firstpointbuffer;
	private Point lastpointbuffer;
	private final ArrayList<LinePoints> lines = new ArrayList<>();
	private final ArrayList<OvalShape> ovals = new ArrayList<>();
	private final ArrayList<RectShape> rects = new ArrayList<>();
	
	//private Point ovalpointbuffer1;
	//private Point ovalpointbuffer2;
	public PaintPanel() {
		firstpointbuffer = new Point();
		lastpointbuffer = new Point();
		this.setBackground(Color.WHITE);
		
		addMouseMotionListener(
				
				new MouseMotionAdapter()
				{
					
					@Override
					public void mouseDragged(MouseEvent e)
					{
						switch(PainterFrame.toolselect) {
						case "����":
							points.add(new PaintPoint(e.getPoint(),PainterFrame.paintsize,PainterFrame.forecolor));
							
							break;
						case"���u":
							lastpointbuffer = e.getPoint();
							break;
						case"����":
							lastpointbuffer = e.getPoint();
							break;
						case"�x��":
							lastpointbuffer = e.getPoint();
							break;
						case"�ꨤ�x��":
							
							break;
						}//end switch
						repaint();
						
					}//end mouseDragged
					
					@Override
					public void mouseMoved(MouseEvent e) {
						PainterFrame.statusBarLabel.setText((String.format("��Ц�m [%d,%d]", e.getX(),e.getY())));
					}//end MouseMoved 
				}//end mousemotionadapter
				);
		addMouseListener(
				new MouseAdapter()
				{
					@Override 
					public void mousePressed(MouseEvent e) {
						firstpointbuffer = e.getPoint();
					}//end mouseRelease
					
					@Override
					public void mouseReleased(MouseEvent e) {
						switch(PainterFrame.toolselect) {
						case "����":
							break;
						case"���u":
							if(PainterFrame.fillcheck) {
							lines.add(new LinePoints(firstpointbuffer,e.getPoint(),new BasicStroke(PainterFrame.paintsize),PainterFrame.forecolor));
							
							repaint();	
							}//end if
							else{
							lines.add(new LinePoints(firstpointbuffer,e.getPoint(),new BasicStroke(
									PainterFrame.paintsize, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0),PainterFrame.forecolor));
							repaint();
							//System.out.println("1");
							
							}//end else
							break;
						case"����":
							ovals.add(new OvalShape(
									firstpointbuffer,e.getPoint(),PainterFrame.forecolor,PainterFrame.backcolor,PainterFrame.fillcheck,new BasicStroke(
											PainterFrame.paintsize)));
							repaint();
							break;
						case"�x��":
							rects.add(new RectShape(
									firstpointbuffer,e.getPoint(),PainterFrame.forecolor,PainterFrame.backcolor,PainterFrame.fillcheck,new BasicStroke(
											PainterFrame.paintsize)));
							repaint();
							
							break;
						case"�ꨤ�x��":
							
							break;
						}//end switch
						
					}//end mouseReleased
					
				}//end mouseadapter
				);//end mouseListener
	}//end constructor
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
			for(PaintPoint point:points) {
				g.setColor(point.forecolor);
				g.fillOval(point.x, point.y, point.size , point.size);}//end for
			
			for(LinePoints line:lines) {
				g2.setColor(line.forecolor);
				g2.setStroke(line.thickness);
				g2.drawLine(line.firstpoint.x, line.firstpoint.y, line.lastpoint.x, line.lastpoint.y);
			}//end for
			
			//�Ȯɪ��u
			if(PainterFrame.toolselect=="���u" && PainterFrame.fillcheck) {
				g2.setColor(PainterFrame.forecolor);
				g2.setStroke(new BasicStroke(PainterFrame.paintsize));
				g2.drawLine(firstpointbuffer.x, firstpointbuffer.y, lastpointbuffer.x, lastpointbuffer.y);
			}//end if
			else if(PainterFrame.toolselect=="���u") {
				g2.setColor(PainterFrame.forecolor);
				g2.setStroke(new BasicStroke(PainterFrame.paintsize, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
				g2.drawLine(firstpointbuffer.x, firstpointbuffer.y, lastpointbuffer.x, lastpointbuffer.y);
			}//end else if
			
			for(OvalShape oval:ovals) {
				g2.setColor(oval.forecolor);
				g2.setStroke(oval.thickness);
				
				if(oval.fillcheck) {
					g2.setColor(oval.backcolor);
					g2.fillOval(oval.startpoint.x, oval.startpoint.y, oval.width, oval.height);//����
					g2.setColor(oval.forecolor);
					g2.drawOval(oval.startpoint.x, oval.startpoint.y, oval.width, oval.height);//�b�e���
				}//end if
				else {
					g2.drawOval(oval.startpoint.x, oval.startpoint.y, oval.width, oval.height);
				}//end else
			}//end for
			
			//�Ȯɾ��
			if(PainterFrame.toolselect == "����") {
				
				g2.setStroke(new BasicStroke(PainterFrame.paintsize));
				g2.setColor(PainterFrame.forecolor);
				if(PainterFrame.fillcheck) {
					g2.setColor(PainterFrame.backcolor);
					g2.fillOval(Math.min(firstpointbuffer.x, lastpointbuffer.x), Math.min(firstpointbuffer.y, lastpointbuffer.y),
							Math.abs(firstpointbuffer.x-lastpointbuffer.x), Math.abs(firstpointbuffer.y-lastpointbuffer.y));//����
					g2.setColor(PainterFrame.forecolor);
					g2.drawOval(Math.min(firstpointbuffer.x, lastpointbuffer.x), Math.min(firstpointbuffer.y, lastpointbuffer.y),
							Math.abs(firstpointbuffer.x-lastpointbuffer.x), Math.abs(firstpointbuffer.y-lastpointbuffer.y));//�b�e���
				}//end if
				else {
					g2.drawOval(Math.min(firstpointbuffer.x, lastpointbuffer.x), Math.min(firstpointbuffer.y, lastpointbuffer.y),
							Math.abs(firstpointbuffer.x-lastpointbuffer.x), Math.abs(firstpointbuffer.y-lastpointbuffer.y));
				}//end else
				
			}//end if
			
			for(RectShape rect:rects) {
				g2.setColor(rect.forecolor);
				g2.setStroke(rect.thickness);
				
				if(rect.fillcheck) {
					g2.setColor(rect.backcolor);
					g2.fillRect(rect.startpoint.x, rect.startpoint.y, rect.width, rect.height);//����
					g2.setColor(rect.forecolor);
					g2.drawRect(rect.startpoint.x, rect.startpoint.y, rect.width, rect.height);//�b�e���
				}//end if
				else {
					g2.drawRect(rect.startpoint.x, rect.startpoint.y, rect.width, rect.height);
				}//end else
			}//end for
			
			//�Ȯɯx��
			if(PainterFrame.toolselect =="�x��" && PainterFrame.clear == false) {
				g2.setStroke(new BasicStroke(PainterFrame.paintsize));
				g2.setColor(PainterFrame.forecolor);
				if(PainterFrame.fillcheck) {
					g2.setColor(PainterFrame.backcolor);
					g2.fillRect(Math.min(firstpointbuffer.x, lastpointbuffer.x), Math.min(firstpointbuffer.y, lastpointbuffer.y),
							Math.abs(firstpointbuffer.x-lastpointbuffer.x), Math.abs(firstpointbuffer.y-lastpointbuffer.y));//����
					g2.setColor(PainterFrame.forecolor);
					g2.drawRect(Math.min(firstpointbuffer.x, lastpointbuffer.x), Math.min(firstpointbuffer.y, lastpointbuffer.y),
							Math.abs(firstpointbuffer.x-lastpointbuffer.x), Math.abs(firstpointbuffer.y-lastpointbuffer.y));//�b�e���
				}//end if
				else {
					g2.drawRect(Math.min(firstpointbuffer.x, lastpointbuffer.x), Math.min(firstpointbuffer.y, lastpointbuffer.y),
							Math.abs(firstpointbuffer.x-lastpointbuffer.x), Math.abs(firstpointbuffer.y-lastpointbuffer.y));
				}//end else
			}
			
		
	}//end PaintComponent
	//�M���e����
	public void clearPanel() {
		points.clear();
		lines.clear();
		ovals.clear();
		rects.clear();
		firstpointbuffer = new Point();
		lastpointbuffer = new Point();
		repaint();
		PainterFrame.clear = false;
	}//end clearPanel
	//�M�ݩ󵧨ꪺ
	public static class PaintPoint extends Point
	{
		public int size;
		public Color forecolor;
		public PaintPoint(Point p,int size,Color forecolor ) {
			super(p);
			this.size = size;
			this.forecolor = forecolor;
		}//end constructor
	}//end PaintPoint
	
	public static class LinePoints{
		public Color forecolor;
		public Point firstpoint;
		public Point lastpoint;
		public BasicStroke thickness;
		public LinePoints(Point one , Point two, BasicStroke thickness,Color forecolor) {
			firstpoint = one;
			lastpoint = two;
			this.thickness = thickness;
			this.forecolor = forecolor;
			
		}//end constructor
	}//end LinePoints
	
	public static class OvalShape {
		public Point startpoint;
		public Point endpoint;
		public int width, height;
		public Color forecolor;
		public Color backcolor;
		public Boolean fillcheck;
		public BasicStroke thickness;
		public OvalShape(Point p,Point p2,Color forecolor,Color backcolor,Boolean fillcheck,BasicStroke thickness) {
			this.startpoint = p;
			this.endpoint = p2;
			this.forecolor = forecolor;
			this.backcolor = backcolor;
			this.fillcheck = fillcheck;
			this.thickness = thickness;
			this.width = Math.abs(p2.x-p.x);
			this.height = Math.abs(p2.y-p.y);
			
			if(p2.x<p.x) {
				this.startpoint.x = p2.x;
			}//end if
			if(p2.y<p.y){
				this.startpoint.y = p2.y;
			}//end if
			
		}//end constructor
	}//end OvalShape
	
	public static class RectShape{
		public Point startpoint;
		public Point endpoint;
		public Color forecolor;
		public Color backcolor;
		public int width,height;
		public boolean fillcheck;
		public BasicStroke thickness;
		public RectShape(Point p,Point p2,Color forecolor,Color backcolor,Boolean fillcheck,BasicStroke thickness) {
			this.startpoint = p;
			this.endpoint = p2;
			this.forecolor = forecolor;
			this.backcolor = backcolor;
			this.thickness = thickness;
			this.fillcheck = fillcheck;
			this.width = Math.abs(p2.x-p.x);
			this.height = Math.abs(p.y-p2.y);
			if(p2.x<p.x) {
				this.startpoint.x = p2.x;
			}//end if
			if(p2.y<p.y){
				this.startpoint.y = p2.y;
			}//end if
			
			
		}//end constructor
	}//end rectShape
}//end class

