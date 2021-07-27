package com.textoit.entity.dto;


import java.util.Comparator;
import java.util.Objects;

public class MovieDTO implements Comparable<MovieDTO> {

    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;

    @Override
    public String toString() {
        return "MovieDTO{" +
                "producer='" + producer + '\'' +
                ", interval=" + interval +
                ", previousWin=" + previousWin +
                ", followingWin=" + followingWin +
                '}';
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getPreviousWin() {
        return previousWin;
    }

    public void setPreviousWin(Integer previousWin) {
        this.previousWin = previousWin;
    }

    public Integer getFollowingWin() {
        return followingWin;
    }

    public void setFollowingWin(Integer followingWin) {
        this.followingWin = followingWin;
    }

    public boolean equals(MovieDTO movieDto) {
        return Objects.equals(producer, movieDto.getProducer())
                && Objects.equals(interval, movieDto.getInterval())
                && Objects.equals(previousWin, movieDto.getPreviousWin())
                && Objects.equals(followingWin, movieDto.getFollowingWin());
    }

    @Override
    public int compareTo(MovieDTO movieDTO) {
        int last = this.interval.compareTo(movieDTO.interval);
        return last == 0 ? this.interval.compareTo(movieDTO.interval) : last;
    }
}
