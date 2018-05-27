import java.math.BigInteger;

public class SaveFeatureList {

    public FeatureList.Feature[] data;

    SaveFeatureList() {

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
