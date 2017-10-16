package com.baixiang.model.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "DoubanMovie")
public class DoubanMovieBean {

    /**
     * rating : {"max":10,"average":8.8,"stars":"45","min":0}
     * reviews_count : 940
     * wish_count : 54768
     * douban_site :
     * year : 2016
     * images : {"small":"https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2403347953.jpg","large":"https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2403347953.jpg","medium":"https://img3.doubanio.com/view/movie_poster_cover/spst/public/p2403347953.jpg"}
     * alt : https://movie.douban.com/subject/2338055/
     * id : 2338055
     * mobile_url : https://movie.douban.com/subject/2338055/mobile
     * title : 西部世界 第一季
     * do_count : 27997
     * share_url : https://m.douban.com/movie/subject/2338055
     * seasons_count : 2
     * schedule_url :
     * episodes_count : 10
     * countries : ["美国"]
     * genres : ["科幻","西部"]
     * collect_count : 111778
     * casts : [{"alt":"https://movie.douban.com/celebrity/1035652/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1770.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1770.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1770.jpg"},"name":"埃文·蕾切尔·伍德","id":"1035652"},{"alt":"https://movie.douban.com/celebrity/1054434/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/611.jpg","large":"https://img3.doubanio.com/img/celebrity/large/611.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/611.jpg"},"name":"安东尼·霍普金斯","id":"1054434"},{"alt":"https://movie.douban.com/celebrity/1048024/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1485163747.76.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1485163747.76.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1485163747.76.jpg"},"name":"艾德·哈里斯","id":"1048024"},{"alt":"https://movie.douban.com/celebrity/1040513/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/1378018910.89.jpg","large":"https://img1.doubanio.com/img/celebrity/large/1378018910.89.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/1378018910.89.jpg"},"name":"坦迪·牛顿","id":"1040513"}]
     * current_season : 1
     * original_title : Westworld
     * summary : 故事设定在未来世界，在一个庞大的高科技成人主题乐园中，有着拟真人的机器“接待员”能让游客享尽情欲、暴力等欲望的放纵，主要叙述被称为“西部世界”的未来主题公园。它提供给游客杀戮与性欲的满足。
     * 但是在这世界下，各种暗流涌动。部分机器人出现自我觉醒，发现了自己只是作为故事角色的存在，并且想摆脱乐园对其的控制；乐园的管理层害怕乐园的创造者控制着乐园的一切而试图夺其控制权，而乐园创造者则不会善罢甘休并且探寻其伙伴创造者曾经留下的谜团；而买下乐园的一名高管试图重新发现当年的旅程留下的谜团。
     * subtype : tv
     * directors : [{"alt":"https://movie.douban.com/celebrity/1275104/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1379217457.32.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1379217457.32.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1379217457.32.jpg"},"name":"乔纳森·诺兰","id":"1275104"},{"alt":"https://movie.douban.com/celebrity/1328760/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1423594944.21.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1423594944.21.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1423594944.21.jpg"},"name":"强尼·坎贝尔","id":"1328760"},{"alt":"https://movie.douban.com/celebrity/1332531/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1383990641.33.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1383990641.33.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1383990641.33.jpg"},"name":"理查德·J·刘易斯","id":"1332531"},{"alt":"https://movie.douban.com/celebrity/1321831/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1391654882.65.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1391654882.65.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1391654882.65.jpg"},"name":"米歇尔·麦克拉伦","id":"1321831"},{"alt":"https://movie.douban.com/celebrity/1018056/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/13285.jpg","large":"https://img3.doubanio.com/img/celebrity/large/13285.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/13285.jpg"},"name":"尼尔·马歇尔","id":"1018056"},{"alt":"https://movie.douban.com/celebrity/1013778/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/6001.jpg","large":"https://img3.doubanio.com/img/celebrity/large/6001.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/6001.jpg"},"name":"文森佐·纳塔利","id":"1013778"},{"alt":"https://movie.douban.com/celebrity/1028639/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1499700171.23.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1499700171.23.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1499700171.23.jpg"},"name":"弗雷德·托耶","id":"1028639"},{"alt":"https://movie.douban.com/celebrity/1356468/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/1490861923.69.jpg","large":"https://img1.doubanio.com/img/celebrity/large/1490861923.69.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/1490861923.69.jpg"},"name":"斯蒂芬·威廉姆斯","id":"1356468"}]
     * comments_count : 37008
     * ratings_count : 123900
     * aka : ["西方极乐园"]
     */

    private long movieId;

    private String msg;
    private int code;
    private String request;

    private RatingBean rating;
    private int reviews_count;
    private int wish_count;
    private String douban_site;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private String mobile_url;
    private String title;
    private int do_count;
    private String share_url;
    private int seasons_count;
    private String schedule_url;
    private String episodes_count;
    private int collect_count;
    private String current_season;
    private String original_title;
    private String summary;
    private String subtype;
    private int comments_count;
    private int ratings_count;
    private List<String> countries;
    private List<String> genres;
    private List<CastsBean> casts;
    private List<DirectorsBean> directors;
    private List<String> aka;

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDo_count() {
        return do_count;
    }

    public void setDo_count(int do_count) {
        this.do_count = do_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public int getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(int seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public String getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(String episodes_count) {
        this.episodes_count = episodes_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(String current_season) {
        this.current_season = current_season;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public static class RatingBean {
        /**
         * max : 10
         * average : 8.8
         * stars : 45
         * min : 0
         */

        private int max;
        private double average;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        @Override
        public String toString() {
            return "RatingBean{" +
                    "max=" + max +
                    ", average=" + average +
                    ", stars='" + stars + '\'' +
                    ", min=" + min +
                    '}';
        }
    }

    public static class ImagesBean {
        /**
         * small : https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2403347953.jpg
         * large : https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2403347953.jpg
         * medium : https://img3.doubanio.com/view/movie_poster_cover/spst/public/p2403347953.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        @Override
        public String toString() {
            return "ImagesBean{" +
                    "small='" + small + '\'' +
                    ", large='" + large + '\'' +
                    ", medium='" + medium + '\'' +
                    '}';
        }
    }

    public static class CastsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1035652/
         * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/1770.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1770.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1770.jpg"}
         * name : 埃文·蕾切尔·伍德
         * id : 1035652
         */

        private String alt;
        private AvatarsBean avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBean avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBean {
            /**
             * small : https://img3.doubanio.com/img/celebrity/small/1770.jpg
             * large : https://img3.doubanio.com/img/celebrity/large/1770.jpg
             * medium : https://img3.doubanio.com/img/celebrity/medium/1770.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }

            @Override
            public String toString() {
                return "AvatarsBean{" +
                        "small='" + small + '\'' +
                        ", large='" + large + '\'' +
                        ", medium='" + medium + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "CastsBean{" +
                    "alt='" + alt + '\'' +
                    ", avatars=" + avatars +
                    ", name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }

    public static class DirectorsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1275104/
         * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/1379217457.32.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1379217457.32.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1379217457.32.jpg"}
         * name : 乔纳森·诺兰
         * id : 1275104
         */

        private String alt;
        private AvatarsBeanX avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBeanX getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBeanX avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBeanX {
            /**
             * small : https://img3.doubanio.com/img/celebrity/small/1379217457.32.jpg
             * large : https://img3.doubanio.com/img/celebrity/large/1379217457.32.jpg
             * medium : https://img3.doubanio.com/img/celebrity/medium/1379217457.32.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }

        @Override
        public String toString() {
            return "DirectorsBean{" +
                    "alt='" + alt + '\'' +
                    ", avatars=" + avatars +
                    ", name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DoubanMovieBean{" +
                "movieId=" + movieId +
                ", msg='" + msg + '\'' +
                ", code=" + code +
                ", request='" + request + '\'' +
                ", rating=" + rating +
                ", reviews_count=" + reviews_count +
                ", wish_count=" + wish_count +
                ", douban_site='" + douban_site + '\'' +
                ", year='" + year + '\'' +
                ", images=" + images +
                ", alt='" + alt + '\'' +
                ", id='" + id + '\'' +
                ", mobile_url='" + mobile_url + '\'' +
                ", title='" + title + '\'' +
                ", do_count=" + do_count +
                ", share_url='" + share_url + '\'' +
                ", seasons_count=" + seasons_count +
                ", schedule_url='" + schedule_url + '\'' +
                ", episodes_count='" + episodes_count + '\'' +
                ", collect_count=" + collect_count +
                ", current_season='" + current_season + '\'' +
                ", original_title='" + original_title + '\'' +
                ", summary='" + summary + '\'' +
                ", subtype='" + subtype + '\'' +
                ", comments_count=" + comments_count +
                ", ratings_count=" + ratings_count +
                ", countries=" + countries +
                ", genres=" + genres +
                ", casts=" + casts +
                ", directors=" + directors +
                ", aka=" + aka +
                '}';
    }
}
