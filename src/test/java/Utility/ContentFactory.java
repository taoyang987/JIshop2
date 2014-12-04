package Utility;

/**
 * Created by taoyang on 14-10-14.
 */
public class ContentFactory {
    static Content content;

    public static Content getContent() {
        if (content == null) {
            content = new Content();
        }
        return content;
    }

    public static void setContent(Content newcontent) {
        content = newcontent;
    }


}

