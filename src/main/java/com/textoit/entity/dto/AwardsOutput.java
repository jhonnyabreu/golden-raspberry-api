package com.textoit.entity.dto;

import java.util.List;

public class AwardsOutput {

    private List<MovieDTO> min;
    private List<MovieDTO> max;

    public List<MovieDTO> getMin() {
        return min;
    }

    public void setMin(List<MovieDTO> min) {
        this.min = min;
    }

    public List<MovieDTO> getMax() {
        return max;
    }

    public void setMax(List<MovieDTO> max) {
        this.max = max;
    }

    //    {
//        "min": [
//        {
//            "producer":"Producer 1",
//                "interval":1,
//                "previousWin":2008,
//                "followingWin":2009
//        },
//        {
//            "producer":"Producer 2",
//                "interval":1,
//                "previousWin":2018,
//                "followingWin":2019
//        }
//        ],
//        "max": [
//        {
//            "producer":"Producer 1",
//                "interval":99,
//                "previousWin":1900,
//                "followingWin":1999
//        },
//        {
//            "producer":"Producer 2",
//                "interval":99,
//                "previousWin":2000,
//                "followingWin":2099
//        }
//]


    }
