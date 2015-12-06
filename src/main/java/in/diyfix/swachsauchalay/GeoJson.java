package in.diyfix.swachsauchalay;

import java.util.List;

public class GeoJson { 
    public String type;
    public List<Feature> features;

    class Feature {
        public int id;
        public String type;
        public Geometry  geometry;
        public Properties properties;
    }

    class Geometry {
        public String type;
        public List<Float> coordinates;
    }

    class Properties {
        public String type;
        public boolean running_water;
        public boolean well_lit;
        public int rating;
        public String comments;
        public String timestamp;
    }
}


