//Patrick Boston (914971954)
//August 27, 2015
//CIS 2168 Section 5
//Assignment 1

package imagepixelization;

import java.awt.Color;
import simplegui.*;

public class ImagePixelization {
    
    public static void main(String[] args) {
        
        //Reads image by getting height and width
        DrwImage im = new DrwImage("temple.jpg");
        int h = im.getHeight();
        int w = im.getWidth();
        
        //Opens simpleGUI window and draws image
        SimpleGUI sg = new SimpleGUI(w,h);
        sg.drawImage(im, 0,0, w, h, null);
        pixelate(im, sg, sg.getSliderValue());
    }
    
    //Nested for loop stepping through the image hitting every g-th row and coloumn
    public static void pixelate(DrwImage im, SimpleGUI sg, int cellSize){
        for (int rows = 0; rows<im.getHeight()-cellSize; rows+=cellSize) {
            for (int cols = 0; cols < im.getWidth()-cellSize; cols+=cellSize) {
                Color color = determineColor(im, rows, cols, cellSize);
                sg.setColorAndTransparency(color, 1.0);
                sg.drawFilledBox(rows, cols, cellSize, cellSize);
            }
        }
    }
    
    //Get the average color for a blocked area of the image
    private static Color determineColor(DrwImage im, int x, int y, int cellSize) {
        
        int sumR = 0;
        int sumG = 0;
        int sumB = 0;
        int count = 0;
        
        //Nested for loop stepping through each pixel of the determined square
        for (int cx = x; cx < x + cellSize; cx++) {
            for (int cy = y; cy < y + cellSize; cy++) {
                RGB rgb = im.getPixelRGB(cx, cy);
                
                int r = rgb.r;
                int g = rgb.g;
                int b = rgb.b;
                
                sumR += r;
                sumG += g;
                sumB += b;
                count++;
            }
        }
        
        //Find average
        int numOfPixels = cellSize * cellSize;
        int avgR = sumR / numOfPixels;
        int avgG = sumG / numOfPixels;
        int avgB = sumB / numOfPixels;
        
        return (new Color(avgR, avgG, avgB));
    }
}