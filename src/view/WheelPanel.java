package view;

import model.interfaces.GameEngine;
import model.interfaces.Slot;
import view.util.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class WheelPanel extends JPanel {

    private BufferedImage image;
    private Slot currentSlot;
    private static final int PREF_WIDTH = 500;
    private static final int PREF_HEIGHT = 500;
    private static final int BALL_SIZE = 20;

    private Map<Slot, Double> slotAngles = new LinkedHashMap<>();

    public WheelPanel(GameEngine gameEngine) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setPreferredSize(new Dimension(PREF_WIDTH, PREF_HEIGHT));

        // The starting position is the top of the wheel which corresponds the angle below
        double slotAngle = 270.0;
        Collection<Slot> slots = gameEngine.getWheelSlots();

        // Set the angles for each slot
        for (Slot slot : slots) {
            slotAngles.put(slot, slotAngle);
            slotAngle += Constants.CIRCLE_DEGREES / slots.size();
        }

        try {
            image = ImageIO.read(new File("src/view/images/Basic_roulette_wheel_1024x1024.png"));
        } catch (Exception e) {
            System.err.println("Image not found");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        Dimension d = getSize();
        // Keep the roulette wheel square by setting the width and height to the same value (the lesser between the 2)
        int size = d.width > d.height ? d.height : d.width;
        double radius = size / 2.0;

        g.drawImage(image, 0, 0, size, size, this);

        if (currentSlot != null) {

            // Ball sits on the edge of the wheel, radius - BALL_SIZE is used here to center it on the slot
            double x = (radius - BALL_SIZE) * Math.cos(Math.toRadians(slotAngles.get(currentSlot)));
            double y = (radius - BALL_SIZE) * Math.sin(Math.toRadians(slotAngles.get(currentSlot)));

            //Center the balls as they initially hang from the top left of the square that holds them
            x = x - (BALL_SIZE / 2.0);
            y = y - (BALL_SIZE / 2.0);

            //Set the position of the circle (from the center = diameter/2), think of the middle of the wheel as the axis of a graph
            Ellipse2D.Double circle = new Ellipse2D.Double(radius + x, radius + y, BALL_SIZE, BALL_SIZE);

            g2d.setColor(Color.YELLOW);
            g2d.fill(circle);
            g2d.dispose();
        }
    }

    public void setCurrentSlot(Slot currentSlot) {
        this.currentSlot = currentSlot;
    }
}
