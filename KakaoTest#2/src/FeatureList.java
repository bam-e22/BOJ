import java.math.BigInteger;

public class FeatureList {

    public Feature[] features;

    FeatureList() {

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (Feature f : features) {

            sb.append(f.toString());
        }

        return sb.toString();
    }

    public class Feature {

        public String id;
        public BigInteger feature;

        Feature() {

        }

        public BigInteger getFeature() {

            return feature;
        }

        public void setFeature(BigInteger feature) {

            this.feature = feature;
        }

        @Override
        public String toString() {

            return "id : " + id + "\n"
                    + "feature : " + feature + "\n";
        }
    }
}
