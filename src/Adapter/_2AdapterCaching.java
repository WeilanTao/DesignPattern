package Adapter;

import java.util.*;
import java.util.function.Consumer;

/**
 * We don't have to regenerate attributes when regenerating instances
 */

class CachePoint {
    public int x, y;

    public CachePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CachePoint that = (CachePoint) o;
        return x == that.x && y == that.y;
    }


    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class CacheLine {
    public CachePoint start, end;

    public CacheLine(CachePoint start, CachePoint end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheLine cacheLine = (CacheLine) o;
        return Objects.equals(start, cacheLine.start) && Objects.equals(end, cacheLine.end);
    }

    @Override
    public int hashCode() {
        int result = start.hashCode();
        result = 31 * result + end.hashCode();
        return result;
    }

}
class CacheVectorObject extends ArrayList<CacheLine> {
}

class CacheVectorRectangle extends CacheVectorObject {
    public CacheVectorRectangle(int x, int y, int width, int height) {
        add(new CacheLine(new CachePoint(x, y), new CachePoint(x + width, y)));
        add(new CacheLine(new CachePoint(x + width, y), new CachePoint(x + width, y + height)));
        add(new CacheLine(new CachePoint(x, y), new CachePoint(x, y + height)));
        add(new CacheLine(new CachePoint(x, y + height), new CachePoint(x + width, y + height)));

    }
}
class CacheLineToPointAdapter implements  Iterable<CachePoint> {
    private static int count = 0;
    private static Map<Integer, List<CachePoint>> cache = new HashMap<>();
    private int hash;

    public CacheLineToPointAdapter(CacheLine line) {
        hash = line.hashCode();

        if (cache.get(hash) != null) {
            return;
        }

        System.out.println(
                String.format("%d: Generating points for line [%d,%d]-[%d,%d] (with caching)",
                        ++count, line.start.x, line.start.y, line.end.x, line.end.y));

        List<CachePoint> points = new ArrayList<>();
        int left = Math.min(line.start.x, line.end.x);
        int right = Math.max(line.start.x, line.end.x);
        int top = Math.min(line.start.y, line.end.y);
        int bottom = Math.max(line.start.y, line.end.y);
        int dx = right - left;
        int dy = line.end.y - line.start.y;

        if (dx == 0) {
            for (int y = top; y <= bottom; ++y) {
                points.add(new CachePoint(left, y));
            }
        } else if (dy == 0) {
            for (int x = left; x <= right; ++x) {
                points.add(new CachePoint(x, top));
            }
        }
        cache.put(hash, points);
    }

    @Override
    public Iterator<CachePoint> iterator() {
        return cache.get(hash).iterator();
    }

    @Override
    public void forEach(Consumer<? super CachePoint> action) {
        cache.get(hash).forEach(action);
    }

    @Override
    public Spliterator<CachePoint> spliterator() {
        return cache.get(hash).spliterator();
    }
}

public class _2AdapterCaching {
    private final static List<CacheVectorObject> vectorObjects
            = new ArrayList<>(Arrays.asList(
            new CacheVectorRectangle(1, 1, 10, 10),
            new CacheVectorRectangle(3, 3, 6, 6)
    ));

    public static void drawPoint(CachePoint p){
        System.out.println(".");
    }

    private static void draw(){
        for(CacheVectorObject vo: vectorObjects){
            for(CacheLine line:vo){
                CacheLineToPointAdapter adapter = new CacheLineToPointAdapter(line);
                adapter.forEach(_2AdapterCaching::drawPoint);
            }
        }
    }
    public static void main(String[] args) {
        draw();
        draw();
    }
}