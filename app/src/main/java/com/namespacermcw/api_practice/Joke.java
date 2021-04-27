
package com.namespacermcw.api_practice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Generated using https://www.jsonschema2pojo.org/
 * Source: https://official-joke-api.appspot.com/random_joke
 *
 * Package: com.example.android_aaui_week_2.models
 * Class Name: GiphyResponseObject
 * Source Type: Json
 * Annotation Style: Gson
 */

@SuppressWarnings("unused")
public class Joke {
    @Expose
    @SerializedName("id")
    private Long id;

    @Expose
    @SerializedName("punchline")
    private String punchline;

    @Expose
    @SerializedName("setup")
    private String setup;

    @Expose
    @SerializedName("type")
    private String type;

    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPunchline() {
        return punchline;
    }

    public void setPunchline(String punchline) {
        this.punchline = punchline;
    }

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() { return imageUrl; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

}
