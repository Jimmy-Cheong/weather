package com.app.abby.perfectweather.model.api;

import java.util.List;

/**
 * Created by Abby on 8/17/2017.
 */

public class WeatherBean {


    private List<HeWeather5Bean> HeWeather5;

    public List<HeWeather5Bean> getHeWeather5() {
        return HeWeather5;
    }

    public static class HeWeather5Bean {

        private AqiBean aqi;
        private BasicBean basic;
        private NowBean now;
        private String status;
        private SuggestionBean suggestion;
        private List<DailyForecastBean> daily_forecast;
        private List<HourlyForecastBean> hourly_forecast;

        public AqiBean getAqi() {
            return aqi;
        }


        public BasicBean getBasic() {
            return basic;
        }


        public NowBean getNow() {
            return now;
        }


        public String getStatus() {
            return status;
        }


        public SuggestionBean getSuggestion() {
            return suggestion;
        }


        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }



        public List<HourlyForecastBean> getHourly_forecast() {
            return hourly_forecast;
        }



        public static class AqiBean {


            private CityBean city;

            public CityBean getCity() {
                return city;
            }


            public static class CityBean {


                private String aqi;
                private String co;
                private String no2;
                private String o3;
                private String pm10;
                private String pm25;
                private String qlty;
                private String so2;

                public String getAqi() {
                    return aqi;
                }


                public String getCo() {
                    return co;
                }


                public String getNo2() {
                    return no2;
                }


                public String getO3() {
                    return o3;
                }


                public String getPm10() {
                    return pm10;
                }


                public String getPm25() {
                    return pm25;
                }


                public String getQlty() {
                    return qlty;
                }


                public String getSo2() {
                    return so2;
                }

            }
        }

        public static class BasicBean {


            private String city;
            private String cnty;
            private String id;
            private String lat;
            private String lon;
            private UpdateBean update;

            public String getCity() {
                return city;
            }


            public String getCnty() {
                return cnty;
            }


            public String getId() {
                return id;
            }


            public String getLat() {
                return lat;
            }


            public String getLon() {
                return lon;
            }


            public UpdateBean getUpdate() {
                return update;
            }


            public static class UpdateBean {

                private String loc;
                private String utc;

                public String getLoc() {
                    return loc;
                }


                public String getUtc() {
                    return utc;
                }

            }
        }

        public static class NowBean {


            private CondBean cond;
            private String fl;
            private String hum;
            private String pcpn;
            private String pres;
            private String tmp;
            private String vis;
            private WindBean wind;

            public CondBean getCond() {
                return cond;
            }


            public String getFl() {
                return fl;
            }


            public String getHum() {
                return hum;
            }


            public String getPcpn() {
                return pcpn;
            }


            public String getPres() {
                return pres;
            }


            public String getTmp() {
                return tmp;
            }


            public String getVis() {
                return vis;
            }


            public WindBean getWind() {
                return wind;
            }


            public static class CondBean {

                private String code;
                private String txt;

                public String getCode() {
                    return code;
                }


                public String getTxt() {
                    return txt;
                }

            }

            public static class WindBean {


                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }


                public String getDir() {
                    return dir;
                }


                public String getSc() {
                    return sc;
                }


                public String getSpd() {
                    return spd;
                }

            }
        }

        public static class SuggestionBean {


            private AirBean air;
            private ComfBean comf;
            private CwBean cw;
            private DrsgBean drsg;
            private FluBean flu;
            private SportBean sport;
            private TravBean trav;
            private UvBean uv;

            public AirBean getAir() {
                return air;
            }


            public ComfBean getComf() {
                return comf;
            }


            public CwBean getCw() {
                return cw;
            }


            public DrsgBean getDrsg() {
                return drsg;
            }


            public FluBean getFlu() {
                return flu;
            }


            public SportBean getSport() {
                return sport;
            }


            public TravBean getTrav() {
                return trav;
            }


            public UvBean getUv() {
                return uv;
            }


            public static class AirBean {


                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }


                public String getTxt() {
                    return txt;
                }

            }

            public static class ComfBean {
                /**
                 * brf : 较不舒适
                 * txt : 白天天气多云，同时会感到有些热，不很舒适。
                 */

                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }


                public String getTxt() {
                    return txt;
                }

            }

            public static class CwBean {


                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }


                public String getTxt() {
                    return txt;
                }

            }

            public static class DrsgBean {


                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }


                public String getTxt() {
                    return txt;
                }

            }

            public static class FluBean {


                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }


                public String getTxt() {
                    return txt;
                }

            }

            public static class SportBean {
                /**
                 * brf : 较适宜
                 * txt : 天气较好，较适宜进行各种运动，但因天气热，请适当减少运动时间，降低运动强度。
                 */

                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }


                public String getTxt() {
                    return txt;
                }

            }

            public static class TravBean {


                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }


                public String getTxt() {
                    return txt;
                }

            }

            public static class UvBean {


                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }


                public String getTxt() {
                    return txt;
                }

            }
        }

        public static class DailyForecastBean {


            private AstroBean astro;
            private CondBeanX cond;
            private String date;
            private String hum;
            private String pcpn;
            private String pop;
            private String pres;
            private TmpBean tmp;
            private String uv;
            private String vis;
            private WindBeanX wind;

            public AstroBean getAstro() {
                return astro;
            }


            public CondBeanX getCond() {
                return cond;
            }


            public String getDate() {
                return date;
            }


            public String getHum() {
                return hum;
            }


            public String getPcpn() {
                return pcpn;
            }


            public String getPop() {
                return pop;
            }


            public String getPres() {
                return pres;
            }


            public TmpBean getTmp() {
                return tmp;
            }


            public String getUv() {
                return uv;
            }


            public String getVis() {
                return vis;
            }


            public WindBeanX getWind() {
                return wind;
            }


            public static class AstroBean {


                private String mr;
                private String ms;
                private String sr;
                private String ss;

                public String getMr() {
                    return mr;
                }


                public String getMs() {
                    return ms;
                }


                public String getSr() {
                    return sr;
                }


                public String getSs() {
                    return ss;
                }

            }

            public static class CondBeanX {


                private String code_d;
                private String code_n;
                private String txt_d;
                private String txt_n;

                public String getCode_d() {
                    return code_d;
                }


                public String getCode_n() {
                    return code_n;
                }

                public void setCode_n(String code_n) {
                    this.code_n = code_n;
                }

                public String getTxt_d() {
                    return txt_d;
                }


                public String getTxt_n() {
                    return txt_n;
                }

            }

            public static class TmpBean {


                private String max;
                private String min;

                public String getMax() {
                    return max;
                }


                public String getMin() {
                    return min;
                }

            }

            public static class WindBeanX {


                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }


                public String getDir() {
                    return dir;
                }


                public String getSc() {
                    return sc;
                }


                public String getSpd() {
                    return spd;
                }

            }
        }

        public static class HourlyForecastBean {


            private CondBeanXX cond;
            private String date;
            private String hum;
            private String pop;
            private String pres;
            private String tmp;
            private WindBeanXX wind;

            public CondBeanXX getCond() {
                return cond;
            }


            public String getDate() {
                return date;
            }


            public String getHum() {
                return hum;
            }


            public String getPop() {
                return pop;
            }


            public String getPres() {
                return pres;
            }


            public String getTmp() {
                return tmp;
            }


            public WindBeanXX getWind() {
                return wind;
            }


            public static class CondBeanXX {


                private String code;
                private String txt;

                public String getCode() {
                    return code;
                }


                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class WindBeanXX {

                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }


                public String getDir() {
                    return dir;
                }


                public String getSc() {
                    return sc;
                }


                public String getSpd() {
                    return spd;
                }

            }
        }
    }
}
