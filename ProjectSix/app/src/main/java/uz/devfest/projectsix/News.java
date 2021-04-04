package uz.devfest.projectsix;

import android.net.Uri;

public class News {
    private String sectionName;
    private String webTitle;
    private String webUrl;
    private String webPublicationDate;
    private String id;
    private String firstName;


    public String getAuthor() {
        return firstName;
    }

    public void setAuthor(String firstName) {
        this.firstName = firstName;
    }

    public void setmId(String id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    public Uri getNewsPageUri() {
        return Uri.parse("https://www.theguardian.com/" + id)
                .buildUpon()
                .build();
    }
}
