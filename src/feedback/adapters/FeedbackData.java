
package feedback.adapters;

import java.awt.Image;

public class FeedbackData {
    private Image screenshot;
    private String title;
    private String description;

    public FeedbackData(Image screenshot, String title, String description) {
        this.screenshot = screenshot;
        this.title = title;
        this.description = description;
    }
}
