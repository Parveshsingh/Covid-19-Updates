package com.example.covid19dashboard;

public class CountryModel {
    private String flag,country,cases,todaycases,deaths,todaydeaths
            ,recovered,activecase,criticalcases;

    public CountryModel() {
    }

    public CountryModel(String flag, String country, String cases, String todaycases, String deaths, String todaydeaths
            , String recovered, String activecase, String criticalcases) {
        this.flag = flag;
        this.country = country;
        this.cases = cases;
        this.todaycases = todaycases;
        this.deaths = deaths;
        this.todaydeaths = todaydeaths;
        this.recovered = recovered;
        this.activecase = activecase;
        this.criticalcases = criticalcases;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodaycases() {
        return todaycases;
    }

    public void setTodaycases(String todaycases) {
        this.todaycases = todaycases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodaydeaths() {
        return todaydeaths;
    }

    public void setTodaydeaths(String todaydeaths) {
        this.todaydeaths = todaydeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getActivecase() {
        return activecase;
    }

    public void setActivecase(String activecase) {
        this.activecase = activecase;
    }

    public String getCriticalcases() {
        return criticalcases;
    }

    public void setCriticalcases(String criticalcases) {
        this.criticalcases = criticalcases;
    }
}
