package com.glo.app.model.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class MovieInfo extends BaseObservable implements Parcelable {

    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    public final static Parcelable.Creator<DBResponse> CREATOR = new Creator<DBResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DBResponse createFromParcel(Parcel in) {
            return new DBResponse(in);
        }

        public DBResponse[] newArray(int size) {
            return (new DBResponse[size]);
        }

    };

    @BindingAdapter({"posterPath"})
    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext()).load("https://image.tmdb.org/t/p/w500" + imageUrl).into(imageView);
    }

    protected MovieInfo(Parcel in) {
        this.popularity = ((Double) in.readValue((Double.class.getClassLoader())));
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.video = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.voteCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.voteAverage = ((Double) in.readValue((Double.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.releaseDate = ((String) in.readValue((String.class.getClassLoader())));
        this.originalLanguage = ((String) in.readValue((String.class.getClassLoader())));
        this.originalTitle = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.genreIds, (java.lang.Integer.class.getClassLoader()));
        this.backdropPath = ((String) in.readValue((String.class.getClassLoader())));
        this.adult = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.overview = ((String) in.readValue((String.class.getClassLoader())));
        this.posterPath = ((String) in.readValue((String.class.getClassLoader())));
    }

    public MovieInfo() {
    }

    @Bindable
    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
        notifyPropertyChanged(BR.popularity);
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
        notifyPropertyChanged(BR.video);
    }

    @Bindable
    public Integer getVoteCount() {
        return voteCount;

    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
        notifyPropertyChanged(BR.voteCount);
    }

    @Bindable
    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
        notifyPropertyChanged(BR.voteAverage);
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        notifyPropertyChanged(BR.releaseDate);
    }

    @Bindable
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
        notifyPropertyChanged(BR.originalLanguage);
    }

    @Bindable
    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
        notifyPropertyChanged(BR.originalTitle);
    }

    @Bindable
    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
        notifyPropertyChanged(BR.genreIds);
    }

    @Bindable
    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
        notifyPropertyChanged(BR.backdropPath);
    }

    @Bindable
    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
        notifyPropertyChanged(BR.adult);
    }

    @Bindable
    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
        notifyPropertyChanged(BR.overview);
    }

    @Bindable
    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
        notifyPropertyChanged(BR.posterPath);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(popularity);
        dest.writeValue(id);
        dest.writeValue(video);
        dest.writeValue(voteCount);
        dest.writeValue(voteAverage);
        dest.writeValue(title);
        dest.writeValue(releaseDate);
        dest.writeValue(originalLanguage);
        dest.writeValue(originalTitle);
        dest.writeList(genreIds);
        dest.writeValue(backdropPath);
        dest.writeValue(adult);
        dest.writeValue(overview);
        dest.writeValue(posterPath);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieInfo)) return false;
        MovieInfo movieInfo = (MovieInfo) o;
        return getPopularity().equals(movieInfo.getPopularity()) &&
                getId() == movieInfo.getId() &&
                getVideo().equals(movieInfo.getVideo()) &&
                getVoteCount().equals(movieInfo.getVoteCount()) &&
                getVoteAverage().equals(movieInfo.getVoteAverage()) &&
                getTitle().equals(movieInfo.getTitle()) &&
                getReleaseDate().equals(movieInfo.getReleaseDate()) &&
                getOriginalLanguage().equals(movieInfo.getOriginalLanguage()) &&
                getOriginalTitle().equals(movieInfo.getOriginalTitle()) &&
                getGenreIds().equals(movieInfo.getGenreIds()) &&
                getBackdropPath().equals(movieInfo.getBackdropPath()) &&
                getAdult().equals(movieInfo.getAdult()) &&
                getOverview().equals(movieInfo.getOverview()) &&
                getPosterPath().equals(movieInfo.getPosterPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPopularity(), getId(), getVideo(), getVoteCount(), getVoteAverage(), getTitle(), getReleaseDate(), getOriginalLanguage(), getOriginalTitle(), getGenreIds(), getBackdropPath(), getAdult(), getOverview(), getPosterPath());
    }


    public static DiffUtil.ItemCallback<MovieInfo> diffCallBack = new DiffUtil.ItemCallback<MovieInfo>() {
        @Override
        public boolean areItemsTheSame(@NonNull MovieInfo oldItem, @NonNull MovieInfo newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull MovieInfo oldItem, @NonNull MovieInfo newItem) {
            return true;
        }
    };

}