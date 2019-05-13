package ua.training.patterns.gof;

import ua.training.Testable;

public class Adapter implements Testable {
    @Override
    public void test() {
        VectorGraphics vectorGraphics = new VectorAdapterFromRaster();
        vectorGraphics.drawLine();
        vectorGraphics.drawSquare();

        VectorGraphics vectorGraphics2 = new VectorAdapterFromRaster2(new RasterGraphics());
        vectorGraphics2.drawLine();
        vectorGraphics2.drawSquare();
    }

    private interface VectorGraphics {
        void drawLine();
        void drawSquare();
    }

    private static class RasterGraphics  {

        public void drawRasterLine() {
            System.out.println("draw line.");
        }

        public void drawRasterSquare() {
            System.out.println("draw square.");
        }
    }

    private static class VectorAdapterFromRaster extends RasterGraphics implements VectorGraphics {

        @Override
        public void drawLine() {
            drawRasterLine();
        }

        @Override
        public void drawSquare() {
            drawRasterSquare();
        }
    }

    private static class VectorAdapterFromRaster2 implements VectorGraphics {
        RasterGraphics rasterGraphics;

        public VectorAdapterFromRaster2(RasterGraphics rasterGraphics) {
            this.rasterGraphics = rasterGraphics;
        }

        @Override
        public void drawLine() {
            rasterGraphics.drawRasterLine();
        }

        @Override
        public void drawSquare() {
            rasterGraphics.drawRasterSquare();
        }
    }
}
