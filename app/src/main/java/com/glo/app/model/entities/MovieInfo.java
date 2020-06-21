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
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

@Entity(tableName = "wishlist")
public class MovieInfo extends BaseObservable implements Parcelable {

    @Ignore
    static final String imageBaseUrl = "https://image.tmdb.org/t/p/w500";

    @SerializedName("popularity")
    @Expose
    @Ignore
    private Double popularity;


    @ColumnInfo(name = "id")
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("video")
    @Expose
    @Ignore
    private Boolean video;

    @SerializedName("vote_count")
    @Expose
    @ColumnInfo(name = "vote_count")
    private Integer voteCount;

    @SerializedName("vote_average")
    @Expose
    @ColumnInfo(name = "vote_average")
    private Double voteAverage;

    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "title")
    private String title;


    @SerializedName("release_date")
    @Expose
    @ColumnInfo(name = "release_date")
    private String releaseDate;

    @SerializedName("original_language")
    @Expose
    @ColumnInfo(name = "original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    @Expose
    @ColumnInfo(name = "original_title")
    private String originalTitle;

    @SerializedName("genre_ids")
    @Expose
    @Ignore
    private List<Integer> genreIds = null;

    @SerializedName("backdrop_path")
    @Expose
    @Ignore
    private String backdropPath;

    @SerializedName("adult")
    @Expose
    @ColumnInfo(name = "adult")
    private Boolean adult;

    @SerializedName("overview")
    @Expose
    @ColumnInfo(name = "overview")
    private String overview;

    @SerializedName("poster_path")
    @Expose
    @ColumnInfo(name = "poster_path")
    private String posterPath;


    protected MovieInfo(Parcel in) {
        if (in.readByte() == 0) {
            popularity = null;
        } else {
            popularity = in.readDouble();
        }
        id = in.readInt();
        byte tmpVideo = in.readByte();
        video = tmpVideo == 0 ? null : tmpVideo == 1;
        if (in.readByte() == 0) {
            voteCount = null;
        } else {
            voteCount = in.readInt();
        }
        if (in.readByte() == 0) {
            voteAverage = null;
        } else {
            voteAverage = in.readDouble();
        }
        title = in.readString();
        releaseDate = in.readString();
        originalLanguage = in.readString();
        originalTitle = in.readString();
        backdropPath = in.readString();
        byte tmpAdult = in.readByte();
        adult = tmpAdult == 0 ? null : tmpAdult == 1;
        overview = in.readString();
        posterPath = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (popularity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(popularity);
        }
        dest.writeInt(id);
        dest.writeByte((byte) (video == null ? 0 : video ? 1 : 2));
        if (voteCount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(voteCount);
        }
        if (voteAverage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(voteAverage);
        }
        dest.writeString(title);
        dest.writeString(releaseDate);
        dest.writeString(originalLanguage);
        dest.writeString(originalTitle);
        dest.writeString(backdropPath);
        dest.writeByte((byte) (adult == null ? 0 : adult ? 1 : 2));
        dest.writeString(overview);
        dest.writeString(posterPath);
    }

    public static final Creator<MovieInfo> CREATOR = new Creator<MovieInfo>() {
        @Override
        public MovieInfo createFromParcel(Parcel in) {
            return new MovieInfo(in);
        }

        @Override
        public MovieInfo[] newArray(int size) {
            return new MovieInfo[size];
        }
    };

    @BindingAdapter({"posterPath"})
    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext()).load(imageBaseUrl + imageUrl).into(imageView);
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