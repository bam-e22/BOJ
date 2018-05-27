public class Documents {

    public String next_url;
    public Image[] images;

    Documents() {

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("next_url : " + next_url + "\n");

        for (Image image : images) {

            sb.append(image.toString() + "\n");
        }

        return sb.toString();
    }

    public class Image {

        public String type;
        public String id;

        Image() {

        }

        @Override
        public String toString() {

            return "type : " + type + "\n"
                    + "id : " + id;
        }
    }
}


