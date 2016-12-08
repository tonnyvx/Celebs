package Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AntonioNeto on 03/12/2016.
 */
public class CelebSearchResult {
    @SerializedName("data")
public List<Celeb> celebs;
}
