package net.mordgren.gtca.common.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MilledOreGen {
    static public void main(String args[]) throws Exception {
        try {
            String hexCode;
            String location;

            ArrayList<String[]> milledOres;
            milledOres = new ArrayList<String[]>();
            milledOres.add(new String[]{"milled_sphalerite", "ffdc88"});
            milledOres.add(new String[]{"milled_chalcopyrite", "96c185"});
            milledOres.add(new String[]{"milled_nickel", "ccdff5"});
            milledOres.add(new String[]{"milled_platinum", "fff4ba"});
            milledOres.add(new String[]{"milled_pentlandite", "e3cf13"});
            milledOres.add(new String[]{"milled_redstone", "ff0000"});
            milledOres.add(new String[]{"milled_spessartine", "ffa81e"});
            milledOres.add(new String[]{"milled_grossular", "ffb777"});
            milledOres.add(new String[]{"milled_almandine", "a21717"});
            milledOres.add(new String[]{"milled_pyrope", "e81958"});
            milledOres.add(new String[]{"milled_monazite", "d0ee98"});

            for (String[] holder : milledOres) {
                hexCode = holder[1];
                location = holder[0];


                int width = 16, height = 16;

                BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

                Graphics2D primary = bi.createGraphics();
                Graphics2D outline = bi.createGraphics();
                Graphics2D accent = bi.createGraphics();

                int primaryRed = Integer.valueOf(hexCode.substring(0, 2), 16);
                int primaryGreen = Integer.valueOf(hexCode.substring(2, 4), 16);
                int primaryBlue = Integer.valueOf(hexCode.substring(4, 6), 16);

                int outlineRed = Integer.valueOf(hexCode.substring(0, 2), 16) - 60;
                int outlineGreen = Integer.valueOf(hexCode.substring(2, 4), 16) - 78;
                int outlineBlue = Integer.valueOf(hexCode.substring(4, 6), 16) - 54;
                System.out.println("Outline RGB" + "|" + outlineRed + "|" + outlineGreen + "|" + outlineBlue);

                int accentRed = Integer.valueOf(hexCode.substring(0, 2), 16) - 45;
                int accentGreen = Integer.valueOf(hexCode.substring(2, 4), 16) - 58;
                int accentBlue = Integer.valueOf(hexCode.substring(4, 6), 16) - 40;
                System.out.println("accent RGB" + "|" + accentRed + "|" + accentGreen + "|" + accentBlue);

                /// Failsafe
                if (outlineRed < 0) outlineRed = 0;
                if (outlineGreen < 0) outlineGreen = 0;
                if (outlineBlue < 0) outlineBlue = 0;
                if (accentRed < 0) accentRed = 0;
                if (accentGreen < 0) accentGreen = 0;
                if (accentBlue < 0) accentBlue = 0;


                Color primaryColor = new Color(primaryRed, primaryGreen, primaryBlue);
                Color outlineColor = new Color(outlineRed, outlineGreen, outlineBlue);
                Color accentColor = new Color(accentRed, accentGreen, accentBlue);


                primary.setPaint(primaryColor);
                outline.setPaint(outlineColor);
                accent.setPaint(accentColor);

                ///Outline
                outline.drawLine(8, 0, 9, 0);
                outline.drawLine(2, 1, 3, 1);
                outline.drawLine(7, 1, 7, 1);
                outline.drawLine(10, 1, 10, 2);
                outline.drawLine(13, 1, 13, 1);
                outline.drawLine(1, 2, 1, 3);
                outline.drawLine(4, 2, 4, 3);
                outline.drawLine(6, 2, 6, 3);
                outline.drawLine(12, 2, 12, 2);
                outline.drawLine(14, 2, 14, 2);
                outline.drawLine(5, 3, 5, 3);
                outline.drawLine(9, 3, 9, 3);
                outline.drawLine(11, 3, 11, 5);
                outline.drawLine(15, 3, 15, 4);
                outline.drawLine(2, 4, 3, 4);
                outline.drawLine(7, 4, 7, 4);
                outline.drawLine(10, 4, 10, 4);
                outline.drawLine(1, 5, 1, 5);
                outline.drawLine(8, 5, 9, 5);
                outline.drawLine(12, 5, 12, 5);
                outline.drawLine(14, 5, 14, 5);
                outline.drawLine(0, 6, 0, 7);
                outline.drawLine(9, 6, 10, 6);
                outline.drawLine(12, 6, 13, 6);
                outline.drawLine(1, 8, 2, 8);
                outline.drawLine(6, 9, 8, 7);
                outline.drawLine(11, 7, 13, 9);
                outline.drawLine(2, 9, 3, 10);
                outline.drawLine(1, 11, 2, 10);
                outline.drawLine(1, 12, 2, 13);
                outline.drawLine(4, 10, 5, 11);
                outline.drawLine(6, 10, 6, 10);
                outline.drawLine(3, 13, 4, 13);
                outline.drawLine(5, 12, 8, 15);
                outline.drawLine(9, 15, 10, 14);
                outline.drawLine(12, 14, 13, 14);
                outline.drawLine(11, 12, 11, 13);
                outline.drawLine(12, 11, 13, 10);
                outline.drawLine(14, 11, 14, 13);
                ///Accent
                accent.drawLine(2, 3, 3, 3);
                accent.drawLine(7, 3, 8, 3);
                accent.drawLine(9, 2, 9, 2);
                accent.drawLine(12, 4, 14, 4);
                accent.drawLine(13, 5, 13, 5);
                accent.drawLine(14, 3, 14, 3);
                accent.drawLine(3, 9, 5, 9);
                accent.drawLine(5, 10, 5, 10);
                accent.drawLine(6, 8, 7, 7);
                accent.drawLine(2, 12, 4, 12);
                accent.drawLine(4, 11, 4, 11);
                accent.drawLine(8, 14, 9, 14);
                accent.drawLine(10, 13, 10, 12);
                accent.drawLine(11, 11, 11, 11);
                accent.drawLine(12, 13, 13, 13);
                accent.drawLine(13, 12, 13, 11);
                accent.drawLine(12, 10, 12, 9);
                ///Primary
                primary.drawLine(2, 2, 3, 2);
                primary.drawLine(7, 2, 8, 2);
                primary.drawLine(8, 1, 9, 1);
                primary.drawLine(13, 2, 13, 2);
                primary.drawLine(12, 3, 13, 3);
                primary.drawLine(10, 3, 10, 3);
                primary.drawLine(4, 4, 6, 4);
                primary.drawLine(8, 4, 9, 4);
                primary.drawLine(2, 5, 7, 5);
                primary.drawLine(10, 5, 11, 6);
                primary.drawLine(1, 6, 8, 6);
                primary.drawLine(1, 7, 6, 7);
                primary.drawLine(3, 8, 5, 8);
                primary.drawLine(2, 11, 3, 11);
                primary.drawLine(9, 7, 10, 7);
                primary.drawLine(8, 8, 11, 8);
                primary.drawLine(7, 9, 11, 9);
                primary.drawLine(7, 10, 11, 10);
                primary.drawLine(6, 11, 10, 11);
                primary.drawLine(6, 12, 9, 12);
                primary.drawLine(7, 13, 9, 13);
                primary.drawLine(12, 12, 12, 12);

                ImageIO.write(bi, "PNG", new File("src\\main\\resources\\assets\\gtca\\textures\\item\\" + location + ".png"));
            }
            } catch(IOException ie){
                ie.printStackTrace();
            }


    }
}
