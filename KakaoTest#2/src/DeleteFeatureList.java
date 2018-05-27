import java.util.ArrayList;

public class DeleteFeatureList {

    FeatureId[] data;

    DeleteFeatureList(ArrayList<String> deleteIds) {

        int length = deleteIds.size();
        data = new FeatureId[length];

        for (int i = 0; i < length; i++) {

            data[i] = new FeatureId(deleteIds.get(i));
        }
    }

    public class FeatureId {

        String id;

        FeatureId(String id) {

            this.id = id;
        }
    }
}
