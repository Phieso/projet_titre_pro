package com.siemens.android.calculateurautonomie;

public class GetterSetter_systeme1 {

    String system;
    float I_veille;
    float I_alarme;
    float T_veille;
    float T_alarme;
    double result;

    public void setSystem(String system) {
        this.system = system;
    }

    public void setI_veille(float i_veille) {
        I_veille = i_veille;
    }

    public void setI_alarme(float i_alarme) {
        I_alarme = i_alarme;
    }

    public void setT_veille(float t_veille) {
        T_veille = t_veille;
    }

    public void setT_alarme(float t_alarme) {
        T_alarme = t_alarme;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getSystem() {
        return system;
    }

    public float getI_veille() {
        return I_veille;
    }

    public float getI_alarme() {
        return I_alarme;
    }

    public float getT_veille() {
        return T_veille;
    }

    public float getT_alarme() {
        return T_alarme;
    }

    public double getResult() {
        return result;
    }




}
